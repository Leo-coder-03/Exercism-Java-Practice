import java.util.HashMap;
import java.util.Map;

class NucleotideCounter {

    private final String sequence;
    private final Map<Character, Integer> counts;

    NucleotideCounter(String sequence) {
        this.sequence = sequence;
        this.counts = new HashMap<>();

        counts.put('A', 0);
        counts.put('C', 0);
        counts.put('G', 0);
        counts.put('T', 0);

        for (char c : sequence.toCharArray()) {
            if (!counts.containsKey(c)) {
                throw new IllegalArgumentException("Invalid nucleotide in strand");
            }
            counts.put(c, counts.get(c) + 1);
        }
    }

    Map<Character, Integer> nucleotideCounts() {
        return counts;
    }
}
