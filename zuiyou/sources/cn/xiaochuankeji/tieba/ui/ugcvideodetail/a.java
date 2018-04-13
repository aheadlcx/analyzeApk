package cn.xiaochuankeji.tieba.ui.ugcvideodetail;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout.LayoutParams;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class a implements AnimationListener {
    private Activity a;
    private View b;
    private Animation c;
    private Animation d;
    private Animation e;
    private Animation f;
    private boolean g = true;
    private boolean h = true;

    public a(AppCompatActivity appCompatActivity) {
        this.a = appCompatActivity;
    }

    public void a(View view) {
        this.b = view;
        this.c = AnimationUtils.loadAnimation(this.a, R.anim.ugcvideo_detail_bottom_in);
        this.c.setInterpolator(new DecelerateInterpolator(2.0f));
        this.c.setAnimationListener(this);
        this.d = AnimationUtils.loadAnimation(this.a, R.anim.ugcvideo_detail_bottom_in_empty);
        this.d.setInterpolator(new DecelerateInterpolator(2.0f));
        this.d.setAnimationListener(this);
        this.e = AnimationUtils.loadAnimation(this.a, R.anim.ugcvideo_detail_bottom_out);
        this.e.setInterpolator(new DecelerateInterpolator(2.0f));
        this.e.setAnimationListener(this);
        this.f = AnimationUtils.loadAnimation(this.a, R.anim.ugcvideo_detail_bottom_out_empty);
        this.f.setInterpolator(new DecelerateInterpolator(2.0f));
        this.f.setAnimationListener(this);
    }

    public void a(boolean z) {
        this.g = z;
    }

    public boolean a() {
        return !this.h;
    }

    public void b() {
        if (this.h) {
            this.h = false;
            this.b.startAnimation(this.g ? this.d : this.c);
        }
    }

    public void c() {
        if (!this.h) {
            this.h = true;
            this.b.startAnimation(this.g ? this.f : this.e);
        }
    }

    public void d() {
        this.h = true;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        LayoutParams layoutParams;
        if (this.c == animation) {
            layoutParams = (LayoutParams) this.b.getLayoutParams();
            layoutParams.topMargin = 0;
            this.b.setLayoutParams(layoutParams);
            this.b.clearAnimation();
        } else if (this.d == animation) {
            layoutParams = (LayoutParams) this.b.getLayoutParams();
            layoutParams.topMargin = 0;
            this.b.setLayoutParams(layoutParams);
            this.b.clearAnimation();
        } else if (this.e == animation) {
            layoutParams = (LayoutParams) this.b.getLayoutParams();
            layoutParams.topMargin = e.a(61.0f);
            this.b.setLayoutParams(layoutParams);
            this.b.clearAnimation();
        } else if (this.f == animation) {
            layoutParams = (LayoutParams) this.b.getLayoutParams();
            layoutParams.topMargin = e.a(83.0f);
            this.b.setLayoutParams(layoutParams);
            this.b.clearAnimation();
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
