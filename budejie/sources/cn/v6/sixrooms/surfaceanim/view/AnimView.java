package cn.v6.sixrooms.surfaceanim.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import cn.v6.sixrooms.surfaceanim.animinterface.IDrawControl;
import cn.v6.sixrooms.surfaceanim.animinterface.IOnDrawListener;

public class AnimView extends View implements IDrawControl {
    private IOnDrawListener a;
    private boolean b;

    public AnimView(Context context) {
        super(context);
    }

    public AnimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public AnimView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setOnDrawListener(IOnDrawListener iOnDrawListener) {
        this.a = iOnDrawListener;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.a != null) {
            this.a.onDraw(canvas);
        }
        if (this.b) {
            invalidate();
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }

    private void a() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        int width = windowManager.getDefaultDisplay().getWidth();
        int height = windowManager.getDefaultDisplay().getHeight();
        if (this.a != null) {
            this.a.onDrawSizeChanged(width, height);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    public void statDraw() {
        this.b = true;
        invalidate();
    }

    public void stopDraw() {
        this.b = false;
    }
}
