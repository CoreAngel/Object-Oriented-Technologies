package com.coreangel.particles.physics;

public class Vector {
    private Point point;

    public Vector(Point point) {
        this.point = point;
    }

    public Vector(double length, double angle) {
        double x = Math.cos(Math.toRadians(angle)) * length;
        double y = Math.sin(Math.toRadians(angle)) * length;

        this.point = new Point(x, y);
    }

    public double getLength() {
        return Math.sqrt(Math.pow(this.point.getX(), 2) + Math.pow(this.point.getY(), 2));
    }

    public double getAngle() {
        double x = this.getX();
        double y = this.getY();

        double degree =  Math.toDegrees(Math.atan(y/x));

        if (x >= 0 && y >= 0) { // 1 quarter
            return degree;
        } else if (x < 0) { // 2, 3 quarter
            return 180.0 + degree;
        } else { // 4 quarter
            return 360.0 + degree;
        }
    }

    public double getX() {
        return this.point.getX();
    }

    public double getY() {
        return this.point.getY();
    }


    @Override
    public String toString() {
        return "Vector2D{" +
                "point=" + point +
                '}' + this.getAngle() + " " + this.getLength();

    }
}
