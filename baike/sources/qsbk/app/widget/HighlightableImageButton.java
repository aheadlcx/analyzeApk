package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import qsbk.app.R;

public class HighlightableImageButton extends ImageButton {
    private static final int[] a = new int[]{R.attr.state_highlighted};
    private boolean b = false;

    public HighlightableImageButton(Context context) {
        super(context);
    }

    public HighlightableImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HighlightableImageButton(Context context, AttributeSet attributeSet, int i) {
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
