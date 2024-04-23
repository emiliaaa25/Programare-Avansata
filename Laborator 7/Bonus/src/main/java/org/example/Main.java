package org.example;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import static org.example.HamiltonianCycleFinder.satisfiesOresCondition;

public class Main {
    public static void main(String[] args) {
        int n = 10;
        int numPlayers = 3;
        long timeLimitMillis = 500000;
        Game game = new Game(n, numPlayers,timeLimitMillis);
        game.startGame();
        Player winner=game.determineWinner();
        Graph<Token, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        for (Token token: winner.getSequence()) {
            if (!graph.containsVertex(token)) {
                graph.addVertex(token);
            }
        }

        for (int i = 0; i < winner.getSequence().size() - 1; i++) {
            Token currentToken = winner.getSequence().get(i);
            Token nextToken = winner.getSequence().get(i + 1);
            graph.addEdge(currentToken, nextToken);
            DefaultEdge edge = graph.getEdge(currentToken, nextToken);
            if (edge == null) {
                System.err.println("Error: Edge not present in sequence: " + currentToken + " -> " + nextToken);
            }
        }

        boolean oresConditionSatisfied = satisfiesOresCondition(graph);
        System.out.println("Graph satisfies Ore's condition: " + oresConditionSatisfied);

        if (oresConditionSatisfied) {
            HierholzerEulerianCycle<Token, DefaultEdge> eulerianCycleFinder = new HierholzerEulerianCycle<>();
            GraphPath<Token, DefaultEdge> eulerianPath = eulerianCycleFinder.getEulerianCycle(graph);

            if (eulerianPath != null) {
                System.out.println("Hamiltonian Cycle: " + eulerianPath.getVertexList());
            } else {
                System.out.println("Hamiltonian cycle not found.");
            }
        }
    }
    }
