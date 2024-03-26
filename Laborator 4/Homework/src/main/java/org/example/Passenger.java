package org.example;

public class Passenger extends Person {
    private int numberOfTrips;

    public Passenger(String name, int age, String address, int numberOfTrips, PersonType type) {
        super(name, age, type, address);
        this.numberOfTrips = numberOfTrips;
    }


}