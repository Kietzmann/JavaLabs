package edu.kytsmen.java.ood;

import edu.kytsmen.java.ood.skipass.Skipass;
import edu.kytsmen.java.ood.skipass.SkipassFactory;
import edu.kytsmen.java.ood.skipass.types.DayOfWeekType;
import edu.kytsmen.java.ood.skipass.types.DayPeriodType;
import edu.kytsmen.java.ood.skipass.types.LimitedLiftType;
import edu.kytsmen.java.ood.statistic.ProcessResult;
import edu.kytsmen.java.ood.turnstile.Turnstile;
import edu.kytsmen.java.ood.utils.SkiPassRequest;
import edu.kytsmen.java.ood.utils.SkipassType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by dkytsmen on 11/2/16.
 */
public class TurnstileTest extends Assert {
    private static final LocalDateTime MONDAY_MORNING = of(2016, Month.NOVEMBER, 7, 9, 0, 0);
    private SkipassFactory factory = SkipassFactory.SKIPASS_FACTORY;
    private Turnstile turnstile;

    @Before
    public void setUp() throws Exception {
        turnstile = new Turnstile();
    }

    @After
    public void cleanAll() throws Exception {
        factory.clear();
    }

    @Test
    public void testBlockedSkipasses() {
        Skipass skipass = factory.getSkipass(new SkiPassRequest(LimitedLiftType.TEN, DayOfWeekType.WORKDAYS));
        skipass.setBlocked(true);
        boolean isPassed = turnstile.processSkipass(skipass);
        assertFalse(isPassed);
    }

    @Test
    public void testNotExistSkiPass() {
        Skipass skipass = factory.getSkipass(new SkiPassRequest(LimitedLiftType.TEN, DayOfWeekType.WORKDAYS));
        factory.removeSkipass(skipass.getUID());
        boolean isPassed = turnstile.processSkipass(skipass);
        assertFalse(isPassed);
    }

    @Test
    public void testStatistics() {
        final long liftsTypeCount = 3;
        final long periodTypeCount = 4;

        for (int i = 0; i < liftsTypeCount; i++) {
            Skipass skipass = factory.getSkipass(new SkiPassRequest(LimitedLiftType.TEN, DayOfWeekType.WORKDAYS));
            turnstile.processSkipass(skipass);
        }

        for (int i = 0; i < periodTypeCount; i++) {
            Skipass skipass = spy(factory.getSkipass(new SkiPassRequest(DayPeriodType.DAY, DayOfWeekType.WORKDAYS)));
            when(skipass.getNow()).thenReturn(MONDAY_MORNING);
            turnstile.processSkipass(skipass);
        }

        ProcessResult liftsStatistics = turnstile.getStatisticsByType(SkipassType.WORKDAYLIFTS);
        ProcessResult periodStatistics = turnstile.getStatisticsByType(SkipassType.WORKDAYPASS);
        ProcessResult totalStatistics = turnstile.getGlobalStatistics();

        assertEquals(liftsTypeCount, liftsStatistics.getAcceptedCounter());
        assertEquals(periodTypeCount, periodStatistics.getAcceptedCounter());
        assertEquals(liftsTypeCount + periodTypeCount, totalStatistics.getAcceptedCounter());
    }
}
