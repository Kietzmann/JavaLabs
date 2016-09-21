package lab3.turnstile;

import lab3.skipass.Skipass;

/**
 * Created by dkytsmen on 9/20/16.
 */
public interface Controllable {
    void proceedSkipass(Skipass skipass);

    void blockSkipass(Skipass skipass);
}
