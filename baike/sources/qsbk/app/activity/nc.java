package qsbk.app.activity;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.Config;
import qsbk.app.QsbkApp;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.GroupLeader;

class nc implements OnClickListener {
    final /* synthetic */ GroupLeader a;
    final /* synthetic */ a b;

    nc(a aVar, GroupLeader groupLeader) {
        this.b = aVar;
        this.a = groupLeader;
    }

    public void onClick(View view) {
        Context context = view.getContext();
        if (QsbkApp.currentUser == null || !TextUtils.equals(QsbkApp.currentUser.userId, String.valueOf(this.a.userId))) {
            IMChatMsgSource iMChatMsgSource = new IMChatMsgSource(7, String.valueOf(this.a.userId), String.valueOf(this.b.a.f.id) + Config.TRACE_TODAY_VISIT_SPLIT + this.b.a.f.name);
            if (this.b.a.f.joinStatus == 0 || this.b.a.f.joinStatus == 1) {
                MyInfoActivity.launch(context, String.valueOf(this.a.userId), MyInfoActivity.FANS_ORIGINS[3], iMChatMsgSource);
                return;
            } else if (this.b.a.f.joinStatus == 2) {
                MyInfoActivity.launch(context, String.valueOf(this.a.userId), String.valueOf(this.b.a.f.id), this.b.a.f.name, MyInfoActivity.FANS_ORIGINS[3], iMChatMsgSource);
                return;
            } else {
                return;
            }
        }
        MyInfoActivity.launch(context);
    }
}
