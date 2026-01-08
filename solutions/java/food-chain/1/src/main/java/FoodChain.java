class FoodChain {

    private final String[] animals = {
        "fly",
        "spider",
        "bird",
        "cat",
        "dog",
        "goat",
        "cow",
        "horse"
    };

    private final String[] comments = {
        "", // fly has no special comment
        "It wriggled and jiggled and tickled inside her.",
        "How absurd to swallow a bird!",
        "Imagine that, to swallow a cat!",
        "What a hog, to swallow a dog!",
        "Just opened her throat and swallowed a goat!",
        "I don't know how she swallowed a cow!",
        "She's dead, of course!"
    };

    String verse(int verse) {
        int idx = verse - 1;

        if (animals[idx].equals("horse")) {
            return "I know an old lady who swallowed a horse.\n" + comments[idx];
        }

        StringBuilder sb = new StringBuilder();
        sb.append("I know an old lady who swallowed a ").append(animals[idx]).append(".\n");

        if (!comments[idx].isEmpty()) {
            sb.append(comments[idx]).append("\n");
        }

        for (int i = idx; i > 0; i--) {
            sb.append("She swallowed the ").append(animals[i])
              .append(" to catch the ").append(animals[i - 1]);

            if (animals[i - 1].equals("spider")) {
                sb.append(" that wriggled and jiggled and tickled inside her");
            }
            sb.append(".\n");
        }

        sb.append("I don't know why she swallowed the fly. Perhaps she'll die.");
        return sb.toString();
    }

    String verses(int startVerse, int endVerse) {
        StringBuilder sb = new StringBuilder();
        for (int v = startVerse; v <= endVerse; v++) {
            sb.append(verse(v));
            if (v != endVerse) sb.append("\n\n");
        }
        return sb.toString();
    }
}
