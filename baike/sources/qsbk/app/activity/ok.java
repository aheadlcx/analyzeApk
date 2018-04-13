package qsbk.app.activity;

import android.view.ViewTreeObserver.OnPreDrawListener;

class ok implements OnPreDrawListener {
    final /* synthetic */ HotCommentPagerActivity a;

    ok(HotCommentPagerActivity hotCommentPagerActivity) {
        this.a = hotCommentPagerActivity;
    }

    public boolean onPreDraw() {
        this.a.e.getViewTreeObserver().removeOnPreDrawListener(this);
        this.a.showUserRemindDialog();
        return true;
    }
}
