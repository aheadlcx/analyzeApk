package com.plattysoft.leonids.modifiers;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.plattysoft.leonids.Particle;

public class ScaleModifier implements ParticleModifier {
    private float a;
    private float b;
    private long c;
    private long d;
    private long e;
    private float f;
    private Interpolator g;

    public ScaleModifier(float f, float f2, long j, long j2, Interpolator interpolator) {
        this.a = f;
        this.b = f2;
        this.d = j;
        this.c = j2;
        this.e = this.c - this.d;
        this.f = this.b - this.a;
        this.g = interpolator;
    }

    public ScaleModifier(float f, float f2, long j, long j2) {
        this(f, f2, j, j2, new LinearInterpolator());
    }

    public void apply(Particle particle, long j) {
        if (j < this.d) {
            particle.mScale = this.a;
        } else if (j > this.c) {
            particle.mScale = this.b;
        } else {
            float interpolation = this.g.getInterpolation((((float) (j - this.d)) * 1.0f) / ((float) this.e));
            particle.mScale = (interpolation * this.f) + this.a;
        }
    }
}
