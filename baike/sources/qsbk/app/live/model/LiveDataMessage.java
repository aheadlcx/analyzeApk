package qsbk.app.live.model;

public class LiveDataMessage extends LiveMessage {
    public LiveDataMessageContent m;

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }
}
