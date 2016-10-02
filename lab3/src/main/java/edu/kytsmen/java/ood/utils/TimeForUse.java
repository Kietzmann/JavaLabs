package edu.kytsmen.java.ood.utils;


import java.time.LocalDateTime;

/**
 * Created by dkytsmen on 9/21/16.
 */
public class TimeForUse {
    private LocalDateTime from = null;
    private LocalDateTime to = null;

    public TimeForUse(LocalDateTime from, LocalDateTime to) {
        if (from == null || to == null)
            throw new IllegalArgumentException("LocalDateTime from and must can't be null!");
        this.from = from;
        this.to = to;
    }
}
