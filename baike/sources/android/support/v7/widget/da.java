package android.support.v7.widget;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

class da extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ int a;
    final /* synthetic */ ToolbarWidgetWrapper b;
    private boolean c = false;

    da(ToolbarWidgetWrapper toolbarWidgetWrapper, int i) {
        this.b = toolbarWidgetWrapper;
        this.a = i;
    }

    public void onAnimationStart(View view) {
        this.b.a.setVisibility(0);
    }

    public void onAnimationEnd(View view) {
        if (!this.c) {
            this.b.a.setVisibility(this.a);
        }
    }

    public void onAnimationCancel(View view) {
        this.c = true;
    }
}
