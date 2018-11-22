package Utils;

public class Matrix {
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
