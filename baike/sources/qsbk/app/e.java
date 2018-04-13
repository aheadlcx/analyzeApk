package qsbk.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.base.BaseActionBarActivity;

class e implements OnClickListener {
    final /* synthetic */ BaseActionBarActivity a;
    final /* synthetic */ QsbkApp b;

    e(QsbkApp qsbkApp, BaseActionBarActivity baseActionBarActivity) {
        this.b = qsbkApp;
        this.a = baseActionBarActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.gotoMarket(this.a);
    }
}
