package org.example;

import java.time.LocalDate;
import java.util.*;

public class TravelPlan {
    private Map<LocalDate, List<Attraction>> plan;

    public TravelPlan() {
        this.plan = new HashMap<>();
    }

    public void addVisit(Attraction attraction, LocalDate date) {
        if (plan.containsKey(date)) {
            plan.get(date).add(attraction);
        } else {
            List<Attraction> attractions = new ArrayList<>();
            attractions.add(attraction);
            plan.put(date, attractions);
        }
    }

    public void printTravelPlan() {
        System.out.println("\nTravel Plan:");
        for (Map.Entry<LocalDate, List<Attraction>> entry : plan.entrySet()) {
            LocalDate date = entry.getKey();
            List<Attraction> attractions = entry.getValue();
            String visitDate = date != null ? date.toString() : "any day";
            for (Attraction attraction : attractions) {
                System.out.println( attraction.getTitle()+" on "+visitDate);
            }
        }
    }

    public Map<LocalDate, List<Attraction>> getPlan() {
        return plan;
    }
}
