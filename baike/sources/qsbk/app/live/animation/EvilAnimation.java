package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.widget.LargeGiftLayout;

public class EvilAnimation extends BaseLargeAnimation {
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;

    public void attach(Context context, LargeGiftLayout largeGiftLayout) {
        super.attach(context, largeGiftLayout);
        f();
    }

    public long getGiftId() {
        return 34;
    }

    protected void a(View view) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getWidth(), view.getHeight());
        layoutParams.topMargin = (int) (((0.67d * ((double) this.e)) - (((double) this.u) * 0.5d)) - ((double) view.getHeight()));
        layoutParams.leftMargin = (int) (((1.385d * ((double) this.d)) - (((double) this.t) * 0.5d)) + (((double) (this.t - view.getWidth())) * 0.5d));
        view.setLayoutParams(layoutParams);
    }

    protected void onLoadAnim(LiveGiftMessage liveGiftMessage) {
        k();
        e();
        i();
        g();
        h();
        j();
        l();
    }

    private void e() {
        ImageView a = a(9, 10, this.f, this.g, a(R.drawable.live_cloud_top_left));
        ImageView a2 = a(11, 10, this.h, this.i, a(R.drawable.live_cloud_top_right));
        ImageView a3 = a(9, 12, this.j, this.k, a(R.drawable.live_cloud_bottom_left));
        ImageView a4 = a(11, 12, this.l, this.m, a(R.drawable.live_cloud_bottom_right));
        a(a, 0, 320, 0.0f, 1.0f);
        a(a, 5720, 240, 1.0f, 0.0f);
        a(a2, 120, 320, 0.0f, 1.0f);
        a(a2, 5600, 280, 1.0f, 0.0f);
        a(a3, 200, 320, 0.0f, 1.0f);
        a(a3, 5600, 280, 1.0f, 0.0f);
        a(a4, 80, 320, 0.0f, 1.0f);
        a(a4, 5720, 240, 1.0f, 0.0f);
    }

    private void f() {
        this.f = (int) (0.361d * ((double) this.d));
        this.g = (int) (0.121d * ((double) this.e));
        this.h = (int) (0.343d * ((double) this.d));
        this.i = (int) (0.335d * ((double) this.e));
        this.j = (int) (0.386d * ((double) this.d));
        this.k = (int) (0.341d * ((double) this.e));
        this.l = (int) (0.478d * ((double) this.d));
        this.m = (int) (0.141d * ((double) this.e));
        this.n = (int) (0.3d * ((double) this.d));
        this.o = (int) (((double) this.e) * 0.08d);
        this.p = (int) (0.15d * ((double) this.d));
        this.q = (int) (((double) this.e) * 0.08d);
        this.r = (int) (0.09d * ((double) this.d));
        this.s = this.r;
        this.t = (int) (0.647d * ((double) this.d));
        this.u = (int) (0.351d * ((double) this.e));
        this.v = (int) (0.88d * ((double) this.d));
        this.w = (int) (0.534d * ((double) this.e));
        int i = (int) (0.044d * ((double) this.d));
        this.y = i;
        this.x = i;
    }

    private ImageView a(int i, int i2, int i3, int i4, Bitmap bitmap) {
        View imageView = new ImageView(this.a);
        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ScaleType.FIT_XY);
        if (i == 9) {
            imageView.setPivotX(0.0f);
        } else {
            imageView.setPivotX((float) i3);
        }
        if (i2 == 10) {
            imageView.setPivotY(0.0f);
        } else {
            imageView.setPivotY((float) i4);
        }
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.addRule(i);
        layoutParams.addRule(i2);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
        imageView.setVisibility(4);
        return imageView;
    }

    private void a(ImageView imageView, long j, long j2, float f, float f2) {
        a(new o(this, imageView, f, f2, j2), j);
    }

    private void g() {
        ImageView b = b((int) ((((double) this.d) * 1.067d) - (0.5d * ((double) this.n))), (int) ((((double) this.e) * 0.637d) - (0.5d * ((double) this.o))), this.n, this.o, a(R.drawable.live_bat_1));
        ImageView b2 = b((int) ((((double) this.d) * 1.04d) - (0.5d * ((double) this.p))), (int) ((((double) this.e) * 0.637d) - (0.5d * ((double) this.q))), this.p, this.q, a(R.drawable.live_bat_2));
        ImageView b3 = b((int) ((((double) this.d) * 1.089d) - (0.5d * ((double) this.r))), (int) ((((double) this.e) * 0.637d) - (0.5d * ((double) this.s))), this.r, this.s, a(R.drawable.live_bat_3));
        a(b, 800, (int) (-1.144d * ((double) this.d)), (int) (-0.485d * ((double) this.e)), 800, 400, 1.28f, 2.0f, 200, 0.0f, 200);
        a(b2, 500, (int) (-1.095d * ((double) this.d)), (int) (-0.218d * ((double) this.e)), 800, 400, 1.0f, 1.5f, 200, 1.0f, 200);
        a(b3, 1000, (int) (-1.165d * ((double) this.d)), (int) (-0.4d * ((double) this.e)), 800, 400, 1.18f, 2.0f, 200, 0.0f, 200);
    }

    private ImageView b(int i, int i2, int i3, int i4, Bitmap bitmap) {
        View imageView = new ImageView(this.a);
        imageView.setImageBitmap(bitmap);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i2;
        imageView.setLayoutParams(layoutParams);
        b(imageView);
        imageView.setVisibility(4);
        return imageView;
    }

    private void a(ImageView imageView, long j, int i, int i2, long j2, long j3, float f, float f2, long j4, float f3, long j5) {
        a(new q(this, imageView, i, i2, j2, f, f2, j4, f3, j3, j5), j);
    }

    private void h() {
        a(c((int) ((1.385d * ((double) this.d)) - (0.5d * ((double) this.t))), (int) ((0.67d * ((double) this.e)) - (0.5d * ((double) this.u))), this.t, this.u, a(R.drawable.live_evil)), 1320, (int) (-0.87d * ((double) this.d)), (int) (-0.211d * ((double) this.e)), 680, 3000, (int) (-1.725d * ((double) this.d)), (int) (-0.445d * ((double) this.e)), 760, true);
    }

    private ImageView c(int i, int i2, int i3, int i4, Bitmap bitmap) {
        View imageView = new ImageView(this.a);
        imageView.setImageBitmap(bitmap);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i2;
        imageView.setLayoutParams(layoutParams);
        b(imageView);
        imageView.setVisibility(4);
        return imageView;
    }

    private void a(View view, long j, int i, int i2, long j2, long j3, int i3, int i4, long j4, boolean z) {
        a(new s(this, view, i, i2, j2, z, i3, i4, j4), j);
    }

    private void i() {
        a(d((int) ((0.5d * ((double) this.d)) - (0.5d * ((double) this.v))), (int) ((0.012d * ((double) this.e)) - (0.5d * ((double) this.w))), this.v, this.w, a(R.drawable.live_evil_moon)), 520, this.d, 240, (int) (0.423d * ((double) this.e)), 200, (int) (0.45d * ((double) this.e)), 120, (int) (1.241d * ((double) this.e)), 4400, 240);
    }

    private ImageView d(int i, int i2, int i3, int i4, Bitmap bitmap) {
        View imageView = new ImageView(this.a);
        imageView.setImageBitmap(bitmap);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i2;
        imageView.setLayoutParams(layoutParams);
        b(imageView);
        imageView.setVisibility(4);
        return imageView;
    }

    private void a(ImageView imageView, long j, int i, long j2, int i2, long j3, int i3, long j4, int i4, long j5, long j6) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{0.0f, (float) i});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, View.SCALE_X, new float[]{0.07f, 0.8f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, new float[]{0.07f, 0.8f});
        animatorSet.setDuration(j2);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        AnimatorSet animatorSet2 = new AnimatorSet();
        ofFloat2 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{(float) i, (float) i2});
        ofFloat3 = ObjectAnimator.ofFloat(imageView, View.ROTATION, new float[]{0.0f, 15.0f});
        animatorSet2.setDuration(j3);
        animatorSet2.playTogether(new Animator[]{ofFloat2, ofFloat3});
        AnimatorSet animatorSet3 = new AnimatorSet();
        ofFloat3 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{(float) i2, (float) i3});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(imageView, View.ROTATION, new float[]{15.0f, 0.0f});
        animatorSet2.setDuration(j4);
        animatorSet3.playTogether(new Animator[]{ofFloat3, ofFloat4});
        ObjectAnimator.ofFloat(imageView, View.ROTATION, new float[]{0.0f, -15.0f}).setDuration(j5);
        AnimatorSet animatorSet4 = new AnimatorSet();
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{(float) i3, (float) i4});
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(imageView, View.ROTATION, new float[]{-15.0f, 50.0f});
        animatorSet4.setDuration(j6);
        animatorSet4.playTogether(new Animator[]{ofFloat5, ofFloat6});
        AnimatorSet animatorSet5 = new AnimatorSet();
        animatorSet5.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3, r5, animatorSet4});
        animatorSet5.addListener(new u(this, imageView));
        a(new v(this, imageView, animatorSet5), j);
    }

    private void j() {
        a(a((int) ((0.586d * ((double) this.d)) - (0.5d * ((double) this.x))), (int) ((0.078d * ((double) this.e)) - (0.5d * ((double) this.y))), this.x, this.y, 30.0f, 1.0f, a(R.drawable.live_evil_star)), 640, 160, 1.0f, 6680, 360, 0.0f, false, true);
        a(a((int) ((0.875d * ((double) this.d)) - (0.5d * ((double) this.x))), (int) ((0.2d * ((double) this.e)) - (0.5d * ((double) this.y))), this.x, this.y, 10.0f, 0.9f, a(R.drawable.live_evil_star)), 1120, 160, 1.0f, 6520, 360, 0.0f, true, true);
        a(a((int) ((0.775d * ((double) this.d)) - ((0.5d * ((double) this.x)) * 1.24d)), (int) ((0.685d * ((double) this.e)) - ((0.5d * ((double) this.y)) * 1.24d)), this.x, this.y, 0.0f, 1.24f, a(R.drawable.live_evil_star)), 640, 160, 1.24f, 6400, 360, 0.0f, true, false);
        a(a((int) ((0.286d * ((double) this.d)) - ((0.5d * ((double) this.x)) * 0.6d)), (int) ((0.918d * ((double) this.e)) - ((0.5d * ((double) this.y)) * 0.6d)), this.x, this.y, 9.0f, 0.6f, a(R.drawable.live_evil_star)), 880, 160, 0.66f, 6600, 360, 0.0f, false, true);
        a(a((int) ((0.085d * ((double) this.d)) - ((0.5d * ((double) this.x)) * 0.7d)), (int) ((0.241d * ((double) this.e)) - ((0.5d * ((double) this.y)) * 0.7d)), this.x, this.y, -28.0f, 0.7f, a(R.drawable.live_evil_star)), 840, 200, 1.15f, 6400, 360, 0.0f, false, false);
        a(a((int) ((0.804d * ((double) this.d)) - ((0.5d * ((double) this.x)) * 0.66d)), (int) ((0.67d * ((double) this.e)) - ((0.5d * ((double) this.y)) * 0.66d)), this.x, this.y, 0.0f, 0.66f, a(R.drawable.live_evil_star)), 880, 160, 1.0f, 6680, 360, 0.0f, false, true);
    }

    private ImageView a(int i, int i2, int i3, int i4, float f, float f2, Bitmap bitmap) {
        View imageView = new ImageView(this.a);
        imageView.setImageBitmap(bitmap);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i2;
        imageView.setLayoutParams(layoutParams);
        imageView.setRotation(f);
        imageView.setAlpha(f2);
        b(imageView);
        imageView.setVisibility(4);
        return imageView;
    }

    private void a(ImageView imageView, long j, long j2, float f, long j3, long j4, float f2, boolean z, boolean z2) {
        a(new w(this, imageView, f, j2, j3, j, f2, j4, z2, z), j);
    }

    private void k() {
        b(a(0, 0, this.d, this.e));
    }

    private ImageView a(int i, int i2, int i3, int i4) {
        View imageView = new ImageView(this.a);
        imageView.setBackgroundColor(this.a.getResources().getColor(R.color.black));
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i2;
        imageView.setLayoutParams(layoutParams);
        b(imageView);
        imageView.setVisibility(4);
        return imageView;
    }

    private void b(ImageView imageView) {
        imageView.setVisibility(0);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        AnimatorSet animatorSet3 = new AnimatorSet();
        AnimatorSet animatorSet4 = new AnimatorSet();
        Animator ofFloat = ObjectAnimator.ofFloat(imageView, View.ALPHA, new float[]{0.0f, 0.3f});
        animatorSet.setDuration(240);
        animatorSet.play(ofFloat);
        ofFloat = ObjectAnimator.ofFloat(imageView, View.ALPHA, new float[]{0.3f, 0.3f});
        animatorSet2.setDuration(5360);
        animatorSet2.play(ofFloat);
        ofFloat = ObjectAnimator.ofFloat(imageView, View.ALPHA, new float[]{0.3f, 0.0f});
        animatorSet3.setDuration(360);
        animatorSet3.play(ofFloat);
        animatorSet4.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3});
        animatorSet4.addListener(new y(this, imageView));
        animatorSet4.start();
    }

    private void l() {
        a(this.b.mUserInfoLayout, 1320, (int) (-0.87d * ((double) this.d)), (int) (-0.211d * ((double) this.e)), 680, 3000, (int) (-1.725d * ((double) this.d)), (int) (-0.445d * ((double) this.e)), 760, false);
    }

    public void releaseResource() {
    }
}
