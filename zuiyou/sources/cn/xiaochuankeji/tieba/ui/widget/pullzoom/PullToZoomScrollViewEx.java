package cn.xiaochuankeji.tieba.ui.widget.pullzoom;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import cn.xiaochuankeji.tieba.R;

public class PullToZoomScrollViewEx extends b<ScrollView> {
    private static final String f = PullToZoomScrollViewEx.class.getSimpleName();
    private static final Interpolator m = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    };
    private boolean g;
    private FrameLayout h;
    private LinearLayout i;
    private View j;
    private int k;
    private c l;

    protected interface b {
        void a(int i, int i2, int i3, int i4);
    }

    protected class a extends ScrollView {
        final /* synthetic */ PullToZoomScrollViewEx a;
        private b b;

        public a(PullToZoomScrollViewEx pullToZoomScrollViewEx, Context context, AttributeSet attributeSet) {
            this.a = pullToZoomScrollViewEx;
            super(context, attributeSet);
        }

        public void setOnScrollViewChangedListener(b bVar) {
            this.b = bVar;
        }

        protected void onScrollChanged(int i, int i2, int i3, int i4) {
            super.onScrollChanged(i, i2, i3, i4);
            if (this.b != null) {
                this.b.a(i, i2, i3, i4);
            }
        }
    }

    class c implements Runnable {
        protected long a;
        protected boolean b = true;
        protected float c;
        protected long d;
        final /* synthetic */ PullToZoomScrollViewEx e;

        c(PullToZoomScrollViewEx pullToZoomScrollViewEx) {
            this.e = pullToZoomScrollViewEx;
        }

        public void a() {
            this.b = true;
        }

        public boolean b() {
            return this.b;
        }

        public void run() {
            if (this.e.c != null && !this.b && ((double) this.c) > 1.0d) {
                float currentThreadTimeMillis = (((float) SystemClock.currentThreadTimeMillis()) - ((float) this.d)) / ((float) this.a);
                currentThreadTimeMillis = this.c - (PullToZoomScrollViewEx.m.getInterpolation(currentThreadTimeMillis) * (this.c - 1.0f));
                LayoutParams layoutParams = this.e.h.getLayoutParams();
                if (currentThreadTimeMillis > 1.0f) {
                    layoutParams.height = (int) (((float) this.e.k) * currentThreadTimeMillis);
                    this.e.h.setLayoutParams(layoutParams);
                    if (this.e.g) {
                        layoutParams = this.e.c.getLayoutParams();
                        layoutParams.height = (int) (currentThreadTimeMillis * ((float) this.e.k));
                        this.e.c.setLayoutParams(layoutParams);
                    }
                    this.e.post(this);
                    return;
                }
                this.b = true;
            }
        }

        public void a(long j) {
            if (this.e.c != null) {
                this.d = SystemClock.currentThreadTimeMillis();
                this.a = j;
                this.c = ((float) this.e.h.getBottom()) / ((float) this.e.k);
                this.b = false;
                this.e.post(this);
            }
        }
    }

    protected /* synthetic */ View a(Context context, AttributeSet attributeSet) {
        return b(context, attributeSet);
    }

    public PullToZoomScrollViewEx(Context context) {
        this(context, null);
    }

    public PullToZoomScrollViewEx(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = false;
        this.l = new c(this);
        ((a) this.a).setOnScrollViewChangedListener(new b(this) {
            final /* synthetic */ PullToZoomScrollViewEx a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, int i3, int i4) {
                if (this.a.a() && this.a.c()) {
                    float scrollY = (float) (((ScrollView) this.a.a).getScrollY() + (this.a.k - this.a.h.getBottom()));
                    if (scrollY > 0.0f && scrollY < ((float) this.a.k)) {
                        this.a.h.scrollTo(0, -((int) (((double) scrollY) * 0.65d)));
                    } else if (this.a.h.getScrollY() != 0) {
                        this.a.h.scrollTo(0, 0);
                    }
                }
            }
        });
    }

    protected void a(int i) {
        if (!(this.l == null || this.l.b())) {
            this.l.a();
        }
        LayoutParams layoutParams = this.h.getLayoutParams();
        layoutParams.height = Math.abs(i) + this.k;
        this.h.setLayoutParams(layoutParams);
        if (this.g) {
            layoutParams = this.c.getLayoutParams();
            layoutParams.height = Math.abs(i) + this.k;
            this.c.setLayoutParams(layoutParams);
        }
    }

    public void setHideHeader(boolean z) {
        if (z != d() && this.h != null) {
            super.setHideHeader(z);
            if (z) {
                this.h.setVisibility(8);
            } else {
                this.h.setVisibility(0);
            }
        }
    }

    public void setHeaderView(View view) {
        if (view != null) {
            this.b = view;
            h();
        }
    }

    public void setZoomView(View view) {
        if (view != null) {
            this.c = view;
            h();
        }
    }

    private void h() {
        if (this.h != null) {
            this.h.removeAllViews();
            if (this.c != null) {
                this.h.addView(this.c);
            }
            if (this.b != null) {
                this.h.addView(this.b);
            }
        }
    }

    public void setScrollContentView(View view) {
        if (view != null) {
            if (this.j != null) {
                this.i.removeView(this.j);
            }
            this.j = view;
            this.i.addView(this.j);
        }
    }

    protected ScrollView b(Context context, AttributeSet attributeSet) {
        ScrollView aVar = new a(this, context, attributeSet);
        aVar.setId(R.id.scrollview);
        return aVar;
    }

    protected void e() {
        Log.d(f, "smoothScrollToTop --> ");
        this.l.a(200);
    }

    protected boolean f() {
        return ((ScrollView) this.a).getScrollY() == 0;
    }

    public void a(TypedArray typedArray) {
        this.i = new LinearLayout(getContext());
        this.i.setOrientation(1);
        this.h = new FrameLayout(getContext());
        if (this.c != null) {
            this.h.addView(this.c);
        }
        if (this.b != null) {
            this.h.addView(this.b);
        }
        int resourceId = typedArray.getResourceId(1, 0);
        if (resourceId > 0) {
            this.j = LayoutInflater.from(getContext()).inflate(resourceId, null, false);
        }
        this.i.addView(this.h);
        if (this.j != null) {
            this.i.addView(this.j);
        }
        this.i.setClipChildren(false);
        this.h.setClipChildren(false);
        ((ScrollView) this.a).addView(this.i);
    }

    public void a(int i, int i2) {
        if (this.h != null) {
            LayoutParams layoutParams = this.h.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(i, i2);
            }
            layoutParams.width = i;
            layoutParams.height = i2;
            this.h.setLayoutParams(layoutParams);
            this.k = i2;
            this.g = true;
        }
    }

    public void setHeaderLayoutParams(LinearLayout.LayoutParams layoutParams) {
        if (this.h != null) {
            this.h.setLayoutParams(layoutParams);
            this.k = layoutParams.height;
            this.g = true;
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        Log.d(f, "onLayout --> ");
        if (this.k == 0 && this.c != null) {
            this.k = this.h.getHeight();
        }
    }
}
