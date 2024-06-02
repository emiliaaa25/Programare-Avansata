package org.example.clientel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientApplication extends Application {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private Integer currentFloor = 0;
    private int idElevator = 2;
    private boolean isInElevator = false;

    @Override
    public void start(Stage primaryStage) {
        Label responseLabel = new Label("Response will be displayed here");
        Button goInTheBuildingButton = new Button("Go to elevator!");

        VBox root = new VBox(10, goInTheBuildingButton, responseLabel);
        root.setAlignment(Pos.TOP_CENTER);

        setBackground(root, "file:src/main/resources/cladire.jpg");

        goInTheBuildingButton.setOnAction(event -> goInBuildingAction(root));

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setBackground(VBox root, String imagePath) {
        Image image = new Image(imagePath);
        BackgroundSize backgroundSize = new BackgroundSize(500, 500, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        root.setBackground(new Background(backgroundImage));
    }

    private void goInBuildingAction(VBox root) {
        Button upDirectionButton = new Button("UP");
        Button downDirectionButton = new Button("DOWN");

        upDirectionButton.setOnAction(event -> fetchDataFromServer(root, "UP"));
        downDirectionButton.setOnAction(event -> fetchDataFromServer(root, "DOWN"));

        setBackground(root, "file:src/main/resources/hallway.jpg");

        root.getChildren().clear();
        root.setAlignment(Pos.BOTTOM_RIGHT);

        Label responseLabel = new Label("Floor");
        root.getChildren().addAll(responseLabel, upDirectionButton, downDirectionButton);
    }

    private void fetchDataFromServer(VBox root, String direction) {
        Label responseLabel = new Label("Requesting elevator...");
        root.getChildren().add(responseLabel);

        String uri = "http://localhost:1520/elevator/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "direction/" + currentFloor + "/" + direction))
                .GET()
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAccept(httpResponse -> {
            String responseBody = httpResponse.body();
            System.out.println("Received response body: " + responseBody);
            idElevator = Integer.parseInt(responseBody.trim());

            HttpRequest updateRequest = HttpRequest.newBuilder()
                    .uri(URI.create(uri + "update/" + idElevator + "/" + currentFloor))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            httpClient.sendAsync(updateRequest, HttpResponse.BodyHandlers.discarding()).thenAccept(updateResponse -> {
                Platform.runLater(() -> {
                    isInElevator = true;
                    root.getChildren().clear();
                    inElevator(root);
                });
            });
        });
    }

    private void inElevator(VBox root) {
        isInElevator = true;
        Platform.runLater(() -> {
            root.getChildren().clear();

            Button[] buttons = new Button[6];
            for (int i = 0; i < 6; i++) {
                int floor = i;
                buttons[i] = new Button(i == 0 ? "P" : String.valueOf(i));
                buttons[i].setOnAction(event -> elevatorButtonHandler(floor, root));
            }

            setBackground(root, "file:src/main/resources/inElevator.jpg");
            root.getChildren().addAll(buttons);
        });
    }

    private void elevatorButtonHandler(int floorWanted, VBox root) {
        isInElevator = true;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:1520/elevator/update/" + idElevator + "/" + floorWanted))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.discarding()).thenRun(() -> {
            Platform.runLater(() -> {
                root.getChildren().clear();
                setBackground(root, "file:src/main/resources/inElevator.jpg");

                Label responseLabel = new Label("The elevator is now at floor " + currentFloor);
                root.getChildren().add(responseLabel);

                String uri = "http://localhost:1520/elevator/";
                updateCurrentFloor(root, floorWanted, responseLabel, uri);
            });
        });
    }

    private void updateCurrentFloor(VBox root, int floorWanted, Label responseLabel, String uri) {
        CompletableFuture.runAsync(() -> {
            AtomicBoolean arrived = new AtomicBoolean(false);
            while (!arrived.get()) {
                HttpRequest floorRequest = HttpRequest.newBuilder()
                        .uri(URI.create(uri + "floor/" + idElevator))
                        .GET()
                        .build();
                httpClient.sendAsync(floorRequest, HttpResponse.BodyHandlers.ofString()).thenAccept(floorResponse -> {
                    int newFloor = Integer.parseInt(floorResponse.body().trim());
                    if (newFloor != currentFloor) {
                        currentFloor = newFloor;
                        System.out.println("The elevator is now at floor " + currentFloor);
                        Platform.runLater(() -> responseLabel.setText("The elevator is now at floor " + currentFloor));
                    }
                    if (newFloor == floorWanted) {
                        arrived.set(true);
                    }
                }).join();
                try {
                    Thread.sleep(1000); // Wait for 1 second before the next check
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> {
                isInElevator = false;
                setFloorBackground(root, floorWanted);
                showHallway(root);
            });
        });
    }

    private void showHallway(VBox root) {
        Platform.runLater(() -> {
            root.getChildren().clear();
            setFloorBackground(root, currentFloor);

            String floorMessage;
            switch (currentFloor) {
                case 0:
                    floorMessage = "You are at the ground floor hallway.";
                    break;
                case 1:
                    floorMessage = "You are at the 1st floor hallway.";
                    break;
                case 2:
                    floorMessage = "You are at the 2nd floor hallway.";
                    break;
                case 3:
                    floorMessage = "You are at the 3rd floor hallway.";
                    break;
                case 4:
                    floorMessage = "You are at the 4th floor hallway.";
                    break;
                case 5:
                    floorMessage = "You are at the 5th floor hallway.";
                    break;
                default:
                    floorMessage = "You are at an unknown floor.";
                    break;
            }

            Label messageLabel = new Label(floorMessage);
            root.getChildren().add(messageLabel);
            Button goToElevatorButton = new Button("Go to Elevator");
            goToElevatorButton.setOnAction(event -> inElevator(root));
            root.getChildren().add(goToElevatorButton);
        });
    }

    private void setFloorBackground(VBox root, int floor) {
        switch (floor) {
            case 0:
                setBackground(root, "file:src/main/resources/hallway.jpg");
                break;
            case 1:
                setBackground(root, "file:src/main/resources/etaj1.png");
                break;
            case 2:
                setBackground(root, "file:src/main/resources/etaj2.png");
                break;
            case 3:
                setBackground(root, "file:src/main/resources/etaj3.png");
                break;
            case 4:
                setBackground(root, "file:src/main/resources/etaj4.png");
                break;
            case 5:
                setBackground(root, "file:src/main/resources/etaj5.png");
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
