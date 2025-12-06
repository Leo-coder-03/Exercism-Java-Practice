class ReverseString {

    String reverse(String inputString) {
        String reversedString = "";
        for(char c:inputString.toCharArray())
            {
                reversedString = c+reversedString;
            }
        return reversedString;
    }
  
}
