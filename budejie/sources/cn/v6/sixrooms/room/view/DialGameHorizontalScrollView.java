package cn.v6.sixrooms.room.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class DialGameHorizontalScrollView extends HorizontalScrollView {
    private OnScrollChangedListener a;

    public interface OnScrollChangedListener {
        void onScrollChanged();
    }

    public DialGameHorizontalScrollView(Context context) {
        super(context);
    }

    public DialGameHorizontalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DialGameHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void fling(int i) {
        super.fling(i / 10);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.a != null) {
            this.a.onScrollChanged();
        }
    }

    public OnScrollChangedListener getOnScrollChangedListener() {
        return this.a;
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.a = onScrollChangedListener;
    }
}
