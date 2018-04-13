package qsbk.app.im;

import qsbk.app.core.AsyncTask;

class is extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ QiushiNotificationCountManager a;

    is(QiushiNotificationCountManager qiushiNotificationCountManager) {
        this.a = qiushiNotificationCountManager;
    }

    protected Void a(Void... voidArr) {
        this.a.init();
        return null;
    }
}
