package com.plattysoft.leonids.modifiers;

import com.plattysoft.leonids.Particle;

public class AccelerationModifier implements ParticleModifier {
    private float a;
    private float b;

    public AccelerationModifier(float f, float f2) {
        float f3 = (float) ((((double) f2) * 3.141592653589793d) / 180.0d);
        this.a = (float) (((double) f) * Math.cos((double) f3));
        this.b = (float) (Math.sin((double) f3) * ((double) f));
    }

    public void apply(Particle particle, long j) {
        particle.mCurrentX += (this.a * ((float) j)) * ((float) j);
        particle.mCurrentY += (this.b * ((float) j)) * ((float) j);
    }
}
