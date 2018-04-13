package qsbk.app.live.model;

public class LiveAdminMsg extends LiveMessageBase {
    public LiveAdminMsgContent m;

    public LiveAdminMsg(long j, int i, long j2, long j3) {
        super(j, i);
        this.m = new LiveAdminMsgContent();
        this.m.t = j2;
        this.m.s = j3;
    }
}
