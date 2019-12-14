package draw.service.entity.memento;

import draw.service.entity.Triangle;
import draw.service.utils.Point;

public class TriangleState implements IState {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;
    private int color;
    private double perimeter;
    private double area;

    public TriangleState(Triangle triangle) {
        this.x1 = triangle.getOne().getX();
        this.y1 = triangle.getOne().getY();

        this.x2 = triangle.getTwo().getX();
        this.y2 = triangle.getTwo().getY();

        this.x3 = triangle.getThree().getX();
        this.y3 = triangle.getThree().getY();
        this.color = triangle.getColor();

        this.area = this.calculateArea(triangle);
        this.perimeter = this.calculatePerimeter(triangle);
    }

    public Point getOne() {
        return new Point(this.x1, this.y1);
    }

    public Point getTwo() {
        return new Point(this.x2, this.y2);
    }

    public Point getThree() {
        return new Point(this.x3, this.y3);
    }

    public int getColor() {
        return color;
    }

    private double calculateArea(Triangle triangle) {
        Point v1 = triangle.getOne();
        Point v2 = triangle.getTwo();
        Point v3 = triangle.getThree();

        double a = v2.getX() - v1.getX();
        double b = v3.getY() - v1.getY();
        double c = v2.getY() - v1.getY();
        double d = v3.getX() - v1.getX();

        return Math.abs(a * b - c * d) / 2;
    }

    private double calculatePerimeter(Triangle triangle) {
        Point v1 = triangle.getOne();
        Point v2 = triangle.getTwo();
        Point v3 = triangle.getThree();

        double v1v2 = v1.getDistanceWith(v2);
        double v2v3 = v2.getDistanceWith(v3);
        double v3v1 = v3.getDistanceWith(v1);

        return v1v2 + v2v3 + v3v1;
    }
}
