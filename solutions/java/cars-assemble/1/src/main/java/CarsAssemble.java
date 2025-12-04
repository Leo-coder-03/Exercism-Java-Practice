public class CarsAssemble {

    public double productionRatePerHour(int speed) {
        int totalCars = speed*221;
        double successRate;
        if(speed>=1 && speed<=4)
            successRate = 1;
        else if(speed>=5 && speed<=8)
            successRate = 0.9;
        else if(speed == 9)
            successRate = 0.8;
        else
            successRate = 0.77;
        return successRate * totalCars; 
    }

    public int workingItemsPerMinute(int speed) {
        double workingCars = productionRatePerHour(speed);
        return (int)workingCars/60;
    }
}
