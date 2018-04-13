package qsbk.app.nearby.api;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class g implements OnClickListener {
    final /* synthetic */ e a;

    g(e eVar) {
        this.a = eVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.a.openServiceSetting();
    }
}
