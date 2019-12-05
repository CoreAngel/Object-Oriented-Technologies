package com.coreangel.particles.window;

import com.coreangel.particles.board.Board;
import com.coreangel.particles.particle.Particle;
import com.coreangel.particles.particle.ParticleRepository;
import com.coreangel.particles.physics.Point;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParticlesCanvas extends JPanel {
    private ParticleRepository repository;
    public static int FPS = 60;

    public ParticlesCanvas(ParticleRepository repository, Board board) {
        this.repository = repository;

        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(board.getWidth(), board.getHeight()));

        Timer timer = new Timer(1000 / ParticlesCanvas.FPS, e -> this.repaint());
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Particle> particles = this.repository.getParticles();

        for (Particle particle : particles) {
            g.setColor(particle.getColor());

            Point pos = particle.getPosition();
            double radius = particle.getRadius();

            int posX = (int)(pos.getX() - radius);
            int posY = (int)(pos.getY() - radius);
            int size = (int)(2 * radius);
            g.fillOval(posX, posY, size, size);
        }
    }
}
