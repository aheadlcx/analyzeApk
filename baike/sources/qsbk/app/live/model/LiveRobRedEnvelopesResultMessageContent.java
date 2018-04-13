package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LiveRobRedEnvelopesResultMessageContent extends LiveCommonMessageContent {
    @JsonProperty("c")
    public long coin;
    @JsonProperty("i")
    public long id;
}
