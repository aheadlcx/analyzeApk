package cn.v6.sixrooms.surfaceanim.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;

public class AnimSurfaceViewMedia extends AnimSurfaceView {
    public AnimSurfaceViewMedia(Context context) {
        super(context);
        init(context);
    }

    public AnimSurfaceViewMedia(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public AnimSurfaceViewMedia(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    @TargetApi(21)
    public AnimSurfaceViewMedia(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    public void init(Context context) {
        setZOrderMediaOverlay(true);
        getHolder().setFormat(-3);
        setBackgroundColor(0);
    }
}
