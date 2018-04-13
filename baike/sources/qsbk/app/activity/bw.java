package qsbk.app.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.List;
import qsbk.app.utils.ToastAndDialog;

class bw implements OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ String b;
    final /* synthetic */ List c;
    final /* synthetic */ Context d;
    final /* synthetic */ AuditNativeActivity e;

    bw(AuditNativeActivity auditNativeActivity, AlertDialog alertDialog, String str, List list, Context context) {
        this.e = auditNativeActivity;
        this.a = alertDialog;
        this.b = str;
        this.c = list;
        this.d = context;
    }

    public void onClick(View view) {
        if (AuditNativeActivity.x(this.e) != -1) {
            this.a.dismiss();
            AuditNativeActivity.b(this.e, this.b, ((Pair) this.c.get(AuditNativeActivity.x(this.e))).second + "");
            return;
        }
        ToastAndDialog.makeNegativeToast(this.d, "选点什么吧~~", Integer.valueOf(0)).show();
    }
}
