package com.meizu.cloud.pushsdk;

import android.content.Context;
import android.content.Intent;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.base.IntentReceiver;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;
import com.meizu.cloud.pushsdk.util.UxIPUtils;

public abstract class MzPushMessageReceiver extends IntentReceiver {
    public static final String TAG = "MzPushMessageReceiver";

    public abstract void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus);

    @Deprecated
    public abstract void onRegister(Context context, String str);

    public abstract void onRegisterStatus(Context context, RegisterStatus registerStatus);

    public abstract void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus);

    public abstract void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus);

    @Deprecated
    public abstract void onUnRegister(Context context, boolean z);

    public abstract void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus);

    public void onReceive(Context context, Intent intent) {
        try {
            super.onReceive(context, intent);
        } catch (Exception e) {
            a.d(TAG, "Event core error " + e.getMessage());
            UxIPUtils.onRecordMessageFlow(context, context.getPackageName(), null, null, PushManager.TAG, "MzPushMessageReceiver " + e.getMessage(), 3000);
        }
    }

    public void onHandleIntent(Context context, Intent intent) {
        PushMessageProxy.with(context).receiverListener(TAG, new MzPushMessageReceiver$1(this)).processMessage(intent);
    }

    public void onMessage(Context context, String str) {
    }

    public void onMessage(Context context, String str, String str2) {
    }

    public void onMessage(Context context, Intent intent) {
    }

    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
    }

    public void onNotificationClicked(Context context, String str, String str2, String str3) {
    }

    public void onNotificationArrived(Context context, String str, String str2, String str3) {
    }

    public void onNotificationDeleted(Context context, String str, String str2, String str3) {
    }

    public void onNotifyMessageArrived(Context context, String str) {
    }
}
