package cn.v6.sixrooms.room.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class AutoMoveLinearLayout extends LinearLayout {
    private Scroller a;
    private OnScrollFinishedListener b;
    private boolean c = false;

    public interface OnScrollFinishedListener {
        void onScrollFinished();
    }

    public AutoMoveLinearLayout(Context context) {
        super(context);
        this.a = new Scroller(context);
    }

    public AutoMoveLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Scroller(context);
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        this.a.startScroll(i, i2, i3, i4, i5);
        if (!this.c && i4 < 0) {
            this.c = true;
        }
        if (this.c && i4 > 0) {
            this.c = false;
        }
        postInvalidate();
    }

    public void computeScroll() {
        if (this.a.computeScrollOffset()) {
            scrollTo(this.a.getCurrX(), this.a.getCurrY());
            invalidate();
        } else if (this.c && this.b != null) {
            this.b.onScrollFinished();
        }
    }

    public OnScrollFinishedListener getOnScrollFinishedListener() {
        return this.b;
    }

    public void setOnScrollFinishedListener(OnScrollFinishedListener onScrollFinishedListener) {
        this.b = onScrollFinishedListener;
    }

    public boolean isStatusDown() {
        return this.c;
    }
}
