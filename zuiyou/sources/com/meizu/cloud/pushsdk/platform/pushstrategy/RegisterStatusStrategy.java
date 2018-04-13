package com.meizu.cloud.pushsdk.platform.pushstrategy;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.networking.common.ANResponse;
import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.platform.PlatformMessageSender;
import com.meizu.cloud.pushsdk.platform.PushIdEncryptUtils;
import com.meizu.cloud.pushsdk.platform.api.PushAPI;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.pushtracer.emitter.classic.Executor;
import com.meizu.cloud.pushsdk.util.PushPreferencesUtils;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RegisterStatusStrategy extends Strategy<RegisterStatus> {
    protected int deviceIdRetry;
    protected Handler mainHandler;
    protected ScheduledExecutorService scheduledExecutorService;

    public RegisterStatusStrategy(Context context, String str, String str2, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, pushAPI, scheduledExecutorService);
        this.deviceIdRetry = 0;
    }

    public RegisterStatusStrategy(Context context, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService) {
        this(context, null, null, pushAPI, scheduledExecutorService);
        this.scheduledExecutorService = (ScheduledExecutorService) Executor.getExecutor();
        this.mainHandler = new Handler(context.getMainLooper()) {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        RegisterStatusStrategy.this.process();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public RegisterStatusStrategy(Context context, PushAPI pushAPI, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, pushAPI, scheduledExecutorService);
        this.enableRPC = z;
    }

    public boolean matchCondition() {
        return (TextUtils.isEmpty(this.appId) || TextUtils.isEmpty(this.appKey)) ? false : true;
    }

    protected RegisterStatus feedBackErrorResponse() {
        RegisterStatus registerStatus = new RegisterStatus();
        registerStatus.setCode(Strategy.FEEDBACK_PARAMETER_ERROR_CODE);
        if (TextUtils.isEmpty(this.appId)) {
            registerStatus.setMessage("appId not empty");
        } else if (TextUtils.isEmpty(this.appKey)) {
            registerStatus.setMessage("appKey not empty");
        }
        return registerStatus;
    }

    public Intent sendRpcRequest() {
        Intent intent = new Intent();
        intent.putExtra(Strategy.APP_ID, this.appId);
        intent.putExtra(Strategy.APP_KEY, this.appKey);
        intent.putExtra(Strategy.STRATEGY_PACKAGE_NANME, this.context.getPackageName());
        intent.putExtra(Strategy.STRATEGY_TYPE, strategyType());
        return intent;
    }

    protected RegisterStatus localResponse() {
        return null;
    }

    public RegisterStatus netWorkRequest() {
        RegisterStatus registerStatus = new RegisterStatus();
        String pushId = PushPreferencesUtils.getPushId(this.context, this.strategyPackageNanme);
        int pushIdExpireTime = PushPreferencesUtils.getPushIdExpireTime(this.context, this.strategyPackageNanme);
        if (retryRegister(pushId, pushIdExpireTime)) {
            PushPreferencesUtils.putPushId(this.context, "", this.strategyPackageNanme);
            this.deviceId = getDeviceId();
            if (!TextUtils.isEmpty(this.deviceId) || this.deviceIdRetry >= 3) {
                this.deviceIdRetry = 0;
                ANResponse register = this.pushAPI.register(this.appId, this.appKey, this.deviceId);
                if (register.isSuccess()) {
                    RegisterStatus registerStatus2 = new RegisterStatus((String) register.getResult());
                    a.d(Strategy.TAG, "registerStatus " + registerStatus2);
                    if (TextUtils.isEmpty(registerStatus2.getPushId())) {
                        return registerStatus2;
                    }
                    PushPreferencesUtils.putPushId(this.context, registerStatus2.getPushId(), this.strategyPackageNanme);
                    PushPreferencesUtils.putPushIdExpireTime(this.context, (int) ((System.currentTimeMillis() / 1000) + ((long) registerStatus2.getExpireTime())), this.strategyPackageNanme);
                    return registerStatus2;
                }
                ANError error = register.getError();
                if (error.getResponse() != null) {
                    a.d(Strategy.TAG, "status code=" + error.getErrorCode() + " data=" + error.getResponse());
                }
                registerStatus.setCode(String.valueOf(error.getErrorCode()));
                registerStatus.setMessage(error.getErrorBody());
                a.d(Strategy.TAG, "registerStatus " + registerStatus);
                return registerStatus;
            }
            a.a(Strategy.TAG, "after " + (this.deviceIdRetry * 10) + " seconds start register");
            executeAfterGetDeviceId((long) (this.deviceIdRetry * 10));
            this.deviceIdRetry++;
            registerStatus.setCode(Strategy.DEVICE_ERROR_CODE);
            registerStatus.setMessage("deviceId is empty");
            return registerStatus;
        }
        registerStatus.setCode("200");
        registerStatus.setMessage("already register PushId,dont register frequently");
        registerStatus.setPushId(pushId);
        registerStatus.setExpireTime((int) (((long) pushIdExpireTime) - (System.currentTimeMillis() / 1000)));
        return registerStatus;
    }

    public void sendReceiverMessage(RegisterStatus registerStatus) {
        PlatformMessageSender.sendRegisterStatus(this.context, !TextUtils.isEmpty(this.strategyPackageNanme) ? this.strategyPackageNanme : this.context.getPackageName(), registerStatus);
    }

    protected int strategyType() {
        return 2;
    }

    protected void executeAfterGetDeviceId(long j) {
        this.scheduledExecutorService.schedule(new Runnable() {
            public void run() {
                RegisterStatusStrategy.this.getDeviceId();
                RegisterStatusStrategy.this.mainHandler.sendEmptyMessage(0);
            }
        }, j, TimeUnit.SECONDS);
    }

    protected boolean retryRegister(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String deviceId = getDeviceId();
        if ((str.startsWith(deviceId) || PushIdEncryptUtils.decryptPushId(str).startsWith(deviceId)) && System.currentTimeMillis() / 1000 < ((long) i)) {
            return false;
        }
        return true;
    }
}
