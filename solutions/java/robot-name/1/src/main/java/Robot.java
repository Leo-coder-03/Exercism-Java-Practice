import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Robot {

    private static final Random RANDOM = new Random();
    private static final Set<String> USED_NAMES = new HashSet<>();
    private String name;
    String getName() {
        if (name == null) {
            name = generateUniqueName();
        }
        return name;
    }

    void reset() {
        if (name != null) {
            USED_NAMES.remove(name);
            name = null;
        }
    }

    private String generateUniqueName() {
        String newName;
        do {
            newName = randomName();
        } while (USED_NAMES.contains(newName));
        USED_NAMES.add(newName);
        return newName;
    }

    private String randomName() {
        char letter1 = (char) ('A' + RANDOM.nextInt(26));
        char letter2 = (char) ('A' + RANDOM.nextInt(26));
        int digits = RANDOM.nextInt(1000);
        return String.format("%c%c%03d", letter1, letter2, digits);
    }
}
