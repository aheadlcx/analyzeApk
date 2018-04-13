package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.LiveMessage;

class w implements OnClickListener {
    final /* synthetic */ DollResultDialog a;

    w(DollResultDialog dollResultDialog) {
        this.a = dollResultDialog;
    }

    public void onClick(View view) {
        this.a.m.sendLiveMessageAndRefreshUI(LiveMessage.createDollMessage(AppUtils.getInstance().getUserInfoProvider().getUserId(), 1));
        this.a.dismiss();
    }
}
