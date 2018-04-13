package qsbk.app.fragments;

import android.animation.ObjectAnimator;
import android.graphics.drawable.RotateDrawable;
import android.view.View;
import android.view.View.OnClickListener;

class ek implements OnClickListener {
    final /* synthetic */ LaiseeVoiceSendFragment a;

    ek(LaiseeVoiceSendFragment laiseeVoiceSendFragment) {
        this.a = laiseeVoiceSendFragment;
    }

    public void onClick(View view) {
        RotateDrawable rotateDrawable = (RotateDrawable) this.a.h.getBackground();
        if (this.a.k.isExpanded()) {
            ObjectAnimator.ofInt(rotateDrawable, "level", new int[]{10000, 0}).start();
            this.a.k.collapse();
            return;
        }
        ObjectAnimator.ofInt(rotateDrawable, "level", new int[]{0, 10000}).start();
        this.a.k.expand();
    }
}
