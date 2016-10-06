package edu.kytsmen.java.ood.skipass;


import edu.kytsmen.java.ood.SkipassType;

import java.time.LocalDateTime;

/**
 * Created by dkytsmen on 9/20/16.
 */
public abstract class Skipass {
    protected int UID;
    protected SkipassType passType;
    protected boolean blocked;
    private LocalDateTime expireDate;


    public Skipass(int UID, SkipassType passType) {
        this.UID = UID;
        this.passType = passType;
        this.blocked = false;
    }

    public Skipass(int UID, SkipassType passType, LocalDateTime expireDate) {
        this(UID, passType);
        this.expireDate = expireDate;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getUID() {
        return UID;
    }

    public SkipassType getPassType() {
        return passType;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public abstract boolean isEligible();
}
