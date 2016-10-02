package main.edu.kytsmen.ood;

/**
 * Created by dkytsmen on 9/20/16.
 */
public enum LimitedLiftEnum {
    TEN(10), TWENTY(20), FIFTY(50), HUNDRED(100);
    private int liftsCounter;

    LimitedLiftEnum(int liftsCounter) {
        this.liftsCounter = liftsCounter;
    }

//    public int getLiftsCounter() {
//        return liftsCounter;
//    }
//
//    public void makeLift(){
//        if (this.liftsCounter < 1){}
//        this.liftsCounter--;
//    }
}
