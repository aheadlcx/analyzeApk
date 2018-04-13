package qsbk.app.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.List;
import qsbk.app.utils.ToastAndDialog;

class cw implements OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ String b;
    final /* synthetic */ List c;
    final /* synthetic */ Context d;
    final /* synthetic */ AuditNativeActivity2 e;

    cw(AuditNativeActivity2 auditNativeActivity2, AlertDialog alertDialog, String str, List list, Context context) {
        this.e = auditNativeActivity2;
        this.a = alertDialog;
        this.b = str;
        this.c = list;
        this.d = context;
    }

    public void onClick(View view) {
        if (this.e.T != -1) {
            this.a.dismiss();
            this.e.b(this.b, ((Pair) this.c.get(this.e.T)).second + "");
            return;
        }
        ToastAndDialog.makeNegativeToast(this.d, "选点什么吧~~", Integer.valueOf(0)).show();
    }
}
