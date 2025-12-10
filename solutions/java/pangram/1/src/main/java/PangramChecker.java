import java.util.Arrays;
public class PangramChecker {

    public boolean isPangram(String input) {
        input = input.replaceAll(" ","");
        char[] inputArray = input.toCharArray();
        int[]charCount = new int[26];
        for(char c:inputArray)
            {
                if(c>='a'&&c<='z')
                    charCount[c-97] = 1;
                else if(c>='A'&&c<='Z')
                    charCount[c-65]=1;
            }
        Arrays.sort(charCount);
        if(charCount[0]==0)
            return false;
        return true;
    }

}
