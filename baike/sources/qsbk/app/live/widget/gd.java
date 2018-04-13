package qsbk.app.live.widget;

import android.app.Activity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.plattysoft.leonids.ParticleSystem;
import qsbk.app.live.R;

class gd implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ View b;
    final /* synthetic */ LargeGiftLayout c;

    gd(LargeGiftLayout largeGiftLayout, int i, View view) {
        this.c = largeGiftLayout;
        this.a = i;
        this.b = view;
    }

    public void run() {
        new ParticleSystem((Activity) this.c.b, 25, R.drawable.live_dog_bone, (long) this.a).setInitialRotationRange(0, 360).setSpeedRange(0.03f, 0.12f).setFadeOut(500).oneShot(this.b, 25, new DecelerateInterpolator());
    }
}
