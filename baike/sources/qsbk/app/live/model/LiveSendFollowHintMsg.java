package qsbk.app.live.model;

public class LiveSendFollowHintMsg extends LiveMessageBase {
    public LiveSendFollowHintMsgContent m;

    public LiveSendFollowHintMsg(long j, int i, int i2) {
        super(j, i);
        this.m = new LiveSendFollowHintMsgContent();
        this.m.t = i2;
    }
}
