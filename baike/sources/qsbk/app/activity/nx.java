package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.GroupActionUtil;

class nx implements OnClickListener {
    final /* synthetic */ GroupNoticeDetailActivity a;

    nx(GroupNoticeDetailActivity groupNoticeDetailActivity) {
        this.a = groupNoticeDetailActivity;
    }

    public void onClick(View view) {
        GroupActionUtil.agreeForJoinGroup(this.a.c.gid, this.a.c.user.userId, this.a.c.msgid, view != this.a.n, this.a.c.iid, new ny(this, view.getContext(), "处理中...", view));
    }
}
