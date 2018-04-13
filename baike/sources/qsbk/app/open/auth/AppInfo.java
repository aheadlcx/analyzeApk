package qsbk.app.open.auth;

import com.umeng.commonsdk.proguard.g;
import org.json.JSONObject;
import qsbk.app.utils.SharePreferenceUtils;

public class AppInfo {
    public static final String KEY_LOACL = "open.auth.app_info";
    public JSONObject mObject;

    public AppInfo(JSONObject jSONObject) {
        this.mObject = jSONObject;
    }

    public static AppInfo getAppInfoFromLocal(String str) {
        try {
            AppInfo appInfo = new AppInfo(new JSONObject(SharePreferenceUtils.getSharePreferencesValue("open.auth.app_info_" + str)));
            long currentTimeMillis = System.currentTimeMillis() - appInfo.getLocalCacheTime();
            return (currentTimeMillis < 0 || currentTimeMillis > 86400000) ? null : appInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveAppInfoToLocal(String str, AppInfo appInfo) {
        appInfo.setLocalCacheTime();
        SharePreferenceUtils.setSharePreferencesValue("open.auth.app_info_" + str, appInfo.mObject.toString());
    }

    public String getName() {
        return this.mObject.optString("app_name");
    }

    public String getIcon() {
        return this.mObject.optString("app_icon");
    }

    public String getSignatureMD5() {
        return this.mObject.optString(g.o);
    }

    public void setLocalCacheTime() {
        try {
            this.mObject.putOpt("local_cache_time", Long.valueOf(System.currentTimeMillis()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getLocalCacheTime() {
        return this.mObject.optLong("local_cache_time", 0);
    }
}
