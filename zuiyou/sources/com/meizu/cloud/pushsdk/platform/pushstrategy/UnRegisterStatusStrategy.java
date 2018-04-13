package com.meizu.cloud.pushsdk.platform.pushstrategy;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.networking.common.ANResponse;
import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.platform.PlatformMessageSender;
import com.meizu.cloud.pushsdk.platform.api.PushAPI;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;
import com.meizu.cloud.pushsdk.util.PushPreferencesUtils;
import java.util.concurrent.ScheduledExecutorService;

public class UnRegisterStatusStrategy extends Strategy<UnRegisterStatus> {
    public UnRegisterStatusStrategy(Context context, String str, String str2, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, pushAPI, scheduledExecutorService);
    }

    public UnRegisterStatusStrategy(Context context, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService) {
        this(context, null, null, pushAPI, scheduledExecutorService);
    }

    public UnRegisterStatusStrategy(Context context, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, pushAPI, scheduledExecutorService);
        this.enableRPC = z;
    }

    protected boolean matchCondition() {
        return (TextUtils.isEmpty(this.appId) || TextUtils.isEmpty(this.appKey)) ? false : true;
    }

    protected UnRegisterStatus feedBackErrorResponse() {
        UnRegisterStatus unRegisterStatus = new UnRegisterStatus();
        unRegisterStatus.setCode(Strategy.FEEDBACK_PARAMETER_ERROR_CODE);
        if (TextUtils.isEmpty(this.appId)) {
            unRegisterStatus.setMessage("appId not empty");
        } else if (TextUtils.isEmpty(this.appKey)) {
            unRegisterStatus.setMessage("appKey not empty");
        }
        return unRegisterStatus;
    }

    protected Intent sendRpcRequest() {
        Intent intent = new Intent();
        intent.putExtra(Strategy.APP_ID, this.appId);
        intent.putExtra(Strategy.APP_KEY, this.appKey);
        intent.putExtra(Strategy.STRATEGY_PACKAGE_NANME, this.context.getPackageName());
        intent.putExtra(Strategy.STRATEGY_TYPE, strategyType());
        return intent;
    }

    protected UnRegisterStatus netWorkRequest() {
        UnRegisterStatus unRegisterStatus = new UnRegisterStatus();
        if (TextUtils.isEmpty(PushPreferencesUtils.getPushId(this.context, this.strategyPackageNanme))) {
            unRegisterStatus.setCode("200");
            unRegisterStatus.setMessage("already unRegister PushId,dont unRegister frequently");
            unRegisterStatus.setIsUnRegisterSuccess(true);
            return unRegisterStatus;
        }
        this.deviceId = getDeviceId();
        ANResponse unRegister = this.pushAPI.unRegister(this.appId, this.appKey, this.deviceId);
        if (unRegister.isSuccess()) {
            UnRegisterStatus unRegisterStatus2 = new UnRegisterStatus((String) unRegister.getResult());
            a.d(Strategy.TAG, "network unRegisterStatus " + unRegisterStatus2);
            if (!"200".equals(unRegisterStatus2.getCode())) {
                return unRegisterStatus2;
            }
            PushPreferencesUtils.putPushId(this.context, "", this.strategyPackageNanme);
            return unRegisterStatus2;
        }
        ANError error = unRegister.getError();
        if (error.getResponse() != null) {
            a.d(Strategy.TAG, "status code=" + error.getErrorCode() + " data=" + error.getResponse());
        }
        unRegisterStatus.setCode(String.valueOf(error.getErrorCode()));
        unRegisterStatus.setMessage(error.getErrorBody());
        a.d(Strategy.TAG, "unRegisterStatus " + unRegisterStatus);
        return unRegisterStatus;
    }

    protected UnRegisterStatus localResponse() {
        return null;
    }

    protected void sendReceiverMessage(UnRegisterStatus unRegisterStatus) {
        PlatformMessageSender.sendUnRegisterStatus(this.context, !TextUtils.isEmpty(this.strategyPackageNanme) ? this.strategyPackageNanme : this.context.getPackageName(), unRegisterStatus);
    }

    protected int strategyType() {
        return 32;
    }
}
