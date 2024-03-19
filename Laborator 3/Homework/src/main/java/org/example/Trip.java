package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Trip {
    private String city;

    private List<Attraction> attractions;

    Trip(String city){
        this.city=city;
        this.attractions = new ArrayList<>();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public void addAttraction(Attraction a) {
        attractions.add(a);
    }

    public void visitableAttractions(){
        List<Visitable> visitable=new ArrayList<>();
        for (Attraction attraction: attractions)
            if(attraction instanceof Visitable&&!(attraction instanceof Payable))
                visitable.add((Visitable) attraction);

        visitable.sort(Comparator.comparing(this::getOpeningTime));

        System.out.println("Visitable locations: ");
        for (Visitable location : visitable) {
            System.out.println(location.getTitle()+ " opens at " + getOpeningTime(location));
        }
    }

    private LocalTime getOpeningTime(Visitable location) {
        Map<LocalDate, TimeInterval<LocalTime>> visitingTimeTable = location.getTimeTable();
        if (!visitingTimeTable.isEmpty()) {
            TimeInterval<LocalTime> time = visitingTimeTable.values().iterator().next();
            return time.getStartVisit();
        }
        return null;
    }


    @Override
    public String toString(){
        StringBuilder result=new StringBuilder();
        result.append("I m going to ").append(city).append("and I'm visiting: ");
        for(Attraction attraction: attractions)
            result.append(attraction).append(", ");
        return result.toString();
    }

}
