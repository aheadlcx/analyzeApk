package android.support.v4.widget;

import android.annotation.TargetApi;
import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;

class g implements OnApplyWindowInsetsListener {
    final /* synthetic */ DrawerLayout a;

    g(DrawerLayout drawerLayout) {
        this.a = drawerLayout;
    }

    @TargetApi(21)
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        ((DrawerLayout) view).setChildInsets(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
        return windowInsets.consumeSystemWindowInsets();
    }
}
