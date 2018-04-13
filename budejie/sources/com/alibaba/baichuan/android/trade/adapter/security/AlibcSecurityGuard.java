package com.alibaba.baichuan.android.trade.adapter.security;

import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.adapter.ut.UserTrackerCompoment;
import com.alibaba.baichuan.android.trade.adapter.ut.performance.Point4Init;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.model.InitResult;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.dynamicdataencrypt.IDynamicDataEncryptComponent;
import com.alibaba.wireless.security.open.opensdk.IOpenSDKComponent;

public class AlibcSecurityGuard implements AlibcSecurity {
    private static AlibcSecurityGuard a;
    private static final String c = AlibcSecurityGuard.class.getSimpleName();
    private SecurityGuardManager b;
    private String d;
    private boolean e;

    private AlibcSecurityGuard() {
    }

    private void a(int i) {
        switch (i) {
            case 202:
            case 204:
            case 205:
            case 212:
                UserTrackerCompoment.sendUseabilityFailure(UserTrackerConstants.U_SECURITYGUARD_INIT, UserTrackerConstants.EM_WRONG_PICTURE);
                return;
            case 203:
                UserTrackerCompoment.sendUseabilityFailure(UserTrackerConstants.U_SECURITYGUARD_INIT, UserTrackerConstants.EM_NO_PICTURE);
                return;
            default:
                return;
        }
    }

    private Long analyzeOpenId(String str, byte[] bArr) {
        try {
            IOpenSDKComponent openSDKComp = SecurityGuardManager.getInstance(AlibcContext.context).getOpenSDKComp();
            if (openSDKComp != null) {
                try {
                    return openSDKComp.analyzeOpenId(str, "AppIDKey", "OpenIDSaltKey", bArr, null);
                } catch (SecException e) {
                    e.printStackTrace();
                }
            }
            return null;
        } catch (SecException e2) {
            return null;
        }
    }

    public static synchronized AlibcSecurityGuard getInstance() {
        AlibcSecurityGuard alibcSecurityGuard;
        synchronized (AlibcSecurityGuard.class) {
            if (a == null) {
                a = new AlibcSecurityGuard();
            }
            alibcSecurityGuard = a;
        }
        return alibcSecurityGuard;
    }

    public Long analyzeItemId(String str) {
        return analyzeOpenId(str, IOpenSDKComponent.OPEN_BIZ_IID);
    }

    public String dynamicDecrypt(String str) {
        if (this.b != null) {
            IDynamicDataEncryptComponent dynamicDataEncryptComp = this.b.getDynamicDataEncryptComp();
            if (dynamicDataEncryptComp != null) {
                try {
                    str = dynamicDataEncryptComp.dynamicDecrypt(str);
                } catch (SecException e) {
                    AlibcLogger.e(c, e.toString());
                }
            }
        }
        return str;
    }

    public String dynamicEncrypt(String str) {
        if (this.b != null) {
            IDynamicDataEncryptComponent dynamicDataEncryptComp = this.b.getDynamicDataEncryptComp();
            if (dynamicDataEncryptComp != null) {
                try {
                    str = dynamicDataEncryptComp.dynamicEncrypt(str);
                } catch (SecException e) {
                    AlibcLogger.e(c, e.toString());
                }
            }
        }
        return str;
    }

    public String getAppKey() {
        return this.d;
    }

    public synchronized InitResult init(Point4Init point4Init) {
        InitResult newSuccessResult;
        point4Init.timeBegin(UserTrackerConstants.PM_SECURITY_INIT_TIME);
        if (this.e) {
            newSuccessResult = InitResult.newSuccessResult();
        } else {
            try {
                int initialize = SecurityGuardManager.getInitializer().initialize(AlibcContext.context);
                this.b = SecurityGuardManager.getInstance(AlibcContext.context);
                this.d = this.b.getStaticDataStoreComp().getAppKeyByIndex(0, null);
                if (initialize == 0) {
                    if (this.d != null) {
                        this.e = true;
                        point4Init.timeEnd(UserTrackerConstants.PM_SECURITY_INIT_TIME);
                        newSuccessResult = InitResult.newSuccessResult();
                    }
                }
                a(initialize);
                AlibcLogger.e(c, "SecurityGuard init error : " + initialize);
                newSuccessResult = InitResult.newFailureResult(initialize, "SecurityGuard init error");
            } catch (SecException e) {
                a(e.getErrorCode());
                AlibcLogger.e(c, e.getMessage());
                newSuccessResult = InitResult.newFailureResult(e.getErrorCode(), "SecurityGuard init error");
            }
        }
        return newSuccessResult;
    }
}
