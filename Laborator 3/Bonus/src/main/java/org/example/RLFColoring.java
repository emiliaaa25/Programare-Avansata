package org.example;

import java.time.LocalDate;
import java.util.*;

public class RLFColoring {
    private Map<LocalDate, List<Attraction>> plan;

    public RLFColoring(Map<LocalDate, List<Attraction>> plan) {
        this.plan = plan;
    }

    public void colorGraph(List<Attraction> attractions) {
        Map<Attraction, Integer> colorAssigned = new HashMap<>();
        Set<Integer> usedColors = new HashSet<>();

        attractions.sort(Comparator.comparingInt(attraction -> getConflictingAttractions(attraction).size()));

        for (Attraction attraction : attractions) {
            Set<Integer> neighborColors = new HashSet<>();
            for (Attraction neighbor : getConflictingAttractions(attraction)) {
                neighborColors.add(colorAssigned.getOrDefault(neighbor, -1));
            }
            int color;
            for (color = 0; usedColors.contains(color); color++) ;
            colorAssigned.put(attraction, color);
            usedColors.add(color);
            plan.put(getEarliestAvailableDate(attraction), Arrays.asList(attraction));
            printTripSchedule(getEarliestAvailableDate(attraction),attractions,colorAssigned);
        }

    }

    public void printTripSchedule(LocalDate date, List<Attraction> attractionsForDay, Map<Attraction, Integer> colorAssigned) {
        System.out.println("Date: " + date);
        for (Attraction attraction : attractionsForDay) {
            int color = colorAssigned.getOrDefault(attraction, -1);
            System.out.println("Visited Attraction: " + attraction.getTitle() + " - Color: " + color);
        }
        System.out.println();
    }

    private LocalDate getEarliestAvailableDate(Attraction attraction) {
        for (LocalDate date : plan.keySet()) {
            boolean canVisit = true;
            for (Attraction scheduledAttraction : plan.get(date)) {
                if (!attraction.canVisit(scheduledAttraction)) {
                    canVisit = false;
                    break;
                }
            }
            if (canVisit) {
                return date;
            }
        }
        return null;
    }

    private List<Attraction> getConflictingAttractions(Attraction attraction) {
        List<Attraction> conflictingAttractions = new ArrayList<>();
        for (List<Attraction> attractions : plan.values()) {
            for (Attraction scheduledAttraction : attractions) {
                if (!attraction.equals(scheduledAttraction) && !attraction.canVisit(scheduledAttraction)) {
                    conflictingAttractions.add(scheduledAttraction);
                }
            }
        }
        return conflictingAttractions;
    }
}
