package org.example.compulsory;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


class ConfigPanel extends HBox {
    private final TextField xTextField;
    private final TextField yTextField;

    public ConfigPanel() {
        setPadding(new Insets(10));
        setSpacing(10);
        xTextField = new TextField();
        yTextField = new TextField();
        Button newGameButton = new Button("Create");
        newGameButton.setOnAction(event -> resetGame());
        getChildren().addAll(xTextField, yTextField, newGameButton);
    }

    private void resetGame() {
        int xDimension = getXDimension();
        int yDimension = getYDimension();
        DrawingPanel drawingPanel = (DrawingPanel) getParent().getChildrenUnmodifiable().get(1);
        drawingPanel.drawBoard(xDimension, yDimension);
    }


    public int getXDimension() {
        return Integer.parseInt(xTextField.getText());
    }

    public int getYDimension() {
        return Integer.parseInt(yTextField.getText());
    }
}