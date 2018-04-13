package qsbk.app.live.model;

public class LiveRobRedEnvelopesResultMessage extends LiveMessage {
    public LiveRobRedEnvelopesResultMessageContent m;

    public long getCoin() {
        return this.m != null ? this.m.coin : 0;
    }

    public long getRedEnvelopesId() {
        return this.m != null ? this.m.id : 0;
    }

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }
}
