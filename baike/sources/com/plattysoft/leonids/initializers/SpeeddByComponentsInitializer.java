package com.plattysoft.leonids.initializers;

import com.plattysoft.leonids.Particle;
import java.util.Random;

public class SpeeddByComponentsInitializer implements ParticleInitializer {
    private float a;
    private float b;
    private float c;
    private float d;

    public SpeeddByComponentsInitializer(float f, float f2, float f3, float f4) {
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
    }

    public void initParticle(Particle particle, Random random) {
        particle.mSpeedX = (random.nextFloat() * (this.b - this.a)) + this.a;
        particle.mSpeedY = (random.nextFloat() * (this.d - this.c)) + this.c;
    }
}
