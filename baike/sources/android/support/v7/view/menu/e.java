package android.support.v7.view.menu;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.MenuItem;

class e implements MenuItemHoverListener {
    final /* synthetic */ CascadingMenuPopup a;

    e(CascadingMenuPopup cascadingMenuPopup) {
        this.a = cascadingMenuPopup;
    }

    public void onItemHoverExit(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
        this.a.a.removeCallbacksAndMessages(menuBuilder);
    }

    public void onItemHoverEnter(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
        int i;
        this.a.a.removeCallbacksAndMessages(null);
        int size = this.a.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (menuBuilder == ((a) this.a.b.get(i2)).menu) {
                i = i2;
                break;
            }
        }
        i = -1;
        if (i != -1) {
            a aVar;
            i++;
            if (i < this.a.b.size()) {
                aVar = (a) this.a.b.get(i);
            } else {
                aVar = null;
            }
            this.a.a.postAtTime(new f(this, aVar, menuItem, menuBuilder), menuBuilder, SystemClock.uptimeMillis() + 200);
        }
    }
}
