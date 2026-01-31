import java.util.*;

public class WordProblemSolver {

    public int solve(final String wordProblem) {
        if (wordProblem == null || !wordProblem.startsWith("What is") || !wordProblem.endsWith("?")) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }

        if (wordProblem.length() <= 8) { // "What is" is 8 chars
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }

        String content = wordProblem.substring(8, wordProblem.length() - 1).trim();

        if (content.isEmpty()) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }

        List<String> tokens = new ArrayList<>();
        String[] words = content.split("\\s+");
        int i = 0;
        while (i < words.length) {
            String word = words[i];

            // Multi-word operators
            if (word.equals("plus") || word.equals("minus")) {
                tokens.add(word);
                i++;
            } else if (word.equals("multiplied") || word.equals("divided")) {
                if (i + 1 < words.length && words[i + 1].equals("by")) {
                    tokens.add(word + " by");
                    i += 2;
                } else {
                    throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
                }
            } else {
                try {
                    int num = Integer.parseInt(word);
                    tokens.add(String.valueOf(num));
                    i++;
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
                }
            }
        }

        if (tokens.isEmpty()) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }

        int result;
        try {
            result = Integer.parseInt(tokens.get(0));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }

        i = 1;
        while (i < tokens.size()) {
            if (i + 1 >= tokens.size()) {
                throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
            }

            String operator = tokens.get(i);
            int number;
            try {
                number = Integer.parseInt(tokens.get(i + 1));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
            }

            switch (operator) {
                case "plus":
                    result += number;
                    break;
                case "minus":
                    result -= number;
                    break;
                case "multiplied by":
                    result *= number;
                    break;
                case "divided by":
                    result /= number;
                    break;
                default:
                    throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
            }

            i += 2; 
        }

        return result;
    }
}
