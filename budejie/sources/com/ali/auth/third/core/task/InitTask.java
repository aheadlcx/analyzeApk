package com.ali.auth.third.core.task;

import android.os.AsyncTask;
import com.ali.auth.third.core.callback.InitResultCallback;
import com.ali.auth.third.core.config.ConfigManager;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.device.DeviceInfo;
import com.ali.auth.third.core.exception.AlibabaSDKException;
import com.ali.auth.third.core.message.Message;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.core.model.KernelMessageConstants;
import com.ali.auth.third.core.model.ResultCode;
import com.ali.auth.third.core.registry.ServiceRegistry;
import com.ali.auth.third.core.service.CredentialService;
import com.ali.auth.third.core.service.MemberExecutorService;
import com.ali.auth.third.core.service.RpcService;
import com.ali.auth.third.core.service.StorageService;
import com.ali.auth.third.core.service.UserTrackerService;
import com.ali.auth.third.core.service.impl.CredentialManager;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.core.util.CommonUtils;
import com.ali.auth.third.core.util.ReflectionUtils;
import com.ali.auth.third.mtop.rpc.ResultActionType;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.umeng.analytics.pro.d.c;
import java.io.File;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class InitTask implements Runnable {
    private static final String TAG = "kernel";
    private Integer envIndex;
    private InitResultCallback initResultCallback;
    private CountDownLatch initializationLock = new CountDownLatch(1);

    public InitTask(final InitResultCallback initResultCallback, Integer num) {
        this.initResultCallback = new InitResultCallback() {
            public void onFailure(int i, String str) {
                if (initResultCallback != null) {
                    initResultCallback.onFailure(i, str);
                }
                InitTask.this.invokeInitResultCallbacks(false, i, str);
            }

            public void onSuccess() {
                if (initResultCallback != null) {
                    initResultCallback.onSuccess();
                }
                InitTask.this.invokeInitResultCallbacks(true, ResultCode.SUCCESS.code, null);
            }
        };
        this.envIndex = num;
    }

    private void invokeInitResultCallbacks(boolean z, int i, String str) {
        final InitResultCallback[] initResultCallbackArr = (InitResultCallback[]) KernelContext.serviceRegistry.getServices(InitResultCallback.class, null);
        if (initResultCallbackArr != null) {
            final boolean z2 = z;
            final int i2 = i;
            final String str2 = str;
            KernelContext.executorService.postTask(new Runnable() {
                public void run() {
                    for (InitResultCallback initResultCallback : initResultCallbackArr) {
                        try {
                            if (z2) {
                                initResultCallback.onSuccess();
                            } else {
                                initResultCallback.onFailure(i2, str2);
                            }
                        } catch (Throwable e) {
                            SDKLogger.e("kernel", e.getMessage(), e);
                        }
                    }
                }
            });
        }
    }

    public boolean initialize() {
        SDKLogger.d("", "sdk version = " + ConfigManager.SDK_VERSION.toString());
        initializeUTDId();
        if (KernelContext.syncInitialized) {
            return true;
        }
        try {
            if (initializeCoreComponents()) {
                KernelContext.syncInitialized = true;
                return true;
            }
        } catch (Throwable th) {
            SDKLogger.e("kernel", "fail to sync start", th);
            doWhenException(th);
        }
        this.initializationLock.countDown();
        return false;
    }

    private void initializeUTDId() {
        DeviceInfo.init(KernelContext.context);
    }

    private boolean initializeCoreComponents() {
        try {
            Class.forName(AsyncTask.class.getName());
        } catch (Exception e) {
        }
        KernelContext.wrapServiceRegistry();
        ConfigManager.getInstance().init(this.envIndex.intValue());
        ServiceRegistry serviceRegistry = KernelContext.serviceRegistry;
        registerRpc(serviceRegistry);
        registerStorage(serviceRegistry);
        registerUserTrack(serviceRegistry);
        Class[] clsArr = new Class[]{MemberExecutorService.class, ExecutorService.class};
        serviceRegistry.registerService(clsArr, KernelContext.executorService, Collections.singletonMap(Constants.PLUGIN_VENDOR_KEY, "kernel"));
        serviceRegistry.registerService(new Class[]{CredentialService.class}, CredentialManager.INSTANCE, Collections.singletonMap("scop", c.a));
        KernelContext.credentialService = (CredentialService) serviceRegistry.getService(CredentialService.class, null);
        boolean loadLogin = loadLogin();
        if (!KernelContext.isMini) {
            loadAccountLink();
        }
        SDKLogger.d("syncRun", "INIT SUCCESS");
        return loadLogin;
    }

    public void await() {
        try {
            this.initializationLock.await();
        } catch (Throwable e) {
            SDKLogger.e("kernel", e.getMessage(), e);
        }
    }

    private void registerStorage(ServiceRegistry serviceRegistry) {
        Object obj = 1;
        SDKLogger.d("kernel", "registerStorage");
        try {
            Class.forName("com.ali.auth.third.securityguard.SecurityGuardWrapper");
            try {
                KernelContext.isMini = false;
                KernelContext.sdkVersion = KernelContext.SDK_VERSION_STD;
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
            obj = null;
        }
        if (obj != null) {
            try {
                obj = getServiceInstance("com.ali.auth.third.securityguard.SecurityGuardWrapper", null, null);
            } catch (NoSuchMethodError e) {
                e.printStackTrace();
                return;
            }
        }
        obj = getServiceInstance("com.ali.auth.third.core.storage.CommonStorageServiceImpl", null, null);
        serviceRegistry.registerService(new Class[]{StorageService.class}, obj, null);
        KernelContext.storageService = (StorageService) serviceRegistry.getService(StorageService.class, null);
    }

    private void registerRpc(ServiceRegistry serviceRegistry) {
        Object obj = 1;
        SDKLogger.d("kernel", "registerRpc");
        try {
            Class.forName("com.ali.auth.third.mtop.rpc.impl.MtopRpcServiceImpl");
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            try {
                obj = getServiceInstance("com.ali.auth.third.mtop.rpc.impl.MtopRpcServiceImpl", null, null);
            } catch (NoSuchMethodError e) {
                e.printStackTrace();
                return;
            }
        }
        obj = getServiceInstance("com.ali.auth.third.core.rpc.CommRpcServiceImpl", null, null);
        serviceRegistry.registerService(new Class[]{RpcService.class}, obj, null);
    }

    private void registerUserTrack(ServiceRegistry serviceRegistry) {
        Object obj = 1;
        SDKLogger.d("kernel", "registerUserTrack");
        try {
            String str = "com.ali.auth.third.ut.UserTrackerImpl";
            try {
                Class.forName(str);
            } catch (Throwable th) {
                obj = null;
            }
            if (obj != null) {
                obj = getServiceInstance(str, null, null);
            } else {
                obj = getServiceInstance("com.ali.auth.third.core.ut.UserTracer", null, null);
            }
            serviceRegistry.registerService(new Class[]{UserTrackerService.class}, obj, null);
        } catch (NoSuchMethodError e) {
            e.printStackTrace();
        }
    }

    private Object getServiceInstance(String str, String[] strArr, Object[] objArr) {
        try {
            return ReflectionUtils.newInstance(str, strArr, objArr);
        } catch (NoSuchMethodError e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean loadLogin() {
        SDKLogger.d("kernel", "register login service");
        try {
            ReflectionUtils.invoke("com.ali.auth.third.login.LoginLifecycleAdapter", UserTrackerConstants.P_INIT, null, Class.forName("com.ali.auth.third.login.LoginLifecycleAdapter"), null);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean loadAccountLink() {
        SDKLogger.d("kernel", "register account link service");
        try {
            ReflectionUtils.invoke("com.ali.auth.third.accountlink.AccountLinkLifecycleAdapter", UserTrackerConstants.P_INIT, null, Class.forName("com.ali.auth.third.accountlink.AccountLinkLifecycleAdapter"), null);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public void run() {
        try {
            KernelContext.initLock.lock();
            asyncRun();
        } catch (Throwable th) {
            SDKLogger.e("kernel", th.getMessage(), th);
            doWhenException(th);
        } finally {
            this.initializationLock.countDown();
            doFinally();
        }
    }

    private boolean asyncRun() {
        try {
            long timeStamp = getTimeStamp();
            SDKLogger.e("kernel", "timeStamp=" + timeStamp);
            KernelContext.timestamp = timeStamp;
        } catch (Throwable e) {
            SDKLogger.e("kernel", e.getMessage(), e);
        }
        if (initialize()) {
            KernelContext.executorService.postUITask(new Runnable() {
                public void run() {
                    if (InitTask.this.initResultCallback != null) {
                        InitTask.this.initResultCallback.onSuccess();
                    }
                }
            });
            KernelContext.isInitOk = Boolean.valueOf(true);
            SDKLogger.d("asyncRun", ResultActionType.SUCCESS);
            return true;
        }
        SDKLogger.d("asyncRun", "FAILURE");
        return false;
    }

    protected void doWhenException(Throwable th) {
        int i;
        String commonUtils;
        KernelContext.isInitOk = Boolean.valueOf(false);
        if (!(th instanceof AlibabaSDKException) || ((AlibabaSDKException) th).getSDKMessage() == null) {
            i = KernelMessageConstants.GENERIC_SYSTEM_ERROR;
            commonUtils = CommonUtils.toString(th);
        } else {
            Message sDKMessage = ((AlibabaSDKException) th).getSDKMessage();
            i = sDKMessage.code;
            commonUtils = sDKMessage.message;
        }
        CommonUtils.onFailure(this.initResultCallback, i, commonUtils);
    }

    protected void doFinally() {
        KernelContext.initLock.unlock();
    }

    private long getTimeStamp() {
        File file = new File(KernelContext.context.getFilesDir().getAbsolutePath() + File.separator + "timestamp");
        if (file.exists()) {
            return file.lastModified();
        }
        try {
            file.createNewFile();
            return file.lastModified();
        } catch (Throwable e) {
            SDKLogger.e("kernel", e.getMessage(), e);
            return 0;
        }
    }
}
