class SpaceAge {

    private final double seconds;
    private static final double EARTH_YEAR_IN_SECONDS = 31557600.0;

    SpaceAge(double seconds) {
        this.seconds = seconds;
    }

    double getSeconds() {
        return seconds;
    }

    double onEarth() {
        return seconds / EARTH_YEAR_IN_SECONDS;
    }

    double onMercury() {
        return seconds / (EARTH_YEAR_IN_SECONDS * 0.2408467);
    }

    double onVenus() {
        return seconds / (EARTH_YEAR_IN_SECONDS * 0.61519726);
    }

    double onMars() {
        return seconds / (EARTH_YEAR_IN_SECONDS * 1.8808158);
    }

    double onJupiter() {
        return seconds / (EARTH_YEAR_IN_SECONDS * 11.862615);
    }

    double onSaturn() {
        return seconds / (EARTH_YEAR_IN_SECONDS * 29.447498);
    }

    double onUranus() {
        return seconds / (EARTH_YEAR_IN_SECONDS * 84.016846);
    }

    double onNeptune() {
        return seconds / (EARTH_YEAR_IN_SECONDS * 164.79132);
    }
}
