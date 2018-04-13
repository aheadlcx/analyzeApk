package qsbk.app.live.model;

public class LiveGameAnchorSelectMsg extends LiveMessageBase {
    public LiveGameAnchorSelectMsgContent m;

    public LiveGameAnchorSelectMsg(long j, int i, long j2, long j3, int i2) {
        super(j, i);
        this.m = new LiveGameAnchorSelectMsgContent();
        this.m.g = j2;
        this.m.r = j3;
        this.m.p = i2;
    }
}
