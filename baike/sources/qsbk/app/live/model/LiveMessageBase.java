package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import qsbk.app.core.utils.AppUtils;

public class LiveMessageBase {
    public long d;
    public long i;
    public long o;
    public int p;
    public long t;

    public LiveMessageBase(long j, int i) {
        this.d = System.currentTimeMillis();
        this.i = j;
        this.p = i;
        this.o = AppUtils.getInstance().getUserInfoProvider().getUserOrigin();
        this.t = this.d;
    }

    @JsonIgnore
    public int getMessageType() {
        return this.p;
    }

    @JsonIgnore
    public long getClientMessageId() {
        return this.d;
    }

    @JsonIgnore
    public long getCreatedAt() {
        return this.t;
    }
}
