package com.budejie.www.activity.view;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class PostsRelativeLayout$2 implements AnimationListener {
    final /* synthetic */ int a;
    final /* synthetic */ PostsRelativeLayout b;

    PostsRelativeLayout$2(PostsRelativeLayout postsRelativeLayout, int i) {
        this.b = postsRelativeLayout;
        this.a = i;
    }

    public void onAnimationStart(Animation animation) {
        PostsRelativeLayout.a(this.b, true);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        PostsRelativeLayout.a(this.b, false);
        this.b.a = 8;
        this.b.clearAnimation();
        PostsRelativeLayout.a(this.b, -this.a, 0);
    }
}
