package org.example.bonus;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.jgrapht.Graph;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.alg.matching.blossom.v5.KolmogorovWeightedPerfectMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

class DrawingPanel extends Canvas implements Serializable {
    int xDimension;
    int yDimension;
    int lastX;
    int lastY;
    int[][] pieces;

    int[][][][] lines;

    int turn;

    boolean playWithAI;

    public DrawingPanel( int x, int y) {
        lastX = lastY = -1;
        turn = 1;
        xDimension = x;
        yDimension = y;
        //System.out.println("constructor");
        setPieces();
        setWidth(400);
        setHeight(400);
        setLines();
        drawBoard(xDimension, yDimension);
        setOnMouseClicked(this::handleMouseClick);
        playWithAI = false;
    }

    public void drawBoard( int xDimension, int yDimension) {
        this.yDimension = yDimension;
        this.xDimension = xDimension;

        xDimension += 1;
        yDimension += 1;
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        double cellWidth = getWidth() / xDimension;
        double cellHeight = getHeight() / yDimension;

        gc.setStroke(Color.MAGENTA);
        for (int i = 1; i <= xDimension - 1; i++) {
            double xPos = i * cellWidth;
            gc.strokeLine(xPos, cellHeight, xPos, getHeight()-cellHeight);
        }
        for (int i = 1; i <= yDimension - 1; i++) {
            double yPos = i * cellHeight;
            gc.strokeLine(cellWidth, yPos, getWidth()-cellWidth, yPos);
        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);
        for(int i = 1; i < xDimension; ++ i)
            for(int j = 1; j < yDimension; ++ j){
                for(int ii = i; ii < xDimension; ++ ii)
                    for(int jj = j; jj < yDimension; ++ jj)
                        if(lines[i][j][ii][jj] == 1){
                            gc.strokeLine(i * cellWidth, j * cellHeight, ii * cellWidth, jj * cellHeight);
                        }
            }

        gc.setFill(Color.TRANSPARENT);
        gc.setLineWidth(1.0);
        gc.setStroke(Color.GRAY);
        for (int i = 1; i <= xDimension - 1; i++) {
            for (int j = 1; j <= yDimension - 1; j++) {
                double xCircle = i * cellWidth;
                double yCircle = j * cellHeight;
                gc.strokeOval(xCircle - cellWidth / 4, yCircle - cellHeight / 4, cellWidth / 2, cellHeight / 2);
                if(pieces[i][j] == 1){
                    gc.setStroke(Color.RED);
                    gc.setFill(Color.RED);
                    gc.fillOval(xCircle - cellWidth / 4, yCircle - cellHeight / 4, cellWidth / 2, cellHeight / 2);

                    gc.setStroke(Color.GRAY);
                }
                else if(pieces[i][j] == 2){
                    gc.setStroke(Color.BLUE);
                    gc.setFill(Color.BLUE);
                    gc.fillOval(xCircle - cellWidth / 4, yCircle - cellHeight / 4, cellWidth / 2, cellHeight / 2);

                    gc.setStroke(Color.GRAY);
                }
            }
        }

        //stage.show();
    }

    public void drawEndBoard(){
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight()/15);

