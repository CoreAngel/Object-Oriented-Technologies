package com.coreangel.particles.physics;

import com.coreangel.particles.board.Board;
import com.coreangel.particles.board.BorderCollisionDirection;
import com.coreangel.particles.particle.Particle;
import com.coreangel.particles.particle.ParticleRepository;

import java.util.List;
import java.util.Optional;

public class CollisionService {
    private ParticleRepository particlesRepository;
    private Board board;

    public CollisionService(ParticleRepository particlesRepository, Board board) {
        this.particlesRepository = particlesRepository;
        this.board = board;
    }

    public void calculateCollision(double time) {
        List<Particle> particles = this.particlesRepository.getParticles();

        for (int i = 0; i < particles.size(); i++) {
            Particle toCheck = particles.get(i);

            this.isIntersectWithBoardBorder(toCheck).ifPresent(direction -> {
                Vector newVelocity;
                Vector oldVelocity = toCheck.getVelocity();
                Point newPosition;
                Point oldPosition = toCheck.getPosition();

                if (direction == BorderCollisionDirection.UP) {
                    newPosition = new Point(oldPosition.getX(), toCheck.getRadius());
                    newVelocity = new Vector(new Point(oldVelocity.getX(), Math.abs(oldVelocity.getY())));
                } else if (direction == BorderCollisionDirection.DOWN) {
                    newPosition = new Point(oldPosition.getX(), this.board.getHeight() - toCheck.getRadius());
                    newVelocity = new Vector(new Point(oldVelocity.getX(), -Math.abs(oldVelocity.getY())));
                } else if (direction == BorderCollisionDirection.LEFT) {
                    newPosition = new Point(toCheck.getRadius(), oldPosition.getY());
                    newVelocity = new Vector(new Point(Math.abs(oldVelocity.getX()), oldVelocity.getY()));
                } else if (direction == BorderCollisionDirection.RIGHT) {
                    newPosition = new Point(this.board.getWidth() - toCheck.getRadius(), oldPosition.getY());
                    newVelocity = new Vector(new Point(-Math.abs(oldVelocity.getX()), oldVelocity.getY()));
                } else {
                    newPosition = oldPosition;
                    newVelocity = oldVelocity;
                }

                toCheck.setPosition(newPosition);
                toCheck.setVelocity(newVelocity);
            });

            for (int j = i+1; j < particles.size(); j++) {
                Particle checkWith = particles.get(j);

                if (this.isIntersectionBetweenParticles(toCheck, checkWith, time)) {
                    this.reactToIntersection(toCheck, checkWith);
                }
                else {
                    this.isIntersectionInBoard(toCheck, checkWith).ifPresent(point -> {
                        if (this.isIntersectPointBetweenStartAndEndParticlePosition(toCheck, point, time)
                        && this.isIntersectPointBetweenStartAndEndParticlePosition(checkWith, point, time)) {
                            this.reactToIntersection(toCheck, checkWith);
                        }
                    });
                }
            }

        }
    }

    private boolean isIntersectionBetweenParticles(Particle p1, Particle p2, double time) {
        Point p1EndPoint = p1.getPositionInTime(time);
        Point p2EndPoint = p2.getPositionInTime(time);

        double distanceBetweenPoints = p1EndPoint.getDistanceWith(p2EndPoint);
        double radiusSum = p1.getRadius() + p2.getRadius();

        return distanceBetweenPoints <= radiusSum;
    }

    private Optional<BorderCollisionDirection> isIntersectWithBoardBorder(Particle particle) {
        Point position = particle.getPosition();
        double radius = particle.getRadius();

        double x = position.getX();
        double y = position.getY();

        double bsx = radius;
        double bex = this.board.getWidth() - radius;
        double bsy = radius;
        double bey = this.board.getHeight() - radius;

        if (x <= bsx) {
            return Optional.of(BorderCollisionDirection.LEFT);
        } else if (x >= bex) {
            return Optional.of(BorderCollisionDirection.RIGHT);
        } else if (y <= bsy) {
            return Optional.of(BorderCollisionDirection.UP);
        } else if (y >= bey) {
            return Optional.of(BorderCollisionDirection.DOWN);
        } else {
            return Optional.empty();
        }
    }

    private void reactToIntersection(Particle p1, Particle p2) {
        Vector newP1Velocity = this.calculateCollisionVector(p1, p2);
        Vector newP2Velocity = this.calculateCollisionVector(p2, p1);

        p1.setVelocity(newP1Velocity);
        p2.setVelocity(newP2Velocity);

        this.moveParticleWithLessMassOutsideMoreMassParticle(p1, p2);
    }

    private Vector calculateCollisionVector(Particle main, Particle sub) {
        double v1x = main.getVelocity().getX();
        double v1y = main.getVelocity().getY();
        double v2x = sub.getVelocity().getX();
        double v2y = sub.getVelocity().getY();

        double m1 = main.getMass();
        double m2 = sub.getMass();

        double x = (v1x * (m1 - m2) + (2 * m2 * v2x)) / (m1 + m2);
        double y = (v1y * (m1 - m2) + (2 * m2 * v2y)) / (m1 + m2);

        return new Vector(new Point(x, y));
    }

    public void moveParticleWithLessMassOutsideMoreMassParticle(Particle p1, Particle p2) {
        Particle particleWithLessMass;
        Particle particleWithMoreMass;

        if (p1.getMass() >= p2.getMass()) {
            particleWithLessMass = p2;
            particleWithMoreMass = p1;
        } else {
            particleWithLessMass = p1;
            particleWithMoreMass = p2;
        }

        Point lessMassPoint = particleWithLessMass.getPosition();
        Point moreMassPoint = particleWithMoreMass.getPosition();

        double distance = lessMassPoint.getDistanceWith(moreMassPoint);
        double radiusSum = particleWithLessMass.getRadius() + particleWithMoreMass.getRadius();

        double diff = radiusSum - distance;
        particleWithLessMass.moveByLength(diff);
    }

    private Optional<Point> isIntersectionInBoard(Particle p1, Particle p2) {
        Optional<Point> optPoint = p1.getPath().getPointOfIntersection(p2.getPath());

        if (optPoint.isPresent()) {
            Point pointOfIntersection = optPoint.get();

            if (board.isPointInside(pointOfIntersection)) {
                return optPoint;
            }
        }

        return Optional.empty();
    }

    private boolean isIntersectPointBetweenStartAndEndParticlePosition(Particle particle, Point point, double time) {
        Point particleStartPoint = particle.getPosition();
        Point particleEndPoint = particle.getPositionInTime(time);

        double sx = particleStartPoint.getX();
        double sy = particleStartPoint.getY();

        double ex = particleEndPoint.getX();
        double ey = particleEndPoint.getY();

        double ix = point.getX();
        double iy = point.getY();

        return sx <= ix && ex >= ix && sy <= iy && ey >= iy;
    }

}
