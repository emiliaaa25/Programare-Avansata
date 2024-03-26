package org.example;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProblemGenerator {
    private int numDrivers;
    private int numPassengers;

    ProblemGenerator(int numDrivers, int numPassengers) {
        this.numDrivers = numDrivers;
        this.numPassengers = numPassengers;
    }

    public List<Person> generateRandomPersons() {
        Faker faker = new Faker();
        Random random = new Random();
        List<String> address = new ArrayList<>();
        int n = 10;
        for (int i = 0; i < n; i++)
            address.add(faker.address().city());
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < numDrivers; i++) {
            persons.add(new Person(faker.name().firstName(), faker.number().numberBetween(18, 70), PersonType.DRIVER, accessNumber(random.nextInt(10), address)));
        }
        for (int i = 0; i < numPassengers; i++) {
            persons.add(new Person(faker.name().firstName(), faker.number().numberBetween(18, 70), PersonType.PASSENGER, accessNumber(random.nextInt(10), address)));
        }
        return persons;
    }

    public static String accessNumber(int n, List<String> address) {
        return address.get(n);
    }

}
