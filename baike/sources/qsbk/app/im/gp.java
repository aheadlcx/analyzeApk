package qsbk.app.im;

import qsbk.app.utils.SharePreferenceUtils;

class gp implements Runnable {
    final /* synthetic */ IMMessageListFragment a;

    gp(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void run() {
        try {
            this.a.k.markAllMessagesToRead();
            this.a.l.markAllMessagesToRead();
            this.a.m.markAllMessagesToRead();
            IMMessageListFragment.e = 0;
            SharePreferenceUtils.setSharePreferencesValue(MessageCountManager.NEWFANS_COUNT, 0);
        } catch (Throwable th) {
        } finally {
            this.a.f.post(new gq(this));
        }
    }
}
