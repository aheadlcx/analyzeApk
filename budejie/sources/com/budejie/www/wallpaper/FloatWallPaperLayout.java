package com.budejie.www.wallpaper;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.budejie.www.R;

public class FloatWallPaperLayout extends LinearLayout {
    private ImageView a;
    private View b;
    private int c;
    private int d;
    private ValueAnimator e;
    private RelativeLayout f;
    private TextView g;
    private boolean h;
    private boolean i;
    private Handler j;
    private ObjectAnimator k;

    public FloatWallPaperLayout(Context context) {
        this(context, null);
    }

    public FloatWallPaperLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatWallPaperLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = new Handler(this) {
            final /* synthetic */ FloatWallPaperLayout a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 2:
                        this.a.b();
                        return;
                    default:
                        return;
                }
            }
        };
        this.b = LayoutInflater.from(context).inflate(R.layout.float_wall_paper_set_view, null);
        addView(this.b);
        d();
    }

    private void d() {
        this.a = (ImageView) this.b.findViewById(R.id.icon_image_view);
        this.f = (RelativeLayout) this.b.findViewById(R.id.wall_paper_set_layout);
        this.g = (TextView) this.b.findViewById(R.id.wall_paper_text_view);
    }

    public RelativeLayout getWallPaperLayout() {
        return this.f;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.c = getWidth();
        this.d = getHeight();
    }

    public void a() {
        this.k = ObjectAnimator.ofFloat(this.b, View.ALPHA, new float[]{0.0f, 1.0f});
        this.k.setDuration(1000);
        this.k.setInterpolator(new LinearInterpolator());
        this.k.start();
        this.j.sendEmptyMessageDelayed(2, 2500);
    }

    public void b() {
        e();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(this.e);
        animatorSet.start();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.k != null) {
            this.k.cancel();
        }
        if (this.e != null) {
            this.e.cancel();
        }
        this.j.removeMessages(2);
    }

    private void e() {
        int width = (this.c - this.a.getWidth()) - (this.b.getPaddingLeft() * 2);
        this.e = ValueAnimator.ofInt(new int[]{0, width});
        this.e.setDuration(500);
        this.e.setInterpolator(new AccelerateDecelerateInterpolator());
        this.e.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ FloatWallPaperLayout a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.b.layout(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0, this.a.c, this.a.d);
            }
        });
        this.e.addListener(new AnimatorListener(this) {
            final /* synthetic */ FloatWallPaperLayout a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.a.h && !this.a.i) {
                    this.a.c();
                }
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

    public void c() {
        if (getVisibility() != 0 || this.g.getVisibility() != 8) {
            setVisibility(0);
            this.g.setVisibility(8);
        }
    }

    public void setSimpleMode(boolean z) {
        this.h = z;
    }

    public void setLandscape(boolean z) {
        this.i = z;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = this.b.getMeasuredWidth();
        int measuredHeight = this.b.getMeasuredHeight();
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            size = (int) TypedValue.applyDimension(0, (float) measuredWidth, getResources().getDisplayMetrics());
        }
        if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
            size2 = (int) TypedValue.applyDimension(0, (float) measuredHeight, getResources().getDisplayMetrics());
        }
        setMeasuredDimension(size, size2);
    }
}
