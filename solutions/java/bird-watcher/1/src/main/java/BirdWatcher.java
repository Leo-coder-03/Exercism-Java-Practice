
class BirdWatcher {
    private final int[] birdsPerDay;

    public BirdWatcher(int[] birdsPerDay) {
        this.birdsPerDay = birdsPerDay.clone();
    }

    public int[] getLastWeek() {
       int[] lastWeekCounts = {0,2,5,3,7,8,4};
        return lastWeekCounts;
    }

    public int getToday() {
        return birdsPerDay[birdsPerDay.length-1];
    }

    public void incrementTodaysCount() {
        birdsPerDay[birdsPerDay.length-1]++;
    }

    public boolean hasDayWithoutBirds() {
        for(int i:birdsPerDay)
            {
                if(i==0)
                    return true;
            }
        return false;
    }

    public int getCountForFirstDays(int numberOfDays) {
        int totalBirds = 0;
        for(int i=0;i<numberOfDays&&i<birdsPerDay.length;i++)
            {
                totalBirds+=birdsPerDay[i];
            }
        return totalBirds;
    }

    public int getBusyDays() {
        int busyDays = 0;
       for(int i:birdsPerDay)
            {
                if(i>=5)
                    busyDays++;
            }
        return busyDays;
    }
}
