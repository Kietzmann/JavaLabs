package main.edu.kytsmen.ood.skipass;


import main.edu.kytsmen.ood.SkipassType;

/**
 * Created by dkytsmen on 9/20/16.
 */
public abstract class Skipass {
    protected int UID;
    protected SkipassType passType;
    protected boolean isBlocked;

    public Skipass(int UID, SkipassType passType) {
        this.UID = UID;
        this.passType = passType;
        this.isBlocked = false;
    }

    public Skipass() {
    }

    public void block() {
        this.isBlocked = true;
    }

    public void unblock() {
        this.isBlocked = false;
    }

    public abstract boolean isProcessed();
}
