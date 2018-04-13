package qsbk.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.widget.BaseGroupDialog;

class ado extends BaseGroupDialog {
    final /* synthetic */ TopicBlackListActivity a;

    ado(TopicBlackListActivity topicBlackListActivity, Context context) {
        this.a = topicBlackListActivity;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_quit_campaign);
        ((TextView) findViewById(R.id.msg)).setText("确认解除所有屏蔽?");
        findViewById(R.id.cancel).setOnClickListener(new adp(this));
        findViewById(R.id.submit).setOnClickListener(new adq(this));
    }
}
