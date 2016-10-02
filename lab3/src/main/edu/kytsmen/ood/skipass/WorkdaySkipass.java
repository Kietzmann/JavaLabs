package main.edu.kytsmen.ood.skipass;


import main.edu.kytsmen.ood.SkipassType;
import main.edu.kytsmen.ood.UnlimitedLiftEnum;
import main.edu.kytsmen.ood.utils.TimeForUse;

/**
 * Created by dkytsmen on 9/20/16.
 */
public class WorkdaySkipass extends Skipass {

    private TimeForUse payedTime = null;
    private int liftCounter = 0;

    public WorkdaySkipass(int UID) {
        super(UID, SkipassType.WORKDAYPASS);
    }

    public WorkdaySkipass(int UID, UnlimitedLiftEnum timeInterval) {
        super(UID, SkipassType.WORKDAYPASS);
        switch (timeInterval) {
            case FIRSTHALFDAY:
                break;
            case SECONDHALFDAY:
                break;
            case DAY:
                break;
            case TWODAYS:
                break;
            case FIVEDAYS:
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
