package com.budejie.www.widget.xrecyclerview;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.widget.a.a;

public class ArrowRefreshHeader extends LinearLayout {
    public int a;
    private LinearLayout b;
    private ImageView c;
    private TextView d;
    private int e;
    private TextView f;
    private Context g;

    public ArrowRefreshHeader(Context context) {
        this(context, null);
    }

    public ArrowRefreshHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 0;
        this.g = context;
        d();
    }

    private void d() {
        int color;
        this.b = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.xlistview_header, null);
        addView(this.b, new LayoutParams(-1, 0));
        setGravity(80);
        this.c = (ImageView) findViewById(R.id.xlistview_header_arrow);
        this.d = (TextView) findViewById(R.id.xlistview_header_hint_textview);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.xlistview_header_content);
        this.f = (TextView) findViewById(R.id.xlistview_header_time);
        measure(-2, -2);
        this.a = relativeLayout.getMeasuredHeight() - an.a(this.g, 3);
        if (ai.a(this.g) == 0) {
            color = getResources().getColor(R.color.new_main_background_color);
        } else {
            color = getResources().getColor(R.color.new_main_background_color_night);
        }
        setBackgroundColor(color);
    }

    public void setArrowImageView(int i) {
        this.c.setImageResource(i);
    }

    public void setState(int i) {
        if (i != this.e) {
            switch (i) {
                case 0:
                    this.d.setText(R.string.xlistview_header_hint_normal);
                    this.c.setBackgroundResource(R.drawable.list_view_pull);
                    break;
                case 1:
                    this.c.setBackgroundResource(R.drawable.list_view_release);
                    this.d.setText(R.string.xlistview_header_hint_ready);
                    break;
                case 2:
                    this.c.setBackgroundResource(R.drawable.recyclerview_header_refreshing_anim);
                    ((AnimationDrawable) this.c.getBackground()).start();
                    this.d.setText(R.string.xlistview_header_hint_loading);
                    break;
            }
            this.e = i;
        }
    }

    public int getState() {
        return this.e;
    }

    public void setLastRefreshTime(long j) {
        this.f.setText(a.a(j));
    }

    public void a() {
        setState(3);
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ ArrowRefreshHeader a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c();
            }
        }, 200);
    }

    public void setVisibleHeight(int i) {
        if (i < 0) {
            i = 0;
        }
        LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
        layoutParams.height = i;
        this.b.setLayoutParams(layoutParams);
    }

    public int getVisibleHeight() {
        return ((LayoutParams) this.b.getLayoutParams()).height;
    }

    public void a(float f) {
        if (getVisibleHeight() > 0 || f > 0.0f) {
            setVisibleHeight(((int) f) + getVisibleHeight());
            if (this.e > 1) {
                return;
            }
            if (getVisibleHeight() > this.a) {
                setState(1);
            } else {
                setState(0);
            }
        }
    }

    public boolean b() {
        boolean z;
        if (getVisibleHeight() == 0) {
        }
        if (getVisibleHeight() <= this.a || this.e >= 2) {
            z = false;
        } else {
            setState(2);
            z = true;
        }
        if (this.e != 2) {
            a(0);
        }
        if (this.e == 2) {
            a(this.a);
        }
        return z;
    }

    public void c() {
        a(0);
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ ArrowRefreshHeader a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.setState(0);
            }
        }, 500);
    }

    private void a(int i) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{getVisibleHeight(), i});
        ofInt.setDuration(300).start();
        ofInt.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ ArrowRefreshHeader a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.setVisibleHeight(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        ofInt.start();
    }
}
