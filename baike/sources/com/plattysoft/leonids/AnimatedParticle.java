package com.plattysoft.leonids;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;

public class AnimatedParticle extends Particle {
    private AnimationDrawable c;
    private int d;

    public AnimatedParticle(AnimationDrawable animationDrawable) {
        this.c = animationDrawable;
        this.a = ((BitmapDrawable) this.c.getFrame(0)).getBitmap();
        this.d = 0;
        for (int i = 0; i < this.c.getNumberOfFrames(); i++) {
            this.d += this.c.getDuration(i);
        }
    }

    public boolean update(long j) {
        int i = 0;
        boolean update = super.update(j);
        if (update) {
            long j2 = 0;
            long j3 = j - this.b;
            if (j3 > ((long) this.d)) {
                if (this.c.isOneShot()) {
                    return false;
                }
                j3 %= (long) this.d;
            }
            while (i < this.c.getNumberOfFrames()) {
                j2 += (long) this.c.getDuration(i);
                if (j2 > j3) {
                    this.a = ((BitmapDrawable) this.c.getFrame(i)).getBitmap();
                    break;
                }
                i++;
            }
        }
        return update;
    }
}
