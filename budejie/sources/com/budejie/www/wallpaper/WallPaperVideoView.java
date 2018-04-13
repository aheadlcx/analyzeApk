package com.budejie.www.wallpaper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class WallPaperVideoView extends VideoView {
    public WallPaperVideoView(Context context) {
        this(context, null);
    }

    public WallPaperVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WallPaperVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
    }
}
