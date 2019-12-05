package com.coreangel.particles;

import com.coreangel.particles.board.Board;
import com.coreangel.particles.board.BoardService;
import com.coreangel.particles.particle.ParticleRepository;
import com.coreangel.particles.physics.CollisionService;
import com.coreangel.particles.window.Window;

public class Main {

    public static void main(String[] args) {
        Board board = new Board(500, 400);
        ParticleRepository repository = new ParticleRepository(board);
        repository.createAndAddRandomParticles(10);
        CollisionService collisionService = new CollisionService(repository, board);
        BoardService boardService = new BoardService(collisionService, repository);

        Thread threadBoard = new Thread(boardService);
        threadBoard.start();

        Window window = new Window(repository, board, boardService);
        window.createWindow("Particles");
    }

}
