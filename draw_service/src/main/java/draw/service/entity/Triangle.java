package draw.service.entity;

import draw.service.component.ColorComponent;
import draw.service.component.PointComponent;
import draw.service.entity.memento.IState;
import draw.service.entity.memento.TriangleState;
import draw.service.system.drawer.Drawer;
import draw.service.utils.Point;

import java.util.Random;

public class Triangle extends AbstractEntity {
    private Point one;
    private Point two;
    private Point three;
    private int color;

    public Triangle(Point one, Point two, Point three) {
        this(one, two, three, Drawer.COLORS[(new Random()).nextInt(Drawer.COLORS.length)]);
    }

    public Triangle(Point one, Point two, Point three, int color) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.color = color;

        this.addComponents(new PointComponent(one), new PointComponent(two), new PointComponent(three), new ColorComponent(this.color));
    }

    public Point getOne() {
        return one;
    }

    public Point getTwo() {
        return two;
    }

    public Point getThree() {
        return three;
    }

    public int getColor() {
        return color;
    }

    public static Triangle loadFromState(TriangleState state) {
        return new Triangle(state.getOne(), state.getTwo(), state.getThree(), state.getColor());
    }

    @Override
    public TriangleState getState() {
        return new TriangleState(this);
    }
}
