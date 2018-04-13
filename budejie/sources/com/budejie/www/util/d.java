package com.budejie.www.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.view.BadgeView;

public class d {
    public static boolean a = false;

    public static void a(Activity activity, TextView textView, String str) {
        Animation loadAnimation = AnimationUtils.loadAnimation(activity, R.anim.hide_down);
        loadAnimation.setAnimationListener(new d$a(activity, textView));
        textView.setAnimation(loadAnimation);
        textView.setVisibility(0);
        textView.setText(str);
        loadAnimation.start();
    }

    public static void b(Activity activity, TextView textView, String str) {
        Animation loadAnimation = AnimationUtils.loadAnimation(activity, R.anim.hide_down_long_time);
        loadAnimation.setAnimationListener(new d$a(activity, textView));
        textView.setAnimation(loadAnimation);
        textView.setVisibility(0);
        textView.setText(str);
        loadAnimation.start();
    }

    private static void b(Context context, TextView textView) {
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.hide_up);
        loadAnimation.setAnimationListener(new d$b(textView));
        textView.setAnimation(loadAnimation);
        loadAnimation.start();
    }

    public static void a(Context context, View view, String str) {
        BadgeView badgeView = new BadgeView(context, view);
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.ding_scale);
        loadAnimation.setAnimationListener(new d$1(badgeView, view));
        badgeView.setBadgePosition(5);
        badgeView.setBadgeBackgroundColor(0);
        badgeView.setTextColor(SupportMenu.CATEGORY_MASK);
        badgeView.setTextSize(8.0f);
        badgeView.setText("+" + str);
        badgeView.a(loadAnimation);
    }
}
