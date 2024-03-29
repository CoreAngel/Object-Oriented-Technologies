package draw.service.entity.memento;

import draw.service.entity.Circle;
import draw.service.utils.Point;

public class CircleState implements IState {
    private double x;
    private double y;
    private double radius;
    private int color;
    private double perimeter;
    private double area;

    public CircleState(Circle circle) {
        this.x = circle.getCenter().getX();
        this.y = circle.getCenter().getY();
        this.radius = circle.getRadius();
        this.color = circle.getColor();
        this.perimeter = 2 * Math.PI * this.radius;
        this.area = Math.PI * this.radius * this.radius;
    }

    public Point getCenter() {
        return new Point(this.x, this.y);
    }

    public double getRadius() {
        return this.radius;
    }

    public int getColor() {
        return this.color;
    }
}
