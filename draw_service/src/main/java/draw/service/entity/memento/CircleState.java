package draw.service.entity.memento;

import draw.service.entity.Circle;
import draw.service.utils.Point;

public class CircleState implements IState {
    private double x;
    private double y;
    private double radius;
    private int color;

    public CircleState(Circle circle) {
        this.x = circle.getCenter().getX();
        this.y = circle.getCenter().getY();
        this.radius = circle.getRadius();
        this.color = circle.getColor();
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
