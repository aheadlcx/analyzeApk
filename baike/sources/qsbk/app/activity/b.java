package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

class b implements OnClickListener {
    final /* synthetic */ JsResult a;
    final /* synthetic */ a b;

    b(a aVar, JsResult jsResult) {
        this.b = aVar;
        this.a = jsResult;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.confirm();
    }
}
