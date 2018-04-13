package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.event.AppForegroundEvent;
import cn.v6.sixrooms.event.EventManager;
import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import cn.v6.sixrooms.utils.ActivityManagerUtils;
import cn.v6.sixrooms.utils.AppCount;
import cn.v6.sixrooms.utils.GlobleValue;

final class a implements Runnable {
    final /* synthetic */ BaseFragmentActivity a;

    a(BaseFragmentActivity baseFragmentActivity) {
        this.a = baseFragmentActivity;
    }

    public final void run() {
        if (this.a.getPackageName().contains(ActivityManagerUtils.getTopPackageName(this.a)) && !GlobleValue.status) {
            GlobleValue.status = true;
            AppCount.sendAppCountInfo(BoxingVoteBean.BOXING_VOTE_STATE_OPEN);
            EventManager.getDefault().nodifyObservers(new AppForegroundEvent(), "");
        }
    }
}
