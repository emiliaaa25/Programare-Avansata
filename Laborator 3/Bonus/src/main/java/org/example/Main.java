package org.example;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<LocalDate, TimeInterval<LocalTime>> churchTimetable1 = new HashMap<>();
        churchTimetable1.put(LocalDate.of(2024, 3, 18), new TimeInterval<>(LocalTime.of(9, 0), LocalTime.of(17, 0)));

        Map<LocalDate, TimeInterval<LocalTime>> churchTimetable2 = new HashMap<>();
        churchTimetable2.put(LocalDate.of(2024, 3, 18), new TimeInterval<>(LocalTime.of(10, 0), LocalTime.of(16, 0)));

        Map<LocalDate, TimeInterval<LocalTime>> churchTimetable3 = new HashMap<>();
        churchTimetable3.put(LocalDate.of(2024, 3, 18), new TimeInterval<>(LocalTime.of(17, 30), LocalTime.of(21, 30)));

        Map<LocalDate, TimeInterval<LocalTime>> churchTimetable4 = new HashMap<>();
        churchTimetable4.put(LocalDate.of(2024, 3, 18), new TimeInterval<>(LocalTime.of(11, 0), LocalTime.of(15, 0)));

        Map<LocalDate, TimeInterval<LocalTime>> museumTimetable1 = new HashMap<>();
        museumTimetable1.put(LocalDate.of(2024, 3, 22), new TimeInterval<>(LocalTime.of(10, 0), LocalTime.of(17, 0)));

        Map<LocalDate, TimeInterval<LocalTime>> museumTimetable2 = new HashMap<>();
        museumTimetable2.put(LocalDate.of(2024, 3, 23), new TimeInterval<>(LocalTime.of(9, 0), LocalTime.of(18, 0)));

        Map<LocalDate, TimeInterval<LocalTime>> museumTimetable3 = new HashMap<>();
        museumTimetable3.put(LocalDate.of(2024, 3, 24), new TimeInterval<>(LocalTime.of(11, 0), LocalTime.of(16, 0)));

        Map<LocalDate, TimeInterval<LocalTime>> museumTimetable4 = new HashMap<>();
        museumTimetable4.put(LocalDate.of(2024, 3, 25), new TimeInterval<>(LocalTime.of(10, 0), LocalTime.of(17, 0)));

        Map<LocalDate, TimeInterval<LocalTime>> museumTimetable5 = new HashMap<>();
        museumTimetable5.put(LocalDate.of(2024, 3, 26), new TimeInterval<>(LocalTime.of(9, 30), LocalTime.of(16, 30)));

        Map<LocalDate, TimeInterval<LocalTime>> statueTimetable1 = new HashMap<>();
        statueTimetable1.put(LocalDate.of(2024, 6, 20), new TimeInterval<>(LocalTime.of(18, 0), LocalTime.of(23, 0)));

        Map<LocalDate, TimeInterval<LocalTime>> statueTimetable2 = new HashMap<>();
        statueTimetable2.put(LocalDate.of(2024, 6, 21), new TimeInterval<>(LocalTime.of(17, 0), LocalTime.of(22, 0)));

        Map<LocalDate, TimeInterval<LocalTime>> statueTimetable3 = new HashMap<>();
        statueTimetable3.put(LocalDate.of(2024, 6, 22), new TimeInterval<>(LocalTime.of(16, 0), LocalTime.of(21, 0)));

        Map<LocalDate, TimeInterval<LocalTime>> statueTimetable4 = new HashMap<>();
        statueTimetable4.put(LocalDate.of(2024, 6, 23), new TimeInterval<>(LocalTime.of(15, 0), LocalTime.of(20, 0)));

        Map<LocalDate, TimeInterval<LocalTime>> statueTimetable5 = new HashMap<>();
        statueTimetable5.put(LocalDate.of(2024, 6, 24), new TimeInterval<>(LocalTime.of(14, 0), LocalTime.of(19, 0)));

        Church church1 = new Church("St. Patrick's Cathedral", churchTimetable1);
        Church church2 = new Church("St. John the Divine", churchTimetable2);
        Church church3 = new Church("Trinity Church", churchTimetable3);
        Church church4 = new Church("St. Paul's Chapel", churchTimetable4);

        Concert concert1 = new Concert("Pop Music Concert", 300F);
        Concert concert2 = new Concert("Jazz Night", 500F);
        Concert concert3 = new Concert("Classical Music Performance", 400F);

        Museum museum1 = new Museum("Metropolitan Museum of Art", museumTimetable1);
        Museum museum2 = new Museum("American Museum of Natural History", museumTimetable2);
        Museum museum3 = new Museum("Museum of Modern Art", museumTimetable3);
        Museum museum4 = new Museum("Guggenheim Museum", museumTimetable4);
        Museum museum5 = new Museum("Intrepid Sea, Air & Space Museum", museumTimetable5);

        Statue statue1 = new Statue("Statue of Liberty", statueTimetable1, 100F);
        Statue statue2 = new Statue("Charging Bull", statueTimetable2, 150F);
        Statue statue3 = new Statue("Alice in Wonderland Statue", statueTimetable3, 10F);
        Statue statue4 = new Statue("The Sphere", statueTimetable4, 3F);
        Statue statue5 = new Statue("Atlas Statue", statueTimetable5, 100F);

        List<Attraction> attractions = new ArrayList<>();
        attractions.add(church1);
        attractions.add(church2);
        attractions.add(church3);
        attractions.add(church4);
        attractions.add(concert1);
        attractions.add(concert2);
        attractions.add(concert3);
        attractions.add(museum1);
        attractions.add(museum2);
        attractions.add(museum3);
        attractions.add(museum4);
        attractions.add(museum5);
        attractions.add(statue1);
        attractions.add(statue2);
        attractions.add(statue3);
        attractions.add(statue4);
        attractions.add(statue5);

        Trip trip = new Trip("New York City");
        trip.setAttractions(attractions);
        trip.visitableAttractions();

        TravelPlan travelPlan = new TravelPlan();
        travelPlan.addVisit(church1, LocalDate.of(2024, 3, 18));
        travelPlan.addVisit(church2, LocalDate.of(2024, 3, 18));
        travelPlan.addVisit(church3, LocalDate.of(2024, 3, 18));
        travelPlan.addVisit(church4, LocalDate.of(2024, 3, 18));
        travelPlan.addVisit(concert1, LocalDate.of(2024, 3, 22));
        travelPlan.addVisit(concert2, LocalDate.of(2024, 3, 23));
        travelPlan.addVisit(concert3, LocalDate.of(2024, 3, 25));
        travelPlan.addVisit(museum1, LocalDate.of(2024, 3, 22));
        travelPlan.addVisit(museum2, LocalDate.of(2024, 3, 23));
        travelPlan.addVisit(museum3, LocalDate.of(2024, 3, 24));
        travelPlan.addVisit(museum4, LocalDate.of(2024, 3, 25));
        travelPlan.addVisit(museum5, LocalDate.of(2024, 3, 26));
        travelPlan.addVisit(statue1, LocalDate.of(2024, 6, 20));
        travelPlan.addVisit(statue2, LocalDate.of(2024, 6, 21));
        travelPlan.addVisit(statue3, LocalDate.of(2024, 6, 22));
        travelPlan.addVisit(statue4, LocalDate.of(2024, 6, 23));
        travelPlan.addVisit(statue5, LocalDate.of(2024, 6, 24));
        travelPlan.printTravelPlan();

        GreedyColoring greedyColoring = new GreedyColoring(travelPlan.getPlan());
        greedyColoring.planTripGreedy();

        RLFColoring rlfColoring = new RLFColoring(travelPlan.getPlan());
       // rlfColoring.colorGraph(attractions);
        //travelPlan.printTravelPlan();
    }



}

