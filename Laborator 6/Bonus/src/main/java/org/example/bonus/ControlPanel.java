package org.example.bonus;
import javafx.scene.Scene;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.plaf.nimbus.State;
import java.io.*;



class ControlPanel extends HBox {
    DrawingPanel drawingPanel;
    public ControlPanel(DrawingPanel stage) {
        drawingPanel = stage;
        setPadding(new Insets(10));
        setSpacing(10);
        Button loadButton = new Button("Load");
        loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                saveDrawingPanelState(stage, "loading.txt");

            }
        });
        Button exitButton = new Button("Exit");
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });
        Button exportButton = new Button("Export");
        exportButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.saveCanvasImage("drawing_image.png");
            }
        });
        Button gameAI = new Button("GameAI");
        gameAI.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setPieces();
                stage.setLastY(-1);
                stage.setLastX(-1);
                stage.setLines();
                stage.setPlayWithAI(true);
                stage.generatePerfectMatch();
                stage.drawBoard(stage.xDimension, stage.yDimension);
            }
        });
        getChildren().addAll(loadButton, saveButton, exportButton, exitButton, gameAI);
    }
    public static void saveDrawingPanelState(DrawingPanel drawingPanel, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            drawingPanel.saveDrawingPanelState(filename);
            System.out.println("A mers DrawingPanel save.");
        } catch (IOException e) {
            System.out.println("Eroare la DrawingPanel save: " + e.getMessage());
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