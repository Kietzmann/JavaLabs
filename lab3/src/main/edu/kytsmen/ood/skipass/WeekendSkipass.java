package main.edu.kytsmen.ood.skipass;

import main.edu.kytsmen.ood.SkipassType;
import main.edu.kytsmen.ood.UnlimitedLiftEnum;
import main.edu.kytsmen.ood.utils.TimeForUse;

/**
 * Created by dkytsmen on 9/20/16.
 */
public class WeekendSkipass extends Skipass {

    private TimeForUse payedTime = null;
    private int liftCounter = 0;

    public WeekendSkipass(int UID) {
        super(UID, SkipassType.WEEKENDPASS);
    }

    public WeekendSkipass(int UID, UnlimitedLiftEnum timeInterval) {
        super(UID, SkipassType.WEEKENDPASS);
        switch (timeInterval) {
            case FIRSTHALFDAY:
                break;
            case SECONDHALFDAY:
                break;
            case DAY:
                break;
            case TWODAYS:
                break;
            default:

                break;
        }
    }


    @Override
    public boolean isProcessed() {
        return false;
    }
}