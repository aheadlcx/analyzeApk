package com.alibaba.baichuan.android.trade.adapter.ut;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.adapter.ut.performance.PagePerformancePoint;
import com.alibaba.baichuan.android.trade.adapter.ut.performance.PerformancePoint;
import com.alibaba.baichuan.android.trade.adapter.ut.performance.Point4Init;
import com.alibaba.baichuan.android.trade.adapter.ut.performance.Point4ShowH5;
import com.alibaba.baichuan.android.trade.adapter.ut.performance.Point4ShowNative;
import com.alibaba.baichuan.android.trade.adapter.ut.performance.Point4UrlLoad;
import com.alibaba.baichuan.android.trade.config.AlibcConfig;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.utils.a;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.alibaba.fastjson.JSON;
import com.alibaba.mtl.appmonitor.AppMonitor;
import com.alibaba.mtl.appmonitor.AppMonitor$Stat;
import com.alibaba.mtl.appmonitor.AppMonitor.Alarm;
import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.tencent.connect.common.Constants;
import com.tencent.open.GameAppOperation;
import com.ut.mini.UTAnalytics;
import com.ut.mini.UTHitBuilders.UTCustomHitBuilder;
import com.ut.mini.core.sign.UTSecuritySDKRequestAuthentication;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import com.ut.mini.internal.UTTeamWork;
import java.util.HashMap;
import java.util.Map;

public class AlibcUserTracker implements IAlibcUserTracker {
    private static final String a = AlibcUserTracker.class.getSimpleName();
    private static AlibcUserTracker e;
    private Context b;
    private String c;
    private String d;

    private AlibcUserTracker() {
        init();
    }

    private void a() {
        AppMonitor.register("BCTradeSDK", UserTrackerConstants.P_INIT, Point4Init.getMeasureSet(), PerformancePoint.getDimensionSet());
        AppMonitor.register("BCTradeSDK", UserTrackerConstants.P_SHOWH5, Point4ShowH5.getMeasureSet(), PerformancePoint.getDimensionSet());
        AppMonitor.register("BCTradeSDK", UserTrackerConstants.P_SHOWNATIVE, Point4ShowNative.getMeasureSet(), PagePerformancePoint.getDimensionSet());
        AppMonitor.register("BCTradeSDK", UserTrackerConstants.P_URLLOAD, Point4UrlLoad.getMeasureSet(), PerformancePoint.getDimensionSet());
    }

    public static synchronized AlibcUserTracker getInstance() {
        AlibcUserTracker alibcUserTracker;
        synchronized (AlibcUserTracker.class) {
            if (e == null) {
                e = new AlibcUserTracker();
            }
            alibcUserTracker = e;
        }
        return alibcUserTracker;
    }

    public String getArgs() {
        if (AlibcContext.getAppKey() == null || AlibcContext.sdkVersion == null || AlibcConfig.getInstance().getChannel() == null) {
            AlibcLogger.e(a, "getArg : appkey/sdkversion/channel is null");
            a.a("AlibcUserTracker", "getArg", "appkey/sdkversion/channel is null");
            return "";
        }
        ArgsInfo argsInfo = new ArgsInfo();
        argsInfo.appKey = AlibcContext.getAppKey();
        argsInfo.sdkVersion = AlibcContext.sdkVersion;
        argsInfo.channel = AlibcConfig.getInstance().getChannel();
        return JSON.toJSONString(argsInfo);
    }

    public String getDefaultUserTrackerId() {
        return AlibcContext.getUtdid();
    }

    public void init() {
        UserTrackerCompoment.startInitTimeRecord();
        this.c = AlibcContext.getAppKey();
        this.b = AlibcContext.context;
        try {
            UTAnalytics.getInstance().setContext(this.b);
            UTAnalytics.getInstance().setAppApplicationInstance((Application) this.b.getApplicationContext());
            UTAnalytics.getInstance().setChannel(AlibcConfig.getInstance().getChannel());
            UTAnalytics.getInstance().setRequestAuthentication(new UTSecuritySDKRequestAuthentication(this.c, ""));
            if (AlibcContext.isDebugMode) {
                UTAnalytics.getInstance().turnOnDebug();
            }
            UTAnalytics.getInstance().turnOffAutoPageTrack();
            if (AlibcContext.isDebugMode) {
                AppMonitor.enableLog(true);
                Map hashMap = new HashMap();
                hashMap.put("debug_api_url", "http://muvp.alibaba-inc.com/online/UploadRecords.do");
                hashMap.put("debug_key", "fhx");
                UTTeamWork.getInstance().turnOnRealTimeDebug(hashMap);
            }
            UTAnalytics.getInstance().getTracker(UserTrackerConstants.TRACKER_ID).setGlobalProperty("sdk_version", AlibcContext.sdkVersion);
        } catch (Exception e) {
        } catch (Throwable th) {
        }
        a();
        UserTrackerCompoment.endInitTimeRecord();
    }

