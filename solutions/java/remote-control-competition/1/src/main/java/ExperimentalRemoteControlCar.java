public class ExperimentalRemoteControlCar implements RemoteControlCar {

    public int distanceDriven;
    public void drive() {
       this.distanceDriven+=20;
    }

    public int getDistanceTravelled() {
        return this.distanceDriven;
    }
}
