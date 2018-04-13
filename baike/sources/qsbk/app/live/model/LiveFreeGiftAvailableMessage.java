package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import qsbk.app.core.utils.ConfigInfoUtil;

public class LiveFreeGiftAvailableMessage extends LiveMessage {
    public LiveFreeGiftAvailableMessageContent m;

    public String getGiftUrl() {
        return this.m != null ? ConfigInfoUtil.instance().getGiftUrlById(this.m.g) : null;
    }

    @JsonIgnore
    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }
}
