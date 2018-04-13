package com.meizu.cloud.pushsdk.handler.impl.notification;

import android.content.Context;
import android.content.Intent;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.AbstractAppLogicListener;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.impl.AbstractMessageHandler;
import com.meizu.cloud.pushsdk.handler.impl.model.NotificationState;
import com.meizu.cloud.pushsdk.notification.PushNotification;
import com.meizu.cloud.pushsdk.util.UxIPUtils;

public class NotificationStateMessageHandler extends AbstractMessageHandler<NotificationState> {
    public NotificationStateMessageHandler(Context context, AbstractAppLogicListener abstractAppLogicListener) {
        super(context, abstractAppLogicListener);
    }

    protected NotificationState getMessage(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.NOTIFICATION_EXTRA_SHOW_PACKAGE_NAME);
        String stringExtra2 = intent.getStringExtra(PushConstants.NOTIFICATION_EXTRA_TASK_ID);
        String stringExtra3 = intent.getStringExtra(PushConstants.NOTIFICATION_EXTRA_SEQ_ID);
        String stringExtra4 = intent.getStringExtra(PushConstants.NOTIFICATION_EXTRA_DEVICE_ID);
        String stringExtra5 = intent.getStringExtra(PushConstants.NOTIFICATION_EXTRA_PUSH_TIMESTAMP);
        String stringExtra6 = intent.getStringExtra(PushConstants.MZ_PUSH_NOTIFICATION_STATE_MESSAGE);
        a.a("AbstractMessageHandler", "current taskId " + stringExtra2 + " seqId " + stringExtra3 + " deviceId " + stringExtra4 + " packageName " + stringExtra);
        NotificationState notificationState = new NotificationState(MessageV3.parse(context().getPackageName(), stringExtra, stringExtra5, stringExtra4, stringExtra2, stringExtra3, stringExtra6));
        String stringExtra7 = intent.getStringExtra("flyme:notification_pkg");
        int intExtra = intent.getIntExtra("flyme:notification_id", 0);
        int intExtra2 = intent.getIntExtra("flyme:notification_state", 0);
        notificationState.setNotificationId(intExtra);
        notificationState.setNotificationPkg(stringExtra7);
        notificationState.setState(intExtra2);
        return notificationState;
    }

    protected void unsafeSend(NotificationState notificationState, PushNotification pushNotification) {
    }

    public boolean messageMatch(Intent intent) {
        a.a("AbstractMessageHandler", "start NotificationStateMessageHandler match");
        return PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_STATE.equals(getIntentMethod(intent));
    }

    public int getProcessorType() {
        return 32768;
    }

    protected void onBeforeEvent(NotificationState notificationState) {
        switch (notificationState.getState()) {
            case -2:
                a.d("AbstractMessageHandler", "notification STATE_NOTIFICATION_SHOW_ACCESS_DENY");
                UxIPUtils.onNoShowPushMessage(context(), notificationState.getMessageV3().getUploadDataPackageName(), notificationState.getMessageV3().getDeviceId(), notificationState.getMessageV3().getTaskId(), notificationState.getMessageV3().getSeqId(), notificationState.getMessageV3().getPushTimestamp());
                return;
            case -1:
                a.d("AbstractMessageHandler", "notification STATE_NOTIFICATION_SHOW_INBOX");
                UxIPUtils.onShowInBoxPushMessage(context(), notificationState.getMessageV3().getUploadDataPackageName(), notificationState.getMessageV3().getDeviceId(), notificationState.getMessageV3().getTaskId(), notificationState.getMessageV3().getSeqId(), notificationState.getMessageV3().getPushTimestamp());
                return;
            case 0:
                a.d("AbstractMessageHandler", "notification STATE_NOTIFICATION_SHOW_NORMAL");
                return;
            case 1:
                a.d("AbstractMessageHandler", "notification STATE_NOTIFICATION_SHOW_FLOAT");
                return;
            default:
                return;
        }
    }
}
