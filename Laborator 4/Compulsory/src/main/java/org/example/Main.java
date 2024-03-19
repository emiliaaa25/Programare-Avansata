package org.example;


import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Emilia", 21, PersonType.DRIVER);
        Person p2 = new Person("Andra", 20, PersonType.DRIVER);
        Person p3 = new Person("Alex", 22, PersonType.PASSENGER);
        Person p4 = new Person("Casiana", 25, PersonType.PASSENGER);
        Person p5 = new Person("Denisa", 19, PersonType.DRIVER);

        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);
        persons.add(p5);

        List<Person> drivers = persons.stream().filter(person -> person.getType() == PersonType.DRIVER).collect(Collectors.toCollection(LinkedList::new));
        drivers.stream().sorted(Person::getAge).forEach(System.out::println);
        System.out.println();

        Set<Person> passengers = persons.stream().filter(person -> person.getType() == PersonType.PASSENGER).collect(Collectors.toCollection(TreeSet::new));
        passengers.stream().sorted(Person::compareTo).forEach(System.out::println);
    }
}