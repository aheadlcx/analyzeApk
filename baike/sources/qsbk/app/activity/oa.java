package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.GroupInfo;

class oa implements OnClickListener {
    final /* synthetic */ GroupNoticeDetailActivity a;

    oa(GroupNoticeDetailActivity groupNoticeDetailActivity) {
        this.a = groupNoticeDetailActivity;
    }

    public void onClick(View view) {
        ApplyForGroupActivity.launchForResult(this.a, new GroupInfo(this.a.c.tribe), -1);
    }
}
