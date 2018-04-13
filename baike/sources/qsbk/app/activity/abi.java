package qsbk.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.widget.BaseGroupDialog;

class abi extends BaseGroupDialog {
    final /* synthetic */ RunForOwnerActivity a;

    abi(RunForOwnerActivity runForOwnerActivity, Context context) {
        this.a = runForOwnerActivity;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_quit_campaign);
        ((TextView) findViewById(R.id.msg)).setText("确定退出群大竞选?");
        findViewById(R.id.cancel).setOnClickListener(new abj(this));
        findViewById(R.id.submit).setOnClickListener(new abk(this));
    }
}
