package qsbk.app.im;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.Config;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.utils.UserClickDelegate;

final class w implements OnClickListener {
    final /* synthetic */ i a;

    w(i iVar) {
        this.a = iVar;
    }

    public void onClick(View view) {
        Context context = view.getContext();
        String valueOf = String.valueOf(this.a.l.userId);
        if (UserClickDelegate.isOfficialAccount(valueOf)) {
            OfficialInfoActivity.launch(context, valueOf, this.a.l.userName, this.a.l.userIcon);
            return;
        }
        MyInfoActivity.launch(context, this.a.l.userId, this.a.m, this.a.n, MyInfoActivity.FANS_ORIGINS[3], new IMChatMsgSource(7, valueOf, this.a.m + Config.TRACE_TODAY_VISIT_SPLIT + this.a.n));
    }
}
