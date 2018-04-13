package com.budejie.www.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import com.budejie.www.R;

public class PostsRelativeLayout extends RelativeLayout {
    public int a = 0;
    private Animation b;
    private boolean c;
    private Animation d;

    public PostsRelativeLayout(Context context) {
        super(context);
    }

    public PostsRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a() {
        if (!this.c && this.a != 0) {
            int dimension = (int) getResources().getDimension(R.dimen.navigation_height);
            if (this.b == null) {
                this.b = new TranslateAnimation(0.0f, 0.0f, (float) (-dimension), 0.0f);
                this.b.setDuration(250);
                this.b.setFillAfter(true);
                this.b.setAnimationListener(new PostsRelativeLayout$1(this));
            }
            a(0, -dimension);
            startAnimation(this.b);
        }
    }

    public void b() {
        if (!this.c && this.a != 8) {
            int dimension = (int) getResources().getDimension(R.dimen.navigation_height);
            if (this.d == null) {
                this.d = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (-dimension));
                this.d.setDuration(250);
                this.d.setAnimationListener(new PostsRelativeLayout$2(this, dimension));
            }
            a(0, -dimension);
            startAnimation(this.d);
        }
    }

    private void a(int i, int i2) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, i, 0, i2);
        setLayoutParams(layoutParams);
    }
}
