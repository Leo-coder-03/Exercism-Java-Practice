class Acronym {
    String acronym;

    Acronym(String phrase) {
        phrase = phrase.replace("-"," ");
        phrase = phrase.replaceAll("[^A-Za-z ]", "");
        String[] words = phrase.trim().split("\\s+");

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0)));
        }

        this.acronym = sb.toString();
    }

    String get() {
       return this.acronym;
    }

}
