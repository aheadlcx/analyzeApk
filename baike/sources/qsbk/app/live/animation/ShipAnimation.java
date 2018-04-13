package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.baidu.mobstat.Config;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.widget.LargeGiftLayout;

public class ShipAnimation extends BaseLargeAnimation {
    private int f;
    private int g;
    private Runnable h;
    private Runnable i;
    private Runnable j;
    private int k;
    private int l;
    private Point[] m;
    private Point[] n;
    private int o = 0;
    private Paint p = new Paint();
    private Path q = new Path();
    private Path r = new Path();

    public void attach(Context context, LargeGiftLayout largeGiftLayout) {
        super.attach(context, largeGiftLayout);
        this.f = this.d;
        this.g = (int) (((double) this.d) * 0.541d);
        this.p.setColor(Color.rgb(0, 255, 242));
        this.p.setAntiAlias(true);
        this.p.setStyle(Style.FILL);
    }

    public long getGiftId() {
        return 8;
    }

    protected void a(View view) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getWidth(), view.getHeight());
        layoutParams.topMargin = (this.e / 3) - view.getHeight();
        layoutParams.leftMargin = (-(this.f + view.getWidth())) / 2;
        view.setVisibility(0);
        view.setLayoutParams(layoutParams);
    }

    public void onLoadAnim(LiveGiftMessage liveGiftMessage) {
        if (!a(liveGiftMessage, a(R.drawable.live_ship_full))) {
            e();
            View imageView = new ImageView(this.a);
            imageView.setImageBitmap(r0);
            c(imageView);
            AnimatorSet d = d(imageView);
            AnimatorSet d2 = d(this.b.mUserInfoLayout);
            d.addListener(new ab(this, imageView));
            d.start();
            d2.start();
            a(new ac(this), 6500);
        }
    }

    private void e() {
        f();
        i();
    }

    private void f() {
        int i;
        double d = 12.566370614359172d / ((double) this.d);
        if (this.m == null) {
            this.m = new Point[(this.d / 5)];
        }
        for (i = 0; i < this.m.length; i++) {
            this.m[i] = new Point();
            this.m[i].x = i * 5;
            this.m[i].y = (int) (((Math.sin(((double) this.m[i].x) * d) * 15.0d) + ((double) this.e)) - 10.0d);
        }
        if (this.n == null) {
            this.n = new Point[(this.d / 5)];
        }
        for (i = 0; i < this.n.length; i++) {
            this.n[i] = new Point();
            this.n[i].x = i * 5;
            this.n[i].y = (int) ((Math.sin(((double) (this.n[i].x - 200)) * d) * 15.0d) + ((double) this.e));
        }
        this.k = 0;
        this.l = 0;
    }

    private void c(View view) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.f, this.g);
        layoutParams.topMargin = this.e / 3;
        layoutParams.leftMargin = -this.f;
        ((ImageView) view).setScaleType(ScaleType.FIT_XY);
        view.setLayoutParams(layoutParams);
        b(view);
    }

    private AnimatorSet d(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{0.0f, (float) (((this.d + this.f) * 1) / 2)});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) (((this.e / 8) * 1) / 2)});
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet2.setDuration(1000);
        AnimatorSet animatorSet3 = new AnimatorSet();
        ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{(float) (((this.d + this.f) * 1) / 2), (float) (((this.d + this.f) * 5) / 8)});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) (((this.e / 8) * 1) / 2), (float) (((this.e / 8) * 5) / 8)});
        animatorSet3.playTogether(new Animator[]{ofFloat2, ofFloat3});
        animatorSet3.setDuration(Config.BPLUS_DELAY_TIME);
        AnimatorSet animatorSet4 = new AnimatorSet();
        ofFloat3 = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{(float) (((this.d + this.f) * 5) / 8), (float) (this.d + this.f)});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) (((this.e / 8) * 5) / 8), (float) (this.e / 8)});
        animatorSet4.playTogether(new Animator[]{ofFloat3, ofFloat4});
        animatorSet4.setDuration(1000);
        animatorSet.playSequentially(new Animator[]{animatorSet2, animatorSet3, animatorSet4});
        animatorSet.setTarget(view);
        return animatorSet;
    }

    private void g() {
        h();
    }

    private void h() {
        if (this.j == null) {
            this.j = new ad(this);
        }
        a(this.j, (long) (8000 / this.e));
    }

    private void a(Point[] pointArr, int i) {
        for (Point point : pointArr) {
            point.y += i;
        }
    }

    private void a(Path path, int i, Point[] pointArr) {
        path.moveTo(0.0f, (float) this.e);
        for (int i2 = 0; i2 < pointArr.length - 1; i2++) {
            path.lineTo((float) (i2 * 5), (float) pointArr[(i + i2) % pointArr.length].y);
        }
        path.lineTo((float) this.d, (float) this.e);
        path.close();
    }

    private void i() {
        if (this.i == null) {
            this.i = new ae(this);
        }
        a(this.i, (long) (8000 / this.e));
    }

    private void j() {
        if (this.h == null) {
            this.h = new af(this);
        }
        a(this.h, 100);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawColor(0);
        this.q.reset();
        this.r.reset();
        a(this.q, this.k, this.m);
        a(this.r, this.l, this.n);
        this.p.setAlpha(77);
        canvas.drawPath(this.q, this.p);
        this.p.setAlpha(128);
        canvas.drawPath(this.r, this.p);
    }

    public void releaseResource() {
        a(this.h);
        a(this.i);
        a(this.j);
    }
}
