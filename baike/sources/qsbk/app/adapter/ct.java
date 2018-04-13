package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import qsbk.app.core.AsyncTask$Status;
import qsbk.app.http.SimpleHttpTask;

class ct implements OnCancelListener {
    final /* synthetic */ SimpleHttpTask a;
    final /* synthetic */ QiuYouAdapter b;

    ct(QiuYouAdapter qiuYouAdapter, SimpleHttpTask simpleHttpTask) {
        this.b = qiuYouAdapter;
        this.a = simpleHttpTask;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.a != null && this.a.getStatus() != AsyncTask$Status.FINISHED) {
            this.a.cancel(true);
        }
    }
}
