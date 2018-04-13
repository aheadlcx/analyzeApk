package cn.tatagou.sdk.android;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.e.a;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.util.b;
import cn.tatagou.sdk.util.i;
import cn.tatagou.sdk.util.p;
import com.ali.auth.third.core.MemberSDK;
import com.ali.auth.third.core.config.AuthOption;
import com.ali.auth.third.mtop.rpc.ResultActionType;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import java.util.Map;

public class TtgSDK {
    private static final String TAG = TtgSDK.class.getSimpleName();
    private static Context context;
    public static boolean isDebug = false;
    public static boolean isShowLog = false;
    public static boolean isTest = true;
    public static int sBcInitFlag = 0;
    public static String sSource = TtgSource.QLDS;

    public static void setShowLog(boolean z) {
        isShowLog = z;
    }

    public static void setSource(String str) {
        sSource = str;
    }

    public static void onInitBcSucc() {
        sBcInitFlag = 1;
    }

    public static void onInitBcFail(int i, String str) {
        sBcInitFlag = -1;
    }

    public static void setIsDebug(boolean z) {
        isDebug = z;
    }

    public static void init(Application application, String str) {
        init(application, str, null);
    }

    public static void init(Application application, String str, Map<String, String> map) {
        initTtg(application, str, map, null);
        initBcSdk(null);
    }

    public static void init(Application application, String str, Map<String, String> map, TtgCallback ttgCallback) {
        initTtg(application, str, map, ttgCallback);
        initBcSdk(ttgCallback);
    }

    public static void initTtg(Application application, String str, Map<String, String> map, TtgCallback ttgCallback) {
        sSource = str;
        if (context == null) {
            context = application.getApplicationContext();
            if (map != null && map.size() > 0) {
                String str2 = (String) map.get(TtgConfigKey.KEY_APPVERSION);
                Config appDeviceId = Config.getInstance().setAppDeviceId((String) map.get(TtgConfigKey.KEY_APPDEVICEID));
                if (p.isEmpty(str2)) {
                    str2 = p.getAppVersionName(context);
                }
                appDeviceId.setAppVersion(str2);
            } else if (p.isEmpty(Config.getInstance().getAppVersion())) {
                Config.getInstance().setAppVersion(p.getAppVersionName(context));
            }
            a init = a.init(context);
            if (!isDebug) {
                init.setLogClient(context.getResources().getString(R.string.ttg_release_log_endPoint), context.getResources().getString(R.string.ttg_release_log_accessKeyID), context.getResources().getString(R.string.ttg_release_log_accessKeySecret), context.getResources().getString(R.string.ttg_release_log_project));
                init.setLogStoreName(context.getResources().getString(R.string.ttg_release_log_logStore));
            }
            init.setSource(str).setAppSource(str).setAppVersion(Config.getInstance().getAppVersion()).setAndroidId(p.getAndroidID(context)).setSdkDeviceId(p.phoneImei(context)).setSdkVersion("2.4.4.6").setPlatform("ANDROID").setLogVersion("0.0.8").setPid(String.valueOf(TtgConfig.getInstance().getPid()));
            if (!p.isEmpty(Config.getInstance().getAppDeviceId())) {
                init.setAppDeviceId(Config.getInstance().getAppDeviceId());
            }
            if (!p.isEmpty(p.getAndroidID(context))) {
                init.setAndroidId(p.getAndroidID(context));
            }
            if (ttgCallback != null) {
                ttgCallback.onInitSuccess(null);
            }
            b.openReportLaunch();
        }
    }

    private static void initBcSdk(TtgCallback ttgCallback) {
        sBcInitFlag = 0;
        try {
            MemberSDK.init(context, new TtgSDK$1());
            AlibcTradeSDK.asyncInit(context, new TtgSDK$2(ttgCallback));
        } catch (Exception e) {
            e.printStackTrace();
            sBcInitFlag = -1;
            Log.d(TAG, "AliTradeSDK Exception : " + e.getMessage());
        }
    }

    private static void bcInitFail(int i, String str) {
        b.reportErrorLog(context, String.valueOf(i), str);
        sBcInitFlag = -1;
        if (isDebug) {
            i.getInstance().writeFile("AlibcTradeSDK: 初始化失败  code ：" + i + " ,=== msg : " + i + "\n");
            Log.d(TAG, "AlibcTradeSDK: 初始化失败  code ：" + i + " ,=== msg : " + i);
        }
    }

    private static void getSysCfg() {
        String loginType = Config.getInstance().getLoginType();
        if (p.isEmpty(loginType)) {
            b.getSysCfg(new TtgSDK$3());
        } else if (!p.isEmpty(loginType)) {
            MemberSDK.setAuthOption(ResultActionType.H5.equals(loginType) ? AuthOption.H5ONLY : AuthOption.NORMAL);
        }
    }

    public static Context getContext() {
        return context;
    }
}
