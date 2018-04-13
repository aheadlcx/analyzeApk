package qsbk.app.live.model;

public class LiveReconnectMessage extends LiveMessage {
    public LiveReconnectMessageContent m;

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }
}
