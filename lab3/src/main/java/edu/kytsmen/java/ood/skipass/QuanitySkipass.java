package edu.kytsmen.java.ood.skipass;

import edu.kytsmen.java.ood.skipass.types.DayOfWeekType;
import edu.kytsmen.java.ood.skipass.types.LimitedLiftType;
import edu.kytsmen.java.ood.utils.SkipassType;

/**
 * Created by dkytsmen on 10/6/16.
 */
public class QuanitySkipass extends Skipass {
    private final LimitedLiftType liftType;
    private final DayOfWeekType dayType;
    private int liftAmount;

    public QuanitySkipass(long id, LimitedLiftType liftType, DayOfWeekType dayType, SkipassType type) {
        super(id, type);
        this.liftType = liftType;
        this.dayType = dayType;
        this.liftAmount = liftType.getAmount();
    }

    @Override
    public boolean isEligible() {
        if (!isExpired() && isCorrectDay()) {
            liftAmount--;
            return true;
        }
        return false;
    }

    @Override
    public int getLiftsAmount() {
        return liftType.getAmount() - liftAmount;
    }

    private boolean isExpired() {
        return liftAmount == 0;
    }

    private boolean isCorrectDay() {
        return dayType.isAppropriateDay(getNow().getDayOfWeek());
    }
}
