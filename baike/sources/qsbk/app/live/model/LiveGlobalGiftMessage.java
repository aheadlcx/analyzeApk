package qsbk.app.live.model;

import android.graphics.Color;
import android.text.TextUtils;
import com.baidu.mobstat.Config;

public class LiveGlobalGiftMessage extends LiveMessage {
    public LiveGlobalGiftMessageContent m;

    public LiveGlobalGiftMessageContent getLiveMessageContent() {
        return this.m;
    }

    public int[] getBackgroundGradientColors() {
        int[] iArr = null;
        if (!(this.m == null || TextUtils.isEmpty(this.m.sc) || TextUtils.isEmpty(this.m.ec))) {
            iArr = new int[2];
            try {
                iArr[0] = Color.parseColor(this.m.sc);
                iArr[1] = Color.parseColor(this.m.ec);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return iArr;
    }

    public String getGiftIcon() {
        return this.m.p;
    }

    public String getHtml() {
        return this.m.h;
    }

    public double getRatio() {
        if (TextUtils.isEmpty(this.m.hw) || !this.m.hw.contains(Config.TRACE_TODAY_VISIT_SPLIT)) {
            return 2.0d;
        }
        String[] split = this.m.hw.split(Config.TRACE_TODAY_VISIT_SPLIT);
        return Double.parseDouble(split[0]) / Double.parseDouble(split[1]);
    }
}
