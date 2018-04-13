package cn.tatagou.sdk.view.pullview;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.util.p;
import java.util.Timer;
import java.util.TimerTask;

public class PullToRefreshLayout extends RelativeLayout {
    private b A;
    private boolean B = true;
    private String C;
    private float D = 0.0f;
    public float a = 0.0f;
    public float b = 8.0f;
    public boolean c;
    Handler d = new Handler(this) {
        final /* synthetic */ PullToRefreshLayout a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.b = (float) (8.0d + (5.0d * Math.tan((1.5707963267948966d / ((double) this.a.getMeasuredHeight())) * ((double) (this.a.a + Math.abs(this.a.i))))));
            if (!this.a.m) {
                if (this.a.e == 2 && this.a.a <= this.a.j) {
                    this.a.a = this.a.j;
                    if (this.a.A != null) {
                        this.a.A.cancel();
                    }
                } else if (this.a.e == 4 && (-this.a.i) <= this.a.k) {
                    this.a.i = -this.a.k;
                    if (this.a.A != null) {
                        this.a.A.cancel();
                    }
                }
            }
            if (this.a.a > 0.0f) {
                PullToRefreshLayout pullToRefreshLayout = this.a;
                pullToRefreshLayout.a -= this.a.b;
            } else if (this.a.i < 0.0f) {
                this.a.i = this.a.i + this.a.b;
            }
            if (this.a.a < 0.0f) {
                this.a.a = 0.0f;
                if (!(this.a.e == 2 || this.a.e == 4)) {
                    this.a.a(0);
                }
                if (this.a.A != null) {
                    this.a.A.cancel();
                }
                this.a.requestLayout();
            }
            if (this.a.i > 0.0f) {
                this.a.i = 0.0f;
                if (!(this.a.e == 2 || this.a.e == 4)) {
                    this.a.a(0);
                }
                if (this.a.A != null) {
                    this.a.A.cancel();
                }
                this.a.requestLayout();
            }
            this.a.requestLayout();
            if (this.a.a + Math.abs(this.a.i) == 0.0f && this.a.A != null) {
                this.a.A.cancel();
            }
        }
    };
    private int e = 0;
    private c f;
    private float g;
    private float h;
    private float i = 0.0f;
    private float j = 200.0f;
    private float k = 200.0f;
    private boolean l = false;
    private boolean m = false;
    private float n = 2.0f;
    private View o;
    private TranslateAnimation p;
    private TranslateAnimation q;
    private ImageView r;
    private ImageView s;
    private TextView t;
    private View u;
    private TextView v;
    private View w;
    private int x;
    private boolean y = true;
    private boolean z = true;

    private class a extends AsyncTask<Integer, Float, String> {
        final /* synthetic */ PullToRefreshLayout a;

        private a(PullToRefreshLayout pullToRefreshLayout) {
            this.a = pullToRefreshLayout;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Integer[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((String) obj);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            a((Float[]) objArr);
        }

        protected String a(Integer... numArr) {
            while (this.a.a < 1.0f * this.a.j) {
                PullToRefreshLayout pullToRefreshLayout = this.a;
                pullToRefreshLayout.a += this.a.b;
                publishProgress(new Float[]{Float.valueOf(this.a.a)});
            }
            return null;
        }

        protected void a(String str) {
            this.a.a(2);
            if (this.a.f != null) {
                this.a.f.onRefresh(this.a);
            }
            this.a.b();
        }

        protected void a(Float... fArr) {
            if (this.a.a > this.a.j) {
                this.a.a(1);
            }
            this.a.requestLayout();
        }
    }

    class b {
        final /* synthetic */ PullToRefreshLayout a;
        private Handler b;
        private Timer c = new Timer();
        private a d;

        class a extends TimerTask {
            final /* synthetic */ b a;
            private Handler b;

            public a(b bVar, Handler handler) {
                this.a = bVar;
                this.b = handler;
            }

            public void run() {
                this.b.obtainMessage().sendToTarget();
            }
        }

        public b(PullToRefreshLayout pullToRefreshLayout, Handler handler) {
            this.a = pullToRefreshLayout;
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

    public void setTopRefreshHintText(String str) {
        this.C = str;
    }

    public void setOnRefreshListener(c cVar) {
        this.f = cVar;
    }

    public PullToRefreshLayout(Context context) {
        super(context);
        a();
    }

    public PullToRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public PullToRefreshLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.A = new b(this, this.d);
    }

    private void b() {
        if (this.A != null) {
            this.A.schedule(5);
        }
    }

    public void refreshFinish(int i) {
        if (this.c) {
            this.c = false;
            switch (i) {
                case 0:
                    if (this.q != null) {
                        this.s.clearAnimation();
                        this.q.cancel();
                    }
                    if (this.p != null) {
                        this.r.clearAnimation();
                        this.p.cancel();
                        break;
                    }
                    break;
            }
            a(5);
            b();
        }
    }

    public void loadmoreFinish(int i) {
        switch (i) {
            case 0:
                this.v.setText(R.string.load_succeed);
                break;
            default:
                this.v.setText(R.string.load_fail);
                break;
        }
        if (this.i < 0.0f) {
            new Handler(this) {
                final /* synthetic */ PullToRefreshLayout a;

                {
                    this.a = r1;
                }

                public void handleMessage(Message message) {
                    this.a.a(5);
                    this.a.b();
                }
            }.sendEmptyMessageDelayed(0, 10);
            return;
        }
        a(5);
        b();
    }

    private void a(int i) {
        if (this.f != null) {
            this.f.onInitView(this);
        }
        if (!(this.t == null || p.isEmpty(this.C))) {
            this.t.setText(this.C);
        }
        this.e = i;
        switch (this.e) {
            case 0:
                this.v.setText(R.string.pullup_to_load);
                return;
            case 1:
                this.s.startAnimation(this.q);
                this.r.startAnimation(this.p);
                this.p.startNow();
                this.q.startNow();
                return;
            case 3:
                this.v.setText(R.string.release_to_load);
                return;
            case 4:
                this.v.setText(R.string.more);
                return;
            default:
                return;
        }
    }

    private void c() {
        this.y = true;
        this.z = true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int i = -1;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.g = motionEvent.getY();
                this.D = motionEvent.getX();
                this.h = this.g;
                this.A.cancel();
                if (this.B) {
                    i = 0;
                }
                this.x = i;
                c();
                break;
            case 1:
                if (this.a > this.j || (-this.i) > this.k) {
                    this.m = false;
                }
                if (this.e == 1) {
                    a(2);
                    if (this.f != null) {
                        this.f.onRefresh(this);
                    }
                } else if (this.e == 3) {
                    a(4);
                    if (this.f != null) {
                        this.f.onLoadMore(this);
                    }
                }
                b();
                break;
            case 2:
                if (this.x != 0) {
                    if (this.B) {
                        i = 0;
                    }
                    this.x = i;
                } else if ((this.a > 0.0f || (((d) this.w).canPullDown() && this.y && this.e != 4)) && Math.abs(this.D - motionEvent.getX()) < Math.abs(this.g - motionEvent.getY())) {
                    this.a += (motionEvent.getY() - this.h) / this.n;
                    if (this.a < 0.0f) {
                        this.a = 0.0f;
                        this.y = false;
                        this.z = true;
                    }
                    if (this.a > ((float) getMeasuredHeight())) {
                        this.a = (float) getMeasuredHeight();
                    }
                    if (this.e == 2) {
                        this.m = true;
                    }
                } else if (this.i < 0.0f || (((d) this.w).canPullUp() && this.z && this.e != 2)) {
                    this.i += (motionEvent.getY() - this.h) / this.n;
                    if (this.i > 0.0f) {
                        this.i = 0.0f;
                        this.y = true;
                        this.z = false;
                    }
                    if (this.i < ((float) (-getMeasuredHeight()))) {
                        this.i = (float) (-getMeasuredHeight());
                    }
                    if (this.e == 4) {
                        this.m = true;
                    }
                } else {
                    c();
                }
                this.h = motionEvent.getY();
                this.n = (float) (2.0d + (2.0d * Math.tan((1.5707963267948966d / ((double) getMeasuredHeight())) * ((double) (this.a + Math.abs(this.i))))));
                if (this.a > 0.0f || this.i < 0.0f) {
                    requestLayout();
                }
                if (this.a > 0.0f) {
                    if (this.a <= this.j && (this.e == 1 || this.e == 5)) {
                        a(0);
                    }
                    if (this.a >= this.j && this.e == 0) {
                        a(1);
                    }
                } else if (this.i < 0.0f) {
                    if ((-this.i) <= this.k && (this.e == 3 || this.e == 5)) {
                        a(0);
                    }
                    if ((-this.i) >= this.k && this.e == 0) {
                        a(3);
                    }
                }
                if (this.a + Math.abs(this.i) > 8.0f) {
                    motionEvent.setAction(3);
                    break;
                }
                break;
            case 5:
            case 6:
                this.x = -1;
                break;
        }
        super.dispatchTouchEvent(motionEvent);
        return true;
    }

    public void autoRefresh() {
        if (this.e == 0) {
            new a().execute(new Integer[]{Integer.valueOf(10)});
        }
    }

    public void autoLoad() {
        this.i = -this.k;
        requestLayout();
        a(4);
        if (this.f != null) {
            this.f.onLoadMore(this);
        }
    }

    private void d() {
        this.r = (ImageView) this.o.findViewById(R.id.ttg_iv_ball_l);
        this.s = (ImageView) this.o.findViewById(R.id.ttg_iv_ball_r);
        this.t = (TextView) this.o.findViewById(R.id.ttg_tv_refresh);
        e();
        this.v = (TextView) this.u.findViewById(R.id.ttg_loadstate_tv);
    }

    private void e() {
        if (this.p == null) {
            this.p = new TranslateAnimation((float) a(5.0f), 0.0f, 0.0f, 0.0f);
            this.p.setFillAfter(false);
            this.p.setDuration(600);
            this.p.setRepeatCount(-1);
            this.p.setRepeatMode(2);
        }
        if (this.q == null) {
            this.q = new TranslateAnimation((float) (-a(5.0f)), 0.0f, 0.0f, 0.0f);
            this.q.setDuration(600);
            this.q.setRepeatCount(-1);
            this.q.setRepeatMode(2);
        }
    }

    private int a(float f) {
        return (int) ((getContext().getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!this.l) {
            this.o = getChildAt(0);
            this.w = getChildAt(1);
            this.u = getChildAt(2);
            this.l = true;
            d();
            if (!(this.o == null || ((ViewGroup) this.o).getChildAt(0) == null)) {
                this.j = (float) ((ViewGroup) this.o).getChildAt(0).getMeasuredHeight();
            }
            if (!(this.u == null || ((ViewGroup) this.u).getChildAt(0) == null)) {
                this.k = (float) ((ViewGroup) this.u).getChildAt(0).getMeasuredHeight();
            }
        }
        if (this.o != null) {
            this.o.layout(0, ((int) (this.a + this.i)) - this.o.getMeasuredHeight(), this.o.getMeasuredWidth(), (int) (this.a + this.i));
        }
        if (this.w != null) {
            this.w.layout(0, (int) (this.a + this.i), this.w.getMeasuredWidth(), ((int) (this.a + this.i)) + this.w.getMeasuredHeight());
        }
        if (this.u != null && this.w != null) {
            this.u.layout(0, ((int) (this.a + this.i)) + this.w.getMeasuredHeight(), this.u.getMeasuredWidth(), (((int) (this.a + this.i)) + this.w.getMeasuredHeight()) + this.u.getMeasuredHeight());
        }
    }
}
