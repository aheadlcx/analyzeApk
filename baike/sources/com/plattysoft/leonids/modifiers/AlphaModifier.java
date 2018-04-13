package com.plattysoft.leonids.modifiers;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.plattysoft.leonids.Particle;

public class AlphaModifier implements ParticleModifier {
    private int a;
    private int b;
    private long c;
    private long d;
    private float e;
    private float f;
    private Interpolator g;

    public AlphaModifier(int i, int i2, long j, long j2, Interpolator interpolator) {
        this.a = i;
        this.b = i2;
        this.c = j;
        this.d = j2;
        this.e = (float) (this.d - this.c);
        this.f = (float) (this.b - this.a);
        this.g = interpolator;
    }

    public AlphaModifier(int i, int i2, long j, long j2) {
        this(i, i2, j, j2, new LinearInterpolator());
    }

    public void apply(Particle particle, long j) {
        if (j < this.c) {
            particle.mAlpha = this.a;
        } else if (j > this.d) {
            particle.mAlpha = this.b;
        } else {
            float f = (float) this.a;
            particle.mAlpha = (int) ((this.g.getInterpolation((((float) (j - this.c)) * 1.0f) / this.e) * this.f) + f);
        }
    }
}
