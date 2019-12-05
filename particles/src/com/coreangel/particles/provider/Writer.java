package com.coreangel.particles.provider;

import com.coreangel.particles.particle.Particle;
import com.coreangel.particles.particle.ParticleRepository;
import com.coreangel.particles.particle.ParticleState;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Writer {
    public static String fileName = "data";

    public static void save(ParticleRepository repository) throws IOException {
        List<Particle> particles = repository.getParticles();

        FileOutputStream fos = new FileOutputStream(Writer.fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Particle particle: particles) {
            ParticleState state = particle.getState();
            oos.writeObject(state);
        }

        oos.close();
    }
}
