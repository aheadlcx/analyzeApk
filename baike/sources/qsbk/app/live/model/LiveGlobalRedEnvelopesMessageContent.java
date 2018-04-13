package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LiveGlobalRedEnvelopesMessageContent extends LiveCommonMessageContent {
    @JsonProperty("c")
    public long anchorId;
    @JsonProperty("cs")
    public long anchorOrigin = 2;
    @JsonProperty("i")
    public long id;
    @JsonProperty("w")
    public String pwd;
}
