package qsbk.app.live.model;

public class LiveSilentMsg extends LiveMessage {
    public LiveSilentMsgContent m;

    public LiveSilentMsg(long j, int i, long j2, long j3) {
        super(j, i);
        this.m = new LiveSilentMsgContent();
        this.m.t = j3;
        this.m.s = j2;
    }
}
