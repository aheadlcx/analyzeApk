package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class LiveRichMessage extends LiveMessage {
    public LiveRichMessageContent m;

    public LiveRichMessageContent getLiveMessageContent() {
        return this.m;
    }

    @JsonIgnore
    public List<GiftRankRichData> getRichData() {
        return this.m != null ? this.m.r : null;
    }
}
