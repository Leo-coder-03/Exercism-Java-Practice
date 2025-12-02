public class JedliksToyCar {
    public int distance = 0;
    public int battery = 100;
    public static JedliksToyCar buy() {
        return new JedliksToyCar();
    }

    public String distanceDisplay() {
        return "Driven "+this.distance+" meters";
    }

    public String batteryDisplay() {
        if(this.battery!=0)
        return "Battery at "+this.battery+"%";
        else
            return "Battery empty";
    }

    public void drive() {
      if(this.battery!=0){
      this.distance+=20;
      this.battery-=1;
      }
    }
}
