package cn.v6.sixrooms.socket.chat;

import cn.v6.sixrooms.room.game.GameLuckIndianaBuyNumBean;
import cn.v6.sixrooms.room.game.GameLuckIndianaInitBean;
import cn.v6.sixrooms.room.game.GameLuckIndianaResultBean;
import java.util.Map;

public interface GameLuckIndianaListener {
    void onNotifyGameBuyNum(Map<String, GameLuckIndianaBuyNumBean> map);

    void onNotifyGameEnd(GameLuckIndianaResultBean gameLuckIndianaResultBean);

    void onNotifyGameStart(GameLuckIndianaInitBean gameLuckIndianaInitBean);
}
