package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        String city = faker.address().city();
        String city1 = faker.address().city();
        String city2 = faker.address().city();
        Person p1 = new Person(faker.name().firstName(), 21, PersonType.DRIVER, city);
        Person p2 = new Person(faker.name().firstName(), 20, PersonType.DRIVER, city1);
        Person p3 = new Person(faker.name().firstName(), 22, PersonType.PASSENGER, city);
        Person p4 = new Person(faker.name().firstName(), 25, PersonType.PASSENGER, faker.address().city());
        Person p5 = new Person(faker.name().firstName(), 19, PersonType.DRIVER, faker.address().city());
        Person p6 = new Person(faker.name().firstName(), 21, PersonType.PASSENGER, city1);
        Person p7 = new Person(faker.name().firstName(), 21, PersonType.DRIVER, city2);
        Person p8 = new Person(faker.name().firstName(), 21, PersonType.PASSENGER, city2);
        Person p9 = new Person(faker.name().firstName(), 21, PersonType.DRIVER, faker.address().city());


        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);
        persons.add(p5);
        persons.add(p6);
        persons.add(p7);
        persons.add(p8);
        persons.add(p9);

        System.out.println("Drivers: ");
        List<Person> drivers = persons.stream().filter(person -> person.getType() == PersonType.DRIVER).collect(Collectors.toCollection(LinkedList::new));
        drivers.stream().sorted(Person::getAge).forEach(System.out::println);
        System.out.println();

        System.out.println("Passengers: ");
        Set<Person> passengers = persons.stream().filter(person -> person.getType() == PersonType.PASSENGER).collect(Collectors.toCollection(TreeSet::new));
        passengers.stream().sorted(Person::compareTo).forEach(System.out::println);

        System.out.println();
        System.out.println("Destinations: ");
        List<String> destinations = drivers.stream().flatMap(driver -> Arrays.stream(driver.getAddress().split(", "))).toList();
        System.out.println(destinations);
        System.out.println();
        System.out.println("Map of destinations and people: ");
        Map<String, Set<Person>> destinationMap = passengers.stream().collect(Collectors.groupingBy(Person::getAddress, Collectors.toSet()));
        System.out.println(destinationMap);


        passengers = passengers.stream().sorted(Comparator.comparing(Person::getAddress)).collect(Collectors.toCollection(LinkedHashSet::new));
        drivers = drivers.stream().sorted(Comparator.comparing(Person::getAddress)).toList();
        System.out.println();
        System.out.println("Greedy algorithm: ");
        List<String> matchedPairs = new ArrayList<>();
        for (Person driver : drivers) {
            for (Person passenger : passengers) {
                if (driver.getAddress().equals(passenger.getAddress())) {
                    matchedPairs.add(driver.getName() + " -> " + passenger.getName());
                    passengers.remove(passenger);
                    break;
                }
            }
        }
        matchedPairs.forEach(System.out::println);
    }
}