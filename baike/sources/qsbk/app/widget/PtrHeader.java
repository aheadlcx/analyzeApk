package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import qsbk.app.R;

public class PtrHeader extends FrameLayout implements PtrUIHandler {
    private int a = 150;
    private RotateAnimation b;
    private RotateAnimation c;
    private View d;
    private View e;

    public PtrHeader(Context context) {
        super(context);
        a();
    }

    public PtrHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public PtrHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    protected void a() {
        b();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_ptr_header, this);
        this.d = inflate.findViewById(R.id.ptr_header_rotate_view);
        this.e = inflate.findViewById(R.id.ptr_header_progressbar);
        c();
    }

    private void b() {
        this.b = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.b.setInterpolator(new LinearInterpolator());
        this.b.setDuration((long) this.a);
        this.b.setFillAfter(true);
        this.c = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.c.setInterpolator(new LinearInterpolator());
        this.c.setDuration((long) this.a);
        this.c.setFillAfter(true);
    }

    private void c() {
        d();
        this.e.setVisibility(4);
    }

    private void d() {
        this.d.clearAnimation();
        this.d.setVisibility(4);
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        c();
    }

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        this.e.setVisibility(4);
        this.d.setVisibility(0);
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        d();
        this.e.setVisibility(0);
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        d();
        this.e.setVisibility(4);
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
        int offsetToRefresh = ptrFrameLayout.getOffsetToRefresh();
        int currentPosY = ptrIndicator.getCurrentPosY();
        int lastPosY = ptrIndicator.getLastPosY();
        if (currentPosY >= offsetToRefresh || lastPosY < offsetToRefresh) {
            if (currentPosY > offsetToRefresh && lastPosY <= offsetToRefresh && z && b == (byte) 2 && this.d != null) {
                this.d.clearAnimation();
                this.d.startAnimation(this.b);
            }
        } else if (z && b == (byte) 2 && this.d != null) {
            this.d.clearAnimation();
            this.d.startAnimation(this.c);
        }
    }
}
