package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;

class aj implements OnClickListener {
    final /* synthetic */ User a;
    final /* synthetic */ LiveBaseActivity b;

    aj(LiveBaseActivity liveBaseActivity, User user) {
        this.b = liveBaseActivity;
        this.a = user;
    }

    public void onClick(View view) {
        this.b.a(this.a);
    }
}
