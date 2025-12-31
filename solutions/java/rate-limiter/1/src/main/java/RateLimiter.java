import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter<K> {

    private final int limit;
    private final Duration windowSize;
    private final TimeSource timeSource;

    private static class WindowInfo {
        Instant windowStart;
        int count;

        WindowInfo(Instant start) {
            this.windowStart = start;
            this.count = 0;
        }
    }

    private final Map<K, WindowInfo> windows = new HashMap<>();

    public RateLimiter(int limit, Duration windowSize, TimeSource timeSource) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.timeSource = timeSource;
    }

    public boolean allow(K clientId) {
        Instant now = timeSource.now();
        WindowInfo info = windows.get(clientId);

        if (info == null) {
            info = new WindowInfo(now);
            windows.put(clientId, info);
        }

        Instant boundary = info.windowStart.plus(windowSize);
        if (!now.isBefore(boundary)) {
            // New window begins
            info.windowStart = now;
            info.count = 0;
        }

        if (info.count < limit) {
            info.count++;
            return true;
        }

        return false;
    }
}
