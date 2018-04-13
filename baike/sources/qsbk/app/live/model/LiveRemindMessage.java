package qsbk.app.live.model;

public class LiveRemindMessage extends LiveMessage {
    public LiveRemindMessageContent m;

    public LiveRemindMessageContent getLiveMessageContent() {
        return this.m;
    }

    public int getType() {
        return this.m != null ? this.m.t : 0;
    }

    public String getContent() {
        return this.m != null ? this.m.c : null;
    }
}
