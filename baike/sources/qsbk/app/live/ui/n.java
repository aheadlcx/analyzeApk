package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.Activity;
import qsbk.app.core.web.ui.WebActivity;

class n implements OnClickListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ LiveBaseActivity b;

    n(LiveBaseActivity liveBaseActivity, Activity activity) {
        this.b = liveBaseActivity;
        this.a = activity;
    }

    public void onClick(View view) {
        WebActivity.launch(this.b.getActivity(), this.a.redirect_url);
    }
}
