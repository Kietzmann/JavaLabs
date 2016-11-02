package edu.kytsmen.java.ood.skipass;

import edu.kytsmen.java.ood.skipass.types.DayOfWeekType;
import edu.kytsmen.java.ood.skipass.types.DayPeriodType;
import edu.kytsmen.java.ood.skipass.types.LimitedLiftType;
import edu.kytsmen.java.ood.skipass.types.SeasonType;
import edu.kytsmen.java.ood.utils.SkiPassRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by dkytsmen on 11/2/16.
 */
public class SkipassFactoryTest extends Assert {
    private SkipassFactory factory = SkipassFactory.SKIPASS_FACTORY;
    private Skipass skipassOne;
    private Skipass skipassTwo;
    private Skipass skipassThree;

    @Before
    public void setUp() throws Exception {
        skipassOne = factory.getSkipass(new SkiPassRequest(LimitedLiftType.TEN, DayOfWeekType.WEEKENDS));
        skipassTwo = factory.getSkipass(new SkiPassRequest(DayPeriodType.DAY, DayOfWeekType.WEEKENDS.WORKDAYS));
        skipassThree = factory.getSkipass(new SkiPassRequest(SeasonType.AUTUMN));
    }

    @After
    public void cleanAll() throws Exception {
        factory.clear();
    }

    @Test
    public void testCreation() {
        assertTrue(factory.checkId(skipassOne.getUID()));
        assertTrue(factory.checkId(skipassTwo.getUID()));
        assertTrue(factory.checkId(skipassThree.getUID()));
    }

    @Test
    public void testRemoval() {
        factory.removeSkipass(skipassOne.getUID());
        assertFalse(factory.checkId(skipassOne.getUID()));
        assertTrue(factory.checkId(skipassTwo.getUID()));
        assertTrue(factory.checkId(skipassThree.getUID()));
    }

    @Test
    public void testClear() {
        factory.clear();
        assertFalse(factory.checkId(skipassOne.getUID()));
        assertFalse(factory.checkId(skipassTwo.getUID()));
        assertFalse(factory.checkId(skipassThree.getUID()));
    }
}
