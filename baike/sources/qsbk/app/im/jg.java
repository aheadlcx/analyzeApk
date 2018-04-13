package qsbk.app.im;

import qsbk.app.im.datastore.Util;

class jg implements Runnable {
    final /* synthetic */ SyncManager a;

    jg(SyncManager syncManager) {
        this.a = syncManager;
    }

    public void run() {
        this.a.b();
        Util.imSyncTimer.postRunnable(this, SyncManager.a);
    }
}
