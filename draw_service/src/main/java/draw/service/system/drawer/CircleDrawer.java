package draw.service.system.drawer;

import draw.service.utils.Point;

import java.awt.*;
import java.util.Random;

public class CircleDrawer implements Drawer {
    private Point point;
    private double radius;
    private int color;

    public CircleDrawer(Point point, double radius, int color) {
        this.point = point;
        this.radius = radius;
        this.color = color;
    }

    public void draw(Graphics g) {
        int x = (int)(point.getX() - radius);
        int y = (int)(point.getY() - radius);
        int size = (int)(radius * 2);
        g.setColor(new Color(color));
        g.fillOval(x , y, size, size);
        g.setColor(Color.BLACK);
        g.drawOval(x , y, size, size);
    }
}
