package qsbk.app.live.ui.family;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.AppUtils;

class bl implements OnClickListener {
    final /* synthetic */ Message a;
    final /* synthetic */ MessageAdapter b;

    bl(MessageAdapter messageAdapter, Message message) {
        this.b = messageAdapter;
        this.a = message;
    }

    public void onClick(View view) {
        AppUtils.getInstance().getUserInfoProvider().toUserPage((Activity) this.b.b, this.a.getUser());
    }
}
