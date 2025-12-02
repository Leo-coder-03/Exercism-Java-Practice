import java.util.*;

public class TestTrack {

    public static void race(RemoteControlCar car) {
       car.drive();
    }

    public static List<ProductionRemoteControlCar> getRankedCars(List<ProductionRemoteControlCar> cars) {
        List<ProductionRemoteControlCar> sorted = new ArrayList<>(cars);
        Collections.sort(sorted); 
        return sorted;
    }
}
