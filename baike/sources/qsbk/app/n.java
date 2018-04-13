package qsbk.app;

import android.support.v4.content.LocalBroadcastManager;
import qsbk.app.service.VoteManager;
import qsbk.app.service.VoteManager.Interceptor;

class n implements Interceptor {
    final /* synthetic */ QsbkApp a;

    n(QsbkApp qsbkApp) {
        this.a = qsbkApp;
    }

    public void onIntercept() {
        if (QsbkApp.currentUser != null && QsbkApp.currentUser.isNewUser()) {
            LocalBroadcastManager.getInstance(QsbkApp.getInstance()).sendBroadcast(VoteManager.getInstance().createVoteCountIntent());
        }
    }
}
