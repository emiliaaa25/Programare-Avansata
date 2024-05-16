package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileReader;
import java.sql.SQLException;
import java.util.Random;

public class ImportData {
    static int id = 0;

    public static void importData(BookDAO books) throws SQLException {
        try {
            String[] genres = {"Science Fiction", "Romance", "Comedy", "Adventure", "Mistery", "Poem", "Drama"};
            CSVParser parser = new CSVParser(new FileReader("books.csv"), CSVFormat.DEFAULT);
            for (var record : parser) {
                id++;
                if (id == 1) continue;
                if (id == 100) return;

                String dir = record.get(1);

                if (dir.contains("'")) continue;
                int[] chosenGenres = new int[7];

                Random random = new Random();
                int numberOfGenres = random.nextInt(2) + 1;
                String[] genresThis = new String[numberOfGenres];
                int genreIndex;
                for (int i = 0; i < numberOfGenres; i++) {
                    genreIndex = random.nextInt(7);
                    while (chosenGenres[genreIndex] == 1) genreIndex = random.nextInt(7);

                    chosenGenres[genreIndex] = 1;

                    genresThis[i] = genres[genreIndex];
                }

                books.create(2003, dir, record.get(2).split("/"), genresThis, record.get(6));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}