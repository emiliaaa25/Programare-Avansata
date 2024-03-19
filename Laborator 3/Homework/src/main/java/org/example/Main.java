package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<LocalDate, TimeInterval<LocalTime>> churchTimetable = new HashMap<>();
        churchTimetable.put(LocalDate.of(2024, 3, 18), new TimeInterval<>(LocalTime.of(9, 0), LocalTime.of(17, 0)));

        Map<LocalDate, TimeInterval<LocalTime>> statueTimetable = new HashMap<>();
        statueTimetable.put(LocalDate.of(2024, 6, 20), new TimeInterval<>(LocalTime.of(18, 0), LocalTime.of(23, 0)));

        Map<LocalDate, TimeInterval<LocalTime>> museumTimetable = new HashMap<>();
        museumTimetable.put(LocalDate.of(2024, 10, 25), new TimeInterval<>(LocalTime.of(10, 0), LocalTime.of(17, 0)));

        Church church = new Church("Peace Church", churchTimetable);
        Statue statue = new Statue("Liberty Statue", statueTimetable, 10F);
        Museum museum = new Museum("New York's museum", museumTimetable);
        Concert concert = new Concert("Rock Concert", 25.0F);

        List<Attraction> attractions = new ArrayList<>();
        attractions.add(church);
        attractions.add(concert);
        attractions.add(statue);
        attractions.add(museum);
        Trip trip = new Trip("New York City");
        trip.setAttractions(attractions);
        trip.visitableAttractions();

        TravelPlan travelPlan = new TravelPlan();
        travelPlan.addVisit(church, LocalDate.of(2024, 3, 18));
        travelPlan.addVisit(statue, LocalDate.of(2024, 3, 20));
        travelPlan.addVisit(concert, LocalDate.of(2024, 3, 22));
        travelPlan.addVisit(museum, LocalDate.of(2024, 3, 22));

        travelPlan.printTravelPlan();
    }
}