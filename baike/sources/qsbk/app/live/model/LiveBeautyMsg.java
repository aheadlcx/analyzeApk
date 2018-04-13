package qsbk.app.live.model;

public class LiveBeautyMsg extends LiveMessageBase {
    public LiveBeautyMsgContent m;

    public LiveBeautyMsg(long j, int i, String str, boolean z) {
        super(j, i);
        this.m = new LiveBeautyMsgContent();
        this.m.f = str;
        this.m.b = z;
    }
}
