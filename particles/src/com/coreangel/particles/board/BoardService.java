package com.coreangel.particles.board;

import com.coreangel.particles.particle.ParticleRepository;
import com.coreangel.particles.physics.CollisionService;

import java.util.Date;

public class BoardService implements Runnable {
    private CollisionService service;
    private ParticleRepository repository;
    private boolean running = true;
    private boolean finished = true;
    public static int FPS = 120;

    public BoardService(CollisionService service, ParticleRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    public void run() {
        int millisecondForFrame = 1000 / BoardService.FPS;
        Date lastFrameTime = new Date();

        while (!Thread.currentThread().isInterrupted()) {
            if (this.running) {
                this.finished = false;
                this.service.calculateCollision(millisecondForFrame);
                this.repository.moveAll(millisecondForFrame);

                long diffInMilli = this.getDiffInMs(new Date(), lastFrameTime);
                try {
                    if (diffInMilli < millisecondForFrame) {
                        Thread.sleep(millisecondForFrame - diffInMilli);
                    }
                    lastFrameTime = new Date();
                } catch (InterruptedException e) {
                    this.close();
                } finally {
                    this.finished = true;
                }
            }
        }

    }

    public boolean isRunning() {
        return running;
    }

    public boolean isFinished() {
        return finished;
    }

    public void toogleRunning() {
        this.running = !this.running;
    }

    public void stopRunning() {
        this.running = false;
    }

    public void startRunning() {
        this.running = true;
    }

    public void close() {
        Thread.currentThread().interrupt();
    }

    public long getDiffInMs(Date now, Date prev) {
        return now.getTime() - prev.getTime();
    }
}
