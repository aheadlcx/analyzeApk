package com.budejie.www.activity.view;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.budejie.www.R;
import com.budejie.www.widget.curtain.FloatVideoLayout;

class BottomRelativeLayout$2 implements AnimationListener {
    final /* synthetic */ BottomRelativeLayout a;

    BottomRelativeLayout$2(BottomRelativeLayout bottomRelativeLayout) {
        this.a = bottomRelativeLayout;
    }

    public void onAnimationStart(Animation animation) {
        BottomRelativeLayout.a(this.a, true);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        BottomRelativeLayout.a(this.a, false);
        this.a.setVisibility(8);
        BottomRelativeLayout.a(this.a, 8);
        FloatVideoLayout.b(this.a.getContext(), false);
        if (BottomRelativeLayout.a(this.a) != null) {
            this.a.layout(0, BottomRelativeLayout.a(this.a).heightPixels, this.a.getWidth(), BottomRelativeLayout.a(this.a).heightPixels + ((int) this.a.getResources().getDimension(R.dimen.bottom_navigation_height)));
        }
    }
}
