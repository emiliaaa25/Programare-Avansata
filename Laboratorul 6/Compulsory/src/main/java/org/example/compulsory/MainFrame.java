package org.example.compulsory;

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

        ConfigPanel configPanel = new ConfigPanel();
        DrawingPanel drawingPanel = new DrawingPanel();
        ControlPanel controlPanel = new ControlPanel();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(configPanel);
        borderPane.setCenter(drawingPanel);
        borderPane.setBottom(controlPanel);

        Scene scene = new Scene(borderPane, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
