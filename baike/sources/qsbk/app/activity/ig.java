package qsbk.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.widget.BaseGroupDialog;

class ig extends BaseGroupDialog {
    final /* synthetic */ CircleTopicActivity a;

    ig(CircleTopicActivity circleTopicActivity, Context context) {
        this.a = circleTopicActivity;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_quit_campaign);
        ((TextView) findViewById(R.id.msg)).setText("确定辞职题主?");
        findViewById(R.id.cancel).setOnClickListener(new ih(this));
        findViewById(R.id.submit).setOnClickListener(new ii(this));
    }
}
