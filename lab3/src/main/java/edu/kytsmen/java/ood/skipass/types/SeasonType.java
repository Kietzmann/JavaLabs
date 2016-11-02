package edu.kytsmen.java.ood.skipass.types;

import java.time.Month;
import java.time.MonthDay;

/**
 * Created by dkytsmen on 10/6/16.
 */
public enum SeasonType {
    SPRING(MonthDay.of(Month.MARCH, 1), MonthDay.of(Month.MAY, 1)),
    SUMMER(MonthDay.of(Month.JULY, 1), MonthDay.of(Month.AUGUST, 31)),
    AUTUMN(MonthDay.of(Month.SEPTEMBER, 1), MonthDay.of(Month.NOVEMBER, 30)),
    WINTER(MonthDay.of(Month.DECEMBER, 1), MonthDay.of(Month.FEBRUARY, 28));

    private final transient MonthDay from;
    private final transient MonthDay to;

    SeasonType(MonthDay from, MonthDay to) {
        this.from = from;
        this.to = to;
    }

    public MonthDay getFrom() {
        return from;
    }

    public MonthDay getTo() {
        return to;
    }
}
