package org.example;

public class Driver extends Person {
    private String licenseNumber;

    public Driver(String name, int age, String licenseNumber, PersonType type) {
        super(name, age, type);
        this.licenseNumber = licenseNumber;

    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return "Driver " + super.getName();
    }

}