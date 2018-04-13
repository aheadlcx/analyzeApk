package com.plattysoft.leonids.initializers;

import com.plattysoft.leonids.Particle;
import java.util.Random;

public class ScaleInitializer implements ParticleInitializer {
    private float a;
    private float b;

    public ScaleInitializer(float f, float f2) {
        this.b = f;
        this.a = f2;
    }

    public void initParticle(Particle particle, Random random) {
        particle.mScale = (random.nextFloat() * (this.a - this.b)) + this.b;
    }
}
