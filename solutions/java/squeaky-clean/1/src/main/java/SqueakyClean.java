class SqueakyClean {
    static String clean(String identifier) {
       identifier = identifier.replace(' ','_');
        char characters[] = identifier.toCharArray();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < characters.length; i++) {

            char c = characters[i];
            if (c == '-' && i + 1 < characters.length) {
                sb.append(Character.toUpperCase(characters[i + 1]));
                i++;
                continue;
            }
            sb.append(c);
        }

        identifier = sb.toString();
       identifier = identifier.replace('4','a');
       identifier = identifier.replace('3','e');
       identifier = identifier.replace('0','o');
       identifier = identifier.replace('1','l');
       identifier = identifier.replace('7','t');
       identifier = identifier.replaceAll("[^A-Za-z_]", "");     
        return identifier;
    }
}
