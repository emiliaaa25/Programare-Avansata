package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Attraction implements Comparable<Attraction> {
    private String title;
    private LocalDate date;
    private Map<LocalDate, Attraction> plan;


    public Attraction(String title) {
        this.title = title;
    }

    public Attraction(String title, LocalDate date) {
        this.title = title;
        this.date = date;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int compareTo(Attraction o) {
        return this.title.compareTo(o.title);
    }


    public abstract boolean canVisit(Attraction scheduledAttraction);

    public LocalDate getDate() {
        return date;
    }


}
