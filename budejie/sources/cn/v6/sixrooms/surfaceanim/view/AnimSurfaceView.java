package cn.v6.sixrooms.surfaceanim.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class AnimSurfaceView extends SurfaceView {
    public AnimSurfaceView(Context context) {
        super(context);
        a();
    }

    public AnimSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public AnimSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    @TargetApi(21)
    public AnimSurfaceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    private void a() {
        setZOrderOnTop(true);
        getHolder().setFormat(-3);
        setBackgroundColor(0);
    }
}
