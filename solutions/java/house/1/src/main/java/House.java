class House {

    private static final String[] SUBJECTS = {
        "the house that Jack built.",
        "the malt ",
        "the rat ",
        "the cat ",
        "the dog ",
        "the cow with the crumpled horn ",
        "the maiden all forlorn ",
        "the man all tattered and torn ",
        "the priest all shaven and shorn ",
        "the rooster that crowed in the morn ",
        "the farmer sowing his corn ",
        "the horse and the hound and the horn "
    };

    private static final String[] ACTIONS = {
        "",
        "that lay in the house that Jack built.",
        "that ate the malt ",
        "that killed the rat ",
        "that worried the cat ",
        "that tossed the dog ",
        "that milked the cow with the crumpled horn ",
        "that kissed the maiden all forlorn ",
        "that married the man all tattered and torn ",
        "that woke the priest all shaven and shorn ",
        "that kept the rooster that crowed in the morn ",
        "that belonged to the farmer sowing his corn "
    };

    String verse(int verse) {
        StringBuilder sb = new StringBuilder();

        sb.append("This is ").append(SUBJECTS[verse - 1]);

        for (int i = verse - 1; i > 0; i--) {
            sb.append(ACTIONS[i]);
        }

        return sb.toString();
    }

    String verses(int startVerse, int endVerse) {
        StringBuilder sb = new StringBuilder();
        for (int i = startVerse; i <= endVerse; i++) {
            sb.append(verse(i));
            if (i < endVerse) sb.append("\n");
        }
        return sb.toString();
    }

    String sing() {
        return verses(1, SUBJECTS.length);
    }
}