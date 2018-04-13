package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.Config;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.GroupNotice;

class nw implements OnClickListener {
    final /* synthetic */ GroupNotice a;
    final /* synthetic */ BaseUserInfo b;
    final /* synthetic */ a c;

    nw(a aVar, GroupNotice groupNotice, BaseUserInfo baseUserInfo) {
        this.c = aVar;
        this.a = groupNotice;
        this.b = baseUserInfo;
    }

    public void onClick(View view) {
        String str = "";
        String str2 = "";
        if (this.a.tribe != null) {
            str = "" + this.a.tribe.id;
            str2 = this.a.tribe.name;
        } else if (this.a.gid > 0) {
            str = "" + this.a.gid;
        }
        MyInfoActivity.launch(view.getContext(), this.b.userId, str, str2, MyInfoActivity.FANS_ORIGINS[3], new IMChatMsgSource(7, this.b.userId, str + Config.TRACE_TODAY_VISIT_SPLIT + str2));
    }
}
