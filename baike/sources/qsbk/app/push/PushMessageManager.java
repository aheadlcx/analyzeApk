package qsbk.app.push;

import android.content.Context;
import android.text.TextUtils;
import com.ixintui.pushsdk.PushSdkApi;
import qsbk.app.ConfigManager;
import qsbk.app.Constants;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.image.issue.TaskExecutor;

public class PushMessageManager {
    public static final String RECEIVE_MESSAGE = "receiveMessage";
    public static boolean isStarted = false;

    public static void initIXinTui(Context context) {
        LogUtil.d("init aixin push");
        PushSdkApi.register(context, 1249315072, ConfigManager.getInstance().getChannel(), Constants.localVersionName);
    }

    public static boolean getReceiveMsgFromLocal() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(RECEIVE_MESSAGE);
        LogUtil.d("lcoal:" + sharePreferencesValue);
        return TextUtils.isEmpty(sharePreferencesValue) || "start".equalsIgnoreCase(sharePreferencesValue);
    }

    public static void updateRecvMsg(boolean z, Context context) {
        if (getReceiveMsgFromLocal() == z) {
            LogUtil.d("is the same,and didn't change");
        } else {
            SharePreferenceUtils.setSharePreferencesValue(RECEIVE_MESSAGE, z ? "start" : "stop");
        }
    }

    public static void setLastBindedPushVersion(String str) {
        SharePreferenceUtils.setSharePreferencesValue("push_bind_suc_version", str);
    }

    public static void registerPushTestSDK(String str, String str2) {
        TaskExecutor.getInstance().addTask(new a(str2, str));
    }

    public static void sendPushTestReply(String str, int i) {
        TaskExecutor.getInstance().addTask(new c(str, i));
    }

    public static void notifyPushBindServer(String str, String str2, String str3) {
        String str4 = str3 + "|" + str2;
        setDevicePushToken(str4);
        TaskExecutor.getInstance().addTask(new e(str, str4));
    }

    public static String getDevicePushToken() {
        return SharePreferenceUtils.getSharePreferencesValue("device_push_token");
    }

    public static void setDevicePushToken(String str) {
        SharePreferenceUtils.setSharePreferencesValue("device_push_token", str);
    }
}
