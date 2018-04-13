package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.EventWindow;
import qsbk.app.utils.ToastUtil;

class kl implements OnClickListener {
    final /* synthetic */ EventWindowActivity a;

    kl(EventWindowActivity eventWindowActivity) {
        this.a = eventWindowActivity;
    }

    public void onClick(View view) {
        if (EventWindow.JUMP_QB_POST.equals(this.a.a.actionType)) {
            ToastUtil.Short("活动说明，在相关爆社详情页");
        }
        this.a.finish();
    }
}
