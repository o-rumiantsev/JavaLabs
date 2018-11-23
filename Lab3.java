public class Lab3 {
    public static void main(String[] args) {
        Figure[] figures = new Figure[30];
        for (int i = 0; i < figures.length; ++i) {
            figures[i] = Figure.random();
            System.out.println(figures[i].toString());
        }
    }
}

class Figure {
    private String type;
    private Point center;
    private double square;
    private double perimeter;

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
        return String.format("Figure {\n" +
                "  type: \"%s\",\n" +
                "  center: %s,\n" +
                "  perimeter: %.4f,\n" +
                "  square: %.4f\n" +
                "}",
                this.type,
                this.center.toString(),
                this.perimeter,
                this.square
        );
    }

    protected static String[] figureTypes;

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
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.format("Point { x: %.4f, y: %.4f }", this.x, this.y);
    }

    public static Point random() {
        double x = Math.random() * 100;
        double y = Math.random() * 100;
        return new Point(x, y);
    }
}