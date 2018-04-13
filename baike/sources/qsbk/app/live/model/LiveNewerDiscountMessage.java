package qsbk.app.live.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LiveNewerDiscountMessage extends LiveMessage {
    public LiveNewerDiscountMessageContent m;

    @JsonIgnore
    public LiveNewerDiscountMessageContent getLiveMessageContent() {
        return this.m;
    }

    public String getDiamondUrl() {
        return (this.m == null || this.m.c == null || this.m.c.length <= 0) ? null : this.m.c[0].u;
    }

    public String getDiamondCount() {
        return (this.m == null || this.m.c == null || this.m.c.length <= 0) ? null : this.m.c[0].n;
    }

    public String getEnterAnimImage() {
        return (this.m == null || this.m.c == null || this.m.c.length <= 1) ? null : this.m.c[1].u;
    }

    public String getEnterAnimDiscription() {
        return (this.m == null || this.m.c == null || this.m.c.length <= 1) ? null : this.m.c[1].n;
    }

    public String getEnterAnimCount() {
        return (this.m == null || this.m.c == null || this.m.c.length <= 1) ? null : this.m.c[1].d;
    }

    public String getFreeGiftImage() {
        return (this.m == null || this.m.c == null || this.m.c.length <= 2) ? null : this.m.c[2].u;
    }

    public String getFreeGiftCount() {
        return (this.m == null || this.m.c == null || this.m.c.length <= 2) ? null : this.m.c[2].n;
    }
}
