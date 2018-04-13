package android.support.v7.app;

import android.graphics.Rect;
import android.support.v7.widget.FitWindowsViewGroup.OnFitSystemWindowsListener;

class u implements OnFitSystemWindowsListener {
    final /* synthetic */ AppCompatDelegateImplV9 a;

    u(AppCompatDelegateImplV9 appCompatDelegateImplV9) {
        this.a = appCompatDelegateImplV9;
    }

    public void onFitSystemWindows(Rect rect) {
        rect.top = this.a.d(rect.top);
    }
}
