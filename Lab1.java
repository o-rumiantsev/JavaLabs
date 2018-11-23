import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter rows count: ");
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

class Matrix {
    private double[][] array;
    public int rows, cols;

    public double get(int row, int col) {
        return this.array[row][col];
    }

    public double set(double value, int row, int col) {
        return this.array[row][col] = value;
    }

    public int size() {
        return this.array.length;
    }

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.array = new double[rows][cols];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                this.array[i][j] = Math.random() * 100;
            }
        }
    }

    public static void print(Matrix matrix) {
        for (double[] row: matrix.array) {
            for (double col: row) {
                System.out.printf("%17.14f ", col);
            }
            System.out.println();
        }
    }

    public static double max(Matrix matrix) {
        double max = Double.NEGATIVE_INFINITY;
        for (double[] row: matrix.array) {
            for (double col: row) {
                if (col > max) max = col;
            }
        }
        return max;
    }

    public double last() {
        return this.array[this.rows - 1][this.cols - 1];
    }
}
