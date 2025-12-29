class CollatzCalculator {

    int computeStepCount(int start) {
        if (start <= 0) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }

        int step = 0;
        while(start != 1)
            {
                step++;
                if(start%2 == 0)
                   start = start/2;
                else
                    start=start*3+1;
            }
        return step;
    }
}
