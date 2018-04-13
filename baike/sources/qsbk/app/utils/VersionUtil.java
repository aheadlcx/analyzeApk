package qsbk.app.utils;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Message;
import android.text.TextUtils;
import java.net.URLEncoder;
import org.json.JSONObject;
import qsbk.app.ConfigManager;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;

public class VersionUtil {
    public static final String LATEST_CHECK_UPDATE_TIME = "latestCheckUpdateTime";
    private static String a = "";

    private static boolean a() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(LATEST_CHECK_UPDATE_TIME);
        long j = 0;
        if (!TextUtils.isEmpty(sharePreferencesValue)) {
            j = Long.valueOf(sharePreferencesValue).longValue();
        }
        if (j + 86400000 >= System.currentTimeMillis()) {
            return false;
        }
        SharePreferenceUtils.setSharePreferencesValue(LATEST_CHECK_UPDATE_TIME, String.valueOf(System.currentTimeMillis()));
        return true;
    }

    public static boolean manualCheck(Context context) {
        try {
            getServiceVersion(context, true);
            if (Constants.serverVersion > Constants.localVersion) {
                return true;
            }
        } catch (Exception e) {
            DebugUtil.error("版本更新检查失败");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isNeedUpdate(Context context) {
        try {
            if (HttpUtils.isWifi(context)) {
                getServiceVersion(context, false);
                if (Constants.serverVersion > Constants.localVersion) {
                    return true;
                }
            } else if (a()) {
                getServiceVersion(context, false);
                if (Constants.serverVersion > Constants.localVersion) {
                    return true;
                }
            }
        } catch (Exception e) {
            DebugUtil.error("版本更新检查失败");
            e.printStackTrace();
        }
        return false;
    }

    public static void initLocalVersion(Context context) {
        String str = "";
        try {
            int i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            Constants.localVersion = i;
            Constants.localVersionName = str2;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getServiceVersion(Context context, boolean z) throws Exception {
        String encode = URLEncoder.encode(Constants.localVersionName);
        String encode2 = URLEncoder.encode(Build.MODEL);
        String channel = ConfigManager.getInstance().getChannel();
        String encode3 = URLEncoder.encode(String.format("%s|%s|%s|%s", new Object[]{Build.MODEL, Build.BRAND, Build.DEVICE, Build.DISPLAY}));
        String encode4 = URLEncoder.encode(String.format("%s_%s", new Object[]{Integer.valueOf(context.getResources().getDisplayMetrics().widthPixels), Integer.valueOf(context.getResources().getDisplayMetrics().heightPixels)}));
        StringBuffer stringBuffer = new StringBuffer(Constants.APPINFO);
        stringBuffer.append("?os=").append(VERSION.SDK_INT);
        stringBuffer.append("&av=").append(Constants.localVersion);
        stringBuffer.append("&an=").append(encode);
        stringBuffer.append("&mb=").append(encode2);
        stringBuffer.append("&c=").append(channel);
        stringBuffer.append("&di=").append(encode3);
        stringBuffer.append("&s=").append(encode4);
        Object obj = HttpClient.getIntentce().get(stringBuffer.toString());
        if (!TextUtils.isEmpty(obj)) {
            JSONObject jSONObject = new JSONObject(obj);
            Constants.serverVersion = jSONObject.getInt("build");
            Constants.serviceVersionName = jSONObject.getString("version");
            Constants.change = jSONObject.getString("change");
            if (!jSONObject.isNull("url")) {
                Constants.UPDATE_URL = jSONObject.getString("url");
            }
            if (!z && !jSONObject.isNull("_logs")) {
                a = jSONObject.getString("_logs");
                if (!TextUtils.isEmpty(a)) {
                    QsbkApp.reportAppInfo();
                    Message obtainMessage = QsbkApp.reportHandler.obtainMessage();
                    obtainMessage.obj = a;
                    obtainMessage.sendToTarget();
                }
            }
        }
    }
}
