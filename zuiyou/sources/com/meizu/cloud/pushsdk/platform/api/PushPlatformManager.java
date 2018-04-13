package com.meizu.cloud.pushsdk.platform.api;

import android.content.Context;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.networking.common.ANResponse;
import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.networking.http.Response;
import com.meizu.cloud.pushsdk.networking.interfaces.OkHttpResponseAndStringRequestListener;
import com.meizu.cloud.pushsdk.platform.message.StrategyMessage;
import com.meizu.cloud.pushsdk.platform.pushstrategy.RegisterStatusStrategy;
import com.meizu.cloud.pushsdk.platform.pushstrategy.SubScribeAliasStrategy;
import com.meizu.cloud.pushsdk.platform.pushstrategy.SubScribeTagStrategy;
import com.meizu.cloud.pushsdk.platform.pushstrategy.SwitchStatusStrategy;
import com.meizu.cloud.pushsdk.platform.pushstrategy.UnRegisterStatusStrategy;
import com.meizu.cloud.pushsdk.pushtracer.emitter.classic.Executor;
import java.io.File;
import java.util.concurrent.ScheduledExecutorService;

public class PushPlatformManager {
    private static final String TAG = "PushPlatformManager";
    private static PushPlatformManager mInstance;
    private ScheduledExecutorService executorService;
    private Context mContext;
    private PushAPI pushAPI;
    private RegisterStatusStrategy registerStatusStrategy;
    private SubScribeAliasStrategy subScribeAliasStrategy;
    private SubScribeTagStrategy subScribeTagStrategy;
    private SwitchStatusStrategy switchStatusStrategy;
    private UnRegisterStatusStrategy unRegisterStatusStrategy;

    public PushPlatformManager(Context context, boolean z) {
        this(context, z, true);
    }

    public PushPlatformManager(Context context, boolean z, boolean z2) {
        this.mContext = context.getApplicationContext();
        this.pushAPI = new PushAPI(this.mContext);
        if (z) {
            this.executorService = (ScheduledExecutorService) Executor.getExecutor();
        }
        this.registerStatusStrategy = new RegisterStatusStrategy(this.mContext, this.pushAPI, this.executorService, z2);
        this.unRegisterStatusStrategy = new UnRegisterStatusStrategy(this.mContext, this.pushAPI, this.executorService, z2);
        this.switchStatusStrategy = new SwitchStatusStrategy(this.mContext, this.pushAPI, this.executorService, z2);
        this.subScribeTagStrategy = new SubScribeTagStrategy(this.mContext, this.pushAPI, this.executorService, z2);
        this.subScribeAliasStrategy = new SubScribeAliasStrategy(this.mContext, this.pushAPI, this.executorService, z2);
    }

