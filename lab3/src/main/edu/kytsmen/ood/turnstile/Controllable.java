package main.edu.kytsmen.ood.turnstile;


import main.edu.kytsmen.ood.skipass.Skipass;

/**
 * Created by dkytsmen on 9/20/16.
 */
public interface Controllable {
    void proceedSkipass(Skipass skipass);

    void blockSkipass(Skipass skipass);
}
