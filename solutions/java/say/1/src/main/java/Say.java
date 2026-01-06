public class Say {

     private static final String[] belowTwenty = {
            "zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] tens = {
            "", "", "twenty", "thirty", "forty",
            "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    public static String say(long number) {
        if(number < 0 || number >999_999_999_999L)
            throw new IllegalArgumentException();
        if (number == 0) return "zero";
        return convert(number).trim();
    }

    private static String convert(long num) {
        if (num < 20) {
            return belowTwenty[(int) num];
        } 
        else if (num < 100) {
            return tens[(int) num / 10] +
                    (num % 10 != 0 ? "-" + belowTwenty[(int) num % 10] : "");
        } 
        else if (num < 1000) {
            return belowTwenty[(int) num / 100] + " hundred" +
                    (num % 100 != 0 ? " " + convert(num % 100) : "");
        } 
        else if (num < 1_000_000) { // thousands
            return convert(num / 1000) + " thousand" +
                    (num % 1000 != 0 ? " " + convert(num % 1000) : "");
        } 
        else if (num < 1_000_000_000) { // millions
            return convert(num / 1_000_000) + " million" +
                    (num % 1_000_000 != 0 ? " " + convert(num % 1_000_000) : "");
        } 
        else { // billions
            return convert(num / 1_000_000_000) + " billion" +
                    (num % 1_000_000_000 != 0 ? " " + convert(num % 1_000_000_000) : "");
        }
    }
}
