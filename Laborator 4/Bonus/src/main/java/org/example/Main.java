package org.example;

import com.github.javafaker.Faker;
import org.jgrapht.Graph;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.matching.DenseEdmondsMaximumCardinalityMatching;
import org.jgrapht.alg.matching.GreedyMaximumCardinalityMatching;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.graph.DefaultEdge;


import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ProblemGenerator problem = new ProblemGenerator(5000, 5000);
        List<Person> persons = problem.generateRandomPersons();
        System.out.println("Drivers: ");
        List<Person> drivers = persons.stream().filter(person -> person.getType() == PersonType.DRIVER).collect(Collectors.toCollection(LinkedList::new));
        drivers.stream().sorted(Person::getAge).forEach(System.out::println);
        System.out.println();
        System.out.println("Passengers: ");
        Set<Person> passengers = persons.stream().filter(person -> person.getType() == PersonType.PASSENGER).collect(Collectors.toCollection(TreeSet::new));
        passengers.stream().sorted(Person::compareTo).forEach(System.out::println);
        System.out.println();
        System.out.println("Destinations that drivers pass through: ");
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
        int matched = 0;
        for (Person driver : drivers) {
            for (Person passenger : passengers) {
                if (driver.getAddress().equals(passenger.getAddress())) {
                    matched++;
                    passengers.remove(passenger);
                    break;
                }
            }
        }
        double edgeProbability = 0.1;
        System.out.println("Number of matched pairs using Greedy without JgraphT: " + matched);
        passengers = persons.stream().filter(person -> person.getType() == PersonType.PASSENGER).collect(Collectors.toCollection(TreeSet::new));

        Solution solver1 = new Solution(drivers, passengers, edgeProbability);
        Graph<Object, DefaultEdge> graph1 = solver1.solve(destinationMap);
        GreedyMaximumCardinalityMatching<Object, DefaultEdge> greedyMatching = new GreedyMaximumCardinalityMatching<>(graph1, true);
        Set<DefaultEdge> matchingEdges = greedyMatching.getMatching().getEdges();
        System.out.println("Number of matched pairs using Greedy with JgraphT: " + matchingEdges.size());


        Solution solver = new Solution(drivers, passengers, edgeProbability);
        Graph<Object, DefaultEdge> graph = solver.solve(destinationMap);
        Set<Object> sPartition = new TreeSet<>();
        Set<Object> tPartition = new TreeSet<>();
        destinationMap.forEach((key, value) -> {
            if (graph.containsVertex(key)) {
                tPartition.add(key);
            }
            value.forEach(person -> {
                if (graph.containsVertex(person)) {
                    sPartition.add(person);
                }

            });
        });

        ArrayList<Object> cardinalitySet = solver.maximumCardinalitySet(graph, sPartition, tPartition);


        System.out.println("Maximum Cardinality Set: " + cardinalitySet.size());


    }

}