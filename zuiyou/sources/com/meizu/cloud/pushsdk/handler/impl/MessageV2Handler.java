package com.meizu.cloud.pushsdk.handler.impl;

import android.content.Context;
import android.content.Intent;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.AbstractAppLogicListener;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.MPushMessage;
import com.meizu.cloud.pushsdk.notification.PushNotification;
import com.meizu.cloud.pushsdk.util.UxIPUtils;

public class MessageV2Handler extends MessageV3Handler {
    public MessageV2Handler(Context context, AbstractAppLogicListener abstractAppLogicListener) {
        super(context, abstractAppLogicListener);
    }

    public boolean messageMatch(Intent intent) {
        a.a("AbstractMessageHandler", "start MessageV2Handler match");
        if (canReceiveMessage(0, getPushServiceDefaultPackageName(intent)) && PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_SHOW.equals(getIntentMethod(intent))) {
            return true;
        }
        return false;
    }

    public int getProcessorType() {
        return 2;
    }

    protected MessageV3 getMessage(Intent intent) {
        MPushMessage mPushMessage = (MPushMessage) intent.getSerializableExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE);
        return MessageV3.parse(getPushServiceDefaultPackageName(intent), getDeviceId(intent), mPushMessage.getTaskId(), mPushMessage);
    }

    protected void unsafeSend(MessageV3 messageV3, PushNotification pushNotification) {
        if (pushNotification != null) {
            pushNotification.show(messageV3);
            appLogicListener().onNotificationArrived(context(), messageV3.getTitle(), messageV3.getContent(), selfDefineContentString(messageV3.getWebUrl(), messageV3.getParamsMap()));
        }
    }

    protected void onBeforeEvent(MessageV3 messageV3) {
        UxIPUtils.onReceivePushMessageEvent(context(), messageV3.getUploadDataPackageName(), messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp());
    }

    protected void onAfterEvent(MessageV3 messageV3) {
        UxIPUtils.onShowPushMessageEvent(context(), messageV3.getUploadDataPackageName(), messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp());
    }
}
