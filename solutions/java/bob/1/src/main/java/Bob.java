class Bob {

    String hey(String input) {

        if (input == null) {
            return "Fine. Be that way!";
        }

        String trimmed = input.trim();

        // Silence
        if (trimmed.isEmpty()) {
            return "Fine. Be that way!";
        }

        boolean isQuestion = trimmed.endsWith("?");
        boolean hasLetters = trimmed.chars().anyMatch(Character::isLetter);
        boolean isYelling = hasLetters && trimmed.equals(trimmed.toUpperCase());

        if (isYelling && isQuestion) {
            return "Calm down, I know what I'm doing!";
        }

        if (isYelling) {
            return "Whoa, chill out!";
        }

        if (isQuestion) {
            return "Sure.";
        }

        return "Whatever.";
    }
}
