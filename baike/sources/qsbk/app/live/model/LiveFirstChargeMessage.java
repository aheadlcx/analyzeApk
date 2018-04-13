package qsbk.app.live.model;

public class LiveFirstChargeMessage extends LiveMessage {
    LiveFirstChargeMessageContent a = new LiveFirstChargeMessageContent();

    public LiveFirstChargeMessage(int i, long j, long j2) {
        super(j2, i);
        this.a.a = j;
    }
}
