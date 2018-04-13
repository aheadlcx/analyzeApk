package cn.tatagou.sdk.view.pullview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.util.p;
import java.util.Timer;
import java.util.TimerTask;

public class AutoPullToRefreshLayout extends RelativeLayout {
    public static final String a = AutoPullToRefreshLayout.class.getSimpleName();
    public float b = 0.0f;
    public float c = 8.0f;
    Handler d = new Handler(this) {
        final /* synthetic */ AutoPullToRefreshLayout a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.c = (float) (8.0d + (5.0d * Math.tan((1.5707963267948966d / ((double) this.a.getMeasuredHeight())) * ((double) this.a.b))));
            if (!this.a.l && this.a.e == 2 && this.a.b <= this.a.i && this.a.j != null) {
                this.a.b = this.a.i;
                this.a.j.cancel();
            }
            if (this.a.b > 0.0f) {
                AutoPullToRefreshLayout autoPullToRefreshLayout = this.a;
                autoPullToRefreshLayout.b -= this.a.c;
            }
            if (this.a.b <= 0.0f) {
                this.a.b = 0.0f;
                if (this.a.e != 2) {
                    this.a.a(0);
                }
                if (this.a.j != null) {
                    this.a.j.cancel();
                }
            }
            this.a.requestLayout();
        }
    };
    private int e = 0;
    private c f;
    private float g;
    private float h;
    private float i = 200.0f;
    private a j;
    private boolean k = false;
    private boolean l = false;
    private float m = 2.0f;
    private View n;
    private View o;
    private int p;
    private TranslateAnimation q;
    private TranslateAnimation r;
    private ImageView s;
    private ImageView t;

    class a {
        final /* synthetic */ AutoPullToRefreshLayout a;
        private Handler b;
        private Timer c = new Timer();
        private a d;

        class a extends TimerTask {
            final /* synthetic */ a a;
            private Handler b;

            public a(a aVar, Handler handler) {
                this.a = aVar;
                this.b = handler;
            }

            public void run() {
                this.b.obtainMessage().sendToTarget();
            }
        }

        public a(AutoPullToRefreshLayout autoPullToRefreshLayout, Handler handler) {
            this.a = autoPullToRefreshLayout;
            this.b = handler;
        }

        public void schedule(long j) {
            if (this.d != null) {
                this.d.cancel();
                this.d = null;
            }
            this.d = new a(this, this.b);
            this.c.schedule(this.d, 0, j);
        }

        public void cancel() {
            if (this.d != null) {
                this.d.cancel();
                this.d = null;
            }
        }
    }

    public void setOnRefreshListener(c cVar) {
        this.f = cVar;
    }

    public AutoPullToRefreshLayout(Context context) {
        super(context);
        a();
    }

    public AutoPullToRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public AutoPullToRefreshLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.j = new a(this, this.d);
    }

    private void b() {
        if (this.q == null) {
            this.q = new TranslateAnimation((float) p.dip2px(getContext(), 5.0f), 0.0f, 0.0f, 0.0f);
            this.q.setFillAfter(false);
            this.q.setDuration(600);
            this.q.setRepeatCount(-1);
            this.q.setRepeatMode(2);
        }
        if (this.r == null) {
            this.r = new TranslateAnimation((float) (-p.dip2px(getContext(), 5.0f)), 0.0f, 0.0f, 0.0f);
            this.r.setDuration(600);
            this.r.setRepeatCount(-1);
            this.r.setRepeatMode(2);
        }
    }

    private void c() {
        if (this.j != null) {
            this.j.schedule(5);
        }
    }

    public void refreshFinish(int i) {
        if (this.r != null) {
            this.t.clearAnimation();
            this.r.cancel();
        }
        if (this.q != null) {
            this.s.clearAnimation();
            this.q.cancel();
        }
        new Handler(this) {
            final /* synthetic */ AutoPullToRefreshLayout a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                this.a.a(3);
                this.a.c();
            }
        }.sendEmptyMessageDelayed(0, 500);
    }

    private void a(int i) {
        this.e = i;
        this.t.startAnimation(this.r);
        this.s.startAnimation(this.q);
        this.q.startNow();
        this.r.startNow();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.g = motionEvent.getY();
                this.h = this.g;
                this.j.cancel();
                this.p = 0;
                break;
            case 1:
                if (this.b > this.i) {
                    this.l = false;
                }
                if (this.e == 1) {
                    a(2);
                    if (this.f != null) {
                        this.f.onAutoRefresh(this);
                    }
                }
                c();
                break;
            case 2:
                if (this.p != 0) {
                    this.p = 0;
                } else if (((b) this.o).canPullDown()) {
                    this.b += (motionEvent.getY() - this.h) / this.m;
                    if (this.b < 0.0f) {
                        this.b = 0.0f;
                    }
                    if (this.b > ((float) getMeasuredHeight())) {
                        this.b = (float) getMeasuredHeight();
                    }
                    if (this.e == 2) {
                        this.l = true;
                    }
                }
                this.h = motionEvent.getY();
                this.m = (float) ((Math.tan((1.5707963267948966d / ((double) getMeasuredHeight())) * ((double) this.b)) * 2.0d) + 2.0d);
                requestLayout();
                if (this.b <= this.i && this.e == 1) {
                    a(0);
                }
                if (this.b >= this.i && this.e == 0) {
                    a(1);
                }
                if (this.b > 8.0f) {
                    motionEvent.setAction(3);
                    break;
                }
                break;
            case 5:
            case 6:
                this.p = -1;
                break;
        }
        super.dispatchTouchEvent(motionEvent);
        return true;
    }

    private void d() {
        this.s = (ImageView) this.n.findViewById(R.id.ttg_iv_ball_l);
        this.t = (ImageView) this.n.findViewById(R.id.ttg_iv_ball_r);
        b();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!this.k) {
            this.n = getChildAt(0);
            this.o = getChildAt(1);
            this.k = true;
            d();
            if (!(this.n == null || ((ViewGroup) this.n).getChildAt(0) == null)) {
                this.i = (float) ((ViewGroup) this.n).getChildAt(0).getMeasuredHeight();
            }
        }
        if (this.n != null) {
            this.n.layout(0, ((int) this.b) - this.n.getMeasuredHeight(), this.n.getMeasuredWidth(), (int) this.b);
        }
        if (this.o != null) {
            this.o.layout(0, (int) this.b, this.o.getMeasuredWidth(), ((int) this.b) + this.o.getMeasuredHeight());
        }
    }
}
