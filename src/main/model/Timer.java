package model;

public class Timer {
    private long time;

    // Constructor
    // EFFECT: set time to system's time in milliseconds for time comparisons
    public Timer() {
        time = System.currentTimeMillis();
    }

    // MODIFIES: this
    // EFFECTS: calculates the time elapsed by subtracting the system finish time from the system begin time, then
    //    converts elapsed milliseconds to a human-readable string of the time taken
    public String stop() {
        long current = System.currentTimeMillis();
        long differenceMs = current - this.time;

        // Inspired by:
        // https://stackoverflow.com/questions/4142313/
        long millis = differenceMs % 1000;
        long second = (differenceMs / 1000) % 60;
        long minute = (differenceMs / (1000 * 60)) % 60;
        long hour = (differenceMs / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d.%d (h:m:s:ms)", hour, minute, second, millis);
    }
}
