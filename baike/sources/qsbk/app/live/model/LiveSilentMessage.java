package qsbk.app.live.model;

public class LiveSilentMessage extends LiveMessage {
    public LiveSilentMessageContent m;

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }
}
