package cn.xiaochuankeji.tieba.ui.widget.pullzoom;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import cn.xiaochuankeji.tieba.R;

public abstract class b<T extends View> extends LinearLayout implements a<T> {
    protected T a;
    protected View b;
    protected View c;
    protected int d;
    protected int e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private int j;
    private boolean k;
    private float l;
    private float m;
    private float n;
    private float o;
    private a p;

    public interface a {
        void a();

        void a(int i);
    }

    protected abstract T a(Context context, AttributeSet attributeSet);

    protected abstract void a(int i);

    protected abstract void e();

    protected abstract boolean f();

    public abstract void setHeaderView(View view);

    public abstract void setZoomView(View view);

    public b(Context context) {
        this(context, null);
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = true;
        this.g = true;
        this.h = false;
        this.i = false;
        this.k = false;
        b(context, attributeSet);
    }

    private void b(Context context, AttributeSet attributeSet) {
        setGravity(17);
        this.j = ViewConfiguration.get(context).getScaledTouchSlop();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.d = displayMetrics.heightPixels;
        this.e = displayMetrics.widthPixels;
        this.a = a(context, attributeSet);
        if (attributeSet != null) {
            LayoutInflater from = LayoutInflater.from(getContext());
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.PullToZoomView);
            int resourceId = obtainStyledAttributes.getResourceId(2, 0);
            if (resourceId > 0) {
                this.c = from.inflate(resourceId, null, false);
            }
            resourceId = obtainStyledAttributes.getResourceId(0, 0);
            if (resourceId > 0) {
                this.b = from.inflate(resourceId, null, false);
            }
            this.g = obtainStyledAttributes.getBoolean(3, true);
            a(obtainStyledAttributes);
            obtainStyledAttributes.recycle();
        }
        addView(this.a, -1, -1);
    }

    public void setOnPullZoomListener(a aVar) {
        this.p = aVar;
    }

    public T getPullRootView() {
        return this.a;
    }

    public View getZoomView() {
        return this.c;
    }

    public View getHeaderView() {
        return this.b;
    }

    public boolean a() {
        return this.f;
    }

    public boolean b() {
        return this.h;
    }

    public boolean c() {
        return this.g;
    }

    public boolean d() {
        return this.i;
    }

    public void setZoomEnabled(boolean z) {
        this.f = z;
    }

    public void setParallax(boolean z) {
        this.g = z;
    }

    public void setHideHeader(boolean z) {
        this.i = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!a() || d()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.k = false;
            return false;
        } else if (action != 0 && this.k) {
            return true;
        } else {
            switch (action) {
                case 0:
                    if (f()) {
                        float y = motionEvent.getY();
                        this.n = y;
                        this.l = y;
                        y = motionEvent.getX();
                        this.o = y;
                        this.m = y;
                        this.k = false;
                        break;
                    }
                    break;
                case 2:
                    if (f()) {
                        float y2 = motionEvent.getY();
                        float x = motionEvent.getX();
                        float f = y2 - this.l;
                        float f2 = x - this.m;
                        float abs = Math.abs(f);
                        if (abs > ((float) this.j) && abs > Math.abs(f2) && f >= 1.0f && f()) {
                            this.l = y2;
                            this.m = x;
                            this.k = true;
                            break;
                        }
                    }
                    break;
            }
            return this.k;
        }
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        if (!a() || d()) {
            return false;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (!f()) {
                    return false;
                }
                float y = motionEvent.getY();
                this.n = y;
                this.l = y;
                y = motionEvent.getX();
                this.o = y;
                this.m = y;
                return true;
            case 1:
            case 3:
                if (!this.k) {
                    return false;
                }
                this.k = false;
                if (!b()) {
                    return true;
                }
                e();
                if (this.p != null) {
                    this.p.a();
                }
                this.h = false;
                return true;
            case 2:
                if (!this.k) {
                    return false;
                }
                this.l = motionEvent.getY();
                this.m = motionEvent.getX();
                g();
                this.h = true;
                return true;
            default:
                return false;
        }
    }

    private void g() {
        int round = Math.round(Math.min(this.n - this.l, 0.0f) / 2.0f);
        a(round);
        if (this.p != null) {
            this.p.a(round);
        }
    }
}
