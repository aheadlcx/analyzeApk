package qsbk.app.activity;

import android.view.ViewTreeObserver.OnPreDrawListener;

class ul implements OnPreDrawListener {
    final /* synthetic */ MyHighlightQiushiActivity a;

    ul(MyHighlightQiushiActivity myHighlightQiushiActivity) {
        this.a = myHighlightQiushiActivity;
    }

    public boolean onPreDraw() {
        this.a.c.getViewTreeObserver().removeOnPreDrawListener(this);
        this.a.showUserRemindDialog();
        return true;
    }
}
