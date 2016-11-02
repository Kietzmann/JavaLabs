package edu.kytsmen.java.ood.statistic;

/**
 * Created by dkytsmen on 11/2/16.
 */
public class ProcessResult {
    private Long rejectedCounter = 0L;
    private Long acceptedCounter = 0L;

    public ProcessResult() {
    }

    public ProcessResult(Long acceptedCounter, Long rejectedCounter) {
        this.rejectedCounter = rejectedCounter;
        this.acceptedCounter = acceptedCounter;
    }

    public ProcessResult plus(ProcessResult processResult) {
        return new ProcessResult(acceptedCounter + processResult.getAcceptedCounter(), rejectedCounter + processResult.getRejectedCounter());
    }

    public long getRejectedCounter() {
        return rejectedCounter;
    }

    public long getAcceptedCounter() {
        return acceptedCounter;
    }

    public void incrementCounter(boolean passed) {
        if (passed) {
            acceptedCounter++;
        } else {
            rejectedCounter++;
        }
    }
}
