package edu.kytsmen.java.ood.skipass;


import edu.kytsmen.java.ood.utils.SkipassType;

import java.time.LocalDateTime;

/**
 * Created by dkytsmen on 9/20/16.
 */
public abstract class Skipass {
    protected long uID;
    protected SkipassType passType;
    protected boolean blocked;
    protected LocalDateTime expireDate;


    public Skipass(long uID, SkipassType passType) {
        this.uID = uID;
        this.passType = passType;
        this.blocked = false;
    }

    public Skipass(int uID, SkipassType passType, LocalDateTime expireDate) {
        this(uID, passType);
        this.expireDate = expireDate;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public long getUID() {
        return uID;
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

    public abstract int getLiftsAmount();

    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
