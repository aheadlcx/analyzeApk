package qsbk.app;

import android.graphics.ColorMatrixColorFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class c implements OnTouchListener {
    public final float[] BT_NOT_SELECTED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    public final float[] BT_SELECTED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, -50.0f, 0.0f, 1.0f, 0.0f, 0.0f, -50.0f, 0.0f, 0.0f, 1.0f, 0.0f, -50.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};

    c() {
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            view.getBackground().setColorFilter(new ColorMatrixColorFilter(this.BT_SELECTED));
            view.setBackgroundDrawable(view.getBackground());
        } else if (motionEvent.getAction() == 1) {
            view.getBackground().setColorFilter(new ColorMatrixColorFilter(this.BT_NOT_SELECTED));
            view.setBackgroundDrawable(view.getBackground());
        }
        return false;
    }
}
