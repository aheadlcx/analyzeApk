package qsbk.app.core.utils;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import qsbk.app.core.ui.base.BaseActivity;

class o implements OnGlobalLayoutListener {
    final /* synthetic */ BaseActivity a;
    final /* synthetic */ View b;
    final /* synthetic */ KeyBoardUtils c;

    o(KeyBoardUtils keyBoardUtils, BaseActivity baseActivity, View view) {
        this.c = keyBoardUtils;
        this.a = baseActivity;
        this.b = view;
    }

    public void onGlobalLayout() {
        int i = 0;
        if (this.a.isOnResume && !this.a.isFinishing()) {
            Rect rect = new Rect();
            this.b.getWindowVisibleDisplayFrame(rect);
            int i2 = rect.bottom;
            int height = this.b.getHeight();
            int abs = Math.abs(height - i2);
            if (this.c.c != abs) {
                boolean z = ((double) (i2 - rect.top)) / ((double) height) > 0.8d;
                if (z) {
                    this.c.d = abs;
                } else if (this.c.isVirutalNavigationChanged(this.c.c - abs)) {
                    KeyBoardUtils keyBoardUtils = this.c;
                    if (abs >= this.c.c) {
                        i = this.c.b;
                    }
                    keyBoardUtils.d = i;
                }
                this.c.e.onKeyboardHiddenChanged(abs, z);
                this.c.c = abs;
            }
        }
    }
}
