package com.umeng.commonsdk.statistics.common;

import android.content.Context;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.c;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.Defcon;

public class ReportPolicy {
    public static final int BATCH_AT_LAUNCH = 1;
    public static final int BATCH_BY_INTERVAL = 6;
    public static final int DAILY = 4;
    public static final int REALTIME = 0;
    public static final int SMART_POLICY = 8;
    public static final int WIFIONLY = 5;

    public static class ReportStrategy {
        public boolean shouldSendMessage(boolean z) {
            return true;
        }

        public boolean isValid() {
            return true;
        }
    }

    public static class DebugPolicy extends ReportStrategy {
        private final long a = 15000;
        private StatTracer b;

        public DebugPolicy(StatTracer statTracer) {
            this.b = statTracer;
        }

        public boolean shouldSendMessage(boolean z) {
            if (System.currentTimeMillis() - UMEnvelopeBuild.lastSuccessfulBuildTime(c.a()) >= 15000) {
                return true;
            }
            return false;
        }
    }

    public static class DefconPolicy extends ReportStrategy {
        private Defcon a;
        private StatTracer b;

        public DefconPolicy(StatTracer statTracer, Defcon defcon) {
            this.b = statTracer;
            this.a = defcon;
        }

        public boolean shouldSendMessage(boolean z) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - UMEnvelopeBuild.lastSuccessfulBuildTime(c.a()) >= this.a.getReqInterval()) {
                return true;
            }
            return false;
        }

        public boolean isValid() {
            return this.a.isOpen();
        }
    }

    public static class LatentPolicy extends ReportStrategy {
        private long a;
        private long b = 0;

        public LatentPolicy(int i) {
            this.a = (long) i;
            this.b = System.currentTimeMillis();
        }

        public boolean shouldSendMessage(boolean z) {
            if (System.currentTimeMillis() - this.b >= this.a) {
                return true;
            }
            return false;
        }

        public boolean isValid() {
            return System.currentTimeMillis() - this.b < this.a;
        }
    }

    public static class ReportAtLaunch extends ReportStrategy {
        public boolean shouldSendMessage(boolean z) {
            return z;
        }
    }

    public static class ReportByInterval extends ReportStrategy {
        private static long a = 90000;
        private static long b = 86400000;
        private long c;
        private StatTracer d;

        public ReportByInterval(StatTracer statTracer, long j) {
            this.d = statTracer;
            setReportInterval(j);
        }

        public boolean shouldSendMessage(boolean z) {
            if (System.currentTimeMillis() - UMEnvelopeBuild.lastSuccessfulBuildTime(c.a()) >= this.c) {
                return true;
            }
            return false;
        }

        public void setReportInterval(long j) {
            if (j < a || j > b) {
                this.c = a;
            } else {
                this.c = j;
            }
        }

        public long getReportInterval() {
            return this.c;
        }

        public static boolean isValidValue(int i) {
            if (((long) i) < a) {
                return false;
            }
            return true;
        }
    }

    public static class ReportDaily extends ReportStrategy {
        private long a = 86400000;
        private StatTracer b;

        public ReportDaily(StatTracer statTracer) {
            this.b = statTracer;
        }

        public boolean shouldSendMessage(boolean z) {
            if (System.currentTimeMillis() - UMEnvelopeBuild.lastSuccessfulBuildTime(c.a()) >= this.a) {
                return true;
            }
            return false;
        }
    }

    public static class ReportRealtime extends ReportStrategy {
        public boolean shouldSendMessage(boolean z) {
            return true;
        }
    }

    public static class ReportWifiOnly extends ReportStrategy {
        private Context a = null;

        public ReportWifiOnly(Context context) {
            this.a = context;
        }

        public boolean shouldSendMessage(boolean z) {
            return DeviceConfig.isWiFiAvailable(this.a);
        }
    }

    public static class SmartPolicy extends ReportStrategy {
        private final long a = 10800000;
        private StatTracer b;

        public SmartPolicy(StatTracer statTracer) {
            this.b = statTracer;
        }

        public boolean shouldSendMessage(boolean z) {
            if (System.currentTimeMillis() - UMEnvelopeBuild.lastSuccessfulBuildTime(c.a()) >= 10800000) {
                return true;
            }
            return false;
        }
    }

    public static boolean isValid(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                return true;
            default:
                return false;
        }
    }
}
