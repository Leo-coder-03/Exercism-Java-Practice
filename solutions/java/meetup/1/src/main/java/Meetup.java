import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

public class Meetup {

    private final int month;
    private final int year;

    public Meetup(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public LocalDate day(DayOfWeek dow, MeetupSchedule schedule) {

        YearMonth ym = YearMonth.of(year, month);

        switch (schedule) {

            case TEENTH:
                // 13–19 → exactly one will match the weekday
                for (int day = 13; day <= 19; day++) {
                    LocalDate d = LocalDate.of(year, month, day);
                    if (d.getDayOfWeek() == dow) return d;
                }
                break;

            case FIRST:
                return nthWeekdayOfMonth(dow, 1);

            case SECOND:
                return nthWeekdayOfMonth(dow, 2);

            case THIRD:
                return nthWeekdayOfMonth(dow, 3);

            case FOURTH:
                return nthWeekdayOfMonth(dow, 4);

            case LAST:
                // Start from last day of month and go backwards
                LocalDate lastDay = ym.atEndOfMonth();
                while (lastDay.getDayOfWeek() != dow) {
                    lastDay = lastDay.minusDays(1);
                }
                return lastDay;
        }

        throw new IllegalArgumentException("Invalid schedule.");
    }

    private LocalDate nthWeekdayOfMonth(DayOfWeek dow, int n) {
        LocalDate date = LocalDate.of(year, month, 1);

        while (date.getDayOfWeek() != dow) {
            date = date.plusDays(1);
        }
        return date.plusDays(7L * (n - 1));
    }
}
