package com.coreangel.particles.window;

import com.coreangel.particles.board.Board;
import com.coreangel.particles.board.BoardService;
import com.coreangel.particles.particle.ParticleRepository;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame frame;
    private ParticlesCanvas canvas;
    private WindowBar bar;

    public Window(ParticleRepository repository, Board board, BoardService service) {
        this.canvas = new ParticlesCanvas(repository, board);
        this.bar = new WindowBar(service, repository);
    }

    public void createWindow(String title) {
        this.frame = new JFrame(title);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.add(this.bar, BorderLayout.NORTH);
        this.frame.add(this.canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

}
