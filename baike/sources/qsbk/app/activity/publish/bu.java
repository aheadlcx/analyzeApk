package qsbk.app.activity.publish;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout.LayoutParams;

class bu implements OnTouchListener {
    int a = 0;
    int b = 0;
    final /* synthetic */ Publish_Image c;

    bu(Publish_Image publish_Image) {
        this.c = publish_Image;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        LayoutParams layoutParams;
        switch (motionEvent.getAction() & 255) {
            case 0:
                layoutParams = (LayoutParams) view.getLayoutParams();
                this.b = rawX - layoutParams.leftMargin;
                this.a = rawY - layoutParams.topMargin;
                break;
            case 2:
                layoutParams = (LayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = rawX - this.b;
                layoutParams.topMargin = rawY - this.a;
                if (layoutParams.leftMargin > 0) {
                    layoutParams.leftMargin = 0;
                }
                if (layoutParams.topMargin > 0) {
                    layoutParams.topMargin = 0;
                }
                if (layoutParams.topMargin < -2500) {
                    layoutParams.topMargin = -2500;
                }
                if (layoutParams.leftMargin < -2500) {
                    layoutParams.leftMargin = -2500;
                }
                view.setLayoutParams(layoutParams);
                this.c.e.invalidate();
                break;
        }
        return true;
    }
}
