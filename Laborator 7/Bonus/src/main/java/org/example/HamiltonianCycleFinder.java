package org.example;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class HamiltonianCycleFinder {

    public static boolean satisfiesOresCondition(Graph<Token, DefaultEdge> graph) {
        int n = graph.vertexSet().size();

        for (Token vertex1 : graph.vertexSet()) {
            for (Token vertex2 : graph.vertexSet()) {
                if (!vertex1.equals(vertex2) && !graph.containsEdge(vertex1, vertex2)) {
                    int degreeSum = graph.degreeOf(vertex1) + graph.degreeOf(vertex2);
                    if (degreeSum < n) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
