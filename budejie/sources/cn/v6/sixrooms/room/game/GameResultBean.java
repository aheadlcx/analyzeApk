package cn.v6.sixrooms.room.game;

import cn.v6.sixrooms.bean.MessageBean;
import java.util.ArrayList;

public class GameResultBean extends MessageBean {
    private static final long serialVersionUID = -5074886430655765513L;
    private GameInfoBean gameInfoBean;
    private String gameResultStr;
    private GameWinnerBean myGameInfo;
    private ArrayList<GameWinnerBean> winnerInfos;

    public GameResultBean(ArrayList<GameWinnerBean> arrayList, GameInfoBean gameInfoBean, GameWinnerBean gameWinnerBean, String str) {
        this.winnerInfos = arrayList;
        this.gameInfoBean = gameInfoBean;
        this.myGameInfo = gameWinnerBean;
        this.gameResultStr = str;
    }

    public ArrayList<GameWinnerBean> getWinnerInfos() {
        return this.winnerInfos;
    }

    public void setWinnerInfos(ArrayList<GameWinnerBean> arrayList) {
        this.winnerInfos = arrayList;
    }

    public GameInfoBean getGameInfoBean() {
        return this.gameInfoBean;
    }

    public void setGameInfoBean(GameInfoBean gameInfoBean) {
        this.gameInfoBean = gameInfoBean;
    }

    public GameWinnerBean getMyGameInfo() {
        return this.myGameInfo;
    }

    public void setMyGameInfo(GameWinnerBean gameWinnerBean) {
        this.myGameInfo = gameWinnerBean;
    }

    public String getGameResultStr() {
        return this.gameResultStr;
    }

    public void setGameResultStr(String str) {
        this.gameResultStr = str;
    }
}
