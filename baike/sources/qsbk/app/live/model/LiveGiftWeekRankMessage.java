package qsbk.app.live.model;

public class LiveGiftWeekRankMessage extends LiveMessage {
    public LiveGiftWeekRankMessageContent m;

    public int getRank() {
        return this.m != null ? this.m.r : 0;
    }
}
