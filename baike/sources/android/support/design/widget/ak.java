package android.support.design.widget;

import android.support.design.widget.FloatingActionButton.OnVisibilityChangedListener;

class ak implements c {
    final /* synthetic */ OnVisibilityChangedListener a;
    final /* synthetic */ FloatingActionButton b;

    ak(FloatingActionButton floatingActionButton, OnVisibilityChangedListener onVisibilityChangedListener) {
        this.b = floatingActionButton;
        this.a = onVisibilityChangedListener;
    }

    public void onShown() {
        this.a.onShown(this.b);
    }

    public void onHidden() {
        this.a.onHidden(this.b);
    }
}
