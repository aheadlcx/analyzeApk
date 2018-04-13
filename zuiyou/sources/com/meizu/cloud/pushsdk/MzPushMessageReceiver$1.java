package com.meizu.cloud.pushsdk;

import android.content.Context;
import android.content.Intent;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.handler.AbstractAppLogicListener;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

class MzPushMessageReceiver$1 extends AbstractAppLogicListener {
    final /* synthetic */ MzPushMessageReceiver this$0;

    MzPushMessageReceiver$1(MzPushMessageReceiver mzPushMessageReceiver) {
        this.this$0 = mzPushMessageReceiver;
    }

    public void onRegister(Context context, String str) {
        a.a(MzPushMessageReceiver.TAG, "onRegister " + str);
        this.this$0.onRegister(context, str);
    }

    public void onMessage(Context context, String str) {
        this.this$0.onMessage(context, str);
        a.a(MzPushMessageReceiver.TAG, "receive message " + str);
    }

    public void onMessage(Context context, String str, String str2) {
        this.this$0.onMessage(context, str, str2);
        a.a(MzPushMessageReceiver.TAG, "receive message " + str + " platformExtra " + str2);
    }

    public void onUnRegister(Context context, boolean z) {
        a.a(MzPushMessageReceiver.TAG, "onUnRegister " + z);
        this.this$0.onUnRegister(context, z);
    }

    public void onMessage(Context context, Intent intent) {
        a.a(MzPushMessageReceiver.TAG, "onMessage Flyme3 " + intent);
        this.this$0.onMessage(context, intent);
    }

    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
        this.this$0.onUpdateNotificationBuilder(pushNotificationBuilder);
    }

    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {
        a.a(MzPushMessageReceiver.TAG, "onPushStatus " + pushSwitchStatus);
        this.this$0.onPushStatus(context, pushSwitchStatus);
    }

    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        a.a(MzPushMessageReceiver.TAG, "onRegisterStatus " + registerStatus);
        this.this$0.onRegisterStatus(context, registerStatus);
    }

    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
        a.a(MzPushMessageReceiver.TAG, "onUnRegisterStatus " + unRegisterStatus);
        this.this$0.onUnRegisterStatus(context, unRegisterStatus);
    }

    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {
        a.a(MzPushMessageReceiver.TAG, "onSubTagsStatus " + subTagsStatus);
        this.this$0.onSubTagsStatus(context, subTagsStatus);
    }

    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {
        a.a(MzPushMessageReceiver.TAG, "onSubAliasStatus " + subAliasStatus);
        this.this$0.onSubAliasStatus(context, subAliasStatus);
    }

    public void onNotificationClicked(Context context, String str, String str2, String str3) {
        a.a(MzPushMessageReceiver.TAG, "onNotificationClicked title " + str + "content " + str2 + " selfDefineContentString " + str3);
        this.this$0.onNotificationClicked(context, str, str2, str3);
    }

    public void onNotificationArrived(Context context, String str, String str2, String str3) {
        a.a(MzPushMessageReceiver.TAG, "onNotificationArrived title " + str + "content " + str2 + " selfDefineContentString " + str3);
        this.this$0.onNotificationArrived(context, str, str2, str3);
    }

    public void onNotificationDeleted(Context context, String str, String str2, String str3) {
        a.a(MzPushMessageReceiver.TAG, "onNotificationDeleted title " + str + "content " + str2 + " selfDefineContentString " + str3);
        this.this$0.onNotificationDeleted(context, str, str2, str3);
    }

    public void onNotifyMessageArrived(Context context, String str) {
        a.a(MzPushMessageReceiver.TAG, "onNotifyMessageArrived " + str);
        this.this$0.onNotifyMessageArrived(context, str);
    }
}