    public void registerPerformancePoint(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet) {
        if (str == null || str2 == null || measureSet == null || dimensionSet == null) {
            a.a(a, "registerPerformancePoint", "module/monitorPoint/measureSet/dimensionSet is null!");
        } else {
            AppMonitor.register(str, str2, measureSet, dimensionSet);
        }
    }

    public void sendCustomHit(String str, int i, String str2, long j, String str3, Map map) {
        UTAnalytics.getInstance().getTracker(UserTrackerConstants.TRACKER_ID).send(new UTOriginalCustomHitBuilder(str3, i, str, str2, String.valueOf(j), map).build());
    }

    public void sendCustomHit(String str, int i, String str2, String str3, String str4, Map map) {
        UTAnalytics.getInstance().getTracker(UserTrackerConstants.TRACKER_ID).send(new UTOriginalCustomHitBuilder(str, i, str2, str3, str4, map).build());
    }

    public void sendCustomHit(String str, long j, String str2, Map map) {
        AlibcLogger.d(a, "调用ut打点，传入参数信息为：label=" + str + " time=" + j + " page=" + str2 + " prop=" + map);
        UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder(str);
        uTCustomHitBuilder.setDurationOnEvent(j);
        uTCustomHitBuilder.setEventPage(str2);
        uTCustomHitBuilder.setProperties(map);
        Map build = uTCustomHitBuilder.build();
        AlibcLogger.d(a, "调用ut打点，经过UTHitBuilders构造后参数为：map=" + build);
        UTAnalytics.getInstance().getTracker(UserTrackerConstants.TRACKER_ID).send(build);
    }

    public void sendCustomHit(String str, Activity activity) {
        sendCustomHit(str, 60, activity != null ? activity.getTitle().toString() : str, null);
    }

    public void sendCustomHit(String str, Activity activity, Map map) {
        sendCustomHit(str, 60, activity != null ? activity.getTitle().toString() : str, map);
    }

    public void sendCustomHit(String str, String str2, Map map) {
        sendCustomHit(str, 60, str2, map);
    }

    public void sendInitHit4DAU() {
        UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("80001");
        Map hashMap = new HashMap();
        hashMap.put("model", AlibcConstants.TRADE_GROUP);
        hashMap.put(GameAppOperation.QQFAV_DATALINE_VERSION, AlibcContext.sdkVersion);
        uTCustomHitBuilder.setProperties(hashMap);
        UTAnalytics.getInstance().getTracker(Constants.VIA_ACT_TYPE_NINETEEN).send(uTCustomHitBuilder.build());
    }

    public void sendPerfomancePoint(String str, String str2, DimensionValueSet dimensionValueSet, MeasureValueSet measureValueSet) {
        if (str == null || str2 == null || measureValueSet == null || dimensionValueSet == null) {
            a.a(a, "sendPerfomancePoint", "module/monitorPoint/measureValueSet/dimensionValueSet is null!");
            AlibcLogger.e(a, "sendPerfomancePoint:module/monitorPoint/measureValueSet/dimensionValueSet is null!");
            return;
        }
        AppMonitor$Stat.commit(str, str2, dimensionValueSet, measureValueSet);
    }

    public void sendUseabilityFailure(String str, String str2, String str3, String str4) {
        if (str == null || str2 == null || str3 == null || str4 == null) {
            a.a(a, "sendUseabilityFailure", "module/monitorPoint/errorCode/errorMsg is null!");
        } else {
            Alarm.commitFail(str, str2, getArgs(), str3, str4);
        }
    }

    public void sendUseabilitySuccess(String str, String str2) {
        if (str == null || str2 == null) {
            a.a(a, "sendUseabilitySuccess", "module/monitorPoint is null!");
        } else {
            Alarm.commitSuccess(str, str2, getArgs());
        }
    }

    public void setSampling() {
        int appMonitorSampling = AlibcConfig.getInstance().getAppMonitorSampling();
        AppMonitor.setSampling(appMonitorSampling);
        AppMonitor$Stat.setSampling(appMonitorSampling);
    }

    public void updateUserTrackerProperties(Map map) {
        if (map != null) {
            String str;
            if (map.containsKey(UserTrackerConstants.USER_NICK)) {
                str = (String) map.get(UserTrackerConstants.USER_NICK);
                UTAnalytics.getInstance().userRegister(str);
                if (map.containsKey(UserTrackerConstants.USER_ID)) {
                    String str2 = (String) map.get(UserTrackerConstants.USER_ID);
                    UTAnalytics instance = UTAnalytics.getInstance();
                    if (str2 == null) {
                        str2 = str;
                    }
                    instance.updateUserAccount(str, str2);
                }
            }
            try {
                str = (String) map.get("app_version");
                if (str != null && !str.equals(this.d)) {
                    this.d = str;
                    UTAnalytics.getInstance().setAppVersion(str);
                }
            } catch (Exception e) {
            } catch (Throwable th) {
            }
        }
    }
}
