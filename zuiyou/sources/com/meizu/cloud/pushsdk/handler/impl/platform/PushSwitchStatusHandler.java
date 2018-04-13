package com.meizu.cloud.pushsdk.handler.impl.platform;

import android.content.Context;
import android.content.Intent;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.AbstractAppLogicListener;
import com.meizu.cloud.pushsdk.handler.impl.AbstractMessageHandler;
import com.meizu.cloud.pushsdk.notification.PushNotification;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;

public class PushSwitchStatusHandler extends AbstractMessageHandler<PushSwitchStatus> {
    public PushSwitchStatusHandler(Context context, AbstractAppLogicListener abstractAppLogicListener) {
        super(context, abstractAppLogicListener);
    }

    protected PushSwitchStatus getMessage(Intent intent) {
        return (PushSwitchStatus) intent.getSerializableExtra(PushConstants.EXTRA_APP_PUSH_SWITCH_STATUS);
    }

    protected void unsafeSend(PushSwitchStatus pushSwitchStatus, PushNotification pushNotification) {
        if (appLogicListener() != null && pushSwitchStatus != null) {
            appLogicListener().onPushStatus(context(), pushSwitchStatus);
        }
    }

    public boolean messageMatch(Intent intent) {
        a.a("AbstractMessageHandler", "start PushSwitchStatusHandler match");
        return PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_PUSH_STATUS.equals(getIntentMethod(intent));
    }

    public int getProcessorType() {
        return 256;
    }
}
