package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LiveBalanceMessage extends LiveMessage {
    public LiveBalanceMessageContent m;

    public LiveBalanceMessageContent getLiveMessageContent() {
        return this.m;
    }

    @JsonIgnore
    public long getBalance() {
        return this.m.c;
    }

    public long getFreeGiftId() {
        return this.m != null ? this.m.g : 0;
    }

    public int getFreeGiftCount() {
        return this.m != null ? this.m.n : 0;
    }
}
