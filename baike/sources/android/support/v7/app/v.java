package android.support.v7.app;

import android.support.v7.widget.ContentFrameLayout.OnAttachListener;

class v implements OnAttachListener {
    final /* synthetic */ AppCompatDelegateImplV9 a;

    v(AppCompatDelegateImplV9 appCompatDelegateImplV9) {
        this.a = appCompatDelegateImplV9;
    }

    public void onAttachedFromWindow() {
    }

    public void onDetachedFromWindow() {
        this.a.i();
    }
}
