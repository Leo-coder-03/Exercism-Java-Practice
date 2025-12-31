import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SwiftScheduling {

    public static LocalDateTime convertToDeliveryDate(LocalDateTime meetingStart, String description) {

        switch (description) {
            case "NOW":
                return meetingStart.plusHours(2);

            case "ASAP":
                if (meetingStart.toLocalTime().isBefore(LocalTime.of(13, 0))) {
                    return meetingStart.toLocalDate().atTime(17, 0);
                } else {
                    return meetingStart.toLocalDate().plusDays(1).atTime(13, 0);
                }

            case "EOW":
                DayOfWeek dow = meetingStart.getDayOfWeek();
                if (dow == DayOfWeek.MONDAY || dow == DayOfWeek.TUESDAY || dow == DayOfWeek.WEDNESDAY) {
                    LocalDate friday = meetingStart.toLocalDate()
                            .with(DayOfWeek.FRIDAY);
                    return friday.atTime(17, 0);
                } else {
                    LocalDate sunday = meetingStart.toLocalDate()
                            .with(DayOfWeek.SUNDAY);
                    return sunday.atTime(20, 0);
                }
        }


        if (description.matches("\\d+M")) {
            int month = Integer.parseInt(description.replace("M", "")); 

            int currentMonth = meetingStart.getMonthValue();
            int year = meetingStart.getYear();

            if (currentMonth >= month) {
                year += 1;
            }

            LocalDate firstWorkday = firstWorkdayOfMonth(year, month);
            return firstWorkday.atTime(8, 0);
        }

        if (description.matches("Q\\d")) {
            int quarter = Integer.parseInt(description.substring(1)); // N

            int currentQuarter = (meetingStart.getMonthValue() - 1) / 3 + 1;
            int year = meetingStart.getYear();

            if (currentQuarter > quarter) {
                year += 1;
            }

            LocalDate lastWorkday = lastWorkdayOfQuarter(year, quarter);
            return lastWorkday.atTime(8, 0);
        }

        throw new IllegalArgumentException("Unknown description: " + description);
    }


    private static LocalDate firstWorkdayOfMonth(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        while (isWeekend(date)) {
            date = date.plusDays(1);
        }
        return date;
    }

    private static LocalDate lastWorkdayOfQuarter(int year, int quarter) {
        int lastMonth = quarter * 3;
        LocalDate date = LocalDate.of(year, lastMonth, 1)
                .withDayOfMonth(LocalDate.of(year, lastMonth, 1).lengthOfMonth());

        while (isWeekend(date)) {
            date = date.minusDays(1);
        }
        return date;
    }

    private static boolean isWeekend(LocalDate date) {
        DayOfWeek dow = date.getDayOfWeek();
        return dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY;
    }
}
