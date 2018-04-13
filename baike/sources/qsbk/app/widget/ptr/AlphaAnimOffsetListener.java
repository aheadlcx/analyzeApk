package qsbk.app.widget.ptr;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import qsbk.app.utils.OnScrollDirectionListener;

public class AlphaAnimOffsetListener extends OnScrollDirectionListener {
    View a;
    private final Animation b = a(0.0f, 1.0f);
    private final Animation c = a(1.0f, 0.0f);
    private int d = -1;

    public AlphaAnimOffsetListener(View view) {
        this.a = view;
    }

    private Animation a(float f, float f2) {
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setDuration(200);
        return alphaAnimation;
    }

    public void onScrollUp() {
        this.d = 2;
        if (this.a != null && this.a.getVisibility() != 8) {
            this.a.startAnimation(this.c);
            this.a.setVisibility(8);
        }
    }

    public void onScrollDown() {
        this.d = 1;
        if (this.a != null && this.a.getVisibility() != 0) {
            this.a.startAnimation(this.b);
            this.a.setVisibility(0);
        }
    }
}
