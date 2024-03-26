package org.example;

import java.util.*;
import java.time.LocalDate;

public class GreedyColoring {
    private Map<LocalDate, List<Attraction>> plan;

    public GreedyColoring(Map<LocalDate, List<Attraction>> plan) {
        this.plan = plan;
    }

    public void planTripGreedy() {
        Map<Attraction, Integer> colorAssigned = new HashMap<>();
        Set<Integer> usedColors = new HashSet<>();

        for (LocalDate date : plan.keySet()) {
            List<Attraction> attractionsForDay = plan.get(date);

            usedColors.clear();

            attractionsForDay.sort(Comparator.comparing(Attraction::getVisit));

            for (Attraction attraction : attractionsForDay) {
                Set<Integer> neighborColors = new HashSet<>();
                for (Attraction neighbor : getAdjacentAttractions(attraction, attractionsForDay)) {
                    Integer neighborColor = colorAssigned.get(neighbor);
                    if (neighborColor != null) {
                        neighborColors.add(neighborColor);
                    }
                }

                int color = 0;
                while (neighborColors.contains(color)) {
                    color++;
                }

                colorAssigned.put(attraction, color);
                usedColors.add(color);
            }

            printTripSchedule(date, attractionsForDay, colorAssigned);
        }
    }

    private List<Attraction> getAdjacentAttractions(Attraction attraction, List<Attraction> attractions) {
        List<Attraction> adjacentAttractions = new ArrayList<>();
        for (Attraction other : attractions) {
            if (attraction != other && attraction.canVisit(other)) {
                adjacentAttractions.add(other);
            }
        }
        return adjacentAttractions;
    }

    public void printTripSchedule(LocalDate date, List<Attraction> attractionsForDay, Map<Attraction, Integer> colorAssigned) {
        System.out.println("Date: " + date);
        for (Attraction attraction : attractionsForDay) {
            int color = colorAssigned.getOrDefault(attraction, -1);
            System.out.println("Visited Attraction: " + attraction.getTitle() + " - Color: " + color);
        }
        System.out.println();
    }
}
