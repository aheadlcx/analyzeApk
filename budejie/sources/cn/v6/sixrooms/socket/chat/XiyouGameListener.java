package cn.v6.sixrooms.socket.chat;

import cn.v6.sixrooms.room.game.GameResultBean;
import cn.v6.sixrooms.room.game.GameStartBean;

public interface XiyouGameListener {
    void onNotifyGameEnd(GameResultBean gameResultBean);

    void onNotifyGameStart(GameStartBean gameStartBean);
}
