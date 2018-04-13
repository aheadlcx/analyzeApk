package qsbk.app.live.widget;

import android.app.Activity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import com.plattysoft.leonids.ParticleSystem;

class gb implements Runnable {
    final /* synthetic */ int[] a;
    final /* synthetic */ int b;
    final /* synthetic */ View c;
    final /* synthetic */ LargeGiftLayout d;

    gb(LargeGiftLayout largeGiftLayout, int[] iArr, int i, View view) {
        this.d = largeGiftLayout;
        this.a = iArr;
        this.b = i;
        this.c = view;
    }

    public void run() {
        for (int particleSystem : this.a) {
            ParticleSystem particleSystem2 = new ParticleSystem((Activity) this.d.b, 3, particleSystem, (long) this.b);
            particleSystem2.setScaleRange(1.8f, 1.8f);
            particleSystem2.setSpeedRange(0.04f, 0.12f);
            particleSystem2.setRotationSpeedRange(0.0f, 180.0f);
            particleSystem2.setFadeOut(1000, new AccelerateInterpolator());
            particleSystem2.oneShot(this.c, 3);
        }
    }
}
