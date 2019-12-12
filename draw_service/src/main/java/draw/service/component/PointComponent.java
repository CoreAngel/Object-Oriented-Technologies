package draw.service.component;

import draw.service.utils.Point;

public class PointComponent implements IComponent {
    private double x;
    private double y;

    public PointComponent(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
