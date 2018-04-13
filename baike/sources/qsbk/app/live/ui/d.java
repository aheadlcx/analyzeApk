package qsbk.app.live.ui;

import qsbk.app.live.ui.share.ShareCallbackHelper.ShareCallback;

class d implements ShareCallback {
    final /* synthetic */ LiveBaseActivity a;

    d(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onShareSuccess(String str) {
        this.a.a(str);
    }
}
