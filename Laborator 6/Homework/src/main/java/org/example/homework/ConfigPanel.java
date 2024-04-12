package org.example.homework;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


class ConfigPanel extends HBox {
    private final TextField xTextField;
    private final TextField yTextField;

    public ConfigPanel(Stage stage) {
        setPadding(new Insets(10));
        setSpacing(10);
        xTextField = new TextField();
        yTextField = new TextField();
        Button newGameButton = new Button("Create");
        newGameButton.setOnAction(event -> resetGame(stage));
        getChildren().addAll(xTextField, yTextField, newGameButton);
    }

    private void resetGame(Stage stage) {
        int xDimension = getXDimension();
        int yDimension = getYDimension();
        System.out.println("xDimension: " + xDimension);
        DrawingPanel drawingPanel = (DrawingPanel) getParent().getChildrenUnmodifiable().get(1);
        drawingPanel.setPieces();
        drawingPanel.setXDimension(xDimension);
        drawingPanel.setYDimension(yDimension);
        drawingPanel.setLastX(-1);
        drawingPanel.setLastY(-1);
        drawingPanel.setLines();
        drawingPanel.setTurn(1);
        drawingPanel.drawBoard(xDimension, yDimension);
        stage.show();
    }

    public int getXDimension() {
        if (xTextField.getText().isEmpty()) {
            return 10;
        } else {
            return Integer.parseInt(xTextField.getText());
        }
    }

    public int getYDimension() {
        if (yTextField.getText().isEmpty()) {
            return 10;
        } else {
            return Integer.parseInt(yTextField.getText());
        }
    }
}