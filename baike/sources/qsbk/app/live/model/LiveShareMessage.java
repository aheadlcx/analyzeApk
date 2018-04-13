package qsbk.app.live.model;

public class LiveShareMessage extends LiveMessage {
    public LiveShareMessageContent m;

    public LiveShareMessage(long j, int i, String str) {
        super(j, i);
        this.m = new LiveShareMessageContent();
        this.m.t = str;
    }

    public LiveShareMessageContent getLiveMessageContent() {
        return this.m;
    }
}
