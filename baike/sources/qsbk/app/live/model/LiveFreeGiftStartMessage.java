package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import qsbk.app.core.utils.ConfigInfoUtil;

public class LiveFreeGiftStartMessage extends LiveMessage {
    public LiveFreeGiftStartMessageContent m;

    @JsonIgnore
    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }

    public String getGiftUrl() {
        return this.m != null ? ConfigInfoUtil.instance().getGiftUrlById(this.m.g) : null;
    }

    public long getDuration() {
        return this.m != null ? this.m.d : 0;
    }
}
