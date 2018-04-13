package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LiveGameAnchorSelectMessage extends LiveGameDataMessage {
    public LiveGameAnchorSelectMessageContent m;

    public int getPos() {
        return this.m != null ? this.m.p : 0;
    }

    @JsonIgnore
    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }
}
