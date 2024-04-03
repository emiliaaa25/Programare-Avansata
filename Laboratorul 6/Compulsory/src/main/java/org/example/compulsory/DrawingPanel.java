package org.example.compulsory;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class DrawingPanel extends Canvas {
    int xDimension = 10;
    int yDimension = 10;

    public DrawingPanel() {
        setWidth(400);
        setHeight(400);
        drawBoard(xDimension, yDimension);
    }

    public void drawBoard(int xDimension, int yDimension) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        double cellWidth ;
        double cellHeight ;
        if(xDimension==yDimension) {
          cellWidth = getWidth() / xDimension;
          cellHeight = getHeight() / yDimension;
        }
        else {
            cellWidth = getWidth() / xDimension;
            cellHeight = getHeight() / yDimension;
        }

        gc.setStroke(Color.MAGENTA);
        for (int i = 1; i <= xDimension - 1; i++) {
            double xPos = i * cellWidth;
            gc.strokeLine(xPos, cellHeight, xPos, getHeight()-cellHeight);
        }
        for (int i = 1; i <= yDimension - 1; i++) {
            double yPos = i * cellHeight;
            gc.strokeLine(cellWidth, yPos, getWidth()-cellWidth, yPos);
        }

        gc.setFill(Color.TRANSPARENT);
        gc.setStroke(Color.GRAY);
        for (int i = 1; i <= xDimension - 1; i++) {
            for (int j = 1; j <= yDimension - 1; j++) {
                double xCircle = i * cellWidth;
                double yCircle = j * cellHeight;
                gc.strokeOval(xCircle - cellWidth / 4, yCircle - cellHeight / 4, cellWidth / 2, cellHeight / 2);

            }
        }
    }
}
