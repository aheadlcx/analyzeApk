package com.plattysoft.leonids.initializers;

import com.plattysoft.leonids.Particle;
import java.util.Random;

public class RotationSpeedInitializer implements ParticleInitializer {
    private float a;
    private float b;

    public RotationSpeedInitializer(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    public void initParticle(Particle particle, Random random) {
        particle.mRotationSpeed = (random.nextFloat() * (this.b - this.a)) + this.a;
    }
}
