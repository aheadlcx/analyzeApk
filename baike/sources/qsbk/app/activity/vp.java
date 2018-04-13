package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import qsbk.app.core.AsyncTask$Status;
import qsbk.app.http.SimpleHttpTask;

class vp implements OnCancelListener {
    final /* synthetic */ SimpleHttpTask a;
    final /* synthetic */ MyInfoActivity b;

    vp(MyInfoActivity myInfoActivity, SimpleHttpTask simpleHttpTask) {
        this.b = myInfoActivity;
        this.a = simpleHttpTask;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.a != null && this.a.getStatus() != AsyncTask$Status.FINISHED) {
            this.a.cancel(true);
        }
    }
}
