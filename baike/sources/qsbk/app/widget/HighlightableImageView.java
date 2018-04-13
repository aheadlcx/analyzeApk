package qsbk.app.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import qsbk.app.R;

public class HighlightableImageView extends AppCompatImageView {
    private static final int[] a = new int[]{R.attr.state_highlighted};
    private boolean b = false;

    public HighlightableImageView(Context context) {
        super(context);
    }

    public HighlightableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HighlightableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public int[] onCreateDrawableState(int i) {
        if (!this.b) {
            return super.onCreateDrawableState(i);
        }
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        mergeDrawableStates(onCreateDrawableState, a);
        return onCreateDrawableState;
    }

    public boolean isHighlighted() {
        return this.b;
    }

    public void setHighlighted(boolean z) {
        if (this.b != z) {
            this.b = z;
            refreshDrawableState();
        }
    }
}
