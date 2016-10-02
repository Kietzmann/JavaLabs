package edu.kytsmen.java.ood.skipass;


import edu.kytsmen.java.ood.SkipassType;

/**
 * Created by dkytsmen on 9/20/16.
 */
public class SeasonSkipass extends Skipass {


    public SeasonSkipass(int UID) {
        super(UID, SkipassType.SEASONPASS);
    }


    @Override
    public boolean isProcessed() {
        return !isBlocked;
    }
}
