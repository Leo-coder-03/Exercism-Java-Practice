class Atbash {

    String encode(String input) {
        StringBuilder cleaned = new StringBuilder();
        for (char c : input.toLowerCase().toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c)) {
                cleaned.append(transform(c));
            }
        }
        StringBuilder grouped = new StringBuilder();
        int count = 0;
        for (char c : cleaned.toString().toCharArray()) {
            if (count == 5) {
                grouped.append(" ");
                count = 0;
            }
            grouped.append(c);
            count++;
        }

        return grouped.toString();
    }

    String decode(String input) {
        StringBuilder decoded = new StringBuilder();
        for (char c : input.replace(" ", "").toLowerCase().toCharArray()) {
            decoded.append(transform(c));
        }

        return decoded.toString();
    }

    private char transform(char c) {
        if (Character.isLetter(c)) {
            return (char) ('a' + ('z' - c));
        }
        return c; 
    }
}
