package org.example.homework;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.embed.swing.SwingFXUtils;


import javax.imageio.ImageIO;
import java.io.*;

class DrawingPanel extends Canvas implements Serializable {
    int xDimension = 10;
    int yDimension = 10;
    int lastX;
    int lastY;
    int[][] pieces;

    int[][][][] lines;

    int turn;

    public DrawingPanel(int x, int y) {
        lastX = lastY = -1;
        turn = 1;
        xDimension = x;
        yDimension = y;
        setPieces();
        setWidth(400);
        setHeight(400);
        setLines();
        drawBoard(xDimension, yDimension);
        setOnMouseClicked(this::handleMouseClick);
    }

    public void drawBoard(int xDimension, int yDimension) {
        this.yDimension = yDimension;
        this.xDimension = xDimension;

        xDimension += 1;
        yDimension += 1;
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        double cellWidth = getWidth() / xDimension;
        double cellHeight = getHeight() / yDimension;

        gc.setStroke(Color.MAGENTA);
        gc.setLineWidth(1.0);
        for (int i = 1; i <= xDimension - 1; i++) {
            double xPos = i * cellWidth;
            gc.strokeLine(xPos, cellHeight, xPos, getHeight() - cellHeight);
        }
        for (int i = 1; i <= yDimension - 1; i++) {
            double yPos = i * cellHeight;
            gc.strokeLine(cellWidth, yPos, getWidth() - cellWidth, yPos);
        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);
        for (int i = 1; i < xDimension; ++i)
            for (int j = 1; j < yDimension; ++j) {
                for (int ii = i; ii < xDimension; ++ii)
                    for (int jj = j; jj < yDimension; ++jj)
                        if (lines[i][j][ii][jj] == 1) {
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
                if (pieces[i][j] == 1) {
                    gc.setStroke(Color.RED);
                    gc.setFill(Color.RED);
                    gc.fillOval(xCircle - cellWidth / 4, yCircle - cellHeight / 4, cellWidth / 2, cellHeight / 2);
                    gc.setStroke(Color.GRAY);
                } else if (pieces[i][j] == 2) {
                    gc.setStroke(Color.BLUE);
                    gc.setFill(Color.BLUE);
                    gc.fillOval(xCircle - cellWidth / 4, yCircle - cellHeight / 4, cellWidth / 2, cellHeight / 2);
                    gc.setStroke(Color.GRAY);
                }
            }
        }

    }

    public void drawEndBoard() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight() / 15);

        String text;
        if (turn == 2) {
            text = "THE WINNER IS RED";
            gc.setStroke(Color.RED);
        } else {
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


        if (lastX != -1 && (lines[lastX][lastY][column][row] == 0 || pieces[column][row] != 0)) {
            System.out.println("Wrong Position!");
            return;
        }
        lastX = column;
        lastY = row;

        pieces[column][row] = turn;
        turn = 3 - turn;

        int gataJocul = 0;
        for (int i = -1; i < 2 && gataJocul == 0; ++i)
            for (int j = -1; j < 2 && gataJocul == 0; ++j)
                if ((column + i) > 0 && (row + j) > 0 && lines[column][row][column + i][row + j] == 1 && pieces[column + i][row + j] == 0)
                    gataJocul = 1;
        if (gataJocul == 0) {
            drawBoard(xDimension, yDimension);
            drawEndBoard();
            return;
        }

        drawBoard(xDimension, yDimension);
    }

    public void setLines() {
        lines = new int[xDimension + 3][yDimension + 3][xDimension + 3][yDimension + 3];
        for (int i = 1; i < xDimension; ++i) {
            for (int j = 1; j < yDimension; ++j) {
                if (j + 1 < yDimension)
                    lines[i][j][i][j + 1] = lines[i][j + 1][i][j] = ((int) (Math.random() * 10)) % 2;
                if (i + 1 < xDimension)
                    lines[i][j][i + 1][j] = lines[i + 1][j][i][j] = ((int) (Math.random() * 10)) % 2;
                if (i - 1 > 0) lines[i][j][i - 1][j] = lines[i - 1][j][i][j] = ((int) (Math.random() * 10)) % 2;
                if (j - 1 > 0) lines[i][j][i][j - 1] = lines[i][j - 1][i][j] = ((int) (Math.random() * 10)) % 2;

            }
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
        pieces = new int[xDimension + 5][yDimension + 5];
        for (int i = 0; i < xDimension; ++i) {
            for (int j = 0; j < yDimension; ++j) {
                pieces[i][j] = 0;
            }
        }
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

    public void setPieces(int[][] pieces) {
        this.pieces = pieces;
    }

    public void setLines(int[][][][] lines) {
        this.lines = lines;
    }

    public void saveDrawingPanelState(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            //out.writeObject(this);
            System.out.println("A mers DrawingPanel save.");
        } catch (IOException e) {
            System.out.println("Eroare la DrawingPanel save: " + e.getMessage());
        }
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
}
