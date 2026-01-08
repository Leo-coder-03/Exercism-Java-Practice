public class AffineCipher {

    private static final int M = 26;

    public String encode(String text, int a, int b) {
        if (!isCoprime(a, M)) {
            throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
        }

        String cleaned = cleanForEncode(text);
        StringBuilder result = new StringBuilder();

        for (char c : cleaned.toCharArray()) {
            if (Character.isDigit(c)) {
                result.append(c);
            } else {
                int x = c - 'a';
                int encoded = mod(a * x + b, M);
                result.append((char) (encoded + 'a'));
            }
        }

        return groupInFives(result.toString());
    }

    public String decode(String text, int a, int b) {
        if (!isCoprime(a, M)) {
            throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
        }

        int inverseA = modInverse(a, M);
        String cleaned = text.replaceAll("\\s+", "").toLowerCase();
        StringBuilder result = new StringBuilder();

        for (char c : cleaned.toCharArray()) {
            if (Character.isDigit(c)) {
                result.append(c);
            } else {
                int y = c - 'a';
                int decoded = mod(inverseA * (y - b), M);
                result.append((char) (decoded + 'a'));
            }
        }

        return result.toString();
    }

    private String cleanForEncode(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(c)) sb.append(c);
        }
        return sb.toString();
    }

    private String groupInFives(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && i % 5 == 0) sb.append(' ');
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    private int mod(int value, int mod) {
        return (value % mod + mod) % mod;
    }

    private boolean isCoprime(int a, int b) {
        return gcd(a, b) == 1;
    }

    private int gcd(int a, int b) {
        return (b == 0) ? Math.abs(a) : gcd(b, a % b);
    }

    private int modInverse(int a, int mod) {
        a = mod(a, mod);
        for (int x = 1; x < mod; x++) {
            if (mod(a * x, mod) == 1) return x;
        }
        throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
    }
}
