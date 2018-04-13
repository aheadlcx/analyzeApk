package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LiveFreeGiftGetMessage extends LiveMessage {
    public LiveFreeGiftGetMessageContent m;

    public int getGiftCount() {
        return this.m != null ? this.m.n : 0;
    }

    public long getGiftId() {
        return this.m != null ? this.m.g : 0;
    }

    @JsonIgnore
    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }

    public boolean hasNextRound() {
        return this.m != null ? this.m.r : false;
    }
}
