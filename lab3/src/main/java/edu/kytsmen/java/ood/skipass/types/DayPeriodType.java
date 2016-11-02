package edu.kytsmen.java.ood.skipass.types;

import java.time.Duration;
import java.time.temporal.TemporalAmount;

/**
 * Created by dkytsmen on 10/6/16.
 */
public enum DayPeriodType {
    FIRST_HALF_DAY(Duration.ofHours(9), Duration.ofHours(14)),
    SECOND_HALF_DAY(Duration.ofHours(14), Duration.ofHours(19)),
    DAY(Duration.ZERO, Duration.ofDays(1)),
    TWO_DAYS(Duration.ZERO, Duration.ofDays(2)),
    FIVE_DAYS(Duration.ZERO, Duration.ofDays(5));


    private final transient TemporalAmount start;
    private final transient TemporalAmount end;

    DayPeriodType(TemporalAmount start, TemporalAmount end) {
        this.start = start;
        this.end = end;
    }

    public TemporalAmount getEnd() {
        return end;
    }

    public TemporalAmount getStart() {
        return start;
    }
}
