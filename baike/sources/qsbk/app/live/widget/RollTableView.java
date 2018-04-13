package qsbk.app.live.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.live.R;

public class RollTableView extends FrameLayout {
    private View a;
    private ImageView b;
    private ImageView c;
    private int d;
    private int e;
    private int f;
    private CountDownTimer g;
    private TextView h;
    private int i;
    private boolean j;
    private Runnable k;

    public RollTableView(Context context) {
        this(context, null);
    }

    public RollTableView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RollTableView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0;
        this.e = 360;
        this.f = 37;
        this.i = 0;
        this.j = true;
        this.k = new ij(this);
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.k);
    }

    private void a() {
        this.a = View.inflate(getContext(), R.layout.view_rolltable, this);
        this.b = (ImageView) this.a.findViewById(R.id.game_rolltable_circle);
        this.c = (ImageView) this.a.findViewById(R.id.game_rolltable_light);
        this.h = (TextView) this.a.findViewById(R.id.tv_countdown);
        postDelayed(this.k, 100);
    }

    public void startRoll(int i, int i2) {
        float f = (360.0f / ((float) this.f)) * ((float) this.d);
        float f2 = 360.0f - ((360.0f / ((float) this.f)) * ((float) i));
        this.i = 1;
        this.h.setText("请稍候");
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.b, "rotation", new float[]{f, ((((float) i2) / 1000.0f) * ((float) this.e)) + f2});
        ofFloat.setDuration((long) i2);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.start();
        ofFloat.addListener(new ik(this));
        this.d = i;
    }

    public void doCountDown(long j) {
        if (this.g != null) {
            this.g.cancel();
        }
        if (j > 0) {
            this.i = 0;
            this.g = new il(this, (1000 * j) + 500, 500);
            this.g.start();
        }
    }
}
