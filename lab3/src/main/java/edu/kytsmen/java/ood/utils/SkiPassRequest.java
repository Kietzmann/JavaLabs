package edu.kytsmen.java.ood.utils;

import edu.kytsmen.java.ood.skipass.types.DayOfWeekType;
import edu.kytsmen.java.ood.skipass.types.DayPeriodType;
import edu.kytsmen.java.ood.skipass.types.LimitedLiftType;
import edu.kytsmen.java.ood.skipass.types.SeasonType;

/**
 * Created by dkytsmen on 11/2/16.
 */
public class SkiPassRequest {
    private final SeasonType seasonType;
    private final DayOfWeekType dayOfWeekType;
    private final LimitedLiftType liftType;
    private final DayPeriodType periodType;
    private final SkipassType skipassType;

    public SkiPassRequest(LimitedLiftType liftType, DayOfWeekType dayOfWeekType) {
        this.dayOfWeekType = dayOfWeekType;
        this.liftType = liftType;
        this.skipassType = dayOfWeekType == DayOfWeekType.WORKDAYS ? SkipassType.WORKDAYLIFTS : SkipassType.WEEKENDLIFTS;
        this.seasonType = null;
        this.periodType = null;
    }

    public SkiPassRequest(DayPeriodType periodType, DayOfWeekType dayOfWeekType) {
        this.dayOfWeekType = dayOfWeekType;
        this.periodType = periodType;
        this.skipassType = dayOfWeekType == DayOfWeekType.WORKDAYS ? SkipassType.WORKDAYPASS : SkipassType.WEEKENDPASS;
        this.seasonType = null;
        this.liftType = null;
    }

    public SkiPassRequest(SeasonType seasonType) {
        this.seasonType = seasonType;
        this.dayOfWeekType = null;
        this.skipassType = SkipassType.SEASONPASS;
        this.liftType = null;
        this.periodType = null;
    }

    public SeasonType getSeasonType() {
        return seasonType;
    }

    public DayOfWeekType getDayOfWeekType() {
        return dayOfWeekType;
    }

    public LimitedLiftType getLiftType() {
        return liftType;
    }

    public DayPeriodType getPeriodType() {
        return periodType;
    }

    public SkipassType getSkipassType() {
        return skipassType;
    }
}
