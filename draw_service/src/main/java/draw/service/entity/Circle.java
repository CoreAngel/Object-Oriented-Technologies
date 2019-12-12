package draw.service.entity;

import draw.service.component.ColorComponent;
import draw.service.component.PointComponent;
import draw.service.component.RadiusComponent;
import draw.service.entity.memento.CircleState;
import draw.service.system.drawer.Drawer;
import draw.service.utils.Point;

import java.util.Random;

public class Circle extends AbstractEntity {
    private Point center;
    private double radius;
    private int color;

    public Circle(Point center, double radius) {
        this(center, radius, Drawer.COLORS[(new Random()).nextInt(Drawer.COLORS.length)]);
    }

    public Circle(Point center, double radius, int color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.addComponents(new PointComponent(center), new RadiusComponent(radius), new ColorComponent(this.color));
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }

    @Override
    public CircleState getState() {
        return new CircleState(this);
    }

    public static Circle loadFromState(CircleState state) {
        return new Circle(state.getCenter(), state.getRadius(), state.getColor());
    }
}
