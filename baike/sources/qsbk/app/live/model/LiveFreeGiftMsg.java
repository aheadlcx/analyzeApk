package qsbk.app.live.model;

public class LiveFreeGiftMsg extends LiveMessageBase {
    public LiveFreeGiftMsgContent m;

    public LiveFreeGiftMsg(long j, int i) {
        super(j, i);
        this.m = new LiveFreeGiftMsgContent();
    }
}
