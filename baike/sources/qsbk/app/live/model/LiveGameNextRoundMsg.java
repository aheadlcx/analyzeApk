package qsbk.app.live.model;

public class LiveGameNextRoundMsg extends LiveMessageBase {
    public LiveGameNextRoundMsgContent m;

    public LiveGameNextRoundMsg(long j, int i, long j2, long j3, int i2) {
        super(j, i);
        this.m = new LiveGameNextRoundMsgContent();
        this.m.g = j2;
        this.m.r = j3;
        this.m.o = i2;
    }
}
