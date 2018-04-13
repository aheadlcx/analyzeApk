package qsbk.app.service;

import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.sdk.util.h;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.model.AppInfo;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.Md5;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.XOR;

public class ReportHandler extends Handler {
    public static final String LATEST_REPORT_TIME = "latestReportTime";

    public ReportHandler(Looper looper) {
        super(looper);
    }

    private static boolean a() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(LATEST_REPORT_TIME);
        long j = 0;
        if (!TextUtils.isEmpty(sharePreferencesValue)) {
            j = Long.valueOf(sharePreferencesValue).longValue();
        }
        if (j + 259200000 >= System.currentTimeMillis()) {
            return false;
        }
        SharePreferenceUtils.setSharePreferencesValue(LATEST_REPORT_TIME, String.valueOf(System.currentTimeMillis()));
        return true;
    }

    public void handleMessage(Message message) {
        try {
            JSONObject jSONObject = new JSONObject((String) message.obj);
            JSONArray names = jSONObject.names();
            if (a()) {
                for (int i = 0; i < names.length(); i++) {
                    String string = names.getString(i);
                    Object obj = "";
                    if (string.equals("001")) {
                        obj = b(jSONObject.getString(string));
                    } else if (string.equals("002")) {
                        obj = a(jSONObject.getString(string));
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("id", string);
                    jSONObject2.put("data", obj);
                    try {
                        HttpClient.getIntentce().post(Constants.APP_LOGS, jSONObject2.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private String a(String str) {
        return XOR.encode(HttpUtils.getTrafficStats(), Md5.MD5(str + DeviceUtils.getAndroidId()).toUpperCase()).replaceAll("\\s", "");
    }

    private String b(String str) {
        return XOR.encode(b(), Md5.MD5(str + DeviceUtils.getAndroidId()).toUpperCase()).replaceAll("\\s", "");
    }

    private String b() {
        StringBuffer stringBuffer = new StringBuffer();
        List installedPackages = QsbkApp.mContext.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
            if ((packageInfo.applicationInfo.flags & 1) == 0) {
                stringBuffer.append(new AppInfo(packageInfo.applicationInfo.loadLabel(QsbkApp.mContext.getPackageManager()).toString(), packageInfo.packageName, packageInfo.versionName).toString());
                stringBuffer.append(h.b);
            }
        }
        return stringBuffer.toString();
    }
}
