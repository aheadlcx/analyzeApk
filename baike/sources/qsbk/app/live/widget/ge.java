package qsbk.app.live.widget;

import android.app.Activity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.ScaleModifier;

class ge implements Runnable {
    final /* synthetic */ int[] a;
    final /* synthetic */ int b;
    final /* synthetic */ View c;
    final /* synthetic */ LargeGiftLayout d;

    ge(LargeGiftLayout largeGiftLayout, int[] iArr, int i, View view) {
        this.d = largeGiftLayout;
        this.a = iArr;
        this.b = i;
        this.c = view;
    }

    public void run() {
        for (int particleSystem : this.a) {
            new ParticleSystem((Activity) this.d.b, 4, particleSystem, (long) this.b).addModifier(new ScaleModifier(0.0f, 1.0f, 0, 600)).setInitialRotationRange(0, 360).setSpeedRange(0.05f, 0.1f).setFadeOut(400).oneShot(this.c, 4, new DecelerateInterpolator());
        }
    }
}
