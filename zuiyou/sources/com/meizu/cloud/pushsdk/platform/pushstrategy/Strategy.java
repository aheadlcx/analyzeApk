package com.meizu.cloud.pushsdk.platform.pushstrategy;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.platform.api.PushAPI;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public abstract class Strategy<T extends BasicPushStatus> {
    public static final String APP_ID = "app_id";
    public static final String APP_KEY = "app_key";
    public static final String DEVICE_ERROR_CODE = "20000";
    public static final String FEEDBACK_PARAMETER_ERROR_CODE = "20001";
    public static final String PUSH_ID = "push_id";
    public static final String STRATEGY_CHILD_TYPE = "strategy_child_type";
    public static final String STRATEGY_PACKAGE_NANME = "strategy_package_name";
    public static final String STRATEGY_PARAMS = "strategy_params";
    public static final int STRATEGY_REGISTER = 2;
    public static final int STRATEGY_SUBALIAS = 8;
    public static final int STRATEGY_SUBTAGS = 4;
    public static final int STRATEGY_SWITCH = 16;
    public static final String STRATEGY_TYPE = "strategy_type";
    public static final int STRATEGY_UNREGISTER = 32;
    public static final String SUCCESS_CODE = "200";
    public static final String TAG = "Strategy";
    protected String appId;
    protected String appKey;
    protected Context context;
    protected volatile String deviceId;
    protected boolean enableRPC = true;
    protected ScheduledExecutorService executorService;
    protected boolean isSupportRemoteInvoke = false;
    private String managerServicePackageName = null;
    protected PushAPI pushAPI;
    protected String strategyPackageNanme;

    protected abstract T feedBackErrorResponse();

    protected abstract T localResponse();

    protected abstract boolean matchCondition();

    protected abstract T netWorkRequest();

    protected abstract void sendReceiverMessage(T t);

    protected abstract Intent sendRpcRequest();

    protected abstract int strategyType();

    public Strategy(Context context, String str, String str2, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService) {
        this.executorService = scheduledExecutorService;
        this.context = context;
        this.appId = str;
        this.appKey = str2;
        this.pushAPI = pushAPI;
    }

    public void setExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.executorService = scheduledExecutorService;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public void setStrategyPackageNanme(String str) {
        this.strategyPackageNanme = str;
    }

    public void setSupportRemoteInvoke(boolean z) {
        this.isSupportRemoteInvoke = z;
    }

    protected boolean supportServiceRpc() {
        return this.enableRPC && this.isSupportRemoteInvoke && !TextUtils.isEmpty(findService(this.context, "com.meizu.cloud.pushservice.action.PUSH_MANAGER_SERVICE"));
    }

    private boolean supportAllResponse() {
        return this.enableRPC && !this.context.getPackageName().equals(this.managerServicePackageName);
    }

    private boolean isServiceCode(int i) {
        return i >= 110000 && i <= 200000;
    }

    protected boolean isRegisterStatus() {
        return 2 == strategyType() || 32 == strategyType();
    }

    public boolean process() {
        if (this.executorService == null) {
            return processMainThread();
        }
        this.executorService.execute(new Runnable() {
            public void run() {
                Strategy.this.processMainThread();
            }
        });
        return true;
    }

    public boolean processMainThread() {
        boolean z;
        BasicPushStatus basicPushStatus = null;
        if (!matchCondition()) {
            a.d(TAG, "Missing required parameters");
            basicPushStatus = feedBackErrorResponse();
            sendReceiverMessage(basicPushStatus);
        } else if (supportServiceRpc()) {
            a.a(TAG, "send message to remote service");
            if (!isRegisterStatus()) {
                basicPushStatus = localResponse();
                if (basicPushStatus != null) {
                    a.d(TAG, "local response " + basicPushStatus);
                    sendReceiverMessage(basicPushStatus);
                }
            }
            Intent sendRpcRequest = sendRpcRequest();
            if (sendRpcRequest != null) {
                sendIntentMessage(sendRpcRequest);
            }
        } else {
            BasicPushStatus netWorkRequest = netWorkRequest();
            a.a(TAG, "real response status " + netWorkRequest);
            if (netWorkRequest == null) {
                basicPushStatus = netWorkRequest;
            } else if (isRegisterStatus() && DEVICE_ERROR_CODE.equals(netWorkRequest.getCode())) {
                return true;
            } else {
                if (supportAllResponse()) {
                    a.d(TAG, "response all request in local app");
                    sendReceiverMessage(netWorkRequest);
                    basicPushStatus = netWorkRequest;
                } else {
                    String code = netWorkRequest.getCode();
                    if (TextUtils.isEmpty(code)) {
                        code = "0";
                    }
                    if ("200".equals(netWorkRequest.getCode())) {
                        sendReceiverMessage(netWorkRequest);
                    }
                    int intValue = Integer.valueOf(code).intValue();
                    if (isServiceCode(intValue)) {
                        a.d(TAG, "service error so notify pushManager invoker code=" + intValue + " message " + netWorkRequest.getMessage());
                        sendReceiverMessage(netWorkRequest);
                    }
                    basicPushStatus = netWorkRequest;
                }
            }
        }
        if (basicPushStatus != null) {
            a.d(TAG, "current status code " + basicPushStatus.getCode());
            z = !isServerError(basicPushStatus);
        } else {
            z = true;
        }
        return z;
    }

    private boolean isServerError(T t) {
        int intValue = Integer.valueOf(t.getCode()).intValue();
        return (intValue > 200 && intValue < 600) || ((intValue > 1000 && intValue < 2000) || intValue == 0);
    }

    protected String getDeviceId() {
        if (TextUtils.isEmpty(this.deviceId)) {
            this.deviceId = MzSystemUtils.getDeviceId(this.context);
            a.d(TAG, "deviceId " + this.deviceId);
        }
        return this.deviceId;
    }

    protected String findService(Context context, String str) {
        String str2;
        if (!TextUtils.isEmpty(str)) {
            List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(new Intent(str), 0);
            if (queryIntentServices != null) {
                for (ResolveInfo resolveInfo : queryIntentServices) {
                    if ("com.meizu.cloud".equals(resolveInfo.serviceInfo.packageName)) {
                        this.managerServicePackageName = resolveInfo.serviceInfo.packageName;
                        str2 = resolveInfo.serviceInfo.name;
                        break;
                    }
                }
                str2 = null;
                if (TextUtils.isEmpty(str2) && queryIntentServices.size() > 0) {
                    this.managerServicePackageName = ((ResolveInfo) queryIntentServices.get(0)).serviceInfo.packageName;
                    str2 = ((ResolveInfo) queryIntentServices.get(0)).serviceInfo.name;
                }
                a.a(TAG, "current process packageName " + this.managerServicePackageName);
                return str2;
            }
        }
        str2 = null;
        a.a(TAG, "current process packageName " + this.managerServicePackageName);
        return str2;
    }

    protected void sendIntentMessage(Intent intent) {
        try {
            intent.setPackage(this.managerServicePackageName);
            intent.setAction("com.meizu.cloud.pushservice.action.PUSH_MANAGER_SERVICE");
            this.context.startService(intent);
        } catch (Exception e) {
            a.d(TAG, "start RemoteService error " + e.getMessage());
        }
    }
}
