package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;

class bb implements OnClickListener {
    final /* synthetic */ ay a;

    bb(ay ayVar) {
        this.a = ayVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BindPhoneActivity.launch(QsbkApp.getInstance());
    }
}
