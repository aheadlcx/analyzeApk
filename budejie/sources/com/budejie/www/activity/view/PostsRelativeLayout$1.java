package com.budejie.www.activity.view;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class PostsRelativeLayout$1 implements AnimationListener {
    final /* synthetic */ PostsRelativeLayout a;

    PostsRelativeLayout$1(PostsRelativeLayout postsRelativeLayout) {
        this.a = postsRelativeLayout;
    }

    public void onAnimationStart(Animation animation) {
        PostsRelativeLayout.a(this.a, true);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        PostsRelativeLayout.a(this.a, false);
        this.a.a = 0;
    }
}
