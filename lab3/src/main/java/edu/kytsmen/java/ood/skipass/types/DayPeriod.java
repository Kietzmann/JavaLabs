package edu.kytsmen.java.ood.skipass.types;

import java.time.Duration;
import java.time.temporal.TemporalAmount;

/**
 * Created by dkytsmen on 10/6/16.
 */
public enum DayPeriod {
    FIRST_HALF(Duration.ofHours(9), Duration.ofHours(13)),
    SECOND_HALF_DAY(Duration.ofHours(13), Duration.ofHours(18)),
    DAY(Duration.ZERO, Duration.ofDays(1)),
    TWO_DAYS(Duration.ZERO, Duration.ofDays(2)),
    FIVE_DAYS(Duration.ZERO, Duration.ofDays(5));


    private final TemporalAmount start;
    private final TemporalAmount end;

    DayPeriod(TemporalAmount start, TemporalAmount end) {
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
