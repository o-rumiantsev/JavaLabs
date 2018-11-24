import java.util.Scanner;
import java.util.HashMap;

public class Lab3 {
    public static void main(String[] args) {
        Container figures = new Container(30);

        figures.table();

        while (true) {
            figures.fetchSelectParameters();
            Figure[] filtered = figures.select();
            Container.table(filtered);
        }
    }
}

class Figure {
    public final String type;
    public final Point center;
    public final double square;
    public final double perimeter;

    public Figure(String type, Point center, double perimeter, double square) {
        this.type = type;
        this.center = center;
        this.square = square;
        this.perimeter = perimeter;
    }

    public String getType() { return this.type; }
    public Point getCenter() { return this.center; }
    public double getSquare() { return this.square; }
    public double getPerimeter() { return this.perimeter; }

    public String toString() {
        return String.format(
                "| %9s | (%7.4f, %7.4f) | %9.6f | %8.5f |",
                this.type,
                this.center.x,
                this.center.y,
                this.perimeter,
                this.square
        );
    }

    public final static String[] figureTypes;

    static {
        figureTypes = new String[]{
                "rectangle",
                "trigon",
                "square",
                "pentagon",
                "systagon",
                "septagon",
                "octagon",
                "decagon",
                "circle",
                "ellipse",
        };
    }

    public static Figure random() {
        int maxIndex = figureTypes.length - 1;
        int typeIndex = (int)(Math.random() * maxIndex);
        String type = figureTypes[typeIndex];
        Point center = Point.random();
        double perimeter = Math.random() * 100;
        double square = Math.random() * 100;
        return new Figure(type, center, perimeter, square);
    }
}

class Point {
    public final double x;
    public final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point random() {
        double x = Math.random() * 100;
        double y = Math.random() * 100;
        return new Point(x, y);
    }
}

class Container {
    public final Figure[] items;
    public HashMap<String, String[]> selectParameters;

    public Container(int size) {
        this.items = new Figure[size];
        for (int i = 0; i < size; ++i) {
            this.items[i] = Figure.random();
        }
    }

    public static void table(Figure[] figures) {
        if (figures.length == 0) {
            System.out.println("Nothig to output");
            return;
        }

        String data = new String();

        for (Figure figure: figures) {
            data += figure.toString() + "\n";
        }

        String meta =
                "|   type    |       center       | perimeter |  square  |\n" +
                "---------------------------------------------------------\n";

        System.out.println(meta + data);
    }

    public void table() { Container.table(this.items); }

    public Figure[] select() {
        int count = 0;
        Selector selector = this.processSelectParameters();

        for (Figure f: this.items) {
            if (selector.test(f)) ++count;
        }

        Figure[] result = new Figure[count];

        for (int i = 0, j = 0; j < this.items.length; ++j) {
            Figure f = this.items[j];
            if (selector.test(f)) result[i++] = f;
        }

        return result;
    }

    // Read line from console. Line should be like:
    //   <fieldName> <operator> <compareValue> [...]
    //
    // Convert this string to a HashMap:
    //   <fieldName> -> [<operator>, <compareValue>]
    public final void fetchSelectParameters() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] words = line.split(" ");

        if (words.length % 3 != 0) {
            System.err.println("Invalid input. Try again");
            this.fetchSelectParameters();
            return;
        }

        this.selectParameters = new HashMap<String, String[]>();

        for (int i = 0; i < words.length; i += 3) {
            String field = words[i];
            String operator = words[i + 1];
            String value = words[i + 2];
            this.selectParameters.put(field, new String[]{operator, value});
        }
    }

    // Process select parameters in hash map. Retrun selector function
    // which will be used in .select() method to filter entities.
    private Selector processSelectParameters() {
        return (f) -> {
            if (this.selectParameters.containsKey("type")) {
                String[] params = this.selectParameters.get("type");
                String value = params[1];
                if (!f.type.equals(value)) return false;
            }

            if (this.selectParameters.containsKey("x")) {
                String[] params = this.selectParameters.get("x");
                String op = params[0];
                double value = Double.parseDouble(params[1]);
                switch (op) {
                    case "=":
                        if (f.center.x != value) return false;
                        break;
                    case "!=":
                        if (f.center.x == value) return false;
                        break;
                    case ">":
                        if (f.center.x <= value) return false;
                        break;
                    case "<":
                        if (f.center.x >= value) return false;
                        break;
                    case ">=":
                        if (f.center.x < value) return false;
                        break;
                    case "<=":
                        if (f.center.x > value) return false;
                        break;
                }
            }

            if (this.selectParameters.containsKey("y")) {
                String[] params = this.selectParameters.get("y");
                String op = params[0];
                double value = Double.parseDouble(params[1]);
                switch (op) {
                    case "=":
                        if (f.center.y != value) return false;
                        break;
                    case "!=":
                        if (f.center.y == value) return false;
                        break;
                    case ">":
                        if (f.center.y <= value) return false;
                        break;
                    case "<":
                        if (f.center.y >= value) return false;
                        break;
                    case ">=":
                        if (f.center.y < value) return false;
                        break;
                    case "<=":
                        if (f.center.y > value) return false;
                        break;
                }
            }

            if (this.selectParameters.containsKey("perimeter")) {
                String[] params = this.selectParameters.get("perimeter");
                String op = params[0];
                double value = Double.parseDouble(params[1]);
                switch (op) {
                    case "=":
                        if (f.perimeter != value) return false;
                        break;
                    case "!=":
                        if (f.perimeter == value) return false;
                        break;
                    case ">":
                        if (f.perimeter <= value) return false;
                        break;
                    case "<":
                        if (f.perimeter >= value) return false;
                        break;
                    case ">=":
                        if (f.perimeter < value) return false;
                        break;
                    case "<=":
                        if (f.perimeter > value) return false;
                        break;
                }
            }

            if (this.selectParameters.containsKey("square")) {
                String[] params = this.selectParameters.get("square");
                String op = params[0];
                double value = Double.parseDouble(params[1]);
                switch (op) {
                    case "=":
                        if (f.square != value) return false;
                        break;
                    case "!=":
                        if (f.square == value) return false;
                        break;
                    case ">":
                        if (f.square <= value) return false;
                        break;
                    case "<":
                        if (f.square >= value) return false;
                        break;
                    case ">=":
                        if (f.square < value) return false;
                        break;
                    case "<=":
                        if (f.square > value) return false;
                        break;
                }
            }

            return true;
        };
    };
}

interface Selector {
    boolean test(Figure f);
}