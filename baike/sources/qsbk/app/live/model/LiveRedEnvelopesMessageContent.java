package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class LiveRedEnvelopesMessageContent extends LiveMessageContent {
    @JsonProperty("ei")
    public List<LiveRedEnvelopes> items;
}
