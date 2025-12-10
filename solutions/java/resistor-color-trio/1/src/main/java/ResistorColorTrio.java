import java.util.Map;
class ResistorColorTrio {
    String label(String[] colors) {
        Map<String,Integer>colorEncoding = Map.of("black",0,
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
        int thirdCode = colorEncoding.get(colors[2]);
        long value = (firstCode*10+secondCode)*(long)Math.pow(10,thirdCode);
        if (value >= 1_000_000_000) {
            return (value / 1_000_000_000) + " gigaohms";
        } else if (value >= 1_000_000) {
            return (value / 1_000_000) + " megaohms";
        } else if (value >= 1_000) {
            return (value / 1_000) + " kiloohms";
        } else {
            return value + " ohms";
        }
    }
}
