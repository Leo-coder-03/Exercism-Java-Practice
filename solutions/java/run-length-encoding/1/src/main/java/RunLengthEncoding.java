class RunLengthEncoding {

    String encode(String data) {
        if (data == null || data.isEmpty()) return "";

        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= data.length(); i++) {
            if (i < data.length() && data.charAt(i) == data.charAt(i - 1)) {
                count++;
            } else {
                if (count > 1) result.append(count);
                result.append(data.charAt(i - 1));
                count = 1;
            }
        }
        return result.toString();
    }

    String decode(String data) {
        if (data == null || data.isEmpty()) return "";

        StringBuilder result = new StringBuilder();
        StringBuilder countBuilder = new StringBuilder();

        for (char c : data.toCharArray()) {
            if (Character.isDigit(c)) {
                countBuilder.append(c);
            } else { 
                int count = countBuilder.length() > 0 ? Integer.parseInt(countBuilder.toString()) : 1;
                for (int i = 0; i < count; i++) {
                    result.append(c);
                }
                countBuilder.setLength(0); // reset
            }
        }
        return result.toString();
    }
}
