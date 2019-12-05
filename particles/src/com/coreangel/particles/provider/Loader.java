package com.coreangel.particles.provider;

import com.coreangel.particles.particle.Particle;
import com.coreangel.particles.particle.ParticleRepository;
import com.coreangel.particles.particle.ParticleState;

import java.io.*;

public class Loader {
    public static String fileName = "data";

    public static void load(ParticleRepository repository) throws IOException {
        InputStream is = new FileInputStream(new File(Loader.fileName));
        ObjectInputStream ois = new ObjectInputStream(is);

        try {
            while (true) {
                ParticleState particleState = (ParticleState) ois.readObject();
                if (particleState != null) {
                    Particle particle = Particle.createFromState(particleState);
                    repository.addParticle(particle);
                }
                else {
                    break;
                }
            }
        } catch (EOFException | ClassNotFoundException e) {
            // end of file
        }

        ois.close();
    }
}
