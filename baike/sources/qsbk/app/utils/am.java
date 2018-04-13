package qsbk.app.utils;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import qsbk.app.utils.KeyboardUtils.OnKeyboardVisibleChangeListener;

final class am implements OnGlobalLayoutListener {
    final /* synthetic */ View a;
    final /* synthetic */ OnKeyboardVisibleChangeListener b;

    am(View view, OnKeyboardVisibleChangeListener onKeyboardVisibleChangeListener) {
        this.a = view;
        this.b = onKeyboardVisibleChangeListener;
    }

    public void onGlobalLayout() {
        Rect rect = new Rect();
        this.a.getWindowVisibleDisplayFrame(rect);
        int height = this.a.getRootView().getHeight();
        boolean z = ((double) (height - rect.bottom)) > ((double) height) * 0.15d;
        if (this.b != null) {
            this.b.onKeyboardVisible(z);
        }
    }
}
