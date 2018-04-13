package cn.xiaochuan.push.meizu;

import android.content.Context;
import android.support.annotation.Nullable;
import cn.xiaochuan.push.a;
import cn.xiaochuan.push.e;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.izuiyou.a.a.b;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

public class MeizuMessageReceiver extends MzPushMessageReceiver {
    public void onNotifyMessageArrived(Context context, String str) {
        super.onNotifyMessageArrived(context, str);
        b.b("Meizu", "message:" + str);
        a.a().a(2, "mz", a(str));
    }

    public void onNotificationArrived(Context context, String str, String str2, String str3) {
        b.b("Meizu", "title:" + str + "  content:" + str2 + "  selfDefineContentString:" + str3);
        a.a().a(2, "mz", a(str3));
    }

    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
        pushNotificationBuilder.setmLargIcon(cn.xc_common.push.a.a.mipush_notification);
        pushNotificationBuilder.setmStatusbarIcon(cn.xc_common.push.a.a.mz_push_notification_small_icon);
        b.b("Meizu", "onUpdateNotificationBuilder");
    }

    public void onNotificationClicked(Context context, String str, String str2, String str3) {
        a.a().a(3, "mz", a(str3));
    }

    public void onNotificationDeleted(Context context, String str, String str2, String str3) {
        a.a().a(4, "mz", a(str3));
    }

    @Nullable
    private e a(String str) {
        try {
            b.b("Meizu", str);
            JSONObject parseObject = JSON.parseObject(str);
            e a = e.a(parseObject);
            a.l = "mz";
            a.k = parseObject;
            a.e = true;
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onRegister(Context context, String str) {
        b.b("Meizu", "onRegister:" + str);
        a.a().a("mz", str);
    }

    public void onUnRegister(Context context, boolean z) {
        b.b("Meizu", "onUnRegister:" + z);
    }

    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {
        b.b("Meizu", "onPushStatus:" + pushSwitchStatus);
    }

    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        b.b("Meizu", "onRegisterStatus:" + registerStatus);
    }

    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
        b.b("Meizu", "onUnRegisterStatus:" + unRegisterStatus);
    }

    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {
        b.b("Meizu", "onSubTagsStatus:" + subTagsStatus);
    }

    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {
        b.b("Meizu", "onSubAliasStatus:" + subAliasStatus);
    }
}
