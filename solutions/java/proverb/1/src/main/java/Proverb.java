class Proverb {

    private final String[] words;

    Proverb(String[] words) {
        this.words = words;
    }

    String recite() {
        if (words.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length - 1; i++) {
            sb.append("For want of a ")
              .append(words[i])
              .append(" the ")
              .append(words[i + 1])
              .append(" was lost.\n");
        }
        sb.append("And all for the want of a ")
          .append(words[0])
          .append(".");

        return sb.toString();
    }
}

