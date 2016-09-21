package lab3.skipass;

import lab3.SkipassType;

/**
 * Created by dkytsmen on 9/20/16.
 */
public class WeekendSkipass extends Skipass {

    public WeekendSkipass(int UID){
        super(UID, SkipassType.WEEKENDPASS);
    }



    @Override
    public boolean isProcessed() {
        return false;
    }
}
