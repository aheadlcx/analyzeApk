package qsbk.app.live.animation.leonids;

import com.plattysoft.leonids.Particle;
import com.plattysoft.leonids.initializers.ParticleInitializer;
import java.util.Random;

public class SpeeddModuleAndRangeInitializer implements ParticleInitializer {
    private float a;
    private float b;
    private int c;
    private int d;
    private int e;
    private int f;

    public SpeeddModuleAndRangeInitializer(float f, float f2, int i, int i2) {
        this.a = f;
        this.b = f2;
        this.c = i;
        this.d = i2;
        while (this.c < 0) {
            this.c += 360;
        }
        while (this.d < 0) {
            this.d += 360;
        }
        if (this.c > this.d) {
            int i3 = this.c;
            this.c = this.d;
            this.d = i3;
        }
    }

    public SpeeddModuleAndRangeInitializer(float f, float f2, int i, int i2, int i3) {
        this(f, f2, i, i2);
        this.e = i3;
    }

    public void initParticle(Particle particle, Random random) {
        int i;
        float nextFloat = this.a + (random.nextFloat() * (this.b - this.a));
        if (this.d == this.c) {
            i = this.c;
        } else if (this.e != 0) {
            this.f = (this.f + this.e) % 360;
            i = this.f;
        } else {
            i = random.nextInt(this.d - this.c) + this.c;
        }
        float f = (float) ((((double) i) * 3.141592653589793d) / 180.0d);
        particle.mSpeedX = (float) (((double) nextFloat) * Math.cos((double) f));
        particle.mSpeedY = (float) (Math.sin((double) f) * ((double) nextFloat));
    }
}
