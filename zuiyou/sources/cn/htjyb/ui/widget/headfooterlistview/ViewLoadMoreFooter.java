package cn.htjyb.ui.widget.headfooterlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import cn.htjyb.a.a.a;
import cn.htjyb.a.a.b;

public class ViewLoadMoreFooter extends FrameLayout {
    private RotateAnimation a;

    public ViewLoadMoreFooter(Context context) {
        super(context);
        b();
    }

    public ViewLoadMoreFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public ViewLoadMoreFooter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void b() {
        LayoutInflater.from(getContext()).inflate(b.view_load_more, this, true);
        this.a = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.a.setInterpolator(new LinearInterpolator());
        this.a.setDuration(1000);
        this.a.setRepeatCount(-1);
        findViewById(a.imgRefreshing).startAnimation(this.a);
    }

    public void a() {
        findViewById(a.imgRefreshing).startAnimation(this.a);
    }
}
