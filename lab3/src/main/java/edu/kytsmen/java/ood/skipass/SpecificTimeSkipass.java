package edu.kytsmen.java.ood.skipass;

import edu.kytsmen.java.ood.utils.SkipassType;
import edu.kytsmen.java.ood.skipass.types.DayOfWeekType;
import edu.kytsmen.java.ood.skipass.types.DayPeriodType;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;

/**
 * Created by dkytsmen on 11/2/16.
 */
public class SpecificTimeSkipass extends Skipass {
    private final DayPeriodType periodType;
    private final DayOfWeekType dayOfWeekType;
    private int liftAmount;
    private LocalDateTime activationTime;

    public SpecificTimeSkipass(long id, SkipassType passType, DayPeriodType periodType, DayOfWeekType dayOfWeekType) {
        super(id, passType);
        this.periodType = periodType;
        this.dayOfWeekType = dayOfWeekType;
    }

    private boolean isRightDate() {
        return dayOfWeekType.isAppropriateDay(getNow().getDayOfWeek());
    }

    @Override
    public boolean isEligible() {
        if (activationTime == null) {
            TemporalAmount start = periodType.getStart();
            TemporalAmount end = periodType.getEnd();
            LocalDateTime toDate = LocalDateTime.of(getNow().toLocalDate(), LocalTime.MIDNIGHT);
            activationTime = toDate.plus(start);
            expireDate = toDate.plus(end);
        }

        if (!tooEarly() && !isExpired() && isRightDate()) {
            liftAmount++;
            return true;
        }
        return false;
    }


    private boolean isExpired() {
        LocalDateTime now = getNow();
        return now.isAfter(expireDate);
    }

    @Override
    public int getLiftsAmount() {
        return liftAmount;
    }

    private boolean tooEarly() {
        LocalDateTime now = getNow();
        return now.isBefore(activationTime);
    }

    public LocalDateTime getActivationTime() {
        return activationTime;
    }
}
