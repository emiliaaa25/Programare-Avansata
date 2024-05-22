package org.example;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.HamiltonianCycleAlgorithm;
import org.jgrapht.alg.tour.HeldKarpTSP;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.List;
import java.util.Random;

public class Bonus {
    public static void main(String[] args) {
        System.out.println("Bonus");
        createScheduale(6, 3, 3);
    }

    public static void createScheduale(int n, int d, int p) {
        int[][] matrice = new int[n + 1][n + 1];
        int i, j;

        for (i = 1; i <= n; ++i)
            for (j = 1; j <= n; ++j)
                matrice[i][j] = 0;

        for (i = 1; i <= d; ++i) {
            for (j = 1; j <= n; ++j)
                for (int k = 1; k <= n; ++k)
                    if (matrice[j][0] < p && matrice[0][k] < p && j != k && matrice[j][k] == 0) {
                        matrice[j][k] = i;
                        matrice[j][0]++;
                        matrice[0][k]++;
                    }


            for (j = 1; j <= n; ++j)
                for (int k = 1; k <= n; ++k)
                    matrice[0][k] = matrice[j][0] = 0;
        }

        for (i = 1; i <= n; ++i)
            for (j = 1; j <= n; ++j)
                if (i != j && matrice[i][j] == 0) {
                    System.out.println("Nu se poate crea un program cu aceste date!");
                    return;
                }

        Random rand = new Random();
        int [][]matrice2 = new int[n + 1][n + 1];
        for (i = 1; i <= n; ++i)
            for (j = 1; j <= i - 1; ++j)
                if (rand.nextInt(2) == 0) {
                    matrice2[i][j] = matrice2[j][i] = i;
                } else {
                    matrice2[i][j] = matrice2[j][i] = j;
                }

        for (int zi = 1; zi <= d; ++zi) {
            System.out.println("ZIUA " + zi);
            for (i = 1; i <= n; ++i) {
                for (j = 1; j <= i; ++j)
                    if (zi == matrice[i][j])
                        System.out.println("| Player " + i + " vs Player " + j + " Winner: " + matrice2[i][j] + " | ");
            }
            System.out.println();
        }

        Graph<String, DefaultWeightedEdge> graph = new DefaultDirectedGraph<>(DefaultWeightedEdge.class);
        for (Integer ii = 1; ii <= n; ++ii)
            graph.addVertex(ii.toString());
        for(Integer ii = 1; ii <= n; ++ ii)
        {
            for(Integer jj = 1; jj <=ii - 1; ++ jj)
                if(matrice2[ii][jj] == ii)
                    graph.addEdge(ii.toString(), jj.toString());
                else
                    graph.addEdge(jj.toString(), ii.toString());

        }
        HamiltonianCycleAlgorithm<String, DefaultWeightedEdge> tsp = new HeldKarpTSP<>();
        List<String> hamiltonianCycle = tsp.getTour(graph).getVertexList();

        // Print the Hamiltonian cycle
        if (hamiltonianCycle != null && !hamiltonianCycle.isEmpty()) {
            System.out.println("Secventa de jucatiri: ");
            String vertex2 = null;
            for (String vertex : hamiltonianCycle) {
                if(vertex2 != null)
                    System.out.print("P" + vertex2 + ",");
                vertex2 = vertex;
            }
        } else {
            System.out.println("Nu putem gasi o decventa de jucatori valida!");
        }

    }

}
