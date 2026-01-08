import java.util.*;

class Alphametics {

    private final List<String> words;
    private final String result;

    private final List<Character> letters;
    private final Map<Character, Integer> assigned;
    private final boolean[] usedDigit;

    private final List<List<Character>> columns;
    private final List<Character> resultColumn;

    private final Set<Character> leadingLetters;

    Alphametics(String puzzle) {
        String[] parts = puzzle.replace(" ", "").split("==");
        this.words = Arrays.asList(parts[0].split("\\+"));
        this.result = parts[1];

        Set<Character> all = new HashSet<>();
        this.leadingLetters = new HashSet<>();

        for (String w : words) {
            leadingLetters.add(w.charAt(0));
            for (char c : w.toCharArray()) all.add(c);
        }

        leadingLetters.add(result.charAt(0));
        for (char c : result.toCharArray()) all.add(c);

        if (all.size() > 10) {
            throw new IllegalArgumentException("Too many letters");
        }

        this.letters = new ArrayList<>(all);
        this.assigned = new HashMap<>();
        this.usedDigit = new boolean[10];

        // Build column-wise representation
        int maxLength = 0;
        for (String w : words) maxLength = Math.max(maxLength, w.length());
        maxLength = Math.max(maxLength, result.length());

        this.columns = new ArrayList<>();
        this.resultColumn = new ArrayList<>();

        for (int i = 0; i < maxLength; i++) {
            List<Character> col = new ArrayList<>();
            for (String w : words) {
                int idx = w.length() - 1 - i;
                col.add(idx >= 0 ? w.charAt(idx) : null);
            }
            columns.add(col);

            int ridx = result.length() - 1 - i;
            resultColumn.add(ridx >= 0 ? result.charAt(ridx) : null);
        }
    }

    public Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        if (search(0, 0)) {
            return assigned;
        }
        throw new UnsolvablePuzzleException();
    }

    private boolean search(int col, int carryIn) {
        if (col == columns.size()) {
            return carryIn == 0;
        }

        List<Character> addends = columns.get(col);
        Character resultChar = resultColumn.get(col);

        return tryAssignments(addends, resultChar, col, 0, carryIn);
    }

    private boolean tryAssignments(List<Character> addends, Character resultChar,
                                   int col, int i, int sum) {

        if (i == addends.size()) {
            int digit = sum % 10;
            int carryOut = sum / 10;

            if (resultChar == null) {
                return digit == 0 && search(col + 1, carryOut);
            }

            Integer assignedDigit = assigned.get(resultChar);

            if (assignedDigit != null) {
                if (assignedDigit != digit) return false;
                return search(col + 1, carryOut);
            } else {
                if (usedDigit[digit]) return false;
                if (digit == 0 && leadingLetters.contains(resultChar)) return false;

                assigned.put(resultChar, digit);
                usedDigit[digit] = true;

                if (search(col + 1, carryOut)) return true;

                assigned.remove(resultChar);
                usedDigit[digit] = false;
                return false;
            }
        }

        Character ch = addends.get(i);
        if (ch == null) return tryAssignments(addends, resultChar, col, i + 1, sum);

        Integer d = assigned.get(ch);
        if (d != null) {
            return tryAssignments(addends, resultChar, col, i + 1, sum + d);
        }

        for (int dig = 0; dig < 10; dig++) {
            if (usedDigit[dig]) continue;
            if (dig == 0 && leadingLetters.contains(ch)) continue;

            assigned.put(ch, dig);
            usedDigit[dig] = true;

            if (tryAssignments(addends, resultChar, col, i + 1, sum + dig)) return true;

            usedDigit[dig] = false;
            assigned.remove(ch);
        }
        return false;
    }
}
