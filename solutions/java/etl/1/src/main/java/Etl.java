import java.util.List;
import java.util.Map;
import java.util.HashMap;

class Etl {
    Map<String, Integer> transform(Map<Integer, List<String>> old) {
        Map<String, Integer> result = new HashMap<>();
        
        for (Map.Entry<Integer, List<String>> entry : old.entrySet()) {
            Integer score = entry.getKey();
            List<String> letters = entry.getValue();
            
            for (String letter : letters) {
                result.put(letter.toLowerCase(), score);
            }
        }
        return result;
    }
}
