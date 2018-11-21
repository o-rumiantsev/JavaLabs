public class Lab1 {
    public static void main(String args[]) {
        Matrix matrix = new Matrix(6, 6);
        double max = Matrix.max(matrix);
        double last = matrix.last();

        Matrix.print(matrix);

        for (int i = 0; i < matrix.rows; ++i) {
            for (int j = 0; j < matrix.cols; ++j) {
                if (matrix.array[i][j] == max) {
                    matrix.array[i][j] = last;
                    matrix.array[matrix.rows - 1][matrix.cols - 1] = max;
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
    int rows, cols;
    double[][] array;

    Matrix(int rows, int cols) {
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
                System.out.print(col);
                System.out.print(' ');
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

