class IsbnVerifier {

    boolean isValid(String stringToVerify) {

        // Remove dashes
        String isbn = stringToVerify.replace("-", "");

        // Must be exactly 10 characters
        if (isbn.length() != 10) {
            return false;
        }

        int sum = 0;

        // Process first 9 characters (must be digits)
        for (int i = 0; i < 9; i++) {
            char c = isbn.charAt(i);

            if (!Character.isDigit(c)) {
                return false;
            }

            int digit = c - '0';
            sum += digit * (10 - i);
        }

        // Process last character (digit OR 'X')
        char last = isbn.charAt(9);
        int lastValue;

        if (last == 'X') {
            lastValue = 10;
        } else if (Character.isDigit(last)) {
            lastValue = last - '0';
        } else {
            return false;
        }

        sum += lastValue * 1;  // weight for last digit is 1

        // Valid if divisible by 11
        return sum % 11 == 0;
    }
}
