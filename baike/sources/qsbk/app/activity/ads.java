package qsbk.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.widget.BaseGroupDialog;

class ads extends BaseGroupDialog {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ TopicBlackListActivity b;

    ads(TopicBlackListActivity topicBlackListActivity, Context context, BaseUserInfo baseUserInfo) {
        this.b = topicBlackListActivity;
        this.a = baseUserInfo;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_quit_campaign);
        ((TextView) findViewById(R.id.msg)).setText("解除屏蔽后，该用户将可在该话题发动态和评论哦");
        findViewById(R.id.cancel).setOnClickListener(new adt(this));
        findViewById(R.id.submit).setOnClickListener(new adu(this));
    }
}
