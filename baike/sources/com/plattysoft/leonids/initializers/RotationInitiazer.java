package com.plattysoft.leonids.initializers;

import com.plattysoft.leonids.Particle;
import java.util.Random;

public class RotationInitiazer implements ParticleInitializer {
    private int a;
    private int b;

    public RotationInitiazer(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public void initParticle(Particle particle, Random random) {
        particle.mInitialRotation = (float) (random.nextInt(this.b - this.a) + this.a);
    }
}
