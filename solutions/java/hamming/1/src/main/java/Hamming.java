public class Hamming {
    private int hammingDistance;
    public Hamming(String leftStrand, String rightStrand) {
        if(leftStrand.length()!=rightStrand.length())
            throw new IllegalArgumentException("strands must be of equal length");
        int hammingDistance = 0;
        char[] leftStrandArray = leftStrand.toCharArray();
        char[] rightStrandArray = rightStrand.toCharArray();
        for(int i=0;i<leftStrand.length();i++)
            {
                if(leftStrandArray[i]!=rightStrandArray[i])
                    hammingDistance++;
            }
        this.hammingDistance = hammingDistance;
    }

    public int getHammingDistance() {
       return this.hammingDistance;
    }
}
