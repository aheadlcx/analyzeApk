package com.meizu.cloud.pushsdk.handler.impl.platform;

import android.content.Context;
import android.content.Intent;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.AbstractAppLogicListener;
import com.meizu.cloud.pushsdk.handler.impl.AbstractMessageHandler;
import com.meizu.cloud.pushsdk.notification.PushNotification;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;

public class SubScribeTagsStatusHandler extends AbstractMessageHandler<SubTagsStatus> {
    public SubScribeTagsStatusHandler(Context context, AbstractAppLogicListener abstractAppLogicListener) {
        super(context, abstractAppLogicListener);
    }

    protected SubTagsStatus getMessage(Intent intent) {
        return (SubTagsStatus) intent.getSerializableExtra(PushConstants.EXTRA_APP_PUSH_SUBTAGS_STATUS);
    }

    protected void unsafeSend(SubTagsStatus subTagsStatus, PushNotification pushNotification) {
        if (appLogicListener() != null && subTagsStatus != null) {
            appLogicListener().onSubTagsStatus(context(), subTagsStatus);
        }
    }

    public boolean messageMatch(Intent intent) {
        a.a("AbstractMessageHandler", "start SubScribeTagsStatusHandler match");
        return PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_SUBTAGS_STATUS.equals(getIntentMethod(intent));
    }

    public int getProcessorType() {
        return 2048;
    }
}
