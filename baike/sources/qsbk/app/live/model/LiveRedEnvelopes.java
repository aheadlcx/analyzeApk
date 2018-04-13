package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LiveRedEnvelopes {
    @JsonProperty("t")
    public long createAt;
    @JsonProperty("i")
    public long id;
    @JsonProperty("l")
    public long leftSeconds;
    @JsonProperty("p")
    public String pwd;
    @JsonProperty("s")
    public long source;
    @JsonProperty("u")
    public LiveUser user;
    @JsonProperty("d")
    public long userId;
    @JsonProperty("v")
    public long validSeconds;
}
