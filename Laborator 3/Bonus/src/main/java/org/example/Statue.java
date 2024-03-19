package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Statue extends Attraction implements Payable,Visitable {
    private String title;
    private float ticket;
    private Map<LocalDate, TimeInterval<LocalTime>> visitingTable;

    public Statue(String title, Map<LocalDate, TimeInterval<LocalTime>> visitingTable,float ticket) {
        super(title);
        this.visitingTable=visitingTable;
        this.ticket=ticket;
    }

    public float getTicket() {
        return ticket;
    }

    public void setTicket(float ticket) {
        this.ticket = ticket;
    }

    @Override
    public void setPrice(float price) {
        ticket=price;
    }

    @Override
    public float getPrice() {
        return ticket;
    }

    @Override
    public String toString(){
        StringBuilder result=new StringBuilder();
        result.append(title);
        return result.toString();
    }

    @Override
    public Map<LocalDate, TimeInterval<LocalTime>> getTimeTable() {
        return visitingTable;
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    public boolean canVisit(Attraction other) {
        if (other instanceof Visitable) {
            for (LocalDate date : visitingTable.keySet()) {
                TimeInterval<LocalTime> thisTimeInterval = visitingTable.get(date);
                Map<LocalDate, TimeInterval<LocalTime>> otherTimetable = ((Visitable) other).getTimeTable();

                for (LocalDate otherDate : otherTimetable.keySet()) {
                    TimeInterval<LocalTime> otherTimeInterval = otherTimetable.get(otherDate);

                    if (thisTimeInterval.overlaps(otherTimeInterval)) {
                        return false; }
                }
            }
        }
        return true;
    }
}