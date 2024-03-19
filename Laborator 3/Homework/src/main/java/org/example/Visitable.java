package org.example;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
public interface Visitable {
    public Map<LocalDate, TimeInterval<LocalTime>> getTimeTable();
    public String getTitle();

    default LocalTime getOpeningHour(LocalDate date) {
        Map<LocalDate, TimeInterval<LocalTime>> visitingTimetable = getTimeTable();
        TimeInterval<LocalTime> timeInterval = visitingTimetable.get(date);
        if(timeInterval != null)
            return timeInterval.getStartVisit();
        return null;
    }

}
