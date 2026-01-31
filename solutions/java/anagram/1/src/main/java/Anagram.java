import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Anagram {

    private final String originalWord;
    private final String sortedLowerWord;

    public Anagram(String word) {
        this.originalWord = word;
        this.sortedLowerWord = sortLetters(word.toLowerCase());
    }

    public List<String> match(List<String> candidates) {
        List<String> result = new ArrayList<>();

        for (String candidate : candidates) {
            String candidateLower = candidate.toLowerCase();

            if (candidateLower.equals(originalWord.toLowerCase())) {
                continue;
            }

            if (sortLetters(candidateLower).equals(sortedLowerWord)) {
                result.add(candidate);
            }
        }

        return result;
    }

    private String sortLetters(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
