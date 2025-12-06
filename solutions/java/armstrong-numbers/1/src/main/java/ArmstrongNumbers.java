class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        int num = numberToCheck;
        int digits = String.valueOf(num).length();
        int rem = 0;
        int sum = 0;
        while(num>0)
            {
            rem = num%10;
            num = num/10;
            sum+=Math.pow(rem,digits);
            }
        if(sum == numberToCheck)
            return true;
        else 
            return false;

    }

}
