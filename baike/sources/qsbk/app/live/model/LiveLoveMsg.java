package qsbk.app.live.model;

public class LiveLoveMsg extends LiveMessageBase {
    public LiveLoveMsgContent m;

    public LiveLoveMsg(long j, int i, int i2, int i3) {
        super(j, i);
        this.m = new LiveLoveMsgContent();
        this.m.l = i2;
        this.m.t = i3;
    }
}
