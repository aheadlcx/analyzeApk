package qsbk.app.nearby.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class SizeChangeScrollView extends ScrollView {
    private OnSizeChangeListener a;

    public interface OnSizeChangeListener {
        void onSizeChange(int i, int i2, int i3, int i4);
    }

    public SizeChangeScrollView(Context context) {
        super(context);
    }

    public SizeChangeScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SizeChangeScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnSizeChangeListner(OnSizeChangeListener onSizeChangeListener) {
        this.a = onSizeChangeListener;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.a != null) {
            this.a.onSizeChange(i, i2, i3, i4);
        }
    }
}
