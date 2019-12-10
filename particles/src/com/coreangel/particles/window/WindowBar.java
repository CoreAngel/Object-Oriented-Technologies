package com.coreangel.particles.window;

import com.coreangel.particles.board.BoardService;
import com.coreangel.particles.particle.ParticleRepository;
import com.coreangel.particles.provider.Loader;
import com.coreangel.particles.provider.Writer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WindowBar extends JPanel {
    private BoardService service;
    private ParticleRepository repository;

    private JButton toogleBtn = new JButton();
    private JButton saveBtn = new JButton("Save state");
    private JButton loadBtn = new JButton("Load state");
    private JButton addBtn = new JButton("Add");

    public WindowBar(BoardService service, ParticleRepository repository) {
        this.service = service;
        this.repository = repository;

        this.setBackground(Color.WHITE);
        this.add(this.toogleBtn, BorderLayout.LINE_START);
        this.add(this.saveBtn, BorderLayout.LINE_START);
        this.add(this.loadBtn, BorderLayout.LINE_START);
        this.add(this.addBtn, BorderLayout.LINE_START);
        this.setRunningBtnLabel();

        this.setToogleAction();
        this.setSaveAction();
        this.setLoadAction();
        this.setAddAction();
    }

    private void setToogleAction() {
        this.toogleBtn.addActionListener(e -> {
            this.service.toogleRunning();
            this.setRunningBtnLabel();
        });
    }

    private void setSaveAction() {
        this.saveBtn.addActionListener(e -> {
            boolean running = this.stopAndWaitForFinished();
            try {
                Writer.save(repository);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (running) {
                    this.service.startRunning();
                }
            }

        });
    }

    private void setLoadAction() {
        this.loadBtn.addActionListener(e -> {
            boolean running = this.stopAndWaitForFinished();
            try {
                repository.clearList();
                Loader.load(repository);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (running) {
                    this.service.startRunning();
                }
            }
        });
    }

    private void setAddAction() {
        this.addBtn.addActionListener(e -> this.repository.createAndAddRandomParticles(1));
    }

    private boolean stopAndWaitForFinished() {
        boolean currentRunning = service.isRunning();
        service.stopRunning();
        while (true) {
            try {
                if (service.isFinished()) { break; }
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return currentRunning;
    }

    private void setRunningBtnLabel() {
        if (service.isRunning()) {
            this.toogleBtn.setText("Stop");
        } else {
            this.toogleBtn.setText("Start");
        }
    }
}
