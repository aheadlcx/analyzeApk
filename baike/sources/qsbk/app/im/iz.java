package qsbk.app.im;

import qsbk.app.core.AsyncTask;

class iz extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ QiuyouquanNotificationCountManager a;

    iz(QiuyouquanNotificationCountManager qiuyouquanNotificationCountManager) {
        this.a = qiuyouquanNotificationCountManager;
    }

    protected Void a(Void... voidArr) {
        this.a.init();
        return null;
    }
}
