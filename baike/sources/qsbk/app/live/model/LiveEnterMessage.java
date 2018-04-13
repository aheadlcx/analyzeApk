package qsbk.app.live.model;

import android.os.Environment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.File;
import java.util.Map;
import qsbk.app.core.model.MarketData;
import qsbk.app.core.utils.ConfigInfoUtil;

public class LiveEnterMessage extends LiveMessage {
    public LiveEnterMessageContent m;

    public LiveEnterMessage(long j, int i, LiveEnterMessageContent liveEnterMessageContent) {
        super(j, i);
        this.m = liveEnterMessageContent;
    }

    @JsonIgnore
    public LiveEnterMessageContent getLiveMessageContent() {
        return this.m;
    }

    public int getActivityLevel() {
        return (this.m == null || this.m.u == null) ? 0 : this.m.u.s;
    }

    public boolean isFrameOrSVGAAnim() {
        Map marketDataMap = ConfigInfoUtil.instance().getMarketDataMap();
        if (marketDataMap == null || !marketDataMap.containsKey(Long.valueOf((long) getEnterAnimId())) || ((MarketData) marketDataMap.get(Long.valueOf((long) getEnterAnimId()))).y == 3) {
            return false;
        }
        if (((MarketData) marketDataMap.get(Long.valueOf((long) getEnterAnimId()))).y != 1) {
            return true;
        }
        if (new File(Environment.getExternalStorageDirectory() + "/Remix/.Market/" + getEnterAnimId()).exists()) {
            return true;
        }
        return false;
    }

    public int getEnterAnimId() {
        return (this.m == null || this.m.u == null) ? 0 : this.m.u.ld;
    }

    public boolean isSmallFrameAnim() {
        Map marketDataMap = ConfigInfoUtil.instance().getMarketDataMap();
        if (marketDataMap == null || !marketDataMap.containsKey(Long.valueOf((long) getEnterAnimId())) || ((MarketData) marketDataMap.get(Long.valueOf((long) getEnterAnimId()))).y != 3) {
            return false;
        }
        if (new File(Environment.getExternalStorageDirectory() + "/Remix/.Market/" + getEnterAnimId()).exists()) {
            return true;
        }
        return false;
    }
}
