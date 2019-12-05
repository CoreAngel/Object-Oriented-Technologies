package com.coreangel.particles.board;

import com.coreangel.particles.physics.Point;

public class Board {
    private int width;
    private int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isPointInside(Point point) {
        double x = point.getX();
        double y = point.getY();

        return x > 0 && y > 0 && x < this.width && y < this.height;
    }
}
