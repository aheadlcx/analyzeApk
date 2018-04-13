package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LiveCommonMessage extends LiveMessage {
    public LiveCommonMessageContent m;

    public LiveCommonMessage(long j, int i, LiveCommonMessageContent liveCommonMessageContent) {
        super(j, i);
        this.m = liveCommonMessageContent;
    }

    @JsonIgnore
    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }
}
