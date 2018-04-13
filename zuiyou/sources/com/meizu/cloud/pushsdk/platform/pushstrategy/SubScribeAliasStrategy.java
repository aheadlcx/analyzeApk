package com.meizu.cloud.pushsdk.platform.pushstrategy;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.networking.common.ANResponse;
import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.platform.PlatformMessageSender;
import com.meizu.cloud.pushsdk.platform.api.PushAPI;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.util.PushPreferencesUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

public class SubScribeAliasStrategy extends Strategy<SubAliasStatus> {
    public static final int CHECK_ALIAS = 2;
    public static final int SUB_ALIAS = 0;
    public static final int UNSUB_ALIAS = 1;
    private String alias;
    private Map<String, Boolean> aliasStatusMap;
    private String pushId;
    private int subAliasType;

    public SubScribeAliasStrategy(Context context, String str, String str2, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, pushAPI, scheduledExecutorService);
        this.aliasStatusMap = new HashMap();
    }

    public SubScribeAliasStrategy(Context context, String str, String str2, String str3, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService) {
        this(context, str, str2, pushAPI, scheduledExecutorService);
        this.pushId = str3;
    }

    public SubScribeAliasStrategy(Context context, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService) {
        this(context, null, null, null, pushAPI, scheduledExecutorService);
    }

    public SubScribeAliasStrategy(Context context, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, pushAPI, scheduledExecutorService);
        this.enableRPC = z;
    }

    public void setSubAliasType(int i) {
        this.subAliasType = i;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public void setPushId(String str) {
        this.pushId = str;
    }

    protected boolean matchCondition() {
        return (TextUtils.isEmpty(this.appId) || TextUtils.isEmpty(this.appKey) || TextUtils.isEmpty(this.pushId)) ? false : true;
    }

    protected SubAliasStatus feedBackErrorResponse() {
        SubAliasStatus subAliasStatus = new SubAliasStatus();
        subAliasStatus.setCode(Strategy.FEEDBACK_PARAMETER_ERROR_CODE);
        if (TextUtils.isEmpty(this.appId)) {
            subAliasStatus.setMessage("appId not empty");
        } else if (TextUtils.isEmpty(this.appKey)) {
            subAliasStatus.setMessage("appKey not empty");
        } else if (TextUtils.isEmpty(this.pushId)) {
            subAliasStatus.setMessage("pushId not empty");
        }
        return subAliasStatus;
    }

    protected Intent sendRpcRequest() {
        if (this.subAliasType == 2) {
            return null;
        }
        Intent intent = new Intent();
        intent.putExtra(Strategy.APP_ID, this.appId);
        intent.putExtra(Strategy.APP_KEY, this.appKey);
        intent.putExtra(Strategy.STRATEGY_PACKAGE_NANME, this.context.getPackageName());
        intent.putExtra(Strategy.PUSH_ID, this.pushId);
        intent.putExtra(Strategy.STRATEGY_TYPE, strategyType());
        intent.putExtra(Strategy.STRATEGY_CHILD_TYPE, this.subAliasType);
        intent.putExtra(Strategy.STRATEGY_PARAMS, this.alias);
        return intent;
    }

    protected SubAliasStatus netWorkRequest() {
        ANResponse aNResponse;
        SubAliasStatus subAliasStatus = new SubAliasStatus();
        subAliasStatus.setPushId(this.pushId);
        subAliasStatus.setMessage("");
        switch (this.subAliasType) {
            case 0:
                if (this.alias.equals(localAlias()) && !isSyncAliasStatus()) {
                    subAliasStatus.setCode("200");
                    subAliasStatus.setAlias(this.alias);
                    aNResponse = null;
                    break;
                }
                changeSyncAliasStatus(true);
                if (isCacheAlias()) {
                    storeAlias(this.alias);
                }
                aNResponse = this.pushAPI.subScribeAlias(this.appId, this.appKey, this.pushId, this.alias);
                break;
                break;
            case 1:
                if (TextUtils.isEmpty(localAlias()) && !isSyncAliasStatus()) {
                    subAliasStatus.setCode("200");
                    subAliasStatus.setAlias("");
                    aNResponse = null;
                    break;
                }
                changeSyncAliasStatus(true);
                if (isCacheAlias()) {
                    storeAlias("");
                }
                aNResponse = this.pushAPI.unSubScribeAlias(this.appId, this.appKey, this.pushId, this.alias);
                break;
            case 2:
                subAliasStatus.setAlias(localAlias());
                subAliasStatus.setCode("200");
                break;
        }
        aNResponse = null;
        if (aNResponse == null) {
            return subAliasStatus;
        }
        if (aNResponse.isSuccess()) {
            SubAliasStatus subAliasStatus2 = new SubAliasStatus((String) aNResponse.getResult());
            a.d(Strategy.TAG, "network subAliasStatus " + subAliasStatus2);
            if (!"200".equals(subAliasStatus2.getCode())) {
                return subAliasStatus2;
            }
            changeSyncAliasStatus(false);
            return subAliasStatus2;
        }
        ANError error = aNResponse.getError();
        if (error.getResponse() != null) {
            a.d(Strategy.TAG, "status code=" + error.getErrorCode() + " data=" + error.getResponse());
        }
        subAliasStatus.setCode(String.valueOf(error.getErrorCode()));
        subAliasStatus.setMessage(error.getErrorBody());
        a.d(Strategy.TAG, "subAliasStatus " + subAliasStatus);
        return subAliasStatus;
    }

    protected SubAliasStatus localResponse() {
        switch (this.subAliasType) {
            case 2:
                SubAliasStatus subAliasStatus = new SubAliasStatus();
                subAliasStatus.setCode("200");
                subAliasStatus.setPushId(this.pushId);
                subAliasStatus.setAlias(localAlias());
                subAliasStatus.setMessage("check alias success");
                return subAliasStatus;
            default:
                return null;
        }
    }

    protected void sendReceiverMessage(SubAliasStatus subAliasStatus) {
        PlatformMessageSender.sendSubAlias(this.context, !TextUtils.isEmpty(this.strategyPackageNanme) ? this.strategyPackageNanme : this.context.getPackageName(), subAliasStatus);
    }

    protected int strategyType() {
        return 8;
    }

    private void storeAlias(String str) {
        PushPreferencesUtils.setAlias(this.context, !TextUtils.isEmpty(this.strategyPackageNanme) ? this.strategyPackageNanme : this.context.getPackageName(), str);
    }

    private String localAlias() {
        return PushPreferencesUtils.getAlias(this.context, !TextUtils.isEmpty(this.strategyPackageNanme) ? this.strategyPackageNanme : this.context.getPackageName());
    }

    private void changeSyncAliasStatus(boolean z) {
        this.aliasStatusMap.put(this.strategyPackageNanme + "_" + this.subAliasType, Boolean.valueOf(z));
    }

    private boolean isSyncAliasStatus() {
        Boolean bool = (Boolean) this.aliasStatusMap.get(this.strategyPackageNanme + "_" + this.subAliasType);
        return bool != null ? bool.booleanValue() : true;
    }

    private boolean isCacheAlias() {
        return !this.isSupportRemoteInvoke && "com.meizu.cloud".equals(this.strategyPackageNanme);
    }
}
