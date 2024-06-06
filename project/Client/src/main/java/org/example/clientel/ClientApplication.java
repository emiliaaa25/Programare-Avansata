package org.example.clientel;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientApplication extends Application {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private Integer currentFloor = 0;
    private int idElevator = -1;
    private boolean isInElevator = false;
    private final Stage stage = new Stage();
    double windowWidth;
    double windowHeight;

    private int clientId;

    private Audio audio = new Audio();


    private final Lock elevatorLock = new ReentrantLock();
    private final AtomicBoolean isElevatorOccupied = new AtomicBoolean();
    String isElevatorOccupied1;


    @Override
    public void start(Stage primaryStage) {
        registerClient();
        Label responseLabel = new Label("Response will be displayed here");
        Button goInTheBuildingButton = new Button("Go to elevator!");

        VBox root = new VBox(10, goInTheBuildingButton, responseLabel);
        root.setAlignment(Pos.TOP_CENTER);

        setBackground(root, "file:src/main/resources/building.jpg");

        goInTheBuildingButton.setOnAction(event -> goInBuildingAction(root, 0));
        stage.setTitle("Client");

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        windowWidth = primaryScreenBounds.getWidth() * 0.7;
        windowHeight = primaryScreenBounds.getHeight();
        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void goInBuildingAction(VBox root, int floor) {
        setBackground(root, floor == 0 ? "file:src/main/resources/hallway.jpg" : "file:src/main/resources/etaj" + floor + ".jpg");
        Platform.runLater(() -> {
            root.getChildren().clear();

            BorderPane borderPane = new BorderPane();
            Label titleLabel = new Label("Choose the floor you want to go to:");
            titleLabel.setStyle("-fx-background-color: #f4f4f4; " +
                    "-fx-border-color: #ccc; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 10px; " +
                    "-fx-padding: 10px;");
            borderPane.setTop(titleLabel);

            GridPane buttonGrid = new GridPane();

            Button[] buttons = new Button[6];
            for (int i = 0; i < 6; i++) {
                int floor1 = i;
                buttons[i] = new Button(floor1 == 0 ? "P" : String.valueOf(i));
                buttons[i].setOnAction(event -> fetchDataFromServer(root, floor1));
                buttons[i].setStyle("-fx-background-color: #686D76; " +
                        "-fx-border-color: #373A40; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 30px; " +
                        "-fx-background-radius: 30px; " +
                        "-fx-font-weight: bold;");
                buttons[i].setMinWidth(50);
                buttons[i].setMinHeight(50);
                int row = i / 3;
                int col = i % 3;
                buttonGrid.add(buttons[i], col, row);
            }

            buttonGrid.setHgap(20);
            buttonGrid.setVgap(10);
            buttonGrid.setAlignment(Pos.TOP_CENTER);
            RowConstraints rowConstraints = new RowConstraints();
            RowConstraints rowConstraints1 = new RowConstraints();
            rowConstraints.setMinHeight(300);
            rowConstraints1.setMinHeight(25);
            buttonGrid.getRowConstraints().addAll(rowConstraints1, rowConstraints1, rowConstraints);
            borderPane.setCenter(buttonGrid);
            Button goToYourRoomButton = new Button("Go to your room");
            goToYourRoomButton.setOnAction(event -> leaveElevator(root));
            root.getChildren().addAll(goToYourRoomButton, borderPane, buttonGrid);
        });

    }

    private void fetchDataFromServer(VBox root, int floor) {


        String uri = "http://localhost:1520/elevator/";
        String direction = new String("");
        if (currentFloor > floor) {
            direction = "DOWN";
        } else
            direction = "UP";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "direction/" + currentFloor + "/" + direction + "/" + floor))
                .GET()
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAccept(httpResponse -> {
            idElevator = Integer.parseInt(httpResponse.body());
            System.out.println("Elevator ales " + idElevator);

            HttpRequest request1 = HttpRequest.newBuilder()
                    .uri(URI.create(uri + "update/" + idElevator + "/" + currentFloor))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();
            httpClient.sendAsync(request1, HttpResponse.BodyHandlers.ofString()).thenAccept(httpResponse1 -> {
                System.out.println("Elevator updated");
            });

            HttpRequest request2 = HttpRequest.newBuilder()
                    .uri(URI.create(uri + "update/isOcupied/" + idElevator + "/" + "TRUE"))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpRequest request3 = HttpRequest.newBuilder()
                    .uri(URI.create(uri + "floor/" + idElevator))
                    .GET()
                    .build();
            AtomicInteger elevatorFloor = new AtomicInteger();
            httpClient.sendAsync(request3, HttpResponse.BodyHandlers.ofString()).thenAccept(httpResponse1 -> {
                elevatorFloor.set(Integer.parseInt(httpResponse1.body()));
                System.out.println("Elevator floor " + elevatorFloor);

                System.out.println("Current floor " + currentFloor);
                while (currentFloor != elevatorFloor.get()) {
                    HttpRequest request4 = HttpRequest.newBuilder()
                            .uri(URI.create(uri + "floor/" + idElevator))
                            .GET()
                            .build();
                    httpClient.sendAsync(request4, HttpResponse.BodyHandlers.ofString()).thenAccept(httpResponse2 -> {
                        Platform.runLater(() -> {
                            root.getChildren().clear();
                            Label responseLabel = new Label("The elevator is at the floor : " + elevatorFloor);
                            root.getChildren().add(responseLabel);
                        });
                        elevatorFloor.set(Integer.parseInt(httpResponse2.body()));
                        System.out.println("Elevator floor2 " + elevatorFloor.get());
                    }).join();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("A ajuns la etajul tau ");

                HttpRequest request5 = HttpRequest.newBuilder()
                        .uri(URI.create(uri + "update/" + idElevator + "/" + floor))
                        .PUT(HttpRequest.BodyPublishers.noBody())
                        .build();
                httpClient.sendAsync(request2, HttpResponse.BodyHandlers.ofString()).thenAccept(httpResponse3 -> {
                    System.out.println("Elevator updated");
                    httpClient.sendAsync(request5, HttpResponse.BodyHandlers.ofString()).thenAccept(httpResponse2 -> {
                        inElevator(root, floor);
                    });
                });

            });
        });
    }

    private void inElevator(VBox root, int floorWanted) {
        audio.play();
        setBackground(root, "file:src/main/resources/inElevator.jpg");

        String uri = "http://localhost:1520/elevator/";
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
                        Platform.runLater(() -> {
                            root.getChildren().clear();
                            Label responseLabel = new Label("The elevator is at the floor : " + currentFloor);
                            root.getChildren().add(responseLabel);
                        });
                    }
                    if (newFloor == floorWanted) {
                        arrived.set(true);
                    }
                }).join();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            audio.stop();
            Platform.runLater(() -> {
                isInElevator = false;
                setFloorBackground(root, floorWanted);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(uri + "update/isOcupied/" + idElevator + "/" + "FALSE"))
                        .PUT(HttpRequest.BodyPublishers.noBody())
                        .build();
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAccept(httpResponse -> {
                            System.out.println("Elevator is not occupied anymore");
                        }
                );
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
            goInBuildingAction(root, currentFloor);

        });
    }

    private void leaveElevator(VBox root) {
        String uri = "http://localhost:1520/elevator/client/" + clientId;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .DELETE()
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenRun(() -> {
            Platform.runLater(() -> {
                System.out.println("Client removed: " + clientId);
                HttpRequest request1 = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:1520/elevator/update/isOcupied/" + idElevator + "/FALSE"))
                        .PUT(HttpRequest.BodyPublishers.noBody())
                        .build();
                httpClient.sendAsync(request1, HttpResponse.BodyHandlers.ofString()).thenAccept(httpResponse -> {
                    System.out.println("Elevator is not occupied anymore");
                });
                stage.close();
            });
        });
    }


    private void setBackground(VBox root, String imagePath) {
        Image image = new Image(imagePath);
        BackgroundSize backgroundSize = new BackgroundSize(windowWidth, windowHeight, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        root.setBackground(new Background(backgroundImage));
    }

    public void registerClient() {
        String uri = "http://localhost:1520/elevator/client";
        String requestBody = "{\n" +
                "\"name\": \"Client\",\n" +
                "\"currentFloor\": " + currentFloor + ",\n" +
                "\"idElevator\": " + idElevator + "\n" +
                "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAccept(httpResponse -> {
            String responseBody = httpResponse.body();
            System.out.println("Received response body: " + responseBody);
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                clientId = jsonNode.get("id").asInt();
                System.out.println("Assigned client ID: " + clientId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setFloorBackground(VBox root, int floor) {
        switch (floor) {
            case 0:
                setBackground(root, "file:src/main/resources/hallway.jpg");
                break;
            case 1:
                setBackground(root, "file:src/main/resources/etaj1.jpg");
                break;
            case 2:
                setBackground(root, "file:src/main/resources/etaj2.jpg");
                break;
            case 3:
                setBackground(root, "file:src/main/resources/etaj3.jpg");
                break;
            case 4:
                setBackground(root, "file:src/main/resources/etaj4.jpg");
                break;
            case 5:
                setBackground(root, "file:src/main/resources/etaj5.jpg");
                break;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
