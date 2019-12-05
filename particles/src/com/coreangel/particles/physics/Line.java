package com.coreangel.particles.physics;

import java.util.Optional;

public class Line {
    private Point p1;
    private Point p2;

    private double a;
    private double b;
    private double c;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;

        this.calculateLineFromPoints(p1, p2);
    }

    public Line moveLineByVector(Vector vector) {
        Point p1 = this.p1.moveByVector(vector);
        Point p2 = this.p2.moveByVector(vector);
        return new Line(p1, p2);
    }

    private void calculateLineFromPoints(Point p1, Point p2) {
        if (p1.getX() == p2.getX()) {
            this.a = 1;
            this.b = 0;
            this.c = -p1.getX();
        } else {
            this.a = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
            this.b = -1;
            this.c = (this.a * -p1.getX()) + p1.getY();
        }
    }

    public Optional<Point> getPointOfIntersection(Line line) {
        if (this.isParallelWith(line)) {
            return Optional.empty();
        } else {
            double w = (this.a * line.getB()) - (line.getA() * this.b);
            double wx = ((-this.c) * line.getB()) - ((-line.getC()) * this.b);
            double wy = (this.a * (-line.getC())) - (line.getA() * (-this.c));

            double x = wx / w;
            double y = wy / w;

            return Optional.of(new Point(x, y));
        }
    }

    public boolean isParallelWith(Line line) {
        return this.a * line.getB() == line.getA() * this.b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    @Override
    public String toString() {
        return "Line{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
