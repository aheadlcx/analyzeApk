package qsbk.app.live.model;

public class LiveGiftZhouxingMessage extends LiveMessage {
    public LiveGiftZhouxingMessageContent m;

    public int getRank() {
        return this.m != null ? this.m.r : 0;
    }

    public long getGiftId() {
        return this.m != null ? this.m.i : 0;
    }

    public String getUrl() {
        return this.m != null ? this.m.w : "";
    }

    public String getGiftUrl() {
        return this.m != null ? this.m.g : "";
    }
}
