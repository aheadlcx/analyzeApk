package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LiveLoveMessage extends LiveMessage {
    public LiveLoveMessageContent m;

    public LiveLoveMessage(long j, int i, int i2, int i3) {
        super(j, i);
        this.m = new LiveLoveMessageContent();
        this.m.l = i2;
        this.m.t = i3;
    }

    public LiveLoveMessageContent getLiveMessageContent() {
        return this.m;
    }

    @JsonIgnore
    public int getLoveColor() {
        return this.m != null ? this.m.t : 0;
    }

    @JsonIgnore
    public void setLoveColor(int i) {
        if (this.m != null) {
            this.m.t = i;
        }
    }
}
