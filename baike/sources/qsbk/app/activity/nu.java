package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.GroupNotice;
import qsbk.app.utils.GroupActionUtil;

class nu implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    nu(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public void onClick(View view) {
        GroupNotice groupNotice = (GroupNotice) this.b.getItem(this.a.b);
        GroupActionUtil.agreeForJoinGroup(groupNotice.gid, groupNotice.user.userId, groupNotice.msgid, true, groupNotice.iid, new nv(this, view.getContext(), "处理中...", groupNotice));
    }
}
