package org.example.homework;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.*;



class ControlPanel extends HBox {
    DrawingPanel drawingPanel;
    public ControlPanel(DrawingPanel stage) {
        drawingPanel = stage;
        setPadding(new Insets(10));
        setSpacing(10);
        Button loadButton = new Button("Load");
        loadButton.setOnMouseClicked(mouseEvent -> {
            drawingPanel = loadDrawingPanelState("loading.txt");
            stage.setXDimension(drawingPanel.xDimension);
            stage.setYDimension(drawingPanel.yDimension);
            stage.setPieces(drawingPanel.pieces);
            stage.setLastY(drawingPanel.lastY);
            stage.setLastX(drawingPanel.lastX);
            stage.setLines(drawingPanel.lines);
            stage.drawBoard(drawingPanel.getXDimension(), drawingPanel.getYDimension());
        });
        Button saveButton = new Button("Save");
        saveButton.setOnMouseClicked(mouseEvent -> saveDrawingPanelState(stage, "loading.txt"));
        Button exitButton = new Button("Exit");
        exitButton.setOnMouseClicked(mouseEvent -> System.exit(0));
        Button exportButton = new Button("Export");
        exportButton.setOnMouseClicked(mouseEvent -> stage.saveCanvasImage("drawing_image.png"));
        getChildren().addAll(loadButton, saveButton, exportButton, exitButton);
    }
    public static void saveDrawingPanelState(DrawingPanel drawingPanel, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            drawingPanel.saveDrawingPanelState(filename);
            System.out.println(" DrawingPanel saved.");
        } catch (IOException e) {
            System.out.println("Error saving DrawingPanel: " + e.getMessage());
        }
    }

    public static DrawingPanel loadDrawingPanelState(String filename) {
        DrawingPanel drawingPanel1 = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            drawingPanel1 = (DrawingPanel) in.readObject();
            System.out.println("DrawingPanel loaded.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading DrawingPanel: " + e.getMessage());
        }
        return drawingPanel1;
    }
}