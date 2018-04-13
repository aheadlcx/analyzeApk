package com.budejie.www.util;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class q {
    public static void a(ImageView imageView) {
        imageView.setVisibility(0);
        Animation scaleAnimation = new ScaleAnimation(0.4f, 1.6f, 0.4f, 1.6f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(150);
        Animation scaleAnimation2 = new ScaleAnimation(1.6f, 0.8f, 1.6f, 0.8f, 1, 0.5f, 1, 0.5f);
        scaleAnimation2.setDuration(150);
        scaleAnimation2.setStartOffset(150);
        Animation scaleAnimation3 = new ScaleAnimation(0.8f, 1.2f, 0.8f, 1.2f, 1, 0.5f, 1, 0.5f);
        scaleAnimation3.setDuration(150);
        scaleAnimation3.setStartOffset(300);
        Animation scaleAnimation4 = new ScaleAnimation(1.2f, 1.0f, 1.2f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation4.setDuration(150);
        scaleAnimation4.setStartOffset(450);
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(150);
        alphaAnimation.setStartOffset(600);
        Animation animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(scaleAnimation2);
        animationSet.addAnimation(scaleAnimation3);
        animationSet.addAnimation(scaleAnimation4);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setAnimationListener(new q$1(imageView));
        imageView.startAnimation(animationSet);
    }
}
