package qsbk.app.utils;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.widget.BaseGroupDialog;

final class ac extends BaseGroupDialog {
    final /* synthetic */ String a;
    final /* synthetic */ SimpleCallBack b;
    final /* synthetic */ int c;
    final /* synthetic */ String d;

    ac(Context context, String str, SimpleCallBack simpleCallBack, int i, String str2) {
        this.a = str;
        this.b = simpleCallBack;
        this.c = i;
        this.d = str2;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_member_delete);
        ((TextView) findViewById(R.id.msg)).setText("确定撤销 " + this.a + " 的管理员职务？");
        ((TextView) findViewById(R.id.tips)).setVisibility(8);
        findViewById(R.id.cancel).setOnClickListener(new ad(this));
        findViewById(R.id.submit).setOnClickListener(new ae(this));
    }
}
