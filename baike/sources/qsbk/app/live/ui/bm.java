package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnLongClickListener;
import qsbk.app.core.utils.AppUtils;

class bm implements OnLongClickListener {
    final /* synthetic */ LiveBaseActivity a;

    bm(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public boolean onLongClick(View view) {
        AppUtils.copyToClipboard(this.a.getActivity(), this.a.aB.getText().toString());
        return true;
    }
}
