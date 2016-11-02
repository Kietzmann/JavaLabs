package edu.kytsmen.java.ood.skipass;


import edu.kytsmen.java.ood.utils.SkipassType;
import edu.kytsmen.java.ood.skipass.types.SeasonType;

import java.time.LocalDate;
import java.time.Year;

/**
 * Created by dkytsmen on 9/20/16.
 */
public class SeasonSkipass extends Skipass {
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private int liftsAmount;

    SeasonSkipass(long id, SeasonType seasonType, SkipassType type) {
        super(id, type);
        Year now = Year.now();
        this.fromDate = seasonType.getFrom().atYear(now.getValue());
        this.toDate = seasonType.getTo().atYear(now.getValue());
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
        LocalDate now = getNow().toLocalDate();
        return now.isAfter(fromDate) && now.isBefore(toDate);
    }
}
