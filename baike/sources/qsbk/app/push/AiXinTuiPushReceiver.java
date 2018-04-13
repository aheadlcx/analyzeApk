package qsbk.app.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.baidu.mobstat.StatService;
import com.ixintui.pushsdk.SdkConstants;
import qsbk.app.AppStat;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.LogUtil;

public class AiXinTuiPushReceiver extends BroadcastReceiver {
    public PushMessageProcessor processor = new PushMessageProcessor();

    public void onReceive(Context context, Intent intent) {
        AppStat.reportAppStart("aixintui_push");
        String action = intent.getAction();
        if (action.equals(SdkConstants.MESSAGE_ACTION)) {
            action = intent.getStringExtra(SdkConstants.MESSAGE);
            String stringExtra = intent.getStringExtra(SdkConstants.ADDITION);
            LogUtil.d("msg:" + action);
            LogUtil.d("extra:" + stringExtra);
            this.processor.processMessage(new CommPushMessage(action), context);
        } else if (action.equals(SdkConstants.RESULT_ACTION)) {
            action = intent.getStringExtra(SdkConstants.COMMAND);
            int intExtra = intent.getIntExtra(SdkConstants.CODE, 0);
            String stringExtra2 = intent.getStringExtra(SdkConstants.ADDITION);
            if (intExtra != 0) {
                LogUtil.d("command is: " + action + " result error: " + intent.getStringExtra(SdkConstants.ERROR));
            } else {
                LogUtil.d("command is: " + action + " result ok");
                if ("register".equals(action)) {
                    PushMessageManager.notifyPushBindServer("start", stringExtra2, "ixintui");
                    String osArch = DeviceUtils.getOsArch();
                    if (!TextUtils.isEmpty(osArch)) {
                        LogUtil.d("osArch:" + osArch);
                        StatService.onEvent(AppContext.getContext(), "arch", osArch);
                    }
                }
            }
            if (stringExtra2 != null) {
                LogUtil.d("result extra: " + stringExtra2);
            }
            LogUtil.d("result:command is: " + action + " result code: " + intExtra + " extra:" + stringExtra2 + " error:" + intent.getStringExtra(SdkConstants.ERROR));
        } else if (action.equals(SdkConstants.NOTIFICATION_CLICK_ACTION)) {
            LogUtil.d("notification click received, msg is: " + intent.getStringExtra(SdkConstants.MESSAGE));
        }
    }
}
