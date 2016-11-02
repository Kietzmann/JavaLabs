package edu.kytsmen.java.ood.skipass;

import edu.kytsmen.java.ood.utils.SkiPassRequest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;
import java.time.Month;

import static edu.kytsmen.java.ood.skipass.SkipassFactory.SKIPASS_FACTORY;
import static edu.kytsmen.java.ood.skipass.types.DayOfWeekType.WORKDAYS;
import static edu.kytsmen.java.ood.skipass.types.DayPeriodType.FIRST_HALF_DAY;
import static java.time.LocalDateTime.of;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by dkytsmen on 11/2/16.
 */
public class SpecificTimeSkipassTest extends Assert {
    private static final LocalDateTime MONDAY_MORNING = of(2016, Month.NOVEMBER, 7, 9, 0, 0);

    @Test
    public void testLifts() {
        Skipass skipass = spy(SKIPASS_FACTORY.getSkipass(new SkiPassRequest(FIRST_HALF_DAY, WORKDAYS)));
        when(skipass.getNow()).thenReturn(MONDAY_MORNING);
        skipass.isEligible();
        skipass.isEligible();
        skipass.isEligible();
        assertEquals(3, skipass.getLiftsAmount());
    }

    @Test
    public void testExpiration() {
        Skipass skipass = spy(SKIPASS_FACTORY.getSkipass(new SkiPassRequest(FIRST_HALF_DAY, WORKDAYS)));
        when(skipass.getNow()).thenAnswer(new Answer<LocalDateTime>() {
            @Override
            public LocalDateTime answer(InvocationOnMock invocationOnMock) throws Throwable {
                if (skipass.getLiftsAmount() > 0) {
                    return MONDAY_MORNING.plusDays(5);
                }
                return MONDAY_MORNING;
            }
        });

        boolean firstLift = skipass.isEligible();
        boolean secondLift = skipass.isEligible();
        assertTrue(firstLift);
        assertFalse(secondLift);
    }

    @Test
    public void testBeforeActivation() {
        Skipass skipass = spy(SKIPASS_FACTORY.getSkipass(new SkiPassRequest(FIRST_HALF_DAY, WORKDAYS)));
        when(skipass.getNow()).thenReturn(MONDAY_MORNING.minusHours(1));
        assertFalse(skipass.isEligible());
    }

    @Test
    public void testWrongDay() {
        Skipass skipass = spy(SKIPASS_FACTORY.getSkipass(new SkiPassRequest(FIRST_HALF_DAY, WORKDAYS)));
        when(skipass.getNow()).thenReturn(MONDAY_MORNING.minusDays(1));
        assertFalse(skipass.isEligible());
    }
}
