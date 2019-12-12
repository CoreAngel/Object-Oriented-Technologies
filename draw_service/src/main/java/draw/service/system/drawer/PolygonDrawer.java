package draw.service.system.drawer;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import draw.service.utils.Point;

public class PolygonDrawer implements Drawer {
    private int[] xPoly;
    private int[] yPoly;
    private int color;

    public PolygonDrawer(List<Point> points, int color) {
        this.color = color;
        List<Integer> xArr = new ArrayList<Integer>();
        List<Integer> yArr = new ArrayList<Integer>();

        for (Point point : points) {
            xArr.add((int) point.getX());
            yArr.add((int) point.getY());
        }

        this.xPoly = this.convertIntegers(xArr);
        this.yPoly = this.convertIntegers(yArr);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(this.color));
        g.fillPolygon(this.xPoly, this.yPoly, this.xPoly.length);
        g.setColor(Color.BLACK);
        g.drawPolygon(this.xPoly, this.yPoly, this.xPoly.length);
    }

    private int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++) {
            ret[i] = integers.get(i);
        }
        return ret;
    }
}
