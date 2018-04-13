package cn.htjyb.ui.widget.headfooterlistview.header;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import cn.htjyb.a.a.a;
import cn.htjyb.a.a.b;

public class c extends d {
    private TextView e;
    private ImageView f;
    private ImageView g;
    private RotateAnimation h;
    private RotateAnimation i;
    private RotateAnimation j;

    public c(Context context) {
        super(context);
    }

    protected void a(Context context) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(b.view_refresh_list_header, this, true);
        this.e = (TextView) findViewById(a.head_tipsTextView);
        this.e.setText(this.b);
        this.g = (ImageView) findViewById(a.head_arrowImageView);
        this.f = (ImageView) findViewById(a.imgRefreshing);
        this.g.setMinimumWidth(70);
        this.g.setMinimumHeight(50);
        this.h = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.h.setInterpolator(new LinearInterpolator());
        this.h.setDuration(250);
        this.h.setFillAfter(true);
        this.i = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.i.setInterpolator(new LinearInterpolator());
        this.i.setDuration(200);
        this.i.setFillAfter(true);
        this.j = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.j.setInterpolator(new LinearInterpolator());
        this.j.setDuration(1000);
        this.j.setRepeatCount(-1);
        setWillNotDraw(false);
    }

    public void setState(State state) {
        switch (state) {
            case kStateDragToRefresh:
                a(this.a == State.kStateReleaseToRefresh);
                break;
            case kStateReleaseToRefresh:
                a();
                break;
            case kStateRefreshing:
                b();
                break;
            case kStateHide:
                c();
                break;
        }
        super.setState(state);
    }

    private void a(boolean z) {
        this.e.setVisibility(0);
        this.g.clearAnimation();
        this.g.setVisibility(0);
        if (z) {
            this.g.clearAnimation();
            this.g.startAnimation(this.i);
            this.e.setText(this.b);
            return;
        }
        this.e.setText(this.b);
    }

    private void a() {
        this.e.setVisibility(0);
        this.g.clearAnimation();
        this.g.startAnimation(this.h);
        this.e.setText(this.c);
    }

    private void b() {
        this.g.clearAnimation();
        this.g.setVisibility(8);
        this.e.setText(this.d);
        this.f.setVisibility(0);
        this.f.startAnimation(this.j);
    }

    private void c() {
        this.g.clearAnimation();
        this.e.setText(this.b);
        this.f.clearAnimation();
        this.f.setVisibility(4);
    }
}
