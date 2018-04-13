package cn.v6.sixrooms.room;

import android.os.Handler;
import android.os.Message;
import cn.v6.sixrooms.bean.LiveStateBean;
import cn.v6.sixrooms.socket.chat.CommonEventStatusBean;

final class u extends Handler {
    final /* synthetic */ RoomActivity a;

    u(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 14:
                this.a.j = true;
                this.a.playerviewFinished();
                return;
            case 22:
                LiveStateBean liveStateBean = (LiveStateBean) message.obj;
                if (!this.a.aq.equals(liveStateBean.getVideotype())) {
                    this.a.F.setVisibility(0);
                    this.a.d();
                    this.a.a(liveStateBean.getVideotype());
                }
                this.a.playerviewLoading();
                this.a.playPrepare();
                return;
            case 1350:
                CommonEventStatusBean commonEventStatusBean = (CommonEventStatusBean) message.obj;
                if (this.a.av != null && commonEventStatusBean != null) {
                    this.a.av.updateVoteMsg(commonEventStatusBean.getVoteMsg());
                    return;
                }
                return;
            default:
                return;
        }
    }
}
