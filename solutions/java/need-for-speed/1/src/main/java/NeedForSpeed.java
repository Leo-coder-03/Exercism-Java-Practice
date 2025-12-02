class NeedForSpeed {
    public int speed;
    public int batteryDrain;
    public int distanceDriven = 0;
    public int battery=100;
    NeedForSpeed(int speed, int batteryDrain) {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
    }

    public boolean batteryDrained() {
         if(this.battery<this.batteryDrain) return true;
        else 
             return false;
    }

    public int distanceDriven() {
       return this.distanceDriven;
    }

    public void drive() {
        if(!batteryDrained()){
        this.distanceDriven+=this.speed;
        this.battery-=this.batteryDrain;
        }
    }

    public static NeedForSpeed nitro() {
        return new NeedForSpeed(50,4);
    }
}

class RaceTrack {
    private int distance;
    RaceTrack(int distance) {
        this.distance = distance;
    }

    public boolean canFinishRace(NeedForSpeed car) {
      int timesDriven = (int)Math.ceil(this.distance*1.0/car.speed);
      car.battery = car.battery - car.batteryDrain*(timesDriven-1);
      if(car.batteryDrained())
          return false;
        else 
          return true;
    }
}
