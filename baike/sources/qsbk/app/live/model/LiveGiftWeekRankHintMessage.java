package qsbk.app.live.model;

public class LiveGiftWeekRankHintMessage extends LiveMessage {
    public LiveGiftWeekRankHintMessageContent m;

    public String getHint() {
        return this.m != null ? this.m.m : "";
    }

    public long getShowTime() {
        return this.m != null ? this.m.t : 10000;
    }
}
