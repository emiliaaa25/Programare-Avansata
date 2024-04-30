package org.example;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.sql.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ReadingList {

    private String[] readingList;

    public void createGraph() throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        int numberGenres = 0;
        int numberBooks = 0;
        int numberAuthors = 0;
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select count(*) from genres")) {
            rs.next();
            numberGenres = rs.getInt(1);

            ResultSet rs2 = stmt.executeQuery("select count(*) from books");
            rs2.next();
            numberBooks = rs2.getInt(1);
        } catch (Exception e) {
            System.err.println(e);
        }

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select count(*) from authors")) {
            rs.next();
            numberAuthors = rs.getInt(1);
        } catch (Exception e) {
            System.err.println(e);
        }

        Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        Graph<Integer, DefaultEdge> graphOnlyBooks = new SimpleGraph<>(DefaultEdge.class);

        for (int i = 0; i < numberGenres; ++i)
            graph.addVertex(i);

        for (int i = numberGenres; i < numberBooks + numberGenres; ++i) {
            graph.addVertex(i);
            graphOnlyBooks.addVertex((i - numberGenres));
        }

        for (int i = numberGenres + numberBooks; i < numberGenres + numberBooks + numberAuthors; ++i)
            graph.addVertex(i);

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from book_genre");
            while (rs.next()) {
                int book_id = rs.getInt(2);
                int genre_id = rs.getInt(3);
                graph.addEdge((book_id + numberGenres - 1), (genre_id - 1));

            }
        } catch (Exception e) {
            System.err.println(e);
        }

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from book_author");
            while (rs.next()) {
                int book_id = rs.getInt(2);
                int author_id = rs.getInt(3);
                graph.addEdge((book_id + numberGenres - 1), (author_id + numberGenres + numberBooks - 1));
            }
        } catch (Exception e) {
            System.err.println(e);

        }

        for (int i = 0; i < numberBooks; ++i) {
            List<Integer> neighbors = Graphs.neighborListOf(graph, (i + numberGenres));
            for (Integer neighbor : neighbors) {
                List<Integer> neighbors2 = Graphs.neighborListOf(graph, neighbor);
                for (int j = 0; j < neighbors2.size(); ++j) {
                    if (neighbors2.get(j) - numberGenres == i) continue;
                    graphOnlyBooks.addEdge(i, (neighbors2.get(j) - numberGenres));
                }
            }
        }

        GreedyColoring<Integer, DefaultEdge> greedyColoring = new GreedyColoring<>(graphOnlyBooks);
        VertexColoringAlgorithm.Coloring<Integer> coloring = greedyColoring.getColoring();

        var colorClasses = coloring.getColorClasses();

        Integer[] sizesColorClasses = new Integer[colorClasses.size()];
        for (int i = 0; i < colorClasses.size(); ++i) {
            sizesColorClasses[i] = colorClasses.get(i).size();
        }

        colorClasses = recalibrate(colorClasses, sizesColorClasses, graphOnlyBooks);

        BookDAO books = new BookDAO();
        readingList = new String[colorClasses.size()];
        for (int i = 0; i < colorClasses.size(); ++i) {
            readingList[i] = "";
            for (var j : colorClasses.get(i)) {
                if (books.findById(j) != null) readingList[i] += books.findById(j) + "\n";
            }
        }
    }

    public static List<Set<Integer>> recalibrate(List<Set<Integer>> colorare, Integer[] sizesColorClasses, Graph<Integer, DefaultEdge> graphOnlyBooks) {
        int max = 0, maxIndex = 0;
        int min = sizesColorClasses[0], minIndex = 0;
        for (int i = 0; i < sizesColorClasses.length; ++i) {
            if (sizesColorClasses[i] > max) {
                max = sizesColorClasses[i];
                maxIndex = i;
            }
            if (sizesColorClasses[i] < min) {
                min = sizesColorClasses[i];
                minIndex = i;
            }
        }
        if (max - min <= 1) return colorare;

        for (var i : colorare.get(maxIndex)) {
            if (!Graphs.neighborListOf(graphOnlyBooks, minIndex).contains(i)) {
                colorare.get(minIndex).add(i);
                colorare.get(maxIndex).remove(i);
                sizesColorClasses[minIndex]++;
                sizesColorClasses[maxIndex]--;
                return recalibrate(colorare, sizesColorClasses, graphOnlyBooks);
            }
        }

        Set<Integer> newColor = new TreeSet<>();
        Set<Integer> newColor2 = new TreeSet<>();
        int ct = 0;
        for (var i : colorare.get(maxIndex)) {
            if (ct < colorare.get(maxIndex).size() / 2) {
                newColor.add(i);
            } else {
                newColor2.add(i);
            }
            ct++;
        }
        colorare.remove(maxIndex);
        colorare.add(newColor);
        colorare.add(newColor2);

        if (newColor.isEmpty() || newColor2.isEmpty()) {
            return colorare;
        }

        return recalibrate(colorare, sizesColorClasses, graphOnlyBooks);
    }

    public String getReadingListById(Integer index) {
        return readingList[index];
    }

    public String[] getReadingList() {
        return readingList;
    }
}
