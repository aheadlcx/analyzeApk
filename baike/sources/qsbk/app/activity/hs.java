package qsbk.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.widget.BaseGroupDialog;

class hs extends BaseGroupDialog {
    final /* synthetic */ CircleTopicActivity a;

    hs(CircleTopicActivity circleTopicActivity, Context context) {
        this.a = circleTopicActivity;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_quit_campaign);
        ((TextView) findViewById(R.id.msg)).setText("申请成为题主后，题主可以管理话题内动态、屏蔽某些用户在该话题内发动态和评论等。同时，题主需要在最近3天内最少访问该话题1次，否则题主职位将被自动解除。");
        findViewById(R.id.cancel).setOnClickListener(new ht(this));
        TextView textView = (TextView) findViewById(R.id.submit);
        textView.setText("确认申请");
        textView.setOnClickListener(new hu(this));
    }
}
