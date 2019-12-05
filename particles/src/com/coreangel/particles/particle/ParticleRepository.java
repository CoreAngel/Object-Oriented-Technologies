package com.coreangel.particles.particle;

import com.coreangel.particles.board.Board;
import com.coreangel.particles.physics.Point;
import com.coreangel.particles.physics.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleRepository {
    private static List<Color> COLORS = new ArrayList<>();
    private List<Particle> particles = new ArrayList<>();
    private Board board;

    static {
        ParticleRepository.COLORS.add(Color.BLUE);
        ParticleRepository.COLORS.add(Color.ORANGE);
        ParticleRepository.COLORS.add(Color.BLACK);
        ParticleRepository.COLORS.add(Color.CYAN);
        ParticleRepository.COLORS.add(Color.YELLOW);
        ParticleRepository.COLORS.add(Color.RED);
        ParticleRepository.COLORS.add(Color.PINK);
        ParticleRepository.COLORS.add(Color.GREEN);
        ParticleRepository.COLORS.add(Color.MAGENTA);
    }

    public ParticleRepository(Board board) {
        this.board = board;
    }

    public Particle createRandomParticle() {
        Random random = new Random();

        double radius = random.nextDouble() * 15 + 12;
        double x = random.nextDouble() * (board.getWidth() - 2 * radius) + radius;
        double y = random.nextDouble() * (board.getHeight() - 2 * radius) + radius;
        Point position = new Point(x, y);

        double xv = random.nextDouble() * 500 - 250;
        double yv = random.nextDouble() * 500 - 250;
        Vector velocity = new Vector(xv, yv);

        int colorIndex = random.nextInt(ParticleRepository.COLORS.size());
        Color color = ParticleRepository.COLORS.get(colorIndex);

        return new Particle(position, radius, velocity, color);
    }

    public void addParticle(Particle particle) {
        this.particles.add(particle);
    }

    public void createAndAddRandomParticles(int number) {
        for (int i = 0; i < number; i++) {
            Particle particle = this.createRandomParticle();
            this.addParticle(particle);
        }
    }

    public void clearList() {
        this.particles.clear();
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public void moveAll(double time) {
        for (Particle particle: particles) {
            particle.move(time);
        }
    }
}
