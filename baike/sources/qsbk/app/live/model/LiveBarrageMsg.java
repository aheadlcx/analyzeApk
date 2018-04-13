package qsbk.app.live.model;

public class LiveBarrageMsg extends LiveMessageBase {
    public LiveBarrageMsgContent m;

    public LiveBarrageMsg(long j, int i, String str, String str2, long j2) {
        super(j, i);
        this.m = new LiveBarrageMsgContent();
        this.m.c = str;
        if (!"on".equals(str2)) {
            this.m.t = str2;
            this.m.p = j2;
        }
    }
}
