package cn.v6.sixrooms.widgets.phone;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;
import cn.v6.sixrooms.utils.DisPlayUtil;

public class DragPopupWindow extends PopupWindow implements OnTouchListener {
    private int[] a;
    private int b;
    private int c;
    private float d;
    private float e;
    private int[] f = null;
    private int[] g = null;
    private boolean h = false;
    private int i = 5;
    private Context j = null;
    private boolean k = false;

    public DragPopupWindow(View view, int i, int i2) {
        int titleBarHeight;
        int i3;
        super(view, i, i2);
        this.j = view.getContext();
        Context context = this.j;
        this.j = context;
        setBackgroundDrawable(new BitmapDrawable());
        setTouchable(true);
        setFocusable(false);
        setTouchInterceptor(this);
        if (context instanceof Activity) {
            if ((((Activity) context).getWindow().getAttributes().flags & 1024) == 1024) {
                titleBarHeight = DisPlayUtil.getTitleBarHeight((Activity) context);
            } else {
                titleBarHeight = 0;
            }
            i3 = titleBarHeight;
            titleBarHeight = DisPlayUtil.getStatusHeight(context);
        } else {
            titleBarHeight = 0;
            i3 = 0;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i4 = displayMetrics.widthPixels;
        titleBarHeight = (displayMetrics.heightPixels - titleBarHeight) - i3;
        this.f = new int[]{0, i4};
        this.g = new int[]{0, titleBarHeight};
    }

    public void setPosition(int[] iArr) {
        this.a = iArr;
        this.b = iArr[0];
        this.c = iArr[1];
    }

    protected void refreshPosition() {
        update(this.a[0], this.a[1], this.a[2], this.a[3]);
    }

    public boolean isMoveEvent() {
        return this.h;
    }

    public void setXMoveArray(int[] iArr) {
        if (iArr.length != 2) {
            throw new IllegalArgumentException("xMoveArray must be an array of two integers");
        }
        this.f = iArr;
    }

    public int[] getXmoveArray() {
        return this.f;
    }

    public int[] getYmoveArray() {
        return this.g;
    }

    public void setYMoveArray(int[] iArr) {
        if (iArr.length != 2) {
            throw new IllegalArgumentException("yMoveArray must be an array of two integers");
        }
        this.g = iArr;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        switch (action) {
            case 0:
                this.h = false;
                setPosition(new int[]{this.b, this.c, this.a[2], this.a[3]});
                this.d = motionEvent.getRawX();
                this.e = motionEvent.getRawY();
                return true;
            case 1:
            case 2:
            case 3:
                if (action == 1 && !this.h) {
                    return false;
                }
                float rawX = motionEvent.getRawX() - this.d;
                float rawY = motionEvent.getRawY() - this.e;
                if (Math.abs(rawX) > ((float) this.i) || Math.abs(rawY) > ((float) this.i)) {
                    this.h = true;
                }
                if (this.h) {
                    action = ((int) rawX) + this.a[0];
                    int i = ((int) rawY) + this.a[1];
                    if (action < this.f[0]) {
                        action = this.f[0];
                    } else if (this.a[2] + action > this.f[1]) {
                        action = this.f[1] - this.a[2];
                    }
                    if (i < this.g[0]) {
                        i = this.g[0];
                    } else if (this.a[3] + i > this.g[1]) {
                        i = this.g[1] - this.a[3];
                    }
                    this.b = action;
                    this.c = i;
                    update(this.b, this.c, this.a[2], this.a[3]);
                }
                return true;
            default:
                return false;
        }
    }

    public void showAtLPosition(View view) {
        showAtLocation(view, 51, this.a[0], this.a[1]);
        if (!this.k) {
            this.k = true;
            View view2 = (View) getContentView().getParent();
            LayoutParams layoutParams;
            if (view2.getLayoutParams() instanceof LayoutParams) {
                layoutParams = (LayoutParams) view2.getLayoutParams();
                layoutParams.flags |= 32;
                ((WindowManager) this.j.getSystemService("window")).updateViewLayout(view2, layoutParams);
            } else if (view2.getParent() != null && (view2.getParent() instanceof View)) {
                view2 = (View) view2.getParent();
                if (view2.getLayoutParams() instanceof LayoutParams) {
                    layoutParams = (LayoutParams) view2.getLayoutParams();
                    layoutParams.flags |= 32;
                    ((WindowManager) this.j.getSystemService("window")).updateViewLayout(view2, layoutParams);
                }
            }
        }
    }
}
