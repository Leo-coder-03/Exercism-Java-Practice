class ProductionRemoteControlCar implements RemoteControlCar,Comparable<ProductionRemoteControlCar> {

    public int distanceDriven;
    private int numberOfVictories;
    public void drive() {
       this.distanceDriven+=10;
    }

    public int getDistanceTravelled() {
        return this.distanceDriven;
    }

    public int getNumberOfVictories() {
       return this.numberOfVictories;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        this.numberOfVictories = numberOfVictories;
    }
    public int compareTo(ProductionRemoteControlCar other)
    {
        return Integer.compare(other.getNumberOfVictories(),this.getNumberOfVictories());
    }
}
