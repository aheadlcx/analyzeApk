package cn.xiaochuankeji.tieba.ui.widget.overscroll;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import cn.xiaochuankeji.tieba.R;

public class RefreshBottomView extends FrameLayout {
    AppCompatTextView a;
    View b;
    View c;
    RotateAnimation d;

    public RefreshBottomView(Context context) {
        this(context, null);
    }

    public RefreshBottomView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RefreshBottomView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.a = new AppCompatTextView(context);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        this.a.setGravity(17);
        this.a.setText("没有更多了");
        this.a.setTextColor(ContextCompat.getColor(context, R.color.gray_80));
        this.a.setTextSize(1, 14.0f);
        addView(this.a, layoutParams);
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, (int) TypedValue.applyDimension(1, 32.0f, getResources().getDisplayMetrics()));
        layoutParams2.gravity = 17;
        this.b = LayoutInflater.from(context).inflate(R.layout.view_load_more, this, false);
        this.d = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.d.setInterpolator(new LinearInterpolator());
        this.d.setDuration(800);
        this.d.setRepeatCount(-1);
        this.c = this.b.findViewById(R.id.imgRefreshing);
        addView(this.b, layoutParams2);
        this.a.setVisibility(8);
        this.c.setVisibility(8);
    }

    public void a() {
        this.a.setVisibility(0);
        this.c.clearAnimation();
        this.b.setVisibility(8);
    }

    public void b() {
        this.a.setVisibility(8);
        this.c.setVisibility(0);
        this.b.setVisibility(0);
        this.c.clearAnimation();
        this.c.startAnimation(this.d);
    }

    public void setHasMore(boolean z) {
        if (z) {
            b();
        } else {
            a();
        }
    }
}
