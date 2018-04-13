package com.plattysoft.leonids;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.plattysoft.leonids.modifiers.ParticleModifier;
import java.util.List;

public class Particle {
    protected Bitmap a;
    protected long b;
    private Matrix c;
    private Paint d;
    private float e;
    private float f;
    private float g;
    private long h;
    private int i;
    private int j;
    private List<ParticleModifier> k;
    public float mAccelerationX;
    public float mAccelerationY;
    public int mAlpha;
    public float mCurrentX;
    public float mCurrentY;
    public float mInitialRotation;
    public float mRotationSpeed;
    public float mScale;
    public float mSpeedX;
    public float mSpeedY;

    protected Particle() {
        this.mScale = 1.0f;
        this.mAlpha = 255;
        this.mInitialRotation = 0.0f;
        this.mRotationSpeed = 0.0f;
        this.mSpeedX = 0.0f;
        this.mSpeedY = 0.0f;
        this.c = new Matrix();
        this.d = new Paint();
    }

    public Particle(Bitmap bitmap) {
        this();
        this.a = bitmap;
    }

    public void init() {
        this.mScale = 1.0f;
        this.mAlpha = 255;
    }

    public void configure(long j, float f, float f2) {
        this.i = this.a.getWidth() / 2;
        this.j = this.a.getHeight() / 2;
        this.e = f - ((float) this.i);
        this.f = f2 - ((float) this.j);
        this.mCurrentX = this.e;
        this.mCurrentY = this.f;
        this.h = j;
    }

    public boolean update(long j) {
        long j2 = j - this.b;
        if (j2 > this.h) {
            return false;
        }
        this.mCurrentX = (this.e + (this.mSpeedX * ((float) j2))) + ((this.mAccelerationX * ((float) j2)) * ((float) j2));
        this.mCurrentY = (this.f + (this.mSpeedY * ((float) j2))) + ((this.mAccelerationY * ((float) j2)) * ((float) j2));
        this.g = this.mInitialRotation + ((this.mRotationSpeed * ((float) j2)) / 1000.0f);
        for (int i = 0; i < this.k.size(); i++) {
            ((ParticleModifier) this.k.get(i)).apply(this, j2);
        }
        return true;
    }

    public void draw(Canvas canvas) {
        this.c.reset();
        this.c.postRotate(this.g, (float) this.i, (float) this.j);
        this.c.postScale(this.mScale, this.mScale, (float) this.i, (float) this.j);
        this.c.postTranslate(this.mCurrentX, this.mCurrentY);
        this.d.setAlpha(this.mAlpha);
        canvas.drawBitmap(this.a, this.c, this.d);
    }

    public Particle activate(long j, List<ParticleModifier> list) {
        this.b = j;
        this.k = list;
        return this;
    }
}
