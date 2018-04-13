package com.meizu.cloud.pushsdk.platform.pushstrategy;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.networking.common.ANResponse;
import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.platform.PlatformMessageSender;
import com.meizu.cloud.pushsdk.platform.api.PushAPI;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.util.PushPreferencesUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

public class SwitchStatusStrategy extends Strategy<PushSwitchStatus> {
    public static final int CHECK_PUSH = 2;
    public static final int SWITCH_ALL = 3;
    public static final int SWITCH_NOTIFICATION = 0;
    public static final int SWITCH_THROUGH_MESSAGE = 1;
    private String pushId;
    private Map<String, Boolean> pushStatusMap;
    private int switchType;
    boolean switcher;

    public SwitchStatusStrategy(Context context, String str, String str2, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, pushAPI, scheduledExecutorService);
        this.switchType = 0;
        this.pushStatusMap = new HashMap();
    }

    public SwitchStatusStrategy(Context context, String str, String str2, String str3, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService) {
        this(context, str, str2, pushAPI, scheduledExecutorService);
        this.pushId = str3;
    }

    public SwitchStatusStrategy(Context context, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService) {
        this(context, null, null, null, pushAPI, scheduledExecutorService);
    }

    public SwitchStatusStrategy(Context context, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, pushAPI, scheduledExecutorService);
        this.enableRPC = z;
    }

    public void setSwitcher(boolean z) {
        this.switcher = z;
    }

    public void setSwitchType(int i) {
        this.switchType = i;
    }

    public void setPushId(String str) {
        this.pushId = str;
    }

    protected boolean matchCondition() {
        return (TextUtils.isEmpty(this.appId) || TextUtils.isEmpty(this.appKey) || TextUtils.isEmpty(this.pushId)) ? false : true;
    }

    protected PushSwitchStatus feedBackErrorResponse() {
        PushSwitchStatus pushSwitchStatus = new PushSwitchStatus();
        pushSwitchStatus.setCode(Strategy.FEEDBACK_PARAMETER_ERROR_CODE);
        if (TextUtils.isEmpty(this.appId)) {
            pushSwitchStatus.setMessage("appId not empty");
        } else if (TextUtils.isEmpty(this.appKey)) {
            pushSwitchStatus.setMessage("appKey not empty");
        } else if (TextUtils.isEmpty(this.pushId)) {
            pushSwitchStatus.setMessage("pushId not empty");
        }
        return pushSwitchStatus;
    }

    protected Intent sendRpcRequest() {
        if (this.switchType == 2) {
            return null;
        }
        Intent intent = new Intent();
        intent.putExtra(Strategy.APP_ID, this.appId);
        intent.putExtra(Strategy.APP_KEY, this.appKey);
        intent.putExtra(Strategy.STRATEGY_PACKAGE_NANME, this.context.getPackageName());
        intent.putExtra(Strategy.PUSH_ID, this.pushId);
        intent.putExtra(Strategy.STRATEGY_TYPE, strategyType());
        intent.putExtra(Strategy.STRATEGY_CHILD_TYPE, this.switchType);
        intent.putExtra(Strategy.STRATEGY_PARAMS, this.switcher ? "1" : "0");
        return intent;
    }

    protected PushSwitchStatus netWorkRequest() {
        PushSwitchStatus pushSwitchStatus = new PushSwitchStatus();
        pushSwitchStatus.setPushId(this.pushId);
        pushSwitchStatus.setCode("200");
        pushSwitchStatus.setMessage("");
        ANResponse aNResponse = null;
        switch (this.switchType) {
            case 0:
                if (notificationSwitch() == this.switcher && !isSyncPushStatus()) {
                    pushSwitchStatus.setSwitchNotificationMessage(this.switcher);
                    pushSwitchStatus.setSwitchThroughMessage(throughMessageSwitch());
                    break;
                }
                changeSyncPushStatus(true);
                switchNotification(this.switcher);
                aNResponse = this.pushAPI.switchPush(this.appId, this.appKey, this.pushId, this.switchType, this.switcher);
                break;
            case 1:
                if (throughMessageSwitch() == this.switcher && !isSyncPushStatus()) {
                    pushSwitchStatus.setSwitchNotificationMessage(notificationSwitch());
                    pushSwitchStatus.setSwitchThroughMessage(this.switcher);
                    break;
                }
                changeSyncPushStatus(true);
                switchThroughMessage(this.switcher);
                aNResponse = this.pushAPI.switchPush(this.appId, this.appKey, this.pushId, this.switchType, this.switcher);
                break;
                break;
            case 2:
                pushSwitchStatus.setSwitchNotificationMessage(notificationSwitch());
                pushSwitchStatus.setSwitchThroughMessage(throughMessageSwitch());
                break;
            case 3:
                if (notificationSwitch() != this.switcher || throughMessageSwitch() != this.switcher || isSyncPushStatus()) {
                    changeSyncPushStatus(true);
                    switchAll(this.switcher);
                    aNResponse = this.pushAPI.switchPush(this.appId, this.appKey, this.pushId, this.switcher);
                    break;
                }
                pushSwitchStatus.setSwitchNotificationMessage(this.switcher);
                pushSwitchStatus.setSwitchThroughMessage(this.switcher);
                break;
                break;
        }
        if (aNResponse != null) {
            if (aNResponse.isSuccess()) {
                PushSwitchStatus pushSwitchStatus2 = new PushSwitchStatus((String) aNResponse.getResult());
                a.d(Strategy.TAG, "network pushSwitchStatus " + pushSwitchStatus2);
                if (!"200".equals(pushSwitchStatus2.getCode())) {
                    return pushSwitchStatus2;
                }
                changeSyncPushStatus(false);
                return pushSwitchStatus2;
            }
            ANError error = aNResponse.getError();
            if (error.getResponse() != null) {
                a.d(Strategy.TAG, "status code=" + error.getErrorCode() + " data=" + error.getResponse());
            }
            pushSwitchStatus.setCode(String.valueOf(error.getErrorCode()));
            pushSwitchStatus.setMessage(error.getErrorBody());
            a.d(Strategy.TAG, "pushSwitchStatus " + pushSwitchStatus);
        }
        return pushSwitchStatus;
    }

    protected PushSwitchStatus localResponse() {
        switch (this.switchType) {
            case 0:
                switchNotification(this.switcher);
                return null;
            case 1:
                switchThroughMessage(this.switcher);
                return null;
            case 2:
                PushSwitchStatus pushSwitchStatus = new PushSwitchStatus();
                pushSwitchStatus.setPushId(this.pushId);
                pushSwitchStatus.setCode("200");
                pushSwitchStatus.setMessage("");
                pushSwitchStatus.setSwitchNotificationMessage(notificationSwitch());
                pushSwitchStatus.setSwitchThroughMessage(throughMessageSwitch());
                return pushSwitchStatus;
            case 3:
                switchAll(this.switcher);
                return null;
            default:
                return null;
        }
    }

    protected void sendReceiverMessage(PushSwitchStatus pushSwitchStatus) {
        PlatformMessageSender.sendPushStatus(this.context, !TextUtils.isEmpty(this.strategyPackageNanme) ? this.strategyPackageNanme : this.context.getPackageName(), pushSwitchStatus);
    }

    protected int strategyType() {
        return 16;
    }

    private void switchNotification(boolean z) {
        PushPreferencesUtils.setNotificationMessageSwitchStatus(this.context, !TextUtils.isEmpty(this.strategyPackageNanme) ? this.strategyPackageNanme : this.context.getPackageName(), z);
    }

    private void switchThroughMessage(boolean z) {
        PushPreferencesUtils.setThroughMessageSwitchStatus(this.context, !TextUtils.isEmpty(this.strategyPackageNanme) ? this.strategyPackageNanme : this.context.getPackageName(), z);
    }

    private void switchAll(boolean z) {
        PushPreferencesUtils.setNotificationMessageSwitchStatus(this.context, !TextUtils.isEmpty(this.strategyPackageNanme) ? this.strategyPackageNanme : this.context.getPackageName(), z);
        PushPreferencesUtils.setThroughMessageSwitchStatus(this.context, !TextUtils.isEmpty(this.strategyPackageNanme) ? this.strategyPackageNanme : this.context.getPackageName(), z);
    }

    private boolean notificationSwitch() {
        return PushPreferencesUtils.getNotificationMessageSwitchStatus(this.context, !TextUtils.isEmpty(this.strategyPackageNanme) ? this.strategyPackageNanme : this.context.getPackageName());
    }

    private boolean throughMessageSwitch() {
        return PushPreferencesUtils.getThroughMessageSwitchStatus(this.context, !TextUtils.isEmpty(this.strategyPackageNanme) ? this.strategyPackageNanme : this.context.getPackageName());
    }

    private void changeSyncPushStatus(boolean z) {
        this.pushStatusMap.put(this.strategyPackageNanme + "_" + this.switchType, Boolean.valueOf(z));
    }

    private boolean isSyncPushStatus() {
        Boolean bool = (Boolean) this.pushStatusMap.get(this.strategyPackageNanme + "_" + this.switchType);
        return bool != null ? bool.booleanValue() : true;
    }
}
