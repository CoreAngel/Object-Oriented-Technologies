package com.coreangel.particles.particle;

import com.coreangel.particles.physics.Line;
import com.coreangel.particles.physics.Point;
import com.coreangel.particles.physics.Vector;

import java.awt.*;

public class Particle {

    private Point position;
    private double radius;
    private double mass;
    private Vector velocity;
    private Line path;
    private Color color;

    public Particle(Point position, double radius, Vector velocity, Color color) {
        this.position = position;
        this.radius = radius;
        this.velocity = velocity;
        this.mass = radius * 4;
        this.color = color;

        this.path = calculateLine();
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public double getMass() {
        return mass;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Point getPosition() {
        return position;
    }

    public double getRadius() {
        return radius;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Line getPath() {
        return path;
    }

    public Color getColor() {
        return color;
    }

    private Line calculateLine() {
        double endPointX = this.position.getX() + this.velocity.getX();
        double endPointY = this.position.getY() + this.velocity.getY();

        return new Line(position, new Point(endPointX, endPointY));
    }

    public Point getPositionInTime(double time) {
        double angle = this.velocity.getAngle();
        double length = this.velocity.getLength();
        double lengthInTime = length / (1000 / time);

        Vector newVector = new Vector(lengthInTime, angle);
        return this.getPosition().moveByVector(newVector);
    }

    public void move(double time) {
        Point endPoint = this.getPositionInTime(time);
        this.setPosition(endPoint);
    }

    public void moveByLength(double length) {
        double angle = this.velocity.getAngle();
        Vector newVector = new Vector(length, angle);
        Point newPosition = this.getPosition().moveByVector(newVector);
        this.setPosition(newPosition);
    }

    public ParticleState getState() {
        return new ParticleState(this.position, this.radius, this.velocity, this.color);
    }

    public static Particle createFromState(ParticleState state) {
        return new Particle(state.getPosition(), state.getRadius(), state.getVelocity(), state.getColor());
    }

    @Override
    public String toString() {
        return "Particle{" +
                "position=" + position +
                ", radius=" + radius +
                ", velocity=" + velocity +
                '}';
    }
}
