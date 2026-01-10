import java.util.*;

class OpticalCharacterReader {
    private static final Map<String, String> DIGITS = Map.ofEntries(
            Map.entry(" _ " +
                      "| |" +
                      "|_|" +
                      "   ", "0"),

            Map.entry("   " +
                      "  |" +
                      "  |" +
                      "   ", "1"),

            Map.entry(" _ " +
                      " _|" +
                      "|_ " +
                      "   ", "2"),

            Map.entry(" _ " +
                      " _|" +
                      " _|" +
                      "   ", "3"),

            Map.entry("   " +
                      "|_|" +
                      "  |" +
                      "   ", "4"),

            Map.entry(" _ " +
                      "|_ " +
                      " _|" +
                      "   ", "5"),

            Map.entry(" _ " +
                      "|_ " +
                      "|_|" +
                      "   ", "6"),

            Map.entry(" _ " +
                      "  |" +
                      "  |" +
                      "   ", "7"),

            Map.entry(" _ " +
                      "|_|" +
                      "|_|" +
                      "   ", "8"),

            Map.entry(" _ " +
                      "|_|" +
                      " _|" +
                      "   ", "9")
    );

    String parse(List<String> input) {

        if (input.size() == 0 || input.size() % 4 != 0) {
            throw new IllegalArgumentException(
                "Number of input rows must be a positive multiple of 4"
            );
        }

        int rows = input.size();
        int cols = input.get(0).length();

        if (cols == 0 || cols % 3 != 0) {
            throw new IllegalArgumentException(
                "Number of input columns must be a positive multiple of 3"
            );
        }

        List<String> results = new ArrayList<>();

        for (int block = 0; block < rows; block += 4) {

            List<String> four = input.subList(block, block + 4);
            results.add(parseRow(four));
        }

        return String.join(",", results);
    }

    private String parseRow(List<String> four) {

        int digits = four.get(0).length() / 3;
        StringBuilder sb = new StringBuilder();

        for (int d = 0; d < digits; d++) {

            String pattern =
                    four.get(0).substring(d * 3, d * 3 + 3) +
                    four.get(1).substring(d * 3, d * 3 + 3) +
                    four.get(2).substring(d * 3, d * 3 + 3) +
                    four.get(3).substring(d * 3, d * 3 + 3);

            sb.append(DIGITS.getOrDefault(pattern, "?"));
        }

        return sb.toString();
    }
}
