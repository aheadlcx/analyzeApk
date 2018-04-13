package qsbk.app.live.model;

import java.util.List;

public class GameRole {
    public long i;
    public GameResult r;
    public long s;
    public float t;
    public long v;

    public long getTotalBet() {
        return this.v;
    }

    public long getMeBet() {
        return this.s;
    }

    public List<Integer> getResultGroup() {
        return this.r != null ? this.r.getResultGroup() : null;
    }

    public long getRoleId() {
        return this.i;
    }

    public GameResult getGameResult() {
        return this.r;
    }

    public float getRate() {
        return this.t;
    }
}
