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

            boolean overlapsExist = checkForOverlaps(attractionsForDay);

            int color = 0;
            if (overlapsExist) {
                for (Attraction attraction : attractionsForDay) {
                    Set<Integer> neighborColors = new HashSet<>();
                    for (Attraction conflictingAttraction : attractionsForDay) {
                        Integer neighborColor = colorAssigned.get(conflictingAttraction);
                        if (neighborColor != null) {
                            neighborColors.add(neighborColor);
                        }
                    }

                    color = 0;
                    while (neighborColors.contains(color) || usedColors.contains(color)) {
                        color++;
                    }

                    colorAssigned.put(attraction, color);
                    usedColors.add(color);


                    for (Attraction attractionNew: checkForOverlapsAttractions(attractionsForDay))
                    {colorAssigned.put(attraction, color);
                    usedColors.add(color);}
                }
            } else {
                for (Attraction attraction : attractionsForDay) {
                    colorAssigned.put(attraction, 0);
                }
            }

            printTripSchedule(date, attractionsForDay, colorAssigned);
        }
    }


    private boolean checkForOverlaps(List<Attraction> attractionsForDay) {
        for (int i = 0; i < attractionsForDay.size(); i++) {
            for (int j = i + 1; j < attractionsForDay.size()-1; j++) {
                Attraction attraction1 = attractionsForDay.get(i);
                Attraction attraction2 = attractionsForDay.get(j);
                if (attraction1.canVisit(attraction2) || attraction2.canVisit(attraction1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Attraction> checkForOverlapsAttractions(List<Attraction> attractionsForDay) {
        List<Attraction> attractions=new ArrayList<>();
        for (int i = 0; i < attractionsForDay.size(); i++) {
            for (int j = i + 1; j < attractionsForDay.size()-1; j++) {
                Attraction attraction1 = attractionsForDay.get(i);
                Attraction attraction2 = attractionsForDay.get(j);
                if (attraction1.canVisit(attraction2) || attraction2.canVisit(attraction1)) {
                    attractions.add(attraction1);
                    attractions.add(attraction2);
                }
            }
        }
        return attractions;
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
