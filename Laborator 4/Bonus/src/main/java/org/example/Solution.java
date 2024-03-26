package org.example;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    private List<Person> drivers;
    private Set<Person> passengers;

    private double edgeProbability;

    public Solution(List<Person> drivers, Set<Person> passengers, double edgeProbability) {
        this.drivers = drivers;
        this.passengers = passengers;
        this.edgeProbability = edgeProbability;
    }

    public Graph<Object, DefaultEdge> solve(Map<String, Set<Person>> destinationMap) {
        Graph<Object, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        Random random = new Random();

        drivers.forEach(graph::addVertex);
        destinationMap.forEach((destination, passengers) -> {
            if (!graph.containsVertex(destination)) {
                graph.addVertex(destination);
            }
            passengers.forEach(passengerNew -> {
                if (!graph.containsVertex(passengerNew)) {
                    graph.addVertex(passengerNew);
                }
                drivers.forEach(driver -> {
                    if (random.nextDouble() < edgeProbability && !graph.containsEdge(driver, passengerNew)) {
                        graph.addEdge(driver, passengerNew);
                    }
                });
            });
        });

        return graph;
    }

    public ArrayList<Object> maximumCardinalitySet(Graph<Object, DefaultEdge> graph, Set<Object> SPartition, Set<Object> TPartition) {
        HopcroftKarpMaximumCardinalityBipartiteMatching<Object, DefaultEdge> matcher =
                new HopcroftKarpMaximumCardinalityBipartiteMatching<>(graph, SPartition, TPartition);
        MatchingAlgorithm.Matching<Object, DefaultEdge> matching = matcher.getMatching();

        ArrayList<Object> cardinalitySet = new ArrayList<>();
        SPartition.forEach(node -> {
            if (!matching.isMatched(node)) {
                cardinalitySet.add(node);
            }
        });
        return cardinalitySet;
    }

}

