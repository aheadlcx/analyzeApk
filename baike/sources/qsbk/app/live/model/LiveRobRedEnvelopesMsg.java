package qsbk.app.live.model;

public class LiveRobRedEnvelopesMsg extends LiveMessageBase {
    public LiveRobRedEnvelopesMsgContent m;

    public LiveRobRedEnvelopesMsg(long j, int i, String str, long j2) {
        super(j, i);
        this.m = new LiveRobRedEnvelopesMsgContent();
        this.m.p = str;
        this.m.i = j2;
    }
}
