package cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku;

import android.app.Activity;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import cn.htjyb.ui.b;
import cn.xiaochuankeji.tieba.R;

public class d extends FrameLayout implements OnClickListener {
    private Activity a;
    private a b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private Animation h;
    private Animation i;
    private Animation j;
    private Animation k;
    private boolean l = false;

    public interface a {
        void a(int i);
    }

    public d(Activity activity, a aVar) {
        super(activity);
        this.a = activity;
        this.b = aVar;
        LayoutInflater.from(activity).inflate(R.layout.danmaku_long_click_view, this);
        getViews();
        d();
        setId(R.id.id_danmaku_long_click_show_view);
        this.h = AnimationUtils.loadAnimation(activity, R.anim.bottom_in);
        this.i = AnimationUtils.loadAnimation(activity, R.anim.bottom_out);
        this.j = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_in);
        this.k = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_out);
    }

    private void getViews() {
        this.f = findViewById(R.id.dim_view);
        this.g = findViewById(R.id.layout_sheet_dialog);
        this.c = findViewById(R.id.vReply);
        this.c.setTag(Integer.valueOf(1));
        this.c.setOnClickListener(this);
        this.d = findViewById(R.id.vReport);
        this.d.setTag(Integer.valueOf(2));
        this.d.setOnClickListener(this);
        this.e = findViewById(R.id.vCancel);
    }

    private void c() {
        ViewGroup c = c(b.a(this.a));
        setLayoutParams(new LayoutParams(-1, -1));
        c.addView(this);
    }

    private void d() {
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(true, null);
            }
        });
    }

    private static d b(Activity activity) {
        View c = c(activity);
        if (c == null) {
            return null;
        }
        return (d) c.findViewById(R.id.id_danmaku_long_click_show_view);
    }

    private static ViewGroup c(Activity activity) {
        return (ViewGroup) activity.findViewById(16908290);
    }

    public static boolean a(Activity activity) {
        d b = b(activity);
        if (b == null || !b.a()) {
            return false;
        }
        b.a(true, null);
        return true;
    }

    public boolean a() {
        return getVisibility() == 0;
    }

    public void b() {
        if (b(this.a) == null) {
            c();
            this.f.startAnimation(this.j);
            this.g.startAnimation(this.h);
        }
    }

    private void a(boolean z, final AnimationListener animationListener) {
        if (z) {
            this.i.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ d b;

                public void onAnimationStart(Animation animation) {
                    this.b.l = true;
                    if (animationListener != null) {
                        animationListener.onAnimationStart(animation);
                    }
                }

                public void onAnimationEnd(Animation animation) {
                    this.b.l = false;
                    this.b.e();
                    if (animationListener != null) {
                        animationListener.onAnimationEnd(animation);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                    if (animationListener != null) {
                        animationListener.onAnimationRepeat(animation);
                    }
                }
            });
            this.f.startAnimation(this.k);
            this.g.startAnimation(this.i);
            return;
        }
        e();
    }

    private void e() {
        c(b.a(this.a)).removeView(this);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return true;
        }
        if (this.l) {
            return false;
        }
        Rect rect = new Rect();
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        this.g.getGlobalVisibleRect(rect);
        if (rect.contains(rawX, rawY)) {
            return true;
        }
        a(true, null);
        return true;
    }

    public void onClick(View view) {
        final int intValue = ((Integer) view.getTag()).intValue();
        a(true, new AnimationListener(this) {
            final /* synthetic */ d b;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (this.b.b != null) {
                    this.b.b.a(intValue);
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
