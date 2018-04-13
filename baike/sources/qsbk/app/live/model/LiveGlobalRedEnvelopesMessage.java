package qsbk.app.live.model;

public class LiveGlobalRedEnvelopesMessage extends LiveMessage {
    public LiveGlobalRedEnvelopesMessageContent m;

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }

    public long getAnchorId() {
        return this.m != null ? this.m.anchorId : 0;
    }

    public long getAnchorOrigin() {
        return this.m != null ? this.m.anchorOrigin : 0;
    }
}
