package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class LiveGameDataMessage extends LiveGameMessage {
    public LiveGameDataMessageContent m;

    public LiveGameDataMessage(long j, int i, LiveGameDataMessageContent liveGameDataMessageContent) {
        super(j, i);
        this.m = liveGameDataMessageContent;
    }

    @JsonIgnore
    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }

    @JsonIgnore
    public int getGameStatus() {
        return this.m.s;
    }

    @JsonIgnore
    public List<GameRole> getGameRoleBetData() {
        return this.m.p;
    }

    @JsonIgnore
    public long getBalance() {
        return this.m.c;
    }

    @JsonIgnore
    public long getCountDownDuration() {
        return this.m.d;
    }

    @JsonIgnore
    public List<BestBetResult> getBestBetResult() {
        return this.m.t;
    }

    @JsonIgnore
    public int getStep() {
        return this.m.st;
    }

    @JsonIgnore
    public int getVoteStatus() {
        return this.m.v;
    }

    @JsonIgnore
    public long getBetNum() {
        return this.m.cp;
    }

    @JsonIgnore
    public int getSelectedIndex() {
        return this.m.i;
    }
}
