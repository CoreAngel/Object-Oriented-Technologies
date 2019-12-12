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

    public TriangleState(Triangle triangle) {
        this.x1 = triangle.getOne().getX();
        this.y1 = triangle.getOne().getY();

        this.x2 = triangle.getTwo().getX();
        this.y2 = triangle.getTwo().getY();

        this.x3 = triangle.getThree().getX();
        this.y3 = triangle.getThree().getY();
        this.color = triangle.getColor();
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
}
