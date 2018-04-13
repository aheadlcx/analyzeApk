package qsbk.app.live.widget;

import android.app.Activity;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.ScaleModifier;
import cz.msebera.android.httpclient.HttpStatus;
import qsbk.app.api.BigCoverHelper;
import qsbk.app.live.R;

class gc implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ View b;
    final /* synthetic */ LargeGiftLayout c;

    gc(LargeGiftLayout largeGiftLayout, int i, View view) {
        this.c = largeGiftLayout;
        this.a = i;
        this.b = view;
    }

    public void run() {
        new ParticleSystem((Activity) this.c.b, 10, R.drawable.live_kiss_heart, (long) this.a).setSpeedModuleAndAngleRange(0.02f, 0.1f, 220, 320).addModifier(new ScaleModifier(0.0f, 0.6f, 0, 800)).setRotationSpeedRange(0.0f, 20.0f).setFadeOut(200).oneShot(this.b, 10, new DecelerateInterpolator());
        new ParticleSystem((Activity) this.c.b, 10, R.drawable.live_kiss_heart, (long) this.a).setSpeedModuleAndAngleRange(0.02f, 0.1f, 310, HttpStatus.SC_GONE).addModifier(new ScaleModifier(0.0f, 0.5f, 0, 1000)).setInitialRotationRange(70, 110).setRotationSpeedRange(0.0f, 20.0f).setFadeOut(200).oneShot(this.b, 10, new DecelerateInterpolator());
        new ParticleSystem((Activity) this.c.b, 10, R.drawable.live_kiss_heart, (long) this.a).setSpeedModuleAndAngleRange(0.02f, 0.1f, 40, 140).addModifier(new ScaleModifier(0.0f, 0.6f, 0, 900)).setInitialRotationRange(BigCoverHelper.REQCODE_CAREMA, 200).setRotationSpeedRange(0.0f, 20.0f).setFadeOut(200).oneShot(this.b, 10, new DecelerateInterpolator());
        new ParticleSystem((Activity) this.c.b, 10, R.drawable.live_kiss_heart, (long) this.a).setSpeedModuleAndAngleRange(0.02f, 0.1f, 130, 230).addModifier(new ScaleModifier(0.0f, 0.5f, 0, 1000)).setInitialRotationRange(Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 290).setRotationSpeedRange(0.0f, 20.0f).setFadeOut(200).oneShot(this.b, 10, new DecelerateInterpolator());
    }
}
