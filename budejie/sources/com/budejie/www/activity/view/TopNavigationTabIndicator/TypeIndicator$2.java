package com.budejie.www.activity.view.TopNavigationTabIndicator;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RadioButton;

class TypeIndicator$2 implements OnGlobalLayoutListener {
    final /* synthetic */ RadioButton a;
    final /* synthetic */ TypeIndicator b;

    TypeIndicator$2(TypeIndicator typeIndicator, RadioButton radioButton) {
        this.b = typeIndicator;
        this.a = radioButton;
    }

    public void onGlobalLayout() {
        this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        TypeIndicator.a(this.b, this.a.getWidth());
    }
}
