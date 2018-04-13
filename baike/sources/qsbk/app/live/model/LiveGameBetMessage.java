package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LiveGameBetMessage extends LiveGameMessage {
    public LiveGameBetMessageContent m;

    public LiveGameBetMessage(long j, int i, LiveGameBetMessageContent liveGameBetMessageContent) {
        super(j, i);
        this.m = liveGameBetMessageContent;
    }

    @JsonIgnore
    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }

    @JsonIgnore
    public long getRoleId() {
        return this.m.p;
    }

    @JsonIgnore
    public GameRole getGameRole() {
        GameRole gameRole = new GameRole();
        gameRole.i = this.m.p;
        gameRole.v = getGameRoleBetTotal();
        gameRole.s = getGameRoleBetMe();
        return gameRole;
    }

    @JsonIgnore
    public long getGameRoleBetTotal() {
        return this.m.t;
    }

    @JsonIgnore
    public long getGameRoleBetMe() {
        return this.m.c;
    }

    @JsonIgnore
    public boolean isWin() {
        return this.m.s == 1;
    }

    @JsonIgnore
    public boolean useFhToWin() {
        return this.m.s == -1;
    }

    @JsonIgnore
    public long getFhkCount() {
        return (long) this.m.rc;
    }

    @JsonIgnore
    public boolean isLose() {
        return this.m.s == 0;
    }

    @JsonIgnore
    public long getWinNum() {
        return this.m.p;
    }

    @JsonIgnore
    public String getAnchorResult() {
        return isWin() ? "恭喜，本局赢了" + this.m.p + "赞！" : "竞猜失败！";
    }

    @JsonIgnore
    public String getResult() {
        return "竞猜失败\n传说中给主播送礼后运气更好";
    }

    @JsonIgnore
    public void setContent(String str) {
        this.m.m = "猜了" + str + "共" + this.m.t + "赞";
    }

    @JsonIgnore
    public long getBalance() {
        return this.m.c;
    }

    @JsonIgnore
    public long getGameUserId() {
        return this.m.i;
    }

    @JsonIgnore
    public long getGameUserOrigin() {
        return this.m.o;
    }

    @JsonIgnore
    public int getStep() {
        return this.m.st;
    }
}
