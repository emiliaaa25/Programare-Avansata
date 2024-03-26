package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Church extends Attraction implements Visitable {

    private String title;
    private Map<LocalDate, TimeInterval<LocalTime>> visitingTable;

    public Church(String title, Map<LocalDate, TimeInterval<LocalTime>> visitingTable) {
        super(title);
        this.visitingTable = visitingTable;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
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
            for (LocalDate thisDate : visitingTable.keySet()) {
                TimeInterval<LocalTime> thisTimeInterval = visitingTable.get(thisDate);
                for (LocalDate otherDate : ((Visitable) other).getTimeTable().keySet()) {
                    TimeInterval<LocalTime> otherTimeInterval = ((Visitable) other).getTimeTable().get(otherDate);
                    if (!thisDate.equals(otherDate) && (thisTimeInterval.getStartVisit().isBefore(otherTimeInterval.getEndVisit()) && thisTimeInterval.getEndVisit().isAfter(otherTimeInterval.getStartVisit())) ||
                            (otherTimeInterval.getStartVisit().isBefore(thisTimeInterval.getEndVisit()) && otherTimeInterval.getEndVisit().isAfter(thisTimeInterval.getStartVisit()))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public LocalTime getVisit() {
        for (LocalDate date : visitingTable.keySet()) {
            // Obținem intervalul de timp asociat datei
            TimeInterval<LocalTime> timeInterval = visitingTable.get(date);
            // Returnăm ora de început a vizitei
            return timeInterval.getStartVisit();
        }
        // Dacă nu există nicio dată în tabel, returnăm null sau o altă valoare semnificativă
        return null;
    }


}
