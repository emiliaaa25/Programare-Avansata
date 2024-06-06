package com.example.project.model;

public class Elevators {
    private int id;
    private String name;
    private int currentFloor;
    private String direction;

    private int startFloor;

    String isOcupied;

    public String getIsOcupied() {
        return isOcupied;
    }

    public void setIsOcupied(String isOcupied) {
        this.isOcupied = isOcupied;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public void setStartFloor(int startFloor) {
        this.startFloor = startFloor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
