package com.alibaba.baichuan.android.trade.constants;

import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.AlibcContext.Environment;
import com.umeng.onlineconfig.a;

public class ConfigConstant {
    public static final String CHECK_GROUP_NAME = "group0";
    public static final String MD5_SALT = "ALITRADE20160628";
    public static final String SIGN_KEY = "sign";
    public static final String SP_CONFIG_NAME = "aliTradeConfigSP";

    /* renamed from: com.alibaba.baichuan.android.trade.constants.ConfigConstant$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Environment.values().length];

        static {
            try {
                a[Environment.PRE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Environment.TEST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static String getConfigUrl() {
        String str;
        switch (AnonymousClass1.a[AlibcContext.environment.ordinal()]) {
            case 1:
                str = "http://nbsdk-baichuan.taobao.com/%s/%s/%s/meta.htm?plat=android";
                break;
            case 2:
                str = "http://100.69.205.47/%s/%s/%s/meta.htm?plat=android";
                break;
            default:
                str = "https://nbsdk-baichuan.alicdn.com/%s/%s/%s/meta.htm?plat=android";
                break;
        }
        return String.format(str, new Object[]{AlibcContext.sdkVersion, AlibcContext.getAppKey(), a.b});
    }
}
