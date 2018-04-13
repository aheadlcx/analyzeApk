package com.tencent.bugly.beta.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.global.ResBean;
import com.tencent.bugly.beta.global.c;
import com.tencent.bugly.beta.utils.e;

public abstract class a extends b {
    protected Context a;
    protected View b;
    protected FrameLayout c;
    protected LinearLayout d;
    protected ImageView e;
    protected TextView f;
    protected TextView g;
    protected TextView h;
    protected LinearLayout i;
    protected ResBean j;
    protected int k;
    protected int l;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = getActivity();
        this.j = ResBean.a;
        if (this.l == 0) {
            int a;
            this.b = new RelativeLayout(this.a);
            ((RelativeLayout) this.b).setGravity(17);
            this.b.setBackgroundColor(Color.argb(100, 0, 0, 0));
            this.c = new FrameLayout(this.a);
            this.c.setLayoutParams(new LayoutParams(-2, -2));
            this.d = new LinearLayout(this.a);
            this.d.setBackgroundColor(-1);
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            this.d.setGravity(17);
            this.d.setLayoutParams(layoutParams);
            this.d.setMinimumWidth(com.tencent.bugly.beta.global.a.a(this.a, 280.0f));
            this.d.setOrientation(1);
            if (this.k == 2) {
                a = com.tencent.bugly.beta.global.a.a(this.a, 6.0f);
                Drawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{(float) a, (float) a, (float) a, (float) a, (float) a, (float) a, (float) a, (float) a}, null, null));
                shapeDrawable.getPaint().setColor(-1);
                shapeDrawable.getPaint().setStyle(Style.FILL_AND_STROKE);
                this.d.setBackgroundDrawable(shapeDrawable);
            }
            this.f = new TextView(this.a);
            this.f.setGravity(16);
            this.f.setSingleLine();
            TextView textView = this.f;
            this.j.getClass();
            textView.setTextColor(Color.parseColor("#273238"));
            this.f.setTextSize(18.0f);
            this.f.setLayoutParams(layoutParams);
            this.f.setOnClickListener(null);
            this.f.setEllipsize(TruncateAt.END);
            a = com.tencent.bugly.beta.global.a.a(this.a, 16.0f);
            this.f.setPadding(a, 0, a, 0);
            this.f.setTypeface(null, 1);
            this.f.setHeight(com.tencent.bugly.beta.global.a.a(this.a, 42.0f));
            this.f.setTag(Beta.TAG_TITLE);
            View textView2 = new TextView(this.a);
            textView2.setBackgroundColor(-3355444);
            textView2.setHeight(1);
            View scrollView = new ScrollView(this.a);
            LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.setMargins(0, 0, 0, com.tencent.bugly.beta.global.a.a(this.a, 52.0f));
            scrollView.setLayoutParams(layoutParams2);
            scrollView.setFillViewport(true);
            scrollView.setVerticalScrollBarEnabled(false);
            scrollView.setHorizontalScrollBarEnabled(false);
            this.i = new LinearLayout(this.a);
            this.i.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            this.i.setOrientation(1);
            this.i.setPadding(a, com.tencent.bugly.beta.global.a.a(this.a, 10.0f), a, 0);
            View linearLayout = new LinearLayout(this.a);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setGravity(17);
            linearLayout.setOrientation(0);
            linearLayout.setPadding(a / 2, a, a / 2, a);
            layoutParams = new FrameLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 80;
            linearLayout.setLayoutParams(layoutParams);
            layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
            layoutParams.setMargins(a / 2, 0, a / 2, 0);
            this.g = new TextView(this.a);
            this.g.setSingleLine();
            this.g.setGravity(17);
            this.g.setTag(Beta.TAG_CANCEL_BUTTON);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            int a2 = com.tencent.bugly.beta.global.a.a(this.a, 30.0f);
            if (this.k == 2) {
                LayoutParams layoutParams4 = new FrameLayout.LayoutParams(a2, a2);
                layoutParams4.gravity = 53;
                this.g.setLayoutParams(layoutParams4);
                this.g.setTextSize((float) (((double) a2) * 0.3d));
            } else {
                this.g.setLayoutParams(layoutParams);
                this.g.setTextSize((float) 16);
                textView = this.g;
                this.j.getClass();
                textView.setTextColor(Color.parseColor("#757575"));
                this.g.setPadding(com.tencent.bugly.beta.global.a.a(this.a, 10.0f), com.tencent.bugly.beta.global.a.a(this.a, 5.0f), com.tencent.bugly.beta.global.a.a(this.a, 10.0f), com.tencent.bugly.beta.global.a.a(this.a, 5.0f));
            }
            this.h = new TextView(this.a);
            this.h.setLayoutParams(layoutParams);
            this.h.setGravity(17);
            this.h.setTextSize((float) 16);
            TextView textView3 = this.h;
            this.j.getClass();
            textView3.setTextColor(Color.parseColor("#273238"));
            this.h.setSingleLine();
            this.h.setPadding(com.tencent.bugly.beta.global.a.a(this.a, 10.0f), com.tencent.bugly.beta.global.a.a(this.a, 5.0f), com.tencent.bugly.beta.global.a.a(this.a, 10.0f), com.tencent.bugly.beta.global.a.a(this.a, 5.0f));
            this.h.setTypeface(null, 1);
            this.h.setTag(Beta.TAG_CONFIRM_BUTTON);
            int a3 = com.tencent.bugly.beta.global.a.a(this.a, 40.0f);
            scrollView.addView(this.i);
            if (this.k == 2) {
                View frameLayout = new FrameLayout(this.a);
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
                this.c.setPadding(a2 / 2, (a2 / 2) - 5, (a2 / 2) - 5, a2 / 2);
                frameLayout.addView(this.c);
                frameLayout.addView(this.g);
                ((RelativeLayout) this.b).addView(frameLayout);
            } else {
                this.b.setPadding(a3, a3, a3, a3);
                ((RelativeLayout) this.b).addView(this.c);
                linearLayout.addView(this.g);
            }
            this.d.addView(this.f);
            this.d.addView(textView2);
            this.d.addView(scrollView);
            this.c.addView(this.d);
            linearLayout.addView(this.h);
            this.c.addView(linearLayout);
            if (this.k == 2) {
                Paint paint = new Paint();
                paint.setStyle(Style.FILL);
                paint.setAntiAlias(true);
                Bitmap createBitmap = Bitmap.createBitmap(a2, a2, Config.ARGB_8888);
                int i = a2 / 2;
                Canvas canvas = new Canvas(createBitmap);
                paint.setColor(-3355444);
                canvas.drawCircle((float) i, (float) i, (float) i, paint);
                canvas.rotate(45.0f, (float) i, (float) i);
                paint.setColor(-7829368);
                int a4 = com.tencent.bugly.beta.global.a.a(this.a, 0.8f);
                canvas.drawRect(((float) i) * 0.4f, (float) (i - a4), ((float) i) * 1.6f, (float) (i + a4), paint);
                canvas.drawRect((float) (i - a4), ((float) i) * 0.4f, (float) (i + a4), ((float) i) * 1.6f, paint);
                canvas.rotate(-45.0f);
                Bitmap createBitmap2 = Bitmap.createBitmap(a2, a2, Config.ARGB_8888);
                canvas = new Canvas(createBitmap2);
                paint.setColor(-7829368);
                canvas.drawCircle((float) i, (float) i, (float) i, paint);
                canvas.rotate(45.0f, (float) i, (float) i);
                paint.setColor(-3355444);
                canvas.drawRect(((float) i) * 0.4f, (float) (i - a4), ((float) i) * 1.6f, (float) (i + a4), paint);
                canvas.drawRect((float) (i - a4), ((float) i) * 0.4f, (float) (i + a4), ((float) i) * 1.6f, paint);
                canvas.rotate(-45.0f);
                Drawable bitmapDrawable = new BitmapDrawable(createBitmap);
                BitmapDrawable bitmapDrawable2 = new BitmapDrawable(createBitmap2);
                this.g.setBackgroundDrawable(bitmapDrawable);
                this.g.setOnTouchListener(new c(1, new Object[]{bitmapDrawable2, bitmapDrawable}));
            }
            this.b.setOnClickListener(null);
            Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(300);
            this.b.startAnimation(alphaAnimation);
        } else {
            this.b = layoutInflater.inflate(this.l, null);
            this.e = (ImageView) this.b.findViewWithTag(Beta.TAG_IMG_BANNER);
            this.f = (TextView) this.b.findViewWithTag(Beta.TAG_TITLE);
            this.g = (TextView) this.b.findViewWithTag(Beta.TAG_CANCEL_BUTTON);
            this.h = (TextView) this.b.findViewWithTag(Beta.TAG_CONFIRM_BUTTON);
        }
        this.g.setVisibility(8);
        this.h.setVisibility(8);
        this.g.setFocusable(true);
        this.h.setFocusable(true);
        this.h.requestFocus();
        return this.b;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.f = null;
        this.e = null;
        this.g = null;
        this.h = null;
        this.i = null;
    }

    protected void a(String str, OnClickListener onClickListener, String str2, OnClickListener onClickListener2) {
        e.a(new a$1(this, str, onClickListener, str2, onClickListener2));
    }

    public void a() {
        if (this.b == null) {
            super.a();
            return;
        }
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(200);
        e.a(new a$2(this, alphaAnimation));
        alphaAnimation.setAnimationListener(new a$3(this));
    }
}
