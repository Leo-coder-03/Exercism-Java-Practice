class PhoneNumber {
    private String number;

    PhoneNumber(String numberString) {
        if (numberString.matches(".*[a-zA-Z].*")) {
            throw new IllegalArgumentException("letters not permitted");
        }

        if (numberString.matches(".*[^0-9+().\\-\\s].*")) {
            throw new IllegalArgumentException("punctuations not permitted");
        }
        String digits = numberString.replaceAll("\\D", "");
        if (digits.length() < 10) {
            throw new IllegalArgumentException("must not be fewer than 10 digits");
        }
        if (digits.length() > 11) {
            throw new IllegalArgumentException("must not be greater than 11 digits");
        }
        if (digits.length() == 11) {
            if (!digits.startsWith("1")) {
                throw new IllegalArgumentException("11 digits must start with 1");
            }
            digits = digits.substring(1); 
        }
        char areaCodeFirst = digits.charAt(0);
        if (areaCodeFirst == '0') {
            throw new IllegalArgumentException("area code cannot start with zero");
        }
        if (areaCodeFirst == '1') {
            throw new IllegalArgumentException("area code cannot start with one");
        }

        char exchangeCodeFirst = digits.charAt(3);
        if (exchangeCodeFirst == '0') {
            throw new IllegalArgumentException("exchange code cannot start with zero");
        }
        if (exchangeCodeFirst == '1') {
            throw new IllegalArgumentException("exchange code cannot start with one");
        }
        this.number = digits;
    }
    String getNumber() {
        return number;
    }
}
