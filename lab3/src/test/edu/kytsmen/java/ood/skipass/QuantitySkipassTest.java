package edu.kytsmen.java.ood.skipass;

import edu.kytsmen.java.ood.skipass.types.LimitedLiftType;
import edu.kytsmen.java.ood.utils.SkiPassRequest;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static edu.kytsmen.java.ood.skipass.SkipassFactory.SKIPASS_FACTORY;
import static edu.kytsmen.java.ood.skipass.types.DayOfWeekType.WORKDAYS;
import static java.time.LocalDateTime.of;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by dkytsmen on 11/2/16.
 */
public class QuantitySkipassTest extends Assert {
    private static final LocalDateTime MONDAY_MORNING = of(2016, Month.NOVEMBER, 7, 9, 0, 0);

    @Test
    public void testExpiration() {
        Skipass skipass = spy(SKIPASS_FACTORY.getSkipass(new SkiPassRequest(LimitedLiftType.TEN, WORKDAYS)));
        when(skipass.getNow()).thenReturn(MONDAY_MORNING);
        for (int i = 0; i < LimitedLiftType.TEN.getAmount(); i++) {
            skipass.isEligible();
        }

        assertFalse(skipass.isEligible());
    }

    @Test
    public void testWrongDay() {
        Skipass skipass = spy(SKIPASS_FACTORY.getSkipass(new SkiPassRequest(LimitedLiftType.TEN, WORKDAYS)));
        when(skipass.getNow()).thenReturn(MONDAY_MORNING.minusDays(1));
        assertFalse(skipass.isEligible());
    }
}
