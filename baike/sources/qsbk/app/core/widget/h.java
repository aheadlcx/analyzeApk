package qsbk.app.core.widget;

import android.app.Activity;
import android.view.View;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.EmptyPlaceholderView.OnEmptyClickListener;

class h implements OnEmptyClickListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ EmptyPlaceholderView b;

    h(EmptyPlaceholderView emptyPlaceholderView, Activity activity) {
        this.b = emptyPlaceholderView;
        this.a = activity;
    }

    public void onEmptyClick(View view) {
        AppUtils.getInstance().getUserInfoProvider().toLogin(this.a, 1001);
    }
}
