package qsbk.app.live.model;

public class LiveSendErrorMessage extends LiveMessage {
    public LiveSendErrorMessageContent m;

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }
}