        String text;
        if(turn == 2) {
            text = "THE WINNER IS RED";
            gc.setStroke(Color.RED);
        }
        else {
            text = "THE WINNER IS BLUE";
            gc.setStroke(Color.BLUE);
        }
        gc.setLineWidth(2.0);
        gc.strokeText(text, getWidth() / 2.7, getHeight() / 15);

    }

    private void handleMouseClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        double cellWidth = getWidth() / xDimension;
        double cellHeight = getHeight() / yDimension;

        int column = (int) (x / cellWidth) + 1;
        int row = (int) (y / cellHeight) + 1;


        if(lastX != -1 && (lines[lastX][lastY][column][row] == 0 || pieces[column][row] != 0)){
            System.out.println("Wrong Position!");
            return;
        }
        lastX = column;
        lastY = row;

        pieces[column][row] = turn;
        turn = 3 - turn;

        int doneGame = 0;
        for(int i = -1; i < 2 && doneGame == 0; ++ i)
            for(int j = -1; j < 2; ++ j)
                if ((column + i) > 0 && (row + j) > 0 && lines[column][row][column + i][row + j] == 1 && pieces[column + i][row + j] == 0) {
                    doneGame = 1;
                    break;
                }
        if(doneGame == 0)
        {
            drawBoard( xDimension, yDimension);
            drawEndBoard();
            return;
        }


        if(playWithAI){
            // asezam urnmatoarea piesa
            //System.out.println("Se joaca cu ai!");

            int newPieceX = 0;
            int newPieceY = 0;

            for(int j = -1; j < 2; ++ j)
            {
                if(lastY + j > 0 && lastY +  j <= yDimension){
                    if(pieces[lastX][lastY + j] == 0 && lines[lastX][lastY] [lastX][lastY + j] == 1){
                        if(piesaFinala(lastX, lastY + j)){
                            pieces[lastX][lastY + j] = 1;
                            doneGame = 0;
                            newPieceX = lastX;
                            newPieceY = lastY + j;
                            break;
                        }
                        else {
                            newPieceX = lastX;
                            newPieceY = lastY +j;
                        }
                    }

                }

                if(lastX + j > 0 && lastX +  j <= xDimension) {
                    if (pieces[lastX + j][lastY] == 0 && lines[lastX + j][lastY][lastX][lastY] == 1) {
                        if(piesaFinala(lastX + j, lastY)){
                            pieces[lastX + j][lastY] = 1;
                            doneGame = 0;
                            newPieceX = lastX + j;
                            newPieceY = lastY;
                            break;
                        }
                        else {
                            newPieceX = lastX + j;
                            newPieceY = lastY;
                        }
                    }
                }

            }
            lastY = newPieceY;
            lastX = newPieceX;
            pieces[lastX][lastY] = 1;
            turn = 2;
            if(doneGame == 0)
            {
                System.out.println("E gata jocul");
                drawBoard( xDimension, yDimension);
                drawEndBoard();
                return;
            }

        }

        drawBoard(xDimension, yDimension);
    }

    public boolean piesaFinala(int i, int j){
        if(j + 1 <= yDimension)
            if(lines[i][j][i][j + 1] != 0 && pieces[i][j + 1] == 0)
                return false;
        if(i + 1 <= xDimension)
            if(lines[i][j][i + 1][j] != 0 && pieces[i + 1][j] == 0)
                return false;
        if(i - 1 > 0)
            if(lines[i][j][i - 1][j] != 0 && pieces[i -1][j] == 0)
                return false;
        if(j - 1 > 0)
            return lines[i][j][i][j - 1] == 0 || pieces[i][j - 1] != 0;
        return true;
    }

    public void setLines(){
        lines = new int[xDimension+3][yDimension+3][xDimension+3][yDimension+3];
        for(int i = 1; i <= xDimension; ++i)
            for(int j = 1;  j <= yDimension; ++ j){

                if(j + 1 <= yDimension)   lines[i][j][i][j + 1] = lines[i][j + 1][i][j] = ((int) (Math.random() * 10) ) %2;
                if(i + 1 <= xDimension)   lines[i][j][i + 1][j] = lines[i + 1][j][i][j] = ((int) (Math.random() * 10) ) %2;
                if(i - 1 > 0)   lines[i][j][i - 1][j] = lines[i - 1][j][i][j] = ((int) (Math.random() * 10) ) %2;
                if(j - 1 > 0)   lines[i][j][i][j -1] = lines[i][j - 1][i][j] = ((int) (Math.random() * 10) ) %2;

            }
    }

    public int getXDimension() {
        return xDimension;
    }

    public void setXDimension(int xDimension) {
        this.xDimension = xDimension;
    }

    public int getYDimension() {
        return yDimension;
    }

    public void setYDimension(int yDimension) {
        this.yDimension = yDimension;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setPieces() {
        pieces = new int[20][20];
        for(int i = 0; i < xDimension; ++ i)
            for(int j = 0; j < yDimension; ++ j)
                pieces[i][j] = 0;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

    public void saveDrawingPanelState(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("A mers DrawingPanel save.");
        } catch (IOException e) {
            System.out.println("Eroare la DrawingPanel save: " + e.getMessage());
        }
    }

    public void setPieces(int[][] pieces) {
        this.pieces = pieces;
    }

    public void setLines(int[][][][] lines) {
        this.lines = lines;
    }

    public void saveCanvasImage(String filename) {
        WritableImage writableImage = new WritableImage((int) getWidth(), (int) getHeight());
        snapshot(null, writableImage);

        File file = new File(filename);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
            System.out.println("Exported current game image to " + filename);
        } catch (IOException e) {
            System.out.println("Error exporting game image: " + e.getMessage());
        }
    }


    public void setPlayWithAI(boolean playWithAI) {
        this.playWithAI = playWithAI;
    }

    public void generatePerfectMatch() {
        boolean isPerfectMatch = false;


        do
        {
            Set<Integer> matchedVertices = new HashSet<>();
            Set<DefaultEdge> matching = new HashSet<>();
            Set<Integer> notPlayableNodes = new HashSet<>();
            Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
            setLines();
            drawBoard(xDimension, yDimension);
            Set<Integer> partition1 = new HashSet<>();
            Set<Integer> partition2 = new HashSet<>();

            for (int i = 1; i <= xDimension * yDimension; i++) {
                graph.addVertex(i);
            }

            for (int i = 1; i <= xDimension; i++)
                for (int j = 1; j <= yDimension; j += 2) {
                    partition1.add((((i - 1) * yDimension) + j));
                    if (j + 1 <= yDimension) {
                        partition2.add(((i - 1) * yDimension) + j + 1);
                        //System.out.println("A fost adaugat nodul: " + (((i - 1) * yDimension) + j +1) + " in partitia 2");
                    }
                }

            // Example: Adding edges to form a bipartite graph
            for (int i = 1; i <= xDimension; ++i)
                for (int j = 1; j <= yDimension; ++j)
                    for (int ii = 1; ii <= xDimension; ++ii)
                        for (int jj = 1; jj <= yDimension; ++jj)
                            if (lines[i][j][ii][jj] == 1 && (((i - 1) * yDimension) + j) < (((ii - 1) * yDimension) + jj)) {
                                graph.addEdge((((i - 1) * yDimension) + j), (((ii - 1) * yDimension) + jj));
                                //System.out.println("i j ii jj: " + i + " " + j + " " + ii + " " + jj + "Nodurile: " + (((i - 1) * yDimension) + j) + " " + (((ii - 1) * yDimension) + jj));
                            }

            HopcroftKarpMaximumCardinalityBipartiteMatching<Integer, DefaultEdge> matchingAlgorithm = new HopcroftKarpMaximumCardinalityBipartiteMatching<>(graph, partition1, partition2);

            // KolmogorovWeightedMatching<Integer, DefaultEdge> matchingAlgorithm = new KolmogorovWeightedMatching<>(graph, ObjectiveSense.MAXIMIZE);

            boolean esteCuplaj = false;
            KolmogorovWeightedPerfectMatching<Integer, DefaultEdge> h = new KolmogorovWeightedPerfectMatching<>(graph);
            try{
                System.out.println("Cuplaj perfect: " + h.getMatching());
                esteCuplaj = true;
            }
            catch (Exception e){
                System.out.println("Nu are perfect match");
            }
            finally {

                if(esteCuplaj){
                    isPerfectMatch = true;
                }
                else {
                    for(int i = 1; i <= xDimension * yDimension ; ++ i)
                    // daca nodul e legat de o muchie
                    {
                        for (Integer vertex : partition1) {
                            for (DefaultEdge edge : graph.edgesOf(vertex)) {
                                Integer neighbor = graph.getEdgeTarget(edge);
                                if (partition2.contains(neighbor) && !matchedVertices.contains(neighbor)) {
                                    matching.add(edge);
                                    matchedVertices.add(vertex);
                                    matchedVertices.add(neighbor);
                                    break;
                                }
                            }
                            System.out.println("Matching: " + matching);
                        }
                    }
                    for(int ii = 1; ii <= xDimension; ++ii)
                        for(int j = 1;  j <= yDimension; ++ j){
                            boolean areVecini = false;

                            if(j + 1 <= yDimension)
                                if(lines[ii][j][ii][j + 1] != 0)
                                    areVecini = true;
                            if(ii + 1 <= xDimension)
                                if(lines[ii][j][ii + 1][j] != 0)
                                    areVecini = true;
                            if(ii - 1 > 0)
                                if(lines[ii][j][ii - 1][j] != 0)
                                    areVecini = true;
                            if(j - 1 > 0)
                                if(lines[ii][j][ii][j -1] != 0)
                                    areVecini = true;

                            if(!areVecini)
                            {
                                notPlayableNodes.add( ( (ii - 1) * yDimension +j) );
                            }
                        }

                    for(int i = 1; i <= xDimension * yDimension ; ++ i)
                        // daca nodul e legat de o muchie
                        if(!notPlayableNodes.contains(i)){
                            boolean ok = false;
                            for(var edge: matching) {// daca nu face parte din match{

                                Integer sourceVertex = graph.getEdgeSource(edge);
                                Integer targetVertex = graph.getEdgeTarget(edge);

                                if(i == sourceVertex || i == targetVertex)
                                {
                                    ok = true;
                                }
                            }
                            if(!ok){
                                // ai incepe aici
                                pieces[(i/yDimension + 1)][ i - (i/yDimension ) * yDimension] = 1;
                                lastY =  i - (i/yDimension ) * yDimension;
                                lastX = (i/yDimension + 1);
                                break;
                            }
                        }
                    setTurn(2);
                    drawBoard(xDimension, yDimension);
                }
            }
        } while(isPerfectMatch);
    }

    public boolean isPerfectMatching(Graph<Integer, DefaultEdge> graph, Set<DefaultEdge> matching,int size) {

        // Check if the number of edges in the matching equals half the number of vertices
        if (matching.size() != size / 2) {
            return false;
        }

        Set<Integer> coveredVertices = new HashSet<>();
        for (DefaultEdge edge : matching) {
            // Get source and target vertices of the edge
            Integer sourceVertex = graph.getEdgeSource(edge);
            Integer targetVertex = graph.getEdgeTarget(edge);

            // If a vertex is already covered, it's not a perfect matching
            if (coveredVertices.contains(sourceVertex) || coveredVertices.contains(targetVertex)) {
                return false;
            }

            // Add vertices to the set of covered vertices
            coveredVertices.add(sourceVertex);
            coveredVertices.add(targetVertex);
        }

        // Check if all vertices are covered
        return coveredVertices.size() == size;
    }
}
