package edu.kytsmen.java.ood.skipass.types;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dkytsmen on 10/6/16.
 */
public enum DayOfWeekType {
    WEEKENDS(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),
    WORKDAYS(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);

    private Set<DayOfWeek> dayOfWeekSet;

    DayOfWeekType(DayOfWeek... dayOfWeeks) {
        this.dayOfWeekSet = new HashSet<>(Arrays.asList(dayOfWeeks));
    }

    public boolean isAppropriateDay(DayOfWeek dayOfWeek) {
        return dayOfWeekSet.contains(dayOfWeek);
    }
}
