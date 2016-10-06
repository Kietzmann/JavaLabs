package edu.kytsmen.java.ood.skipass.types;

/**
 * Created by dkytsmen on 9/20/16.
 */
public enum LimitedLiftType {
    TEN(10), TWENTY(20), FIFTY(50), HUNDRED(100);
    private final int liftsAmount;

    LimitedLiftType(int liftsAmount) {
        this.liftsAmount = liftsAmount;
    }

    public int getAmount() {
        return liftsAmount;
    }
}
