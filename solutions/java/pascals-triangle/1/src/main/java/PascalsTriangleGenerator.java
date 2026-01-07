class PascalsTriangleGenerator {

    int[][] generateTriangle(int rows) {
        if (rows <= 0) return new int[0][0];
        int[][] triangle = new int[rows][];
        for (int i = 0; i < rows; i++) {
            triangle[i] = new int[i + 1];
            triangle[i][0] = 1; 
            triangle[i][i] = 1; 

            for (int j = 1; j < i; j++) {
                triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
            }
        }
        return triangle;
    }

    void printTriangle(int[][] triangle) {
        for (int i = 0; i < triangle.length; i++) {
            for (int k = 0; k < triangle.length - i - 1; k++) {
                System.out.print(" ");
            }
            for (int j = 0; j < triangle[i].length; j++) {
                System.out.print(triangle[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        PascalsTriangleGenerator generator = new PascalsTriangleGenerator();
        int[][] triangle = generator.generateTriangle(5); 
        generator.printTriangle(triangle);
    }
}
