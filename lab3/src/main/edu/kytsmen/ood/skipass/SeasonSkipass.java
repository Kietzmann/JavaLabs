package main.edu.kytsmen.ood.skipass;


import main.edu.kytsmen.ood.SkipassType;

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
