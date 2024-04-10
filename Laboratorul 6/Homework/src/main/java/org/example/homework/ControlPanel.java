package org.example.homework;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.*;


class ControlPanel extends HBox {
    DrawingPanel drawingPanel;

    public ControlPanel(DrawingPanel stage) {
        drawingPanel = stage;
        setPadding(new Insets(10));
        setSpacing(10);
        Button loadButton = new Button("Load");
        loadButton.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                drawingPanel = loadDrawingPanelState("loading.txt");
                stage.setXDimension(drawingPanel.xDimension);
                stage.setYDimension(drawingPanel.yDimension);
                stage.setPieces(drawingPanel.pieces);
                stage.setLastY(drawingPanel.lastY);
                stage.setLastX(drawingPanel.lastX);
                stage.setLines(drawingPanel.lines);
                stage.drawBoard(drawingPanel.getXDimension(), drawingPanel.getYDimension());
            }
        });
        Button saveButton = new Button("Save");
        saveButton.setOnMouseClicked(mouseEvent -> {
            saveDrawingPanelState(stage, "loading.txt");
            //stage.saveCanvasImage("drawing_image.png");
        });
        Button exitButton = new Button("Exit");
        exitButton.setOnMouseClicked(mouseEvent -> System.exit(0));
        getChildren().addAll(loadButton, saveButton, exitButton);
    }

    public void saveDrawingPanelState(DrawingPanel drawingPanel, String filename) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            drawingPanel.saveDrawingPanelState(filename);
            out.writeObject(this);
            System.out.println("DrawingPanel state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving DrawingPanel state: " + e.getMessage());
        }

    }

    public static DrawingPanel loadDrawingPanelState(String filename) {
        DrawingPanel drawingPanel1 = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            drawingPanel1 = (DrawingPanel) in.readObject();
            System.out.println("A mers DrawingPanel load.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Eroare la DrawingPanel load: " + e.getMessage());
        }
        return drawingPanel1;
    }
}