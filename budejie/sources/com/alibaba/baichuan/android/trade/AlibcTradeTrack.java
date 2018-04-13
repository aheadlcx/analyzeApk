package com.alibaba.baichuan.android.trade;

import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.umeng.analytics.pro.x;
import com.ut.device.UTDevice;
import com.ut.mini.UTAnalytics;
import com.ut.mini.UTHitBuilders.UTCustomHitBuilder;
import java.util.HashMap;
import java.util.Map;

public class AlibcTradeTrack {
    public static final String AlibcTradeEventId_ContentClick = "BAICHUAN_CONTENT_CLICK";
    public static final String AlibcTradeEventId_Exposure = "BAICHUAN_EXPOSURE";

    public static class AlibcTrackParams {
        private String a = UTDevice.getUtdid(AlibcContext.context);
        public String label;
        public String page;
        public String puid;
        public String pvid;
        public String scm;

        public Map toMap() {
            Map hashMap = new HashMap();
            hashMap.put(AlibcConstants.SCM, this.scm);
            hashMap.put(AlibcConstants.PVID, this.pvid);
            hashMap.put(x.at, this.puid);
            hashMap.put("pguid", this.a);
            hashMap.put("page", this.page);
            hashMap.put("label", this.label);
            return hashMap;
        }
    }

    public static void addTraceLog(String str, AlibcTrackParams alibcTrackParams) {
        UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder(str);
        uTCustomHitBuilder.setEventPage(alibcTrackParams.page);
        uTCustomHitBuilder.setProperty(AlibcConstants.SCM, alibcTrackParams.scm);
        uTCustomHitBuilder.setProperty(AlibcConstants.PVID, alibcTrackParams.pvid);
        uTCustomHitBuilder.setProperty(x.at, alibcTrackParams.puid);
        uTCustomHitBuilder.setProperty("pguid", alibcTrackParams.a);
        uTCustomHitBuilder.setProperty("page", alibcTrackParams.page);
        uTCustomHitBuilder.setProperty("label", alibcTrackParams.label);
        UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
    }
}