    public static PushPlatformManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (PushPlatformManager.class) {
                if (mInstance == null) {
                    mInstance = new PushPlatformManager(context, true);
                }
            }
        }
        return mInstance;
    }

    public void enableRemoteInvoker(boolean z) {
        this.registerStatusStrategy.setSupportRemoteInvoke(z);
        this.unRegisterStatusStrategy.setSupportRemoteInvoke(z);
        this.switchStatusStrategy.setSupportRemoteInvoke(z);
        this.subScribeAliasStrategy.setSupportRemoteInvoke(z);
        this.subScribeTagStrategy.setSupportRemoteInvoke(z);
    }

    public boolean dispatcherStrategyMessage(StrategyMessage strategyMessage) {
        boolean z = false;
        if (strategyMessage == null) {
            return true;
        }
        switch (strategyMessage.getStrategyType()) {
            case 2:
                return register(strategyMessage.getAppId(), strategyMessage.getAppKey(), strategyMessage.getPackageName());
            case 4:
                if (strategyMessage.getStrategyChildType() == 0) {
                    return subScribeTags(strategyMessage.getAppId(), strategyMessage.getAppKey(), strategyMessage.getPackageName(), strategyMessage.getPushId(), strategyMessage.getParams());
                }
                if (3 == strategyMessage.getStrategyChildType()) {
                    return checkSubScribeTags(strategyMessage.getAppId(), strategyMessage.getAppKey(), strategyMessage.getPackageName(), strategyMessage.getPushId());
                }
                if (1 == strategyMessage.getStrategyChildType()) {
                    return unSubScribeTags(strategyMessage.getAppId(), strategyMessage.getAppKey(), strategyMessage.getPackageName(), strategyMessage.getPushId(), strategyMessage.getParams());
                }
                if (2 == strategyMessage.getStrategyChildType()) {
                    return unSubScribeAllTags(strategyMessage.getAppId(), strategyMessage.getAppKey(), strategyMessage.getPackageName(), strategyMessage.getPushId());
                }
                return true;
            case 8:
                if (strategyMessage.getStrategyChildType() == 0) {
                    return subScribeAlias(strategyMessage.getAppId(), strategyMessage.getAppKey(), strategyMessage.getPackageName(), strategyMessage.getPushId(), strategyMessage.getParams());
                }
                if (1 == strategyMessage.getStrategyChildType()) {
                    return unSubScribeAlias(strategyMessage.getAppId(), strategyMessage.getAppKey(), strategyMessage.getPackageName(), strategyMessage.getPushId(), strategyMessage.getParams());
                }
                if (2 == strategyMessage.getStrategyChildType()) {
                    return checkSubScribeAlias(strategyMessage.getAppId(), strategyMessage.getAppKey(), strategyMessage.getPackageName(), strategyMessage.getPushId());
                }
                return true;
            case 16:
                String appId;
                String appKey;
                String packageName;
                String pushId;
                boolean z2;
                if (strategyMessage.getStrategyChildType() == 0) {
                    appId = strategyMessage.getAppId();
                    appKey = strategyMessage.getAppKey();
                    packageName = strategyMessage.getPackageName();
                    pushId = strategyMessage.getPushId();
                    if ("1".equals(strategyMessage.getParams())) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    return switchPush(appId, appKey, packageName, pushId, 0, z2);
                } else if (1 == strategyMessage.getStrategyChildType()) {
                    appId = strategyMessage.getAppId();
                    appKey = strategyMessage.getAppKey();
                    packageName = strategyMessage.getPackageName();
                    pushId = strategyMessage.getPushId();
                    if ("1".equals(strategyMessage.getParams())) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    return switchPush(appId, appKey, packageName, pushId, 1, z2);
                } else if (3 == strategyMessage.getStrategyChildType()) {
                    appId = strategyMessage.getAppId();
                    appKey = strategyMessage.getAppKey();
                    packageName = strategyMessage.getPackageName();
                    pushId = strategyMessage.getPushId();
                    if ("1".equals(strategyMessage.getParams())) {
                        z = true;
                    }
                    return switchPush(appId, appKey, packageName, pushId, z);
                } else if (2 == strategyMessage.getStrategyChildType()) {
                    return checkPush(strategyMessage.getAppId(), strategyMessage.getAppKey(), strategyMessage.getPackageName(), strategyMessage.getPushId());
                } else {
                    return true;
                }
            case 32:
                return unRegister(strategyMessage.getAppId(), strategyMessage.getAppKey(), strategyMessage.getPackageName());
            default:
                return true;
        }
    }

    public boolean register(String str, String str2, String str3) {
        this.registerStatusStrategy.setAppId(str);
        this.registerStatusStrategy.setAppKey(str2);
        this.registerStatusStrategy.setStrategyPackageNanme(str3);
        return this.registerStatusStrategy.process();
    }

    public boolean unRegister(String str, String str2, String str3) {
        this.unRegisterStatusStrategy.setAppId(str);
        this.unRegisterStatusStrategy.setAppKey(str2);
        this.unRegisterStatusStrategy.setStrategyPackageNanme(str3);
        return this.unRegisterStatusStrategy.process();
    }

    public void unRegisterAdvance(final String str, String str2) {
        this.pushAPI.unRegister(str, str2, new OkHttpResponseAndStringRequestListener() {
            public void onResponse(Response response, String str) {
                a.d(PushPlatformManager.TAG, "unregisetr advance pakcage " + str + " result " + str);
            }

            public void onError(ANError aNError) {
                a.d(PushPlatformManager.TAG, "unregisetr advance pakcage " + str + " error " + aNError.getErrorBody());
            }
        });
    }

    public boolean checkPush(String str, String str2, String str3, String str4) {
        this.switchStatusStrategy.setAppId(str);
        this.switchStatusStrategy.setAppKey(str2);
        this.switchStatusStrategy.setStrategyPackageNanme(str3);
        this.switchStatusStrategy.setPushId(str4);
        this.switchStatusStrategy.setSwitchType(2);
        return this.switchStatusStrategy.process();
    }

    public boolean switchPush(String str, String str2, String str3, String str4, int i, boolean z) {
        this.switchStatusStrategy.setAppId(str);
        this.switchStatusStrategy.setAppKey(str2);
        this.switchStatusStrategy.setStrategyPackageNanme(str3);
        this.switchStatusStrategy.setPushId(str4);
        this.switchStatusStrategy.setSwitchType(i);
        this.switchStatusStrategy.setSwitcher(z);
        return this.switchStatusStrategy.process();
    }

    public boolean switchPush(String str, String str2, String str3, String str4, boolean z) {
        this.switchStatusStrategy.setAppId(str);
        this.switchStatusStrategy.setAppKey(str2);
        this.switchStatusStrategy.setStrategyPackageNanme(str3);
        this.switchStatusStrategy.setPushId(str4);
        this.switchStatusStrategy.setSwitchType(3);
        this.switchStatusStrategy.setSwitcher(z);
        return this.switchStatusStrategy.process();
    }

    public boolean subScribeTags(String str, String str2, String str3, String str4, String str5) {
        this.subScribeTagStrategy.setAppId(str);
        this.subScribeTagStrategy.setAppKey(str2);
        this.subScribeTagStrategy.setStrategyPackageNanme(str3);
        this.subScribeTagStrategy.setPushId(str4);
        this.subScribeTagStrategy.setSubTagType(0);
        this.subScribeTagStrategy.setSubTags(str5);
        return this.subScribeTagStrategy.process();
    }

    public boolean unSubScribeTags(String str, String str2, String str3, String str4, String str5) {
        this.subScribeTagStrategy.setAppId(str);
        this.subScribeTagStrategy.setAppKey(str2);
        this.subScribeTagStrategy.setStrategyPackageNanme(str3);
        this.subScribeTagStrategy.setPushId(str4);
        this.subScribeTagStrategy.setSubTagType(1);
        this.subScribeTagStrategy.setSubTags(str5);
        return this.subScribeTagStrategy.process();
    }

    public boolean unSubScribeAllTags(String str, String str2, String str3, String str4) {
        this.subScribeTagStrategy.setAppId(str);
        this.subScribeTagStrategy.setAppKey(str2);
        this.subScribeTagStrategy.setStrategyPackageNanme(str3);
        this.subScribeTagStrategy.setPushId(str4);
        this.subScribeTagStrategy.setSubTagType(2);
        return this.subScribeTagStrategy.process();
    }

    public boolean checkSubScribeTags(String str, String str2, String str3, String str4) {
        this.subScribeTagStrategy.setAppId(str);
        this.subScribeTagStrategy.setAppKey(str2);
        this.subScribeTagStrategy.setStrategyPackageNanme(str3);
        this.subScribeTagStrategy.setPushId(str4);
        this.subScribeTagStrategy.setSubTagType(3);
        return this.subScribeTagStrategy.process();
    }

    public boolean subScribeAlias(String str, String str2, String str3, String str4, String str5) {
        this.subScribeAliasStrategy.setAppId(str);
        this.subScribeAliasStrategy.setAppKey(str2);
        this.subScribeAliasStrategy.setStrategyPackageNanme(str3);
        this.subScribeAliasStrategy.setPushId(str4);
        this.subScribeAliasStrategy.setSubAliasType(0);
        this.subScribeAliasStrategy.setAlias(str5);
        return this.subScribeAliasStrategy.process();
    }

    public boolean unSubScribeAlias(String str, String str2, String str3, String str4, String str5) {
        this.subScribeAliasStrategy.setAppId(str);
        this.subScribeAliasStrategy.setAppKey(str2);
        this.subScribeAliasStrategy.setStrategyPackageNanme(str3);
        this.subScribeAliasStrategy.setPushId(str4);
        this.subScribeAliasStrategy.setSubAliasType(1);
        this.subScribeAliasStrategy.setAlias(str5);
        return this.subScribeAliasStrategy.process();
    }

    public boolean checkSubScribeAlias(String str, String str2, String str3, String str4) {
        this.subScribeAliasStrategy.setAppId(str);
        this.subScribeAliasStrategy.setAppKey(str2);
        this.subScribeAliasStrategy.setStrategyPackageNanme(str3);
        this.subScribeAliasStrategy.setPushId(str4);
        this.subScribeAliasStrategy.setSubAliasType(2);
        return this.subScribeAliasStrategy.process();
    }

    public ANResponse<String> uploadLogFile(String str, String str2, String str3, File file) {
        return this.pushAPI.uploadLogFile(str, str2, str3, file);
    }
}
