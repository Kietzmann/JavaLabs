package edu.kytsmen.java.ood.turnstile;


import edu.kytsmen.java.ood.skipass.Skipass;
import edu.kytsmen.java.ood.skipass.SkipassFactory;
import edu.kytsmen.java.ood.statistic.ProcessResult;
import edu.kytsmen.java.ood.utils.SkipassType;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by dkytsmen on 9/20/16.
 */
public class Turnstile {

    private Map<SkipassType, ProcessResult> statistics = new EnumMap<>(SkipassType.class);
    private SkipassFactory factory = SkipassFactory.SKIPASS_FACTORY;

    public boolean processSkipass(Skipass skipass) {
        boolean isExist = factory.checkId(skipass.getUID());
        if (isExist && !skipass.isBlocked()) {
            boolean isPassed = skipass.isEligible();
            saveResult(isPassed, skipass.getPassType());
            return isPassed;
        } else {
            saveResult(false, skipass.getPassType());
            return false;
        }
    }


    public void saveResult(boolean isPassed, SkipassType skipassType) {
        if (statistics.containsKey(skipassType)) {
            ProcessResult result = statistics.get(skipassType);
            result.incrementCounter(isPassed);
        } else {
            ProcessResult result = new ProcessResult();
            result.incrementCounter(isPassed);
            statistics.put(skipassType, result);
        }
    }

    public ProcessResult getStatisticsByType(SkipassType skipassType) {
        return statistics.get(skipassType);
    }

    public ProcessResult getGlobalStatistics() {
        ProcessResult total = new ProcessResult();
        for (ProcessResult result : statistics.values()) {
            total = total.plus(result);
        }
        return total;
    }
}
