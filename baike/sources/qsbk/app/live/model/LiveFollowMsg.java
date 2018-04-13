package qsbk.app.live.model;

public class LiveFollowMsg extends LiveMessageBase {
    public LiveFollowMsgContent m;

    public LiveFollowMsg(long j, int i, long j2) {
        super(j, i);
        this.m = new LiveFollowMsgContent();
        this.m.t = j2;
    }
}
