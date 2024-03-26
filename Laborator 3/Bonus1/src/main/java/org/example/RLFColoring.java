package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class RLFColoring {
    private Map<LocalDate, List<Attraction>> plan;

    public RLFColoring(Map<LocalDate, List<Attraction>> plan) {
        this.plan = plan;
    }

    public void planTripRLF() {
        Map<Attraction, Integer> colorAssigned = new HashMap<>();
        Set<Integer> usedColors = new HashSet<>();

        Random random = new Random();

        for (LocalDate date : plan.keySet()) {
            List<Attraction> attractionsForDay = plan.get(date);

            usedColors.clear();

            attractionsForDay.sort((a1, a2) -> {
                TimeInterval<LocalTime> timeInterval1 = a1.getTimeTable().get(date);
                TimeInterval<LocalTime> timeInterval2 = a2.getTimeTable().get(date);


                if (timeInterval1 != null && timeInterval2 != null) {
                    int duration1 = timeInterval1.getEndVisit().getHour() - timeInterval1.getStartVisit().getHour();
                    int duration2 = timeInterval2.getEndVisit().getHour() - timeInterval2.getStartVisit().getHour();
                    return Integer.compare(duration2, duration1);
                } else {
                    return 0;
                }
            });

            for (Attraction attraction : attractionsForDay) {
                Set<Integer> neighborColors = new HashSet<>();
                for (Attraction neighbor : getAdjacentAttractions(attraction, attractionsForDay)) {
                    Integer neighborColor = colorAssigned.get(neighbor);
                    if (neighborColor != null) {
                        neighborColors.add(neighborColor);
                    }
                }

                int colorAttempts = 0;
                int color;
                do {
                    color = random.nextInt(attractionsForDay.size());
                    colorAttempts++;

                    if (colorAttempts > attractionsForDay.size()) {
                        System.out.println("Unable to find a valid color for attraction: " + attraction.getTitle());
                    }
                } while (neighborColors.contains(color) || usedColors.contains(color));

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
