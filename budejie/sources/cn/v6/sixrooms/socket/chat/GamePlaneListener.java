package cn.v6.sixrooms.socket.chat;

import cn.v6.sixrooms.room.game.GamePlaneEndBean;
import cn.v6.sixrooms.room.game.GamePlaneStartBean;

public interface GamePlaneListener {
    void onNotifyGameEnd(GamePlaneEndBean gamePlaneEndBean);

    void onNotifyGameStart(GamePlaneStartBean gamePlaneStartBean);

    void onNotifySapphireAward(String str);
}
