package com.qq.e.ads.cfg;

import com.qq.e.comm.util.GDTLogger;
import org.json.JSONObject;

public class VideoOption {
    private final boolean a;
    private final int b;

    public static final class AutoPlayPolicy {
        public static final int ALWAYS = 1;
        public static final int WIFI = 0;
    }

    public static final class Builder {
        private boolean a = true;
        private int b = 0;

        public final VideoOption build() {
            return new VideoOption();
        }

        public final Builder setAutoPlayMuted(boolean z) {
            this.a = z;
            return this;
        }

        public final Builder setAutoPlayPolicy(int i) {
            this.b = i;
            return this;
        }
    }

    private VideoOption(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
    }

    public boolean getAutoPlayMuted() {
        return this.a;
    }

    public int getAutoPlayPolicy() {
        return this.b;
    }

    public JSONObject getOptions() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("autoPlayMuted", Boolean.valueOf(this.a));
            jSONObject.putOpt("autoPlayPolicy", Integer.valueOf(this.b));
        } catch (Exception e) {
            GDTLogger.e("Get video options error: " + e.getMessage());
        }
        return jSONObject;
    }
}
