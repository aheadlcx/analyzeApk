package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import qsbk.app.core.AsyncTask$Status;
import qsbk.app.http.SimpleHttpTask;

class bl implements OnCancelListener {
    final /* synthetic */ SimpleHttpTask a;
    final /* synthetic */ ContactQiuYouAdapter b;

    bl(ContactQiuYouAdapter contactQiuYouAdapter, SimpleHttpTask simpleHttpTask) {
        this.b = contactQiuYouAdapter;
        this.a = simpleHttpTask;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.a != null && this.a.getStatus() != AsyncTask$Status.FINISHED) {
            this.a.cancel(true);
        }
    }
}
