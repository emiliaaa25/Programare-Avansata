package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Concert extends Attraction implements Payable {

    private String title;
    private float ticket;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean canVisit(Attraction scheduledAttraction) {
        return false;
    }

    public Concert(String title, float ticket) {
        super(title);
        this.title = title;
        this.ticket = ticket;
    }

    public float getTicket() {
        return ticket;
    }

    public void setTicket(float ticket) {
        this.ticket = ticket;
    }

    @Override
    public void setPrice(float price) {
        ticket = price;
    }

    @Override
    public float getPrice() {
        return ticket;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(title);
        return result.toString();
    }

    @Override
    public LocalTime getVisit() {
        return LocalTime.of(22, 0);
    }

    @Override
    public Map<LocalDate, TimeInterval<LocalTime>> getTimeTable() {
        Random random = new Random();
        TimeInterval<LocalTime> timeInterval = new TimeInterval<>(
                LocalTime.of(21, 00),
                LocalTime.of(23, 00)
        );


        Map<LocalDate, TimeInterval<LocalTime>> timetable = new HashMap<>();
        timetable.put(LocalDate.of(2024, 3, 20), timeInterval);

        return timetable;
    }
}
