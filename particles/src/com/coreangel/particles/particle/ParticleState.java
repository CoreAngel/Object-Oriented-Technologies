package com.coreangel.particles.particle;

import com.coreangel.particles.physics.Point;
import com.coreangel.particles.physics.Vector;

import java.awt.*;
import java.io.Serializable;

public class ParticleState implements Serializable {
    private double posX;
    private double posY;
    private double radius;
    private double velX;
    private double velY;
    private int rgb;

    public ParticleState(Point position, double radius, Vector velocity, Color color) {
        this.posX = position.getX();
        this.posY = position.getY();
        this.radius = radius;
        this.velX = velocity.getX();
        this.velY = velocity.getY();
        this.rgb = color.getRGB();
    }

    public Point getPosition() {
        return new Point(this.posX, this.posY);
    }

    public double getRadius() {
        return radius;
    }

    public Vector getVelocity() {
        return new Vector(new Point(this.velX, this.velY));
    }

    public Color getColor() {
        return new Color(this.rgb);
    }
}
