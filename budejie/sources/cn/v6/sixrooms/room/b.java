package cn.v6.sixrooms.room;

import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.event.EventObserver;
import cn.v6.sixrooms.room.game.MiniGameMsgEvent;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.utils.ChatStyleUtils;

final class b implements EventObserver {
    final /* synthetic */ BaseRoomActivity a;

    b(BaseRoomActivity baseRoomActivity) {
        this.a = baseRoomActivity;
    }

    public final void onEventChange(Object obj, String str) {
        if ((obj instanceof MiniGameMsgEvent) && this.a.k != null) {
            MiniGameMsgEvent miniGameMsgEvent = (MiniGameMsgEvent) obj;
            RoommsgBean roommsgBean = new RoommsgBean();
            roommsgBean.setContent(miniGameMsgEvent.getMsg());
            roommsgBean.setTypeID(SocketUtil.FLAG_ON_FULL);
            this.a.k.onNotifyPublicDataSetChanged(ChatStyleUtils.chatStyleHandle(roommsgBean), false);
        }
    }
}
