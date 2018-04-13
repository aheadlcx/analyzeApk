package qsbk.app.fragments;

import android.content.Context;
import android.view.View;

class au extends View {
    final /* synthetic */ CircleTopicWeeklyFragment a;

    au(CircleTopicWeeklyFragment circleTopicWeeklyFragment, Context context) {
        this.a = circleTopicWeeklyFragment;
        super(context);
    }

    protected void onMeasure(int i, int i2) {
        int measuredWidth = this.a.k.getMeasuredWidth();
        if (measuredWidth == 0) {
            this.a.k.measure(i, i2);
            measuredWidth = this.a.k.getMeasuredWidth();
        }
        setMeasuredDimension(measuredWidth, this.a.k.getMeasuredHeight() - this.a.n);
    }
}
