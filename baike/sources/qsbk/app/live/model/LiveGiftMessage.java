package qsbk.app.live.model;

import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LiveGiftMessage extends LiveMessage {
    @JsonIgnore
    public int count;
    public LiveGiftMessageContent m;

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }

    @JsonIgnore
    public long getGiftId() {
        return (this.m == null || this.m.g == null) ? 0 : this.m.g.i;
    }

    @JsonIgnore
    public String getGiftName() {
        return (this.m == null || this.m.g == null) ? "" : this.m.g.n;
    }

    @JsonIgnore
    public String getGiftUrl() {
        return (this.m == null || this.m.g == null) ? "" : this.m.g.m;
    }

    @JsonIgnore
    public int getGiftCount() {
        return this.m != null ? this.m.c : 1;
    }

    @JsonIgnore
    public void setGiftCount(int i) {
        this.m.c = i;
    }

    @JsonIgnore
    public void setShowTime(long j) {
        this.m.showTime = j;
    }

    public long getShowTime() {
        return this.m.showTime;
    }

    public boolean isWebpAnim() {
        return (this.m == null || this.m.g == null || TextUtils.isEmpty(this.m.g.a) || this.m.g.d <= 0) ? false : true;
    }

    public String getWebpAnimUrl() {
        return this.m.g.a;
    }

    public long getWebpAnimDuration() {
        return this.m.g.d;
    }

    public void setGiftContent(String str) {
        this.m.m = str;
    }

    public String getGiftValueContent() {
        return getContent();
    }

    public int getComboCount() {
        return this.m != null ? this.m.c : 0;
    }

    public long getLuckyEggDiamondCount() {
        return this.m != null ? this.m.g.c : 0;
    }

    public long getLuckyEggGiftId() {
        return this.m != null ? this.m.g.g : 0;
    }

    public long getLuckyEggGiftCount() {
        return this.m != null ? this.m.g.b : 0;
    }

    public String getGiftShowContent() {
        return (this.m == null || TextUtils.isEmpty(this.m.w)) ? "" : this.m.w.replace("$", this.m.g.n);
    }

    public void setGiftShowContent(String str) {
        if (this.m != null) {
            this.m.w = str;
        }
    }
}
