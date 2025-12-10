import java.util.Map;
class ResistorColorDuo {
    int value(String[] colors) {
        Map<String,Integer> colorEncoding = Map.of("black",0,
                                                  "brown",1,
                                                  "red",2,
                                                  "orange",3,
                                                  "yellow",4,
                                                  "green",5,
                                                  "blue",6,
                                                  "violet",7,
                                                  "grey",8,
                                                  "white",9);
        int firstCode = colorEncoding.get(colors[0]);
        int secondCode = colorEncoding.get(colors[1]);
        int finalCode = firstCode*10+secondCode;
        return finalCode;
        
    }
}
