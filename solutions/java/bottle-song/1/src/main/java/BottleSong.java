class BottleSong {

    private static final String[] NUMBER_WORDS = {
        "zero", "one", "two", "three", "four", "five",
        "six", "seven", "eight", "nine", "ten"
    };

    String recite(int startBottles, int takeDown) {
        StringBuilder song = new StringBuilder();

        for (int i = 0; i < takeDown; i++) {
            int bottles = startBottles - i;
            int nextBottles = bottles - 1;

            // Capitalize first letter for lines 1 & 2
            String bottlesWordCapitalized = capitalize(NUMBER_WORDS[bottles]);

            // Line 1
            song.append(bottlesWordCapitalized)
                .append(" green ")
                .append(bottles == 1 ? "bottle" : "bottles")
                .append(" hanging on the wall,\n");

            // Line 2
            song.append(bottlesWordCapitalized)
                .append(" green ")
                .append(bottles == 1 ? "bottle" : "bottles")
                .append(" hanging on the wall,\n");

            // Line 3
            song.append("And if one green bottle should accidentally fall,\n");

            // Line 4
            if (nextBottles > 0) {
                song.append("There'll be ")
                    .append(NUMBER_WORDS[nextBottles])  // lowercase
                    .append(" green ")
                    .append(nextBottles == 1 ? "bottle" : "bottles")
                    .append(" hanging on the wall.\n");
            } else {
                song.append("There'll be no green bottles hanging on the wall.\n");
            }

            // Blank line between verses, except after the last verse
            if (i < takeDown - 1) {
                song.append("\n");
            }
        }

        return song.toString();
    }

    private String capitalize(String word) {
        if (word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
