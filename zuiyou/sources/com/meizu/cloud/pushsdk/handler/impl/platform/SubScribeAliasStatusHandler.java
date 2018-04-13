package com.meizu.cloud.pushsdk.handler.impl.platform;

import android.content.Context;
import android.content.Intent;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.AbstractAppLogicListener;
import com.meizu.cloud.pushsdk.handler.impl.AbstractMessageHandler;
import com.meizu.cloud.pushsdk.notification.PushNotification;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.util.PushPreferencesUtils;

public class SubScribeAliasStatusHandler extends AbstractMessageHandler<SubAliasStatus> {
    public SubScribeAliasStatusHandler(Context context, AbstractAppLogicListener abstractAppLogicListener) {
        super(context, abstractAppLogicListener);
    }

    protected SubAliasStatus getMessage(Intent intent) {
        SubAliasStatus subAliasStatus = (SubAliasStatus) intent.getSerializableExtra(PushConstants.EXTRA_APP_PUSH_SUBALIAS_STATUS);
        if ("200".equals(subAliasStatus.getCode())) {
            storeAlias(subAliasStatus.getAlias());
        }
        return subAliasStatus;
    }

    protected void unsafeSend(SubAliasStatus subAliasStatus, PushNotification pushNotification) {
        if (appLogicListener() != null && subAliasStatus != null) {
            appLogicListener().onSubAliasStatus(context(), subAliasStatus);
        }
    }

    public boolean messageMatch(Intent intent) {
        a.a("AbstractMessageHandler", "start SubScribeAliasStatusHandler match");
        return PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_SUBALIAS_STATUS.equals(getIntentMethod(intent));
    }

    public int getProcessorType() {
        return 4096;
    }

    private void storeAlias(String str) {
        PushPreferencesUtils.setAlias(context(), context().getPackageName(), str);
    }
}
