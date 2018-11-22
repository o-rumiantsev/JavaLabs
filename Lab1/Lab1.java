package Lab1;

import Utils.Matrix;
import java.util.Scanner;

public class Lab1 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int customSize = sc.nextInt();
        int defaultSize = 6;
        int size = customSize > 0 ? customSize : defaultSize;
        Matrix matrix = new Matrix(size, size);

        double max = Matrix.max(matrix);
        double last = matrix.last();

        Matrix.print(matrix);

        for (int i = 0; i < matrix.rows; ++i) {
            for (int j = 0; j < matrix.cols; ++j) {
                if (matrix.get(i, j) == max) {
                    matrix.set(last, i, j);
                    matrix.set(max, matrix.rows - 1, matrix.cols - 1);
                    break;
                }
            }
        }


        System.out.println();
        System.out.println("---------------------------------");
        System.out.printf( "  Max value: %.14f  |\n", max);
        System.out.println("---------------------------------\n");

        Matrix.print(matrix);
    }
}
