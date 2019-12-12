package draw.service.entity;

import draw.service.component.ColorComponent;
import draw.service.component.PointComponent;
import draw.service.entity.memento.IState;
import draw.service.entity.memento.RectangleState;
import draw.service.system.drawer.Drawer;
import draw.service.utils.Point;
import draw.service.utils.Size;

import java.util.Random;

public class Rectangle extends AbstractEntity {
    private Point leftDownPoint;
    private Size size;
    private int color;

    public Rectangle(Point leftDownPoint, Size size) {
        this(leftDownPoint, size, Drawer.COLORS[(new Random()).nextInt(Drawer.COLORS.length)]);
    }

    public Rectangle(Point leftDownPoint, Size size, int color) {
        this.leftDownPoint = leftDownPoint;
        this.size = size;
        this.color = color;

        double x = leftDownPoint.getX();
        double y = leftDownPoint.getY();
        double height = size.getHeight();
        double width = size.getWidth();

        PointComponent leftDown = new PointComponent(leftDownPoint);
        PointComponent leftUp = new PointComponent(new Point(x, y + height));
        PointComponent rightDown = new PointComponent(new Point(x + width, y));
        PointComponent rightUp = new PointComponent(new Point(x + width, y + height));

        this.addComponents(leftDown, leftUp, rightUp, rightDown, new ColorComponent(this.color));
    }

    public Point getLeftDownPoint() {
        return leftDownPoint;
    }

    public Size getSize() {
        return size;
    }

    public int getColor() {
        return color;
    }

    public static Rectangle loadFromState(RectangleState state) {
        return new Rectangle(state.getLeftDownPoint(), state.getSize(), state.getColor());
    }

    @Override
    public RectangleState getState() {
        return new RectangleState(this);
    }
}
