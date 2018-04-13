package qsbk.app.live.model;

public class LiveCommentMessage extends LiveMessage {
    public LiveCommentMessageContent m;

    public LiveCommentMessage(long j, int i, String str) {
        super(j, i);
        this.m = new LiveCommentMessageContent();
        this.m.c = str;
    }

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }
}
