import java.util.Objects;

class Clock {

    private int totalMinutes;

    public Clock(int hours, int minutes) {
        totalMinutes = ((hours * 60) + minutes) % (24 * 60);
        if (totalMinutes < 0) {
            totalMinutes += 24 * 60; 
        }
    }

    public void add(int minutes) {
        totalMinutes = (totalMinutes + minutes) % (24 * 60);
        if (totalMinutes < 0) {
            totalMinutes += 24 * 60;
        }
    }

    @Override
    public String toString() {
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Clock)) return false;
        Clock other = (Clock) obj;
        return this.totalMinutes == other.totalMinutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalMinutes);
    }
}
