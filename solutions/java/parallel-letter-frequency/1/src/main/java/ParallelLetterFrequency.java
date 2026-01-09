import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

class ParallelLetterFrequency {

    private final String[] texts;

    ParallelLetterFrequency(String[] texts) {
        this.texts = texts;
    }
    Map<Character, Integer> countLetters() {
        if (texts.length == 0) {
            return Collections.emptyMap();
        }
        ExecutorService executor = Executors.newFixedThreadPool(texts.length);
        List<Future<Map<Character, Integer>>> futures = new ArrayList<>();
        for (String text : texts) {
            futures.add(executor.submit(() -> countLettersInSingleText(text)));
        }

        Map<Character, Integer> result = new HashMap<>();
        for (Future<Map<Character, Integer>> future : futures) {
            try {
                Map<Character, Integer> partial = future.get();
                merge(result, partial);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();

        return result;
    }
    private Map<Character, Integer> countLettersInSingleText(String text) {
        Map<Character, Integer> freq = new HashMap<>();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char ch = Character.toLowerCase(c);
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            }
        }
        return freq;
    }
    private void merge(Map<Character, Integer> main, Map<Character, Integer> partial) {
        for (Map.Entry<Character, Integer> entry : partial.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            main.put(c, main.getOrDefault(c, 0) + count);
        }
    }
}
