package edu.kytsmen.java.ood.skipass;

import edu.kytsmen.java.ood.skipass.types.DayOfWeekType;
import edu.kytsmen.java.ood.skipass.types.DayPeriodType;
import edu.kytsmen.java.ood.skipass.types.LimitedLiftType;
import edu.kytsmen.java.ood.skipass.types.SeasonType;
import edu.kytsmen.java.ood.utils.SkiPassRequest;
import edu.kytsmen.java.ood.utils.SkipassType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dkytsmen on 11/2/16.
 */
public enum SkipassFactory {
    SKIPASS_FACTORY;

    private Long counter = 1L;
    private Map<Long, Skipass> skipassMap = new HashMap<>();

    private Long nextId() {
        return counter++;
    }

    public Skipass getSkipass(SkiPassRequest request) {
        if (request.getSeasonType() != null) {
            return getSkipass(request.getSeasonType(), request.getSkipassType());
        } else if (request.getLiftType() != null) {
            return getSkipass(request.getDayOfWeekType(), request.getLiftType(), request.getSkipassType());
        } else {
            return getSkipass(request.getDayOfWeekType(), request.getPeriodType(), request.getSkipassType());
        }
    }

    private Skipass getSkipass(DayOfWeekType dayOfWeekType, LimitedLiftType liftType, SkipassType skipassType) {
        QuanitySkipass skipass = new QuanitySkipass(nextId(), liftType, dayOfWeekType, skipassType);
        skipassMap.put(skipass.getUID(), skipass);
        return skipass;
    }

    private Skipass getSkipass(DayOfWeekType dayOfWeekType, DayPeriodType periodType, SkipassType skipassType) {
        SpecificTimeSkipass skipass = new SpecificTimeSkipass(nextId(), skipassType, periodType, dayOfWeekType);
        skipassMap.put(skipass.getUID(), skipass);
        return skipass;
    }

    private Skipass getSkipass(SeasonType seasonType, SkipassType skipassType) {
        SeasonSkipass skipass = new SeasonSkipass(nextId(), seasonType, skipassType);
        skipassMap.put(skipass.getUID(), skipass);
        return skipass;
    }

    public boolean checkId(Long id) {
        return skipassMap.containsKey(id);
    }

    public void removeSkipass(Long id) {
        skipassMap.remove(id);
    }

    public void clear() {
        skipassMap.clear();
    }
}
