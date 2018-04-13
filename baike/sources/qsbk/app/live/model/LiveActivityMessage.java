package qsbk.app.live.model;

public class LiveActivityMessage extends LiveMessage {
    public LiveActivityMessageContent m;

    public LiveActivityMessageContent getLiveMessageContent() {
        return this.m;
    }

    public int getShowTime() {
        LiveActivityMessageContent liveMessageContent = getLiveMessageContent();
        return liveMessageContent != null ? liveMessageContent.t : 0;
    }

    public String getWebUrl() {
        LiveActivityMessageContent liveMessageContent = getLiveMessageContent();
        return (liveMessageContent == null || liveMessageContent.e == null) ? "" : liveMessageContent.e;
    }
}
