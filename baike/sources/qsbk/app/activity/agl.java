package qsbk.app.activity;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class agl implements OnTouchListener {
    final /* synthetic */ WebEmotionActivity a;

    agl(WebEmotionActivity webEmotionActivity) {
        this.a = webEmotionActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.a.g.setVisibility(0);
            this.a.f.setVisibility(8);
        }
        return false;
    }
}
