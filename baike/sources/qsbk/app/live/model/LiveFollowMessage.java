package qsbk.app.live.model;

public class LiveFollowMessage extends LiveMessage {
    public LiveFollowMessageContent m;

    public LiveFollowMessage(long j, int i, long j2) {
        super(j, i);
        this.m = new LiveFollowMessageContent();
        this.m.t = j2;
    }

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }
}
