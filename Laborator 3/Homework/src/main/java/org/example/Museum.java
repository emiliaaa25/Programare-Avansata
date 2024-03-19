package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Museum extends Attraction implements Visitable{

    private String title;
    private Map<LocalDate, TimeInterval<LocalTime>> visitingTable;

    public Museum(String title,Map<LocalDate, TimeInterval<LocalTime>> visitingTable) {
        super(title);
        this.visitingTable=visitingTable;
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
}
