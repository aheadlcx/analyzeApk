package qsbk.app.live.model;

public class LiveGiftMsg extends LiveMessageBase {
    public LiveGiftMsgContent m;

    public LiveGiftMsg(long j, int i, long j2, long j3, long j4, long j5) {
        super(j, i);
        this.m = new LiveGiftMsgContent();
        this.m.t = j3;
        this.m.s = j2;
        this.m.g = new LiveGiftBase();
        this.m.g.i = j4;
        this.m.n = j5;
    }
}
