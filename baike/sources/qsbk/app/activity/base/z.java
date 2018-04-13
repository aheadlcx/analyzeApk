package qsbk.app.activity.base;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class z implements OnTouchListener {
    final /* synthetic */ BaseEmotionActivity a;

    z(BaseEmotionActivity baseEmotionActivity) {
        this.a = baseEmotionActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.a.O.setVisibility(0);
            this.a.K.setVisibility(8);
        }
        return false;
    }
}
