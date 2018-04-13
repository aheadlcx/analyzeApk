package qsbk.app.live.model;

public class LiveGameBetMsg extends LiveMessageBase {
    public LiveGameBetMsgContent m;

    public LiveGameBetMsg(long j, int i, long j2, long j3, long j4, long j5) {
        super(j, i);
        this.m = new LiveGameBetMsgContent();
        this.m.i = j2;
        this.m.r = j3;
        this.m.t = j4;
        this.m.v = j5;
    }
}
