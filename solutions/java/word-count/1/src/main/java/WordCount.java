import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WordCount {

    public Map<String, Integer> phrase(String input) {
        Map<String, Integer> counts = new HashMap<>();
        if (input == null || input.isEmpty()) return counts;
        Pattern pattern = Pattern.compile("(?i)[a-z0-9]+(?:'[a-z0-9]+)*");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String word = matcher.group().toLowerCase();
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        return counts;
    }
}
