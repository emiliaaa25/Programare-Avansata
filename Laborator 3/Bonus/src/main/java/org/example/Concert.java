package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Concert extends Attraction implements Payable {

    private String title;
    private float ticket;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Concert(String title, float ticket) {
        super(title);
        this.title = title;
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
    public boolean canVisit(Attraction other) {
        return false;}
}
