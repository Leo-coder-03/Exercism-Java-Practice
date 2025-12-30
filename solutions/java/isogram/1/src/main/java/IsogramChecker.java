class IsogramChecker {

    boolean isIsogram(String phrase) {

        phrase = phrase.toLowerCase();

        // Use a boolean array for 26 letters
        boolean[] seen = new boolean[26];

        for (char c : phrase.toCharArray()) {
            if (c >= 'a' && c <= 'z') {   
                int index = c - 'a';
                if (seen[index]) {
                    return false;        
                }
                seen[index] = true;
            }
        }
        return true; 
    }
}
