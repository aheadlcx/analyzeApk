package qsbk.app.utils;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.widget.BaseGroupDialog;

final class w extends BaseGroupDialog {
    final /* synthetic */ String a;
    final /* synthetic */ SimpleCallBack b;
    final /* synthetic */ int c;
    final /* synthetic */ String d;

    w(Context context, String str, SimpleCallBack simpleCallBack, int i, String str2) {
        this.a = str;
        this.b = simpleCallBack;
        this.c = i;
        this.d = str2;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_member_delete);
        ((TextView) findViewById(R.id.msg)).setText("确定把" + this.a + "踢出群?");
        findViewById(R.id.cancel).setOnClickListener(new x(this));
        findViewById(R.id.submit).setOnClickListener(new y(this));
    }
}
