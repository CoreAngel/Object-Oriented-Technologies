package draw.service.entity.memento;

import draw.service.entity.Rectangle;
import draw.service.utils.Point;
import draw.service.utils.Size;

public class RectangleState implements IState {
    private double xLeftDown;
    private double yLeftDown;
    private double width;
    private double height;
    private int color;

    public RectangleState(Rectangle rectangle) {
        this.xLeftDown = rectangle.getLeftDownPoint().getX();
        this.yLeftDown = rectangle.getLeftDownPoint().getY();
        this.width = rectangle.getSize().getWidth();
        this.height = rectangle.getSize().getHeight();
        this.color = rectangle.getColor();
    }

    public Point getLeftDownPoint() {
        return new Point(this.xLeftDown, this.yLeftDown);
    }

    public Size getSize() {
        return new Size(this.width, this.height);
    }

    public int getColor() {
        return color;
    }
}
