class RotationalCipher {

    private final int shiftKey;

    RotationalCipher(int shiftKey) {
        this.shiftKey = shiftKey % 26;
    }

    String rotate(String data) {
        StringBuilder result = new StringBuilder();

        for (char c : data.toCharArray()) {
            if (Character.isLowerCase(c)) {
                int shifted = (c - 'a' + shiftKey) % 26;
                result.append((char) ('a' + shifted));

            } else if (Character.isUpperCase(c)) {
                int shifted = (c - 'A' + shiftKey) % 26;
                result.append((char) ('A' + shifted));

            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}

