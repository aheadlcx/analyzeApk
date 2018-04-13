package qsbk.app.activity;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

class gz implements OnClickListener {
    final /* synthetic */ CircleTopicActivity a;
    private Animation b;
    private Animation c;
    private Animation d;
    private int e;
    private int f;

    gz(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    private void a() {
        if (this.d == null) {
            this.b = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
            this.b.setInterpolator(new LinearInterpolator());
            this.b.setDuration(150);
            this.b.setFillAfter(true);
            this.c = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
            this.c.setInterpolator(new LinearInterpolator());
            this.c.setDuration(150);
            this.c.setFillAfter(true);
            this.d = new ha(this);
            this.d.setAnimationListener(new hb(this));
        }
    }

    public void onClick(View view) {
        if (this.a.p.getVisibility() == 0) {
            a();
            this.a.A = !this.a.A;
            int height = this.a.n.getHeight();
            if (this.a.A) {
                this.a.n.setMaxLines(Integer.MAX_VALUE);
                this.a.n.clearAnimation();
                this.a.n.measure(MeasureSpec.makeMeasureSpec(this.a.n.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
                this.e = height;
                this.f = this.a.n.getMeasuredHeight();
                this.a.n.setHeight(this.e);
                this.d.setDuration((long) (Math.abs(this.f - this.e) + 150));
                this.a.n.startAnimation(this.d);
                this.a.q.clearAnimation();
                this.a.q.startAnimation(this.b);
                return;
            }
            this.f = (this.a.n.getLayout().getLineBottom(0) + this.a.n.getPaddingTop()) + this.a.n.getPaddingBottom();
            this.e = height;
            this.a.n.clearAnimation();
            this.d.setDuration((long) (Math.abs(this.f - this.e) + 150));
            this.a.n.startAnimation(this.d);
            this.a.q.clearAnimation();
            this.a.q.startAnimation(this.c);
        }
    }
}
