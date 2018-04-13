package qsbk.app.live.ui.helper;

import java.util.ArrayList;
import java.util.List;
import qsbk.app.live.ui.LiveBaseActivity;

public class StopLiveHelper {
    private static LiveBaseActivity a;
    private static List<Long> b;

    public static void setLivingActivity(LiveBaseActivity liveBaseActivity) {
        a = liveBaseActivity;
    }

    public static void setStopLive() {
        if (a != null) {
            a.setStopLive();
        }
    }

    public static void setRobedRedEnvelopes(long j) {
        if (b == null) {
            b = new ArrayList();
        }
        if (!b.contains(Long.valueOf(j))) {
            b.add(Long.valueOf(j));
        }
    }

    public static boolean isRobedRedEnvelopes(long j) {
        return b != null && b.contains(Long.valueOf(j));
    }
}
