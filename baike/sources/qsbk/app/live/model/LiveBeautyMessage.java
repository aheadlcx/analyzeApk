package qsbk.app.live.model;

public class LiveBeautyMessage extends LiveMessage {
    public LiveBeautyMessageContent m;

    public LiveBeautyMessage(long j, int i, String str, boolean z) {
        super(j, i);
        this.m = new LiveBeautyMessageContent();
        this.m.f = str;
        this.m.b = z;
    }

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }
}
