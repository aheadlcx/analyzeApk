package com.umeng.analytics.dplus;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.statistics.common.MLog;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class UMADplus {
    public static void track(Context context, String str) {
        if (AnalyticsConfig.FLAG_DPLUS) {
            MobclickAgent.getAgent().a(context, str, null);
        } else {
            MLog.e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        }
    }

    public static void track(Context context, String str, Map<String, Object> map) {
        if (AnalyticsConfig.FLAG_DPLUS) {
            if (map == null || map.size() <= 0) {
                MLog.e("the map is null!");
            }
            MobclickAgent.getAgent().a(context, str, (Map) map);
            return;
        }
        MLog.e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
    }

    public static void registerSuperProperty(Context context, String str, Object obj) {
        if (AnalyticsConfig.FLAG_DPLUS) {
            MobclickAgent.getAgent().a(context, str, obj);
        } else {
            MLog.e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        }
    }

    public static void unregisterSuperProperty(Context context, String str) {
        if (AnalyticsConfig.FLAG_DPLUS) {
            MobclickAgent.getAgent().d(context, str);
        } else {
            MLog.e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        }
    }

    public static Object getSuperProperty(Context context, String str) {
        if (AnalyticsConfig.FLAG_DPLUS) {
            return MobclickAgent.getAgent().e(context, str);
        }
        MLog.e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        return null;
    }

    public static String getSuperProperties(Context context) {
        if (AnalyticsConfig.FLAG_DPLUS) {
            return MobclickAgent.getAgent().d(context);
        }
        MLog.e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        return null;
    }

    public static void clearSuperProperties(Context context) {
        if (AnalyticsConfig.FLAG_DPLUS) {
            MobclickAgent.getAgent().e(context);
        } else {
            MLog.e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        }
    }

    public static void setFirstLaunchEvent(Context context, List<String> list) {
        if (AnalyticsConfig.FLAG_DPLUS) {
            MobclickAgent.getAgent().a(context, (List) list);
        } else {
            MLog.e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        }
    }

    public static void registerPreProperties(Context context, JSONObject jSONObject) {
        if (AnalyticsConfig.FLAG_DPLUS) {
            MobclickAgent.getAgent().a(context, jSONObject);
        } else {
            MLog.e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        }
    }

    public static void unregisterPreProperty(Context context, String str) {
        if (AnalyticsConfig.FLAG_DPLUS) {
            MobclickAgent.getAgent().f(context, str);
        } else {
            MLog.e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        }
    }

    public static void clearPreProperties(Context context) {
        if (AnalyticsConfig.FLAG_DPLUS) {
            MobclickAgent.getAgent().f(context);
        } else {
            MLog.e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        }
    }

    public static JSONObject getPreProperties(Context context) {
        if (AnalyticsConfig.FLAG_DPLUS) {
            return MobclickAgent.getAgent().g(context);
        }
        MLog.e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        return null;
    }
}
