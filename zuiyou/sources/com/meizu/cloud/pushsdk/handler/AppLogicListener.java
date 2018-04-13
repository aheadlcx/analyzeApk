package com.meizu.cloud.pushsdk.handler;

import android.content.Context;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

public interface AppLogicListener {
    void onMessage(Context context, String str);

    void onMessage(Context context, String str, String str2);

    void onNotificationArrived(Context context, String str, String str2, String str3);

    void onNotificationClicked(Context context, String str, String str2, String str3);

    void onNotificationDeleted(Context context, String str, String str2, String str3);

    void onNotifyMessageArrived(Context context, String str);

    void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus);

    void onRegister(Context context, String str);

    void onRegisterStatus(Context context, RegisterStatus registerStatus);

    void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus);

    void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus);

    void onUnRegister(Context context, boolean z);

    void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus);

    void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder);
}
