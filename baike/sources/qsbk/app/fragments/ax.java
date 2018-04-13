package qsbk.app.fragments;

import android.support.v4.view.ViewCompat;
import android.util.Log;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout.OnScrollOffsetListener;

class ax implements OnScrollOffsetListener {
    final /* synthetic */ CircleTopicWeeklyFragment a;

    ax(CircleTopicWeeklyFragment circleTopicWeeklyFragment) {
        this.a = circleTopicWeeklyFragment;
    }

    public void onScrollOffsetChange(float f, int i, int i2) {
        float max;
        if (this.a.b.getFirstVisiblePosition() == 0) {
            i2 += this.a.n + this.a.n;
            f = ((float) i) / ((float) i2);
        } else if (this.a.b.getFirstVisiblePosition() == 1) {
            i += this.a.k.getHeight() - this.a.n;
            i2 = this.a.k.getHeight() + this.a.n;
            f = ((float) i) / ((float) i2);
        }
        if (f < 1.0f) {
            int bottom = i2 - this.a.o.getBottom();
            this.a.l.offsetContent(-i);
            max = Math.max(0.0f, Math.min(1.0f, bottom <= 0 ? 1.0f : ((float) i) / ((float) bottom)));
        } else {
            this.a.l.offsetContent(-this.a.l.getHeight());
            max = 1.0f;
        }
        Log.i("circletopic offset", "offset=" + f + " percent=" + max);
        int i3 = ((int) (255.0f * max)) << 24;
        this.a.p.setTextColor((this.a.p.getCurrentTextColor() & ViewCompat.MEASURED_SIZE_MASK) | i3);
        this.a.o.setBackgroundColor((UIHelper.isNightTheme() ? 1513755 : 16759317) | i3);
        if (max >= 1.0f) {
            this.a.o.setOnTouchListener(new ay(this));
        } else {
            this.a.o.setOnTouchListener(null);
        }
    }
}
