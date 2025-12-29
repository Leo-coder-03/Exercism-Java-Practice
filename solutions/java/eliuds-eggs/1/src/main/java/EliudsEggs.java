public class EliudsEggs {
    public int eggCount(int number) {
        int ones = 0;
        int rem = 0;
        while(number>0)
            {
                rem = number%2;
                if(rem == 1)
                    ones++;
                number = number/2;
            }
        return ones;
    }
}
