package qsbk.app.push;

import android.content.Context;
import com.ixintui.pushsdk.PushSdkApi;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;
import qsbk.app.ConfigManager;
import qsbk.app.Constants;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.MiUiUtils;

public class PushTest {
    public static void initPushTest(Context context) {
        if (isIXinTuiOn()) {
            LogUtil.d("init aixin push");
            PushSdkApi.register(context, 1249315072, ConfigManager.getInstance().getChannel(), Constants.localVersionName);
        }
    }

    public static void initLogger(Context context) {
        Logger.setLogger(context, new k());
    }

    public static void initMiPush(Context context) {
        if (!MiUiUtils.isMiUiSystemPushEnable()) {
            return;
        }
        if (PushMessageManager.getReceiveMsgFromLocal()) {
            initLogger(context);
            MiPushClient.registerPush(context, "2000265", "530200072265");
            LogUtil.d("init mipush client");
            return;
        }
        MiPushClient.unregisterPush(context);
        LogUtil.d("unregister mipush client");
    }

    public static char getLastChar() {
        String androidId = DeviceUtils.getAndroidId();
        LogUtil.d("deviceId:" + androidId);
        char charAt = androidId.charAt(androidId.length() - 1);
        LogUtil.d("lastChar:" + charAt);
        return charAt;
    }

    public static boolean isXiaoMiOn() {
        return true;
    }

    public static boolean isBaiduOn() {
        return isIXinTuiOn() || isXiaoMiOn();
    }

    public static boolean isIXinTuiOn() {
        return false;
    }
}
