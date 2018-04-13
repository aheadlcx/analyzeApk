package qsbk.app.live.ui;

import qsbk.app.core.utils.KeyBoardUtils.OnKeyboardHiddenChangedListener;

class ae extends OnKeyboardHiddenChangedListener {
    final /* synthetic */ LiveBaseActivity a;

    ae(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onKeyboardHiddenChanged(int i, boolean z) {
        if (isVirutalNavigationChanged(i - getPreviousKeyboardHeight())) {
            onNavigationBarChanged(i);
        } else if (!this.a.w()) {
            onSoftKeyboardHiddenChanged(i, z);
        }
    }

    public void onNavigationBarChanged(int i) {
        this.a.a();
    }

    public void onSoftKeyboardHiddenChanged(int i, boolean z) {
        this.a.a(i, z);
    }
}
