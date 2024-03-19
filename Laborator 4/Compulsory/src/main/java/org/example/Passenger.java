package org.example;

public class Passenger extends Person {
    private int numberOfTrips;

    public Passenger(String name, int age, int numberOfTrips, PersonType type) {
        super(name, age, type);
        this.numberOfTrips = numberOfTrips;
    }


}