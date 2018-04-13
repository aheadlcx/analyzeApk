package qsbk.app.live.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class GiftRichRecyclerView extends RecyclerView {
    public GiftRichRecyclerView(Context context) {
        super(context);
    }

    public GiftRichRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GiftRichRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected float getLeftFadingEdgeStrength() {
        return 0.0f;
    }
}
