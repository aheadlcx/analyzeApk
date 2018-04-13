package qsbk.app.game;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import com.baidu.mobstat.StatService;
import java.io.File;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.model.EventWindow;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.PackageUtil;

public class GamePlugin extends Plugin {
    public static final String DOWNLOAD = "download";
    public static final String GAME_STATUS_CAN_UPDATE = "can_update";
    public static final String GAME_STATUS_DOWNLOADED = "downloaded";
    public static final String GAME_STATUS_DOWNLOADING = "downloading";
    public static final String GAME_STATUS_DOWNLOAD_SUSPEND = "download_suspend";
    public static final String GAME_STATUS_INSTALLED = "installed";
    public static final String GAME_STATUS_UNINSTALLED = "uninstalled";
    public static final String GET_STATUS = "get_status";
    public static final String INSTALL_APP = "install_app";
    public static final String KEY_STATUS = "status";
    public static final String OPEN_APP = "open_app";

    public static JSONObject getDownloadStateObject(String str, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("download_url", str);
            if (z) {
                jSONObject.put("status", GAME_STATUS_DOWNLOADED);
            } else {
                jSONObject.put("status", GAME_STATUS_DOWNLOAD_SUSPEND);
            }
        } catch (Exception e) {
        }
        return jSONObject;
    }

    public static JSONObject getDownloadingStateObject(String str, long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("download_url", str);
            jSONObject.put("status", GAME_STATUS_DOWNLOADING);
            jSONObject.put("current", j);
            jSONObject.put("total", j2);
        } catch (Exception e) {
        }
        return jSONObject;
    }

    public static void installApp(String str) {
        File file = new File(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        QsbkApp.mContext.startActivity(intent);
    }

    public static boolean openAndroidAppByPackage(String str) {
        try {
            Intent launchIntentForPackage = QsbkApp.mContext.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                throw new NameNotFoundException();
            }
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
            launchIntentForPackage.setFlags(ClientDefaults.MAX_MSG_SIZE);
            QsbkApp.mContext.startActivity(launchIntentForPackage);
            return true;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void onDestroy() {
    }

    public JSONObject getPackageStatus(String str, int i, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        PackageInfo pacageInfo = PackageUtil.getPacageInfo(str);
        if (pacageInfo == null) {
            DownloadUnit downloadUnit = ContinueDownloader.instance().getDownloadUnit(str2);
            if (downloadUnit == null) {
                jSONObject.put("status", GAME_STATUS_UNINSTALLED);
            } else if (downloadUnit.isCompleted()) {
                if (downloadUnit.checkFileFinished()) {
                    jSONObject.put("status", GAME_STATUS_DOWNLOADED);
                } else {
                    jSONObject.put("status", GAME_STATUS_UNINSTALLED);
                }
            } else if (downloadUnit.isDownloading()) {
                jSONObject.put("status", GAME_STATUS_DOWNLOADING);
                jSONObject.put("current", downloadUnit.a);
                jSONObject.put("total", downloadUnit.getTotal());
            } else {
                jSONObject.put("status", GAME_STATUS_DOWNLOAD_SUSPEND);
            }
        } else if (pacageInfo.versionCode >= i) {
            jSONObject.put("status", GAME_STATUS_INSTALLED);
        } else {
            jSONObject.put("status", GAME_STATUS_CAN_UPDATE);
        }
        return jSONObject;
    }

    private void a(String str) {
        StatService.onEvent(QsbkApp.mContext, EventWindow.JUMP_GAME, str);
    }

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        LogUtil.d("game action:" + str);
        if (GET_STATUS.equalsIgnoreCase(str)) {
            JSONArray optJSONArray = jSONObject.optJSONArray("apps");
            JSONObject jSONObject2 = new JSONObject();
            if (optJSONArray == null) {
                callback.sendResult(1, "params in apps is not valid", "");
                return;
            }
            int i = 0;
            while (i < optJSONArray.length()) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                String optString = optJSONObject.optString("package_name");
                int optInt = optJSONObject.optInt("version", -1);
                Object optString2 = optJSONObject.optString("download_url");
                if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2) || optInt == -1) {
                    callback.sendResult(1, "params in apps is not valid", "");
                    return;
                } else {
                    jSONObject2.put(optString, getPackageStatus(optString, optInt, optString2));
                    i++;
                }
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("apps", jSONObject2);
            callback.sendResult(jSONObject3, 0, null);
        } else if ("download".equals(str)) {
            Object optString3 = jSONObject.optString("download_url");
            String optString4 = jSONObject.optString("app_name");
            if (TextUtils.isEmpty(optString3)) {
                callback.sendResult(1, "can't find params download_url", "");
                return;
            }
            a("download_" + optString4);
            ContinueDownloader.instance().download(optString3, optString4, null, false);
            callback.sendResult(0, null, "");
        } else if (OPEN_APP.equals(str)) {
            r0 = jSONObject.optString("package_name");
            if (TextUtils.isEmpty(r0)) {
                callback.sendResult(1, "传入的包名错误", "");
                return;
            }
            a("open_app_" + r0);
            openAndroidAppByPackage(r0);
            callback.sendResult(0, null, "");
        } else if (INSTALL_APP.equals(str)) {
            DownloadUnit downloadUnit = ContinueDownloader.instance().getDownloadUnit(jSONObject.optString("download_url"));
            if (downloadUnit != null) {
                a("install_" + downloadUnit.getName());
                r0 = downloadUnit.getTmpFile();
                if (new File(r0).exists()) {
                    installApp(r0);
                    callback.sendResult(0, null, "");
                    return;
                }
            }
            callback.sendResult(1, "找不到安装包", "");
        } else {
            callback.sendResult(1, str + " is not exist...", "");
        }
    }
}
