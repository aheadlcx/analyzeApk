package qsbk.app.slide;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class b implements OnTouchListener {
    final /* synthetic */ BaseEmotionFragment a;

    b(BaseEmotionFragment baseEmotionFragment) {
        this.a = baseEmotionFragment;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.a.f.setVisibility(0);
            this.a.e.setVisibility(8);
        }
        return false;
    }
}
