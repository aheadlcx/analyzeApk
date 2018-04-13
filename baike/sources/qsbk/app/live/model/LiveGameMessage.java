package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LiveGameMessage extends LiveMessage {
    public LiveGameMessage(long j, int i) {
        super(j, i);
    }

    @JsonIgnore
    public long getGameId() {
        LiveGameMessageContent liveGameMessageContent = (LiveGameMessageContent) getLiveMessageContent();
        return liveGameMessageContent != null ? liveGameMessageContent.g : 0;
    }

    @JsonIgnore
    public long getGameRoundId() {
        LiveGameMessageContent liveGameMessageContent = (LiveGameMessageContent) getLiveMessageContent();
        return liveGameMessageContent != null ? liveGameMessageContent.r : 0;
    }

    @JsonIgnore
    public long getGameResult() {
        LiveGameMessageContent liveGameMessageContent = (LiveGameMessageContent) getLiveMessageContent();
        return liveGameMessageContent != null ? (long) liveGameMessageContent.sw : 0;
    }

    @JsonIgnore
    public String getGameResultContent() {
        return ((LiveGameMessageContent) getLiveMessageContent()).ds;
    }
}
