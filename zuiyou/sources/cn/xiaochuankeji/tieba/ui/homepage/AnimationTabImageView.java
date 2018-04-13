package cn.xiaochuankeji.tieba.ui.homepage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import c.a.i.i;

public class AnimationTabImageView extends i {
    private Animation a;
    private Animation b;
    private a c;

    public interface a {
        void a();
    }

    public AnimationTabImageView(Context context) {
        super(context);
        b();
    }

    public AnimationTabImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public AnimationTabImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
    }

    public void a() {
        if (!isSelected()) {
            f();
        }
    }

    private AnimationSet c() {
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setDuration(80);
        animationSet.addAnimation(new ScaleAnimation(1.0f, 1.15f, 1.0f, 1.15f, 1, 0.5f, 1, 0.5f));
        animationSet.setFillBefore(false);
        animationSet.setFillAfter(false);
        return animationSet;
    }

    private AnimationSet e() {
        AnimationSet animationSet = new AnimationSet(false);
        Animation scaleAnimation = new ScaleAnimation(1.15f, 0.97f, 1.15f, 0.97f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(160);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new ScaleAnimation(0.97f, 1.0f, 0.97f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(160);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        animationSet.addAnimation(scaleAnimation);
        animationSet.setDuration(320);
        animationSet.setFillBefore(false);
        animationSet.setFillAfter(false);
        return animationSet;
    }

    public void setAnimationListener(a aVar) {
        this.c = aVar;
    }

    private void f() {
        this.a = c();
        this.b = e();
        AnimationListener anonymousClass1 = new AnimationListener(this) {
            final /* synthetic */ AnimationTabImageView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (animation.equals(this.a.a)) {
                    this.a.setSelected(true);
                    this.a.startAnimation(this.a.b);
                } else if (animation.equals(this.a.b)) {
                    this.a.clearAnimation();
                    if (this.a.c != null) {
                        this.a.c.a();
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }
        };
        this.a.setAnimationListener(anonymousClass1);
        this.b.setAnimationListener(anonymousClass1);
        startAnimation(this.a);
    }

    public void d() {
        super.d();
    }
}
