class PigLatinTranslator {

    public String translate(String input) {
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(translateWord(words[i]));
        }
        return sb.toString();
    }

    private String translateWord(String word) {
        if (word.matches("^(?:[aeiou]|xr|yt).*")) {
            return word + "ay";
        }
        if (word.matches("^[^aeiou]*qu.*")) {
            int quIndex = word.indexOf("qu") + 2;
            return word.substring(quIndex) + word.substring(0, quIndex) + "ay";
        }
        if (word.startsWith("y")) {
            return word.substring(1) + "y" + "ay";
        }
        if (word.matches("^[^aeiou]*y.*")) {
            int yIndex = word.indexOf("y");
            return word.substring(yIndex) + word.substring(0, yIndex) + "ay";
        }
        int firstVowelIndex = indexOfFirstVowel(word);
        return word.substring(firstVowelIndex) + word.substring(0, firstVowelIndex) + "ay";
    }

    private int indexOfFirstVowel(String word) {
        for (int i = 0; i < word.length(); i++) {
            if ("aeiou".indexOf(word.charAt(i)) >= 0) {
                return i;
            }
        }
        return 0;
    }
}
