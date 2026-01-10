import java.util.Random;

public class Cipher {
    private final String key;

    public Cipher() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append((char) ('a' + random.nextInt(26)));
        }
        this.key = sb.toString();
    }

    public Cipher(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String encode(String plainText) {
        return shiftText(plainText, true);
    }

    public String decode(String cipherText) {
        return shiftText(cipherText, false);
    }

    private String shiftText(String text, boolean isEncoding) {
        StringBuilder result = new StringBuilder();
        int keyLen = key.length();
        for (int i = 0; i < text.length(); i++) {
            char p = text.charAt(i);
            char k = key.charAt(i % keyLen);
            int shift = k - 'a';
            if (!isEncoding) shift = 26 - shift; 
            char c = (char) ('a' + (p - 'a' + shift) % 26);
            result.append(c);
        }
        return result.toString();
    }
}
