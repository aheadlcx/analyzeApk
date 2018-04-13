package com.budejie.www.activity.view.TopNavigationTabIndicator;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

class TypeIndicator$1 implements OnClickListener {
    final /* synthetic */ TypeIndicator a;

    TypeIndicator$1(TypeIndicator typeIndicator) {
        this.a = typeIndicator;
    }

    public void onClick(View view) {
        Log.i("TypeIndicator", "onClick");
        if (TypeIndicator.a(this.a)) {
            TypeIndicator.a(this.a, false);
            if (TypeIndicator.b(this.a) != null) {
                TypeIndicator.b(this.a).onClick(view);
            }
        } else if (this.a.b && TypeIndicator.b(this.a) != null) {
            TypeIndicator.b(this.a).onClick(view);
        }
    }
}
