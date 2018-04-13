package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.util.ArrayList;
import java.util.Random;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.utils.ThemeUtils;

public class FavorLayout extends RelativeLayout {
    public static final int FAVOR_COUNT = 10;
    public static final int LOVE_ANIM_DURATION = 3000;
    private String a;
    private Random b;
    private int c;
    private int d;
    private int e;
    private int f;
    private LayoutParams g;
    private Drawable[] h;
    private Interpolator i;
    private Interpolator j;
    private Interpolator k;
    private Interpolator l;
    private Interpolator[] m;
    private ArrayList<Integer> n;
    private Handler o;

    public class BezierEvaluator implements TypeEvaluator<PointF> {
        final /* synthetic */ FavorLayout a;
        private PointF b;
        private PointF c;

        public BezierEvaluator(FavorLayout favorLayout, PointF pointF, PointF pointF2) {
            this.a = favorLayout;
            this.b = pointF;
            this.c = pointF2;
        }

        public PointF evaluate(float f, PointF pointF, PointF pointF2) {
            float f2 = 1.0f - f;
            PointF pointF3 = new PointF();
            pointF3.x = (((((f2 * f2) * f2) * pointF.x) + ((((3.0f * f2) * f2) * f) * this.b.x)) + ((((3.0f * f2) * f) * f) * this.c.x)) + (((f * f) * f) * pointF2.x);
            pointF3.y = (((((f2 * 3.0f) * f) * f) * this.c.y) + ((((f2 * f2) * f2) * pointF.y) + ((((3.0f * f2) * f2) * f) * this.b.y))) + (((f * f) * f) * pointF2.y);
            return pointF3;
        }
    }

    private class a extends AnimatorListenerAdapter {
        final /* synthetic */ FavorLayout a;
        private ImageView b;

