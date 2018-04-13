package qsbk.app.utils;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.widget.BaseGroupDialog;

final class z extends BaseGroupDialog {
    final /* synthetic */ String a;
    final /* synthetic */ SimpleCallBack b;
    final /* synthetic */ int c;
    final /* synthetic */ String d;

    z(Context context, String str, SimpleCallBack simpleCallBack, int i, String str2) {
        this.a = str;
        this.b = simpleCallBack;
        this.c = i;
        this.d = str2;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_member_delete);
        ((TextView) findViewById(R.id.msg)).setText("确定设置 " + this.a + " 为本群管理员？");
        ((TextView) findViewById(R.id.tips)).setVisibility(8);
        findViewById(R.id.cancel).setOnClickListener(new aa(this));
        findViewById(R.id.submit).setOnClickListener(new ab(this));
    }
}
