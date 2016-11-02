package edu.kytsmen.java.ood.skipass;

import edu.kytsmen.java.ood.skipass.types.SeasonType;
import edu.kytsmen.java.ood.utils.SkiPassRequest;
import org.junit.Assert;
import org.junit.Test;

import java.time.Month;

import static edu.kytsmen.java.ood.skipass.SkipassFactory.SKIPASS_FACTORY;
import static java.time.LocalDateTime.of;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by dkytsmen on 11/2/16.
 */
public class SeasonSkipassTest extends Assert {

    @Test
    public void testWrongPeriod() {
        Skipass skipass = spy(SKIPASS_FACTORY.getSkipass(new SkiPassRequest(SeasonType.AUTUMN)));
        when(skipass.getNow()).thenReturn(of(2016, Month.MARCH, 7, 9, 0, 0));
        assertFalse(skipass.isEligible());
    }
}
