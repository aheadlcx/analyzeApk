package qsbk.app.live;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import qsbk.app.core.AsyncTask$Status;
import qsbk.app.http.SimpleHttpTask;

class b implements OnDismissListener {
    final /* synthetic */ SimpleHttpTask a;
    final /* synthetic */ LivePullLauncher b;

    b(LivePullLauncher livePullLauncher, SimpleHttpTask simpleHttpTask) {
        this.b = livePullLauncher;
        this.a = simpleHttpTask;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.a.getStatus() != AsyncTask$Status.FINISHED) {
            this.a.cancel(true);
        }
    }
}
