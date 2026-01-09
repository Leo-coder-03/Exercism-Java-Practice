public class RailFenceCipher {

    private final int rows;

    public RailFenceCipher(int rows) {
        if (rows < 2) {
            throw new IllegalArgumentException("Rail count must be at least 2.");
        }
        this.rows = rows;
    }

    public String getEncryptedData(String message) {
        if (message == null || message.isEmpty()) return "";

        StringBuilder[] railBuilders = new StringBuilder[rows];
        for (int i = 0; i < rows; i++) {
            railBuilders[i] = new StringBuilder();
        }

        int rail = 0;
        int step = 1;

        for (char ch : message.toCharArray()) {
            railBuilders[rail].append(ch);
            if (rail == 0) {
                step = 1;
            } else if (rail == rows - 1) {
                step = -1;
            }

            rail += step;
        }

        StringBuilder encrypted = new StringBuilder();
        for (StringBuilder sb : railBuilders) {
            encrypted.append(sb);
        }

        return encrypted.toString();
    }

    public String getDecryptedData(String message) {
        if (message == null || message.isEmpty()) return "";

        int len = message.length();
        int[] railLengths = new int[rows];

        int rail = 0;
        int step = 1;
        for (int i = 0; i < len; i++) {
            railLengths[rail]++;

            if (rail == 0) step = 1;
            else if (rail == rows - 1) step = -1;

            rail += step;
        }

        String[] rails = new String[rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            rails[i] = message.substring(index, index + railLengths[i]);
            index += railLengths[i];
        }

        StringBuilder decrypted = new StringBuilder();
        int[] railPointers = new int[rows];
        rail = 0;
        step = 1;
        for (int i = 0; i < len; i++) {
            decrypted.append(rails[rail].charAt(railPointers[rail]++));
            
            if (rail == 0) step = 1;
            else if (rail == rows - 1) step = -1;

            rail += step;
        }

        return decrypted.toString();
    }
}