        public a(FavorLayout favorLayout, ImageView imageView) {
            this.a = favorLayout;
            this.b = imageView;
        }

        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.b.setVisibility(8);
            Drawable drawable = this.b.getDrawable();
            if (drawable != null) {
                drawable.setCallback(null);
            }
            this.b.setImageDrawable(null);
            this.b.clearFocus();
            this.a.post(new bk(this));
            if (this.a.n.size() > 0) {
                int intValue;
                synchronized (this) {
                    intValue = ((Integer) this.a.n.remove(0)).intValue();
                }
                this.a.addFavor(intValue);
            }
        }
    }

    private class b implements AnimatorUpdateListener {
        final /* synthetic */ FavorLayout a;
        private View b;

        public b(FavorLayout favorLayout, View view) {
            this.a = favorLayout;
            this.b = view;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            PointF pointF = (PointF) valueAnimator.getAnimatedValue();
            this.b.setX(pointF.x);
            this.b.setY(pointF.y);
            this.b.setAlpha(1.0f - valueAnimator.getAnimatedFraction());
        }
    }

    public FavorLayout(Context context) {
        this(context, null);
        a();
    }

    public FavorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        a();
    }

    public FavorLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = FavorLayout.class.getSimpleName();
        this.b = new Random();
        this.g = new LayoutParams(-2, -2);
        this.i = new LinearInterpolator();
        this.j = new LinearInterpolator();
        this.k = new LinearInterpolator();
        this.l = new LinearInterpolator();
        this.n = new ArrayList();
        a();
    }

    private void a() {
        this.o = new Handler();
        b();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f = getMeasuredWidth();
        this.e = getMeasuredHeight();
        this.g.width = this.d;
        this.g.height = this.c;
        this.g.addRule(12, -1);
        WindowUtils.dp2Px(16);
        this.g.leftMargin = ((this.f - this.d) / 2) + WindowUtils.dp2Px(16);
    }

    private void b() {
        if (this.h == null) {
            TypedArray obtainTypedArray = getResources().obtainTypedArray(ThemeUtils.getLoveThemeArrayResId());
            this.h = new Drawable[obtainTypedArray.length()];
            for (int i = 0; i < obtainTypedArray.length(); i++) {
                this.h[i] = obtainTypedArray.getDrawable(i);
            }
            obtainTypedArray.recycle();
            this.d = WindowUtils.dp2Px(26);
            this.c = WindowUtils.dp2Px(26);
            this.m = new Interpolator[4];
            this.m[0] = this.i;
            this.m[1] = this.j;
            this.m[2] = this.k;
            this.m[3] = this.l;
        }
    }

    public void addFavor(int i) {
        addFavor(i, 0);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addFavor(int r7, int r8, int r9) {
        /*
        r6 = this;
        r0 = 10;
        r2 = 1;
        r1 = r6.a;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "live add love color index ";
        r3 = r3.append(r4);
        r3 = r3.append(r7);
        r4 = " favor ";
        r3 = r3.append(r4);
        r3 = r3.append(r8);
        r3 = r3.toString();
        qsbk.app.core.utils.LogUtils.d(r1, r3);
        r6.addFavor(r7);
        if (r8 <= r2) goto L_0x0050;
    L_0x002a:
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r9 <= 0) goto L_0x0051;
    L_0x002e:
        r1 = r9 + 1;
        r1 = r1 * 1000;
        r1 = r1 / r8;
        if (r1 > r0) goto L_0x0051;
    L_0x0035:
        r1 = r2;
    L_0x0036:
        if (r1 >= r8) goto L_0x0050;
    L_0x0038:
        r2 = r6.o;
        r3 = new qsbk.app.live.widget.bj;
        r3.<init>(r6, r7);
        r4 = r6.b;
        r4 = r4.nextInt();
        r4 = r4 % r0;
        r5 = r1 * r0;
        r4 = r4 + r5;
        r4 = (long) r4;
        r2.postDelayed(r3, r4);
        r1 = r1 + 1;
        goto L_0x0036;
    L_0x0050:
        return;
    L_0x0051:
        r0 = r1;
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.live.widget.FavorLayout.addFavor(int, int, int):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addFavor(int r5, long r6) {
        /*
        r4 = this;
        r4.b();
        monitor-enter(r4);
        r0 = r4.getChildCount();	 Catch:{ all -> 0x0060 }
        r1 = 100;
        if (r0 < r1) goto L_0x0021;
    L_0x000c:
        r0 = r4.n;	 Catch:{ all -> 0x0060 }
        r0 = r0.size();	 Catch:{ all -> 0x0060 }
        r1 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        if (r0 >= r1) goto L_0x001f;
    L_0x0016:
        r0 = r4.n;	 Catch:{ all -> 0x0060 }
        r1 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x0060 }
        r0.add(r1);	 Catch:{ all -> 0x0060 }
    L_0x001f:
        monitor-exit(r4);	 Catch:{ all -> 0x0060 }
    L_0x0020:
        return;
    L_0x0021:
        monitor-exit(r4);	 Catch:{ all -> 0x0060 }
        r0 = 0;
        if (r5 > 0) goto L_0x0063;
    L_0x0025:
        r0 = java.lang.System.currentTimeMillis();
        r2 = r4.h;
        r2 = r2.length;
        r2 = (long) r2;
        r0 = r0 % r2;
        r0 = (int) r0;
    L_0x002f:
        r1 = new android.widget.ImageView;
        r2 = r4.getContext();
        r1.<init>(r2);
        r2 = r4.h;
        r0 = r2[r0];
        r1.setImageDrawable(r0);
        r0 = r4.g;
        r1.setLayoutParams(r0);
        r4.addView(r1);
        r0 = r4.a(r1);
        r2 = new qsbk.app.live.widget.FavorLayout$a;
        r2.<init>(r4, r1);
        r0.addListener(r2);
        r2 = 0;
        r1 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r1 == 0) goto L_0x005c;
    L_0x0059:
        r0.setStartDelay(r6);
    L_0x005c:
        r0.start();
        goto L_0x0020;
    L_0x0060:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0060 }
        throw r0;
    L_0x0063:
        r1 = r4.h;
        r1 = r1.length;
        if (r5 > r1) goto L_0x002f;
    L_0x0068:
        r0 = r5 + -1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.live.widget.FavorLayout.addFavor(int, long):void");
    }

    private Animator a(View view) {
        AnimatorSet b = b(view);
        ValueAnimator c = c(view);
        Animator animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{b, c});
        animatorSet.setInterpolator(this.m[this.b.nextInt(4)]);
        animatorSet.setTarget(view);
        return animatorSet;
    }

    private AnimatorSet b(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.4f, 1.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.4f, 1.0f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.4f, 1.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        animatorSet.setTarget(view);
        return animatorSet;
    }

    private ValueAnimator c(View view) {
        ValueAnimator ofObject = ValueAnimator.ofObject(new BezierEvaluator(this, a(2), a(1)), new Object[]{new PointF((float) (((this.f - this.d) / 2) + WindowUtils.dp2Px(17)), (float) (this.e - this.c)), new PointF((float) this.b.nextInt(this.f - (this.d / 2)), 0.0f)});
        ofObject.addUpdateListener(new b(this, view));
        ofObject.setTarget(view);
        ofObject.setDuration(3000);
        return ofObject;
    }

    private PointF a(int i) {
        PointF pointF = new PointF();
        pointF.x = (float) this.b.nextInt(this.f - this.d);
        if (i == 1) {
            pointF.y = (float) this.b.nextInt((this.e - this.c) / 2);
        } else {
            pointF.y = (float) (this.b.nextInt((this.e - this.c) / 4) + ((this.e - this.c) / 2));
        }
        return pointF;
    }

    public void releaseResource() {
        this.o.removeCallbacksAndMessages(null);
        this.n.clear();
        removeAllViews();
    }
}
