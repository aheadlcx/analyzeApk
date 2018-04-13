package qsbk.app.live.widget;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.View.OnClickListener;

class in implements OnClickListener {
    final /* synthetic */ SendRedEnvelopesDialog a;

    in(SendRedEnvelopesDialog sendRedEnvelopesDialog) {
        this.a = sendRedEnvelopesDialog;
    }

    public void onClick(View view) {
        float f;
        float f2 = 0.0f;
        int height = this.a.e.getHeight();
        if (this.a.e.getVisibility() != 0) {
            f = (float) height;
        } else {
            this.a.e.setVisibility(4);
            f2 = (float) height;
            f = 0.0f;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f2, f});
        ofFloat.addUpdateListener(new io(this));
        ofFloat.addListener(new ip(this, f2));
        ofFloat.start();
    }
}
