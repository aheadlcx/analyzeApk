package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LiveGameStepMessage extends LiveGameMessage {
    public LiveGameStepMessageContent m;

    public int getStep() {
        return this.m != null ? this.m.st : 0;
    }

    public long getCoupon() {
        return this.m != null ? this.m.cp : 0;
    }

    @JsonIgnore
    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }
}
