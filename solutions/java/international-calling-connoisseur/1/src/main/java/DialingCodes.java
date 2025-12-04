import java.util.Map;
import java.util.HashMap;

public class DialingCodes {

    private Map<Integer, String> codes = new HashMap<>();
    public Map<Integer, String> getCodes() {
         return codes;
    }

    public void setDialingCode(Integer code, String country) {
       getCodes().put(code,country);
    }

    public String getCountry(Integer code) {
       return getCodes().get(code);
    }

    public void addNewDialingCode(Integer code, String country) {
       if(!getCodes().containsKey(code) && !getCodes().containsValue(country))
           getCodes().put(code,country);
    }

    public Integer findDialingCode(String country) {
       for (Map.Entry<Integer, String> entry : getCodes().entrySet()) {
            if (entry.getValue().equals(country)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void updateCountryDialingCode(Integer code, String country) {
      if(getCodes().containsValue(country)){
          Integer oldCode = findDialingCode(country);
          getCodes().remove(oldCode);
          getCodes().put(code,country);
      }
    }
}
