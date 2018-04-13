package qsbk.app.live.model;

public class LiveProTopMessage extends LiveMessage {
    public LiveProTopMessageContent m;

    public LiveProTopMessageContent getLiveMessageContent() {
        return this.m;
    }

    public int getTopNumber() {
        LiveProTopMessageContent liveMessageContent = getLiveMessageContent();
        return liveMessageContent != null ? liveMessageContent.r : 0;
    }

    public String getProTopContent() {
        LiveProTopMessageContent liveMessageContent = getLiveMessageContent();
        return (liveMessageContent == null || liveMessageContent.d == null) ? "" : liveMessageContent.d;
    }
}
