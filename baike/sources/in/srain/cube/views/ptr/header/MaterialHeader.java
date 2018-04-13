package in.srain.cube.views.ptr.header;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.PtrUIHandlerHook;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

public class MaterialHeader extends View implements PtrUIHandler {
    private MaterialProgressDrawable a;
    private float b = 1.0f;
    private PtrFrameLayout c;
    private Animation d = new a(this);

    public MaterialHeader(Context context) {
        super(context);
        a();
    }

    public MaterialHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public MaterialHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public void setPtrFrameLayout(PtrFrameLayout ptrFrameLayout) {
        PtrUIHandlerHook bVar = new b(this);
        this.d.setDuration(200);
        this.d.setAnimationListener(new c(this, bVar));
        this.c = ptrFrameLayout;
        this.c.setRefreshCompleteHook(bVar);
    }

    private void a() {
        this.a = new MaterialProgressDrawable(getContext(), this);
        this.a.setBackgroundColor(-1);
        this.a.setCallback(this);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (drawable == this.a) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public void setColorSchemeColors(int[] iArr) {
        this.a.setColorSchemeColors(iArr);
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec((this.a.getIntrinsicHeight() + getPaddingTop()) + getPaddingBottom(), 1073741824));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int intrinsicHeight = this.a.getIntrinsicHeight();
        this.a.setBounds(0, 0, intrinsicHeight, intrinsicHeight);
    }

    protected void onDraw(Canvas canvas) {
        int save = canvas.save();
        Rect bounds = this.a.getBounds();
        canvas.translate((float) (getPaddingLeft() + ((getMeasuredWidth() - this.a.getIntrinsicWidth()) / 2)), (float) getPaddingTop());
        canvas.scale(this.b, this.b, bounds.exactCenterX(), bounds.exactCenterY());
        this.a.draw(canvas);
        canvas.restoreToCount(save);
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        this.b = 1.0f;
        this.a.stop();
    }

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        this.a.setAlpha(255);
        this.a.start();
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        this.a.stop();
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
        float min = Math.min(1.0f, ptrIndicator.getCurrentPercent());
        if (b == (byte) 2) {
            this.a.setAlpha((int) (255.0f * min));
            this.a.showArrow(true);
            this.a.setStartEndTrim(0.0f, Math.min(0.8f, min * 0.8f));
            this.a.setArrowScale(Math.min(1.0f, min));
            this.a.setProgressRotation(((min * 2.0f) + (-0.25f + (0.4f * min))) * 0.5f);
            invalidate();
        }
    }
}
