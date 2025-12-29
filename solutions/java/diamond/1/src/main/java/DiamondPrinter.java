import java.util.ArrayList;
import java.util.List;

class DiamondPrinter {

    List<String> printToList(char letter) {
        List<String> diamond = new ArrayList<>();

        int size = letter - 'A';
        int width = size * 2 + 1;

        // Build top half including middle row
        for (int i = 0; i <= size; i++) {
            diamond.add(buildRow(i, size));
        }

        // Build bottom half (mirror of top half)
        for (int i = size - 1; i >= 0; i--) {
            diamond.add(buildRow(i, size));
        }

        return diamond;
    }

    private String buildRow(int i, int size) {
        char ch = (char) ('A' + i);

        int outerSpaces = size - i;
        if (i == 0) {
            // A row: only one letter
            return repeat(" ", outerSpaces) + ch + repeat(" ", outerSpaces);
        }

        // Rows with two letters
        int innerSpaces = i * 2 - 1;

        return repeat(" ", outerSpaces)
                + ch
                + repeat(" ", innerSpaces)
                + ch
                + repeat(" ", outerSpaces);
    }

    private String repeat(String s, int count) {
        return s.repeat(count);
    }
}
