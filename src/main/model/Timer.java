package model;

public class Timer {
    private long time;

    public Timer() {
        time = System.currentTimeMillis();
    }

    public String stop() {
        long current = System.currentTimeMillis();
        long differenceMs = current - this.time;

        // Inspired by:
        // https://stackoverflow.com/questions/4142313/
        long millis = differenceMs % 1000;
        long second = (differenceMs / 1000) % 60;
        long minute = (differenceMs / (1000 * 60)) % 60;
        long hour = (differenceMs / (1000 * 60 * 60)) % 24;

        String timeString = String.format("%02d:%02d:%02d.%d (h:m:s:ms)", hour, minute, second, millis);
        return timeString;
    }
}
