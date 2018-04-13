package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LiveSystemMessage extends LiveMessage {
    public LiveSystemMessageContent m;

    public LiveSystemMessage(long j, int i, LiveSystemMessageContent liveSystemMessageContent) {
        super(j, i);
        this.m = liveSystemMessageContent;
    }

    @JsonIgnore
    public LiveSystemMessageContent getLiveMessageContent() {
        return this.m;
    }

    public int getType() {
        return this.m != null ? this.m.t : 0;
    }

    public boolean isPopupWindowType() {
        return getType() == 1;
    }

    public boolean isNormalType() {
        return getType() == 0;
    }
}
