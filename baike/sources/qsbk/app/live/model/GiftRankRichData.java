package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GiftRankRichData {
    @JsonProperty("av")
    public String avatar;
    @JsonProperty("c")
    public long cp;
    @JsonProperty("i")
    public long id;
    @JsonProperty("l")
    public int level;
    @JsonProperty("s")
    public long source;
}
