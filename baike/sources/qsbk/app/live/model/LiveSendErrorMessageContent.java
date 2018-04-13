package qsbk.app.live.model;

public class LiveSendErrorMessageContent extends LiveCommonMessageContent {
    public int e;

    public int getErrorCode() {
        return this.e;
    }
}
