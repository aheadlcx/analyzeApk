package com.bruce.pickerview.popwindow;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class d implements AnimationListener {
    final /* synthetic */ DatePickerPopWin a;

    d(DatePickerPopWin datePickerPopWin) {
        this.a = datePickerPopWin;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.dismiss();
    }
}
