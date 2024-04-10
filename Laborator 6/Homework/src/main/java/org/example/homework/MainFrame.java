package org.example.homework;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFrame extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Positional Game");

        ConfigPanel configPanel = new ConfigPanel(primaryStage);
        DrawingPanel drawingPanel = new DrawingPanel(10, 10);
        ControlPanel controlPanel = new ControlPanel(drawingPanel);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(configPanel);
        borderPane.setCenter(drawingPanel);
        borderPane.setBottom(controlPanel);

        Scene scene = new Scene(borderPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
