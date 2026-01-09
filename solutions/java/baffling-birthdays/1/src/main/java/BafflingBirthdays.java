import java.time.LocalDate;
import java.util.*;

class BafflingBirthdays {

    boolean sharedBirthday(List<String> birthdates) {
        Set<String> seen = new HashSet<>();
        for (String dateStr : birthdates) {
            LocalDate date = LocalDate.parse(dateStr);
            String birthday = date.getMonthValue() + "-" + date.getDayOfMonth();
            if (!seen.add(birthday)) {
                return true;
            }
        }
        return false;
    }
    List<String> randomBirthdates(int groupSize) {
        Random rand = new Random();
        List<String> birthdates = new ArrayList<>();
        int startYear = 1900;
        int endYear = 2022;

        while (birthdates.size() < groupSize) {
            int year = startYear + rand.nextInt(endYear - startYear + 1);
            if (LocalDate.of(year, 1, 1).isLeapYear()) {
                continue;
            }

            int dayOfYear = 1 + rand.nextInt(LocalDate.of(year, 12, 31).lengthOfYear());
            LocalDate date = LocalDate.ofYearDay(year, dayOfYear);

            if (date.getMonthValue() == 2 && date.getDayOfMonth() == 29) {
                continue;
            }

            birthdates.add(date.toString());
        }

        return birthdates;
    }

    double estimatedProbabilityOfSharedBirthday(int groupSize) {
        if (groupSize <= 1) return 0.0;

        int trials = 10000;
        int count = 0;

        for (int i = 0; i < trials; i++) {
            List<String> group = randomBirthdates(groupSize);
            if (sharedBirthday(group)) {
                count++;
            }
        }

        return count * 100.0 / trials;
    }
}
