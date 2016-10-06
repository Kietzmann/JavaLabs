package edu.kytsmen.java.ood.skipass;

import edu.kytsmen.java.ood.SkipassType;
import edu.kytsmen.java.ood.skipass.types.DayOfWeekType;
import edu.kytsmen.java.ood.skipass.types.LimitedLiftType;

import java.time.LocalDate;

/**
 * Created by dkytsmen on 10/6/16.
 */
public class QuanitySkipass extends Skipass {
    private final LimitedLiftType liftType;
    private final DayOfWeekType dayType;
    private int liftAmmount;

    public QuanitySkipass(int id, LimitedLiftType liftType, DayOfWeekType dayType, SkipassType type) {
        super(id, type);
        this.liftType = liftType;
        this.dayType = dayType;
        this.liftAmmount = liftType.getAmount();
    }

    @Override
    public boolean isEligible() {
        if (!isExpired() && isCorrectDay()) {
            liftAmmount--;
            return true;
        }
        return false;
    }

    private boolean isExpired() {
        return liftAmmount == 0;
    }

    private boolean isCorrectDay() {
        return dayType.isAppropriateDay(LocalDate.now().getDayOfWeek());
    }
}
