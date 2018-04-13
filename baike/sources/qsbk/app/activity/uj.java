package qsbk.app.activity;

import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;

class uj implements OnPreDrawListener {
    final /* synthetic */ View a;
    final /* synthetic */ MyAuditListActivity b;

    uj(MyAuditListActivity myAuditListActivity, View view) {
        this.b = myAuditListActivity;
        this.a = view;
    }

    public boolean onPreDraw() {
        this.a.getViewTreeObserver().removeOnPreDrawListener(this);
        this.b.showUserRemindDialog();
        return true;
    }
}
