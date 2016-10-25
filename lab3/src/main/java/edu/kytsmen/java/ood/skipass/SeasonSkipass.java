package edu.kytsmen.java.ood.skipass;


import edu.kytsmen.java.ood.SkipassType;
import edu.kytsmen.java.ood.skipass.types.SeasonType;

import java.time.LocalDate;
import java.time.Year;

/**
 * Created by dkytsmen on 9/20/16.
 */
public class SeasonSkipass extends Skipass {
    private final LocalDate fromPeriod;
    private final LocalDate toPeriod;
    private int liftsAmount;

    SeasonSkipass(int id, SeasonType seasonType, SkipassType type) {
        super(id, type);
        Year now = Year.now();
        this.fromPeriod = seasonType.getFrom().atYear(now.getValue());
        this.toPeriod = seasonType.getTo().atYear(now.getValue());
    }

    @Override
    public boolean isEligible() {
        if (isRightPeriod()) {
            liftsAmount++;
            return true;
        }
        return false;
    }

    @Override
    public int getLiftsAmount() {
        return liftsAmount;
    }

    private boolean isRightPeriod() {
        LocalDate now = LocalDate.now();
        return now.isAfter(fromPeriod) && now.isBefore(toPeriod);
    }
}
