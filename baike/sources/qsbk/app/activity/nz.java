package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.Config;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.BaseUserInfo;

class nz implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ GroupNoticeDetailActivity b;

    nz(GroupNoticeDetailActivity groupNoticeDetailActivity, BaseUserInfo baseUserInfo) {
        this.b = groupNoticeDetailActivity;
        this.a = baseUserInfo;
    }

    public void onClick(View view) {
        String str = "";
        String str2 = "";
        if (this.b.c.tribe != null) {
            str = "" + this.b.c.tribe.id;
            str2 = this.b.c.tribe.name;
        } else if (this.b.c.gid > 0) {
            str = "" + this.b.c.gid;
        }
        MyInfoActivity.launch(view.getContext(), this.a.userId, str, str2, MyInfoActivity.FANS_ORIGINS[3], new IMChatMsgSource(7, this.a.userId, str + Config.TRACE_TODAY_VISIT_SPLIT + str2));
    }
}
