package com.plattysoft.leonids.initializers;

import com.plattysoft.leonids.Particle;
import java.util.Random;

public class AccelerationInitializer implements ParticleInitializer {
    private float a;
    private float b;
    private int c;
    private int d;

    public AccelerationInitializer(float f, float f2, int i, int i2) {
        this.a = f;
        this.b = f2;
        this.c = i;
        this.d = i2;
    }

    public void initParticle(Particle particle, Random random) {
        float f = (float) this.c;
        if (this.d != this.c) {
            f = (float) (random.nextInt(this.d - this.c) + this.c);
        }
        f = (float) ((((double) f) * 3.141592653589793d) / 180.0d);
        float nextFloat = (random.nextFloat() * (this.b - this.a)) + this.a;
        particle.mAccelerationX = (float) (((double) nextFloat) * Math.cos((double) f));
        particle.mAccelerationY = (float) (Math.sin((double) f) * ((double) nextFloat));
    }
}
