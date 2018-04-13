package com.lnyp.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lnyp.recyclerview.c.a;
import com.lnyp.recyclerview.c.b;

public class RecyclerViewLoadingFooter extends RelativeLayout {
    protected State a = State.Normal;
    private View b;
    private View c;
    private View d;
    private ProgressBar e;
    private TextView f;
    private RotateAnimation g;

    public enum State {
        Normal,
        TheEnd,
        Loading,
        NetWorkError,
        Hide
    }

    public RecyclerViewLoadingFooter(Context context) {
        super(context);
        a(context);
    }

    public void a(Context context) {
        inflate(context, b.common_list_footer, this);
        setOnClickListener(null);
        this.g = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.g.setInterpolator(new LinearInterpolator());
        this.g.setDuration(1000);
        this.g.setRepeatCount(-1);
        a(State.Normal, true);
    }

    public State getState() {
        return this.a;
    }

    public void setState(State state) {
        a(state, true);
    }

    public void a(State state, boolean z) {
        int i = 0;
        if (this.a != state) {
            this.a = state;
            int i2;
            switch (state) {
                case Normal:
                    setOnClickListener(null);
                    if (this.b != null) {
                        this.b.setVisibility(8);
                    }
                    if (this.d != null) {
                        this.d.setVisibility(8);
                    }
                    if (this.c != null) {
                        this.c.setVisibility(8);
                        return;
                    }
                    return;
                case Loading:
                    setOnClickListener(null);
                    if (this.d != null) {
                        this.d.setVisibility(8);
                    }
                    if (this.c != null) {
                        this.c.setVisibility(8);
                    }
                    if (this.b == null) {
                        this.b = ((ViewStub) findViewById(a.loading_viewstub)).inflate();
                        this.e = (ProgressBar) this.b.findViewById(a.loading_progress);
                        this.f = (TextView) this.b.findViewById(a.loading_text);
                    } else {
                        this.b.setVisibility(0);
                    }
                    View view = this.b;
                    if (z) {
                        i2 = 0;
                    } else {
                        i2 = 8;
                    }
                    view.setVisibility(i2);
                    if (z) {
                        this.e.setVisibility(0);
                        setVisibility(0);
                    } else {
                        this.e.setVisibility(0);
                        setVisibility(0);
                    }
                    return;
                case TheEnd:
                    setOnClickListener(null);
                    if (this.b != null) {
                        this.b.setVisibility(8);
                    }
                    if (this.c != null) {
                        this.c.setVisibility(8);
                    }
                    if (this.d == null) {
                        this.d = ((ViewStub) findViewById(a.end_viewstub)).inflate();
                    } else {
                        this.d.setVisibility(0);
                    }
                    View view2 = this.d;
                    if (!z) {
                        i = 8;
                    }
                    view2.setVisibility(i);
                    setVisibility(8);
                    return;
                case NetWorkError:
                    if (this.b != null) {
                        this.b.setVisibility(8);
                    }
                    if (this.d != null) {
                        this.d.setVisibility(8);
                    }
                    if (this.c == null) {
                        this.c = ((ViewStub) findViewById(a.network_error_viewstub)).inflate();
                    } else {
                        this.c.setVisibility(0);
                    }
                    View view3 = this.c;
                    if (z) {
                        i2 = 0;
                    } else {
                        i2 = 4;
                    }
                    view3.setVisibility(i2);
                    setVisibility(0);
                    return;
                case Hide:
                    setVisibility(4);
                    return;
                default:
                    return;
            }
        }
    }
}
