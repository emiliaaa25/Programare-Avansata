package org.example.compulsory;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


class ControlPanel extends HBox {
    public ControlPanel() {
        setPadding(new Insets(10));
        setSpacing(10);
        Button loadButton = new Button("Load");
        Button saveButton = new Button("Save");
        Button exitButton = new Button("Exit");
        getChildren().addAll(loadButton, saveButton, exitButton);
    }
}