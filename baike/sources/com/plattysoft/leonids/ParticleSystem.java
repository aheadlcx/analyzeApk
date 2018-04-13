package com.plattysoft.leonids;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.plattysoft.leonids.initializers.AccelerationInitializer;
import com.plattysoft.leonids.initializers.ParticleInitializer;
import com.plattysoft.leonids.initializers.RotationInitiazer;
import com.plattysoft.leonids.initializers.RotationSpeedInitializer;
import com.plattysoft.leonids.initializers.ScaleInitializer;
import com.plattysoft.leonids.initializers.SpeeddByComponentsInitializer;
import com.plattysoft.leonids.initializers.SpeeddModuleAndRangeInitializer;
import com.plattysoft.leonids.modifiers.AlphaModifier;
import com.plattysoft.leonids.modifiers.ParticleModifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class ParticleSystem {
    private ViewGroup a;
    private int b;
    private Random c;
    private ParticleField d;
    private ArrayList<Particle> e;
    private final ArrayList<Particle> f;
    private long g;
    private long h;
    private float i;
    private int j;
    private long k;
    private List<ParticleModifier> l;
    private List<ParticleInitializer> m;
    private ValueAnimator n;
    private Timer o;
    private float p;
    private int[] q;
    private int r;
    private int s;
    private int t;
    private int u;

    static /* synthetic */ long b(ParticleSystem particleSystem, long j) {
        long j2 = particleSystem.h + j;
        particleSystem.h = j2;
        return j2;
    }

    private ParticleSystem(Activity activity, int i, long j, int i2) {
        this.f = new ArrayList();
        this.h = 0;
        this.c = new Random();
        this.a = (ViewGroup) activity.findViewById(i2);
        this.l = new ArrayList();
        this.m = new ArrayList();
        this.b = i;
        this.e = new ArrayList();
        this.g = j;
        this.q = new int[2];
        this.a.getLocationInWindow(this.q);
        this.p = activity.getResources().getDisplayMetrics().xdpi / 160.0f;
    }

    public ParticleSystem(Activity activity, int i, int i2, long j) {
        this(activity, i, activity.getResources().getDrawable(i2), j, 16908290);
    }

    public ParticleSystem(Activity activity, int i, int i2, long j, int i3) {
        this(activity, i, activity.getResources().getDrawable(i2), j, i3);
    }

    public ParticleSystem(Activity activity, int i, Drawable drawable, long j) {
        this(activity, i, drawable, j, 16908290);
    }

    public ParticleSystem(Activity activity, int i, Drawable drawable, long j, int i2) {
        int i3 = 0;
        this(activity, i, j, i2);
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            while (i3 < this.b) {
                this.e.add(new Particle(bitmap));
                i3++;
            }
        } else if (drawable instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            while (i3 < this.b) {
                this.e.add(new AnimatedParticle(animationDrawable));
                i3++;
            }
        }
    }

    public float dpToPx(float f) {
        return this.p * f;
    }

    public ParticleSystem(Activity activity, int i, Bitmap bitmap, long j) {
        this(activity, i, bitmap, j, 16908290);
    }

    public ParticleSystem(Activity activity, int i, Bitmap bitmap, long j, int i2) {
        this(activity, i, j, i2);
        for (int i3 = 0; i3 < this.b; i3++) {
            this.e.add(new Particle(bitmap));
        }
    }

    public ParticleSystem(Activity activity, int i, AnimationDrawable animationDrawable, long j) {
        this(activity, i, animationDrawable, j, 16908290);
    }

    public ParticleSystem(Activity activity, int i, AnimationDrawable animationDrawable, long j, int i2) {
        this(activity, i, j, i2);
        for (int i3 = 0; i3 < this.b; i3++) {
            this.e.add(new AnimatedParticle(animationDrawable));
        }
    }

    public ParticleSystem addModifier(ParticleModifier particleModifier) {
        this.l.add(particleModifier);
        return this;
    }

    public ParticleSystem setSpeedRange(float f, float f2) {
        this.m.add(new SpeeddModuleAndRangeInitializer(dpToPx(f), dpToPx(f2), 0, 360));
        return this;
    }

    public ParticleSystem setSpeedModuleAndAngleRange(float f, float f2, int i, int i2) {
        this.m.add(new SpeeddModuleAndRangeInitializer(dpToPx(f), dpToPx(f2), i, i2));
        return this;
    }

    public ParticleSystem setSpeedByComponentsRange(float f, float f2, float f3, float f4) {
        this.m.add(new SpeeddByComponentsInitializer(dpToPx(f), dpToPx(f2), dpToPx(f3), dpToPx(f4)));
        return this;
    }

    public ParticleSystem setInitialRotationRange(int i, int i2) {
        this.m.add(new RotationInitiazer(i, i2));
        return this;
    }

    public ParticleSystem setScaleRange(float f, float f2) {
        this.m.add(new ScaleInitializer(f, f2));
        return this;
    }

    public ParticleSystem setRotationSpeed(float f) {
        this.m.add(new RotationSpeedInitializer(f, f));
        return this;
    }

    public ParticleSystem setRotationSpeedRange(float f, float f2) {
        this.m.add(new RotationSpeedInitializer(f, f2));
        return this;
    }

    public ParticleSystem setAccelerationModuleAndAndAngleRange(float f, float f2, int i, int i2) {
        this.m.add(new AccelerationInitializer(dpToPx(f), dpToPx(f2), i, i2));
        return this;
    }

    public ParticleSystem setAcceleration(float f, int i) {
        this.m.add(new AccelerationInitializer(f, f, i, i));
        return this;
    }

    public ParticleSystem setParentViewGroup(ViewGroup viewGroup) {
        this.a = viewGroup;
        return this;
    }

    public ParticleSystem setStartTime(int i) {
        this.h = (long) i;
        return this;
    }

    public ParticleSystem setFadeOut(long j, Interpolator interpolator) {
        this.l.add(new AlphaModifier(255, 0, this.g - j, this.g, interpolator));
        return this;
    }

    public ParticleSystem setFadeOut(long j) {
        return setFadeOut(j, new LinearInterpolator());
    }

    public void emitWithGravity(View view, int i, int i2, int i3) {
        a(view, i);
        b(i2, i3);
    }

    public void emit(View view, int i, int i2) {
        emitWithGravity(view, 17, i, i2);
    }

    public void emit(View view, int i) {
        emitWithGravity(view, 17, i);
    }

    public void emitWithGravity(View view, int i, int i2) {
        a(view, i);
        a(i2);
    }

    private void a(int i) {
        this.j = 0;
        this.i = ((float) i) / 1000.0f;
        this.d = new ParticleField(this.a.getContext());
        this.a.addView(this.d);
        this.k = -1;
        this.d.setParticles(this.f);
        b(i);
        this.o = new Timer();
        this.o.schedule(new a(this), 0, 50);
    }

    public void emit(int i, int i2, int i3, int i4) {
        a(i, i2);
        b(i3, i4);
    }

    private void a(int i, int i2) {
        this.r = i - this.q[0];
        this.s = this.r;
        this.t = i2 - this.q[1];
        this.u = this.t;
    }

    private void b(int i, int i2) {
        this.j = 0;
        this.i = ((float) i) / 1000.0f;
        this.d = new ParticleField(this.a.getContext());
        this.a.addView(this.d);
        this.d.setParticles(this.f);
        b(i);
        this.k = (long) i2;
        a(new LinearInterpolator(), ((long) i2) + this.g);
    }

    public void emit(int i, int i2, int i3) {
        a(i, i2);
        a(i3);
    }

    public void updateEmitPoint(int i, int i2) {
        a(i, i2);
    }

    public void oneShot(View view, int i) {
        oneShot(view, i, new LinearInterpolator());
    }

    public void oneShot(View view, int i, Interpolator interpolator) {
        int i2 = 0;
        a(view, 17);
        this.j = 0;
        this.k = this.g;
        while (i2 < i && i2 < this.b) {
            a(0);
            i2++;
        }
        this.d = new ParticleField(this.a.getContext());
        this.a.addView(this.d);
        this.d.setParticles(this.f);
        a(interpolator, this.g);
    }

    private void a(Interpolator interpolator, long j) {
        this.n = ValueAnimator.ofInt(new int[]{0, (int) j});
        this.n.setDuration(j);
        this.n.addUpdateListener(new b(this));
        this.n.addListener(new c(this));
        this.n.setInterpolator(interpolator);
        this.n.start();
    }

    private void a(View view, int i) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        if (c(i, 3)) {
            this.r = iArr[0] - this.q[0];
            this.s = this.r;
        } else if (c(i, 5)) {
            this.r = (iArr[0] + view.getWidth()) - this.q[0];
            this.s = this.r;
        } else if (c(i, 1)) {
            this.r = (iArr[0] + (view.getWidth() / 2)) - this.q[0];
            this.s = this.r;
        } else {
            this.r = iArr[0] - this.q[0];
            this.s = (iArr[0] + view.getWidth()) - this.q[0];
        }
        if (c(i, 48)) {
            this.t = iArr[1] - this.q[1];
            this.u = this.t;
        } else if (c(i, 80)) {
            this.t = (iArr[1] + view.getHeight()) - this.q[1];
            this.u = this.t;
        } else if (c(i, 16)) {
            this.t = (iArr[1] + (view.getHeight() / 2)) - this.q[1];
            this.u = this.t;
        } else {
            this.t = iArr[1] - this.q[1];
            this.u = (iArr[1] + view.getHeight()) - this.q[1];
        }
    }

    private boolean c(int i, int i2) {
        return (i & i2) == i2;
    }

    private void a(long j) {
        Particle particle = (Particle) this.e.remove(0);
        particle.init();
        for (int i = 0; i < this.m.size(); i++) {
            ((ParticleInitializer) this.m.get(i)).initParticle(particle, this.c);
        }
        particle.configure(this.g, (float) d(this.r, this.s), (float) d(this.t, this.u));
        particle.activate(j, this.l);
        this.f.add(particle);
        this.j++;
    }

    private int d(int i, int i2) {
        return i == i2 ? i : i + this.c.nextInt(i2 - i);
    }

    private void b(long j) {
        while (true) {
            if (((this.k <= 0 || j >= this.k) && this.k != -1) || this.e.isEmpty() || ((float) this.j) >= this.i * ((float) j)) {
            } else {
                a(j);
            }
        }
        synchronized (this.f) {
            int i = 0;
            while (i < this.f.size()) {
                if (!((Particle) this.f.get(i)).update(j)) {
                    Particle particle = (Particle) this.f.remove(i);
                    i--;
                    this.e.add(particle);
                }
                i++;
            }
        }
        this.d.postInvalidate();
    }

    private void a() {
        this.a.removeView(this.d);
        this.d = null;
        this.a.postInvalidate();
        this.e.addAll(this.f);
    }

    public void stopEmitting() {
        this.k = this.h;
    }

    public void cancel() {
        if (this.n != null && this.n.isRunning()) {
            this.n.cancel();
        }
        if (this.o != null) {
            this.o.cancel();
            this.o.purge();
            a();
        }
    }

    private void b(int i) {
        if (i != 0) {
            long j = (this.h / 1000) / ((long) i);
            if (j != 0) {
                long j2 = this.h / j;
                for (int i2 = 1; ((long) i2) <= j; i2++) {
                    b((((long) i2) * j2) + 1);
                }
            }
        }
    }
}
