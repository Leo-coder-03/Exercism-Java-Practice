import java.util.ArrayList;
import java.util.List;

public class SplitSecondStopwatch {

    private enum State { READY, RUNNING, STOPPED }

    private State state = State.READY;

    private int totalSeconds = 0;
    private int currentLapSeconds = 0;

    private final List<String> previousLaps = new ArrayList<>();

    // Advance only when running
    public void advanceTime(String timeString) {
        int seconds = parse(timeString);
        if (state == State.RUNNING) {
            currentLapSeconds += seconds;
            totalSeconds += seconds;
        }
    }

    // --- STATE METHODS ---

    public void start() {
        switch (state) {
            case READY:
            case STOPPED:
                state = State.RUNNING;
                return;
            case RUNNING:
                throw new IllegalStateException("cannot start an already running stopwatch");
        }
    }

    public void stop() {
        if (state != State.RUNNING) {
            throw new IllegalStateException("cannot stop a stopwatch that is not running");
        }
        state = State.STOPPED;
    }

    public void reset() {
        if (state != State.STOPPED) {
            throw new IllegalStateException("cannot reset a stopwatch that is not stopped");
        }

        state = State.READY;
        totalSeconds = 0;
        currentLapSeconds = 0;
        previousLaps.clear();
    }

    public void lap() {
        if (state != State.RUNNING) {
            throw new IllegalStateException("cannot lap a stopwatch that is not running");
        }

        previousLaps.add(format(currentLapSeconds));
        currentLapSeconds = 0;
    }

    // --- QUERY METHODS ---

    public String state() {
        switch (state) {
            case READY: return "ready";
            case RUNNING: return "running";
            case STOPPED: return "stopped";
        }
        throw new IllegalStateException("Unknown state");
    }

    public String currentLap() {
        return format(currentLapSeconds);
    }

    public String total() {
        return format(totalSeconds);
    }

    public List<String> previousLaps() {
        return new ArrayList<>(previousLaps);
    }

    // --- UTILITY METHODS ---

    private int parse(String time) {
        // hh:mm:ss
        String[] p = time.split(":");
        int h = Integer.parseInt(p[0]);
        int m = Integer.parseInt(p[1]);
        int s = Integer.parseInt(p[2]);
        return h * 3600 + m * 60 + s;
    }

    private String format(int seconds) {
        int h = seconds / 3600;
        seconds %= 3600;
        int m = seconds / 60;
        int s = seconds % 60;

        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
