public class Twofer {
    public String twofer(String name) {
        String statement = "";
        if(name != null)
           statement = "One for "+name+", one for me.";
        else
           statement = "One for you, one for me.";
        return statement;
    }
}
