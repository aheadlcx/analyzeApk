package com.sina.weibo.sdk.cmd;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.net.NetUtils;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.Utility;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import qsbk.app.im.TimeUtils;

public class WbAppActivator {
    private static final String a = WbAppActivator.class.getName();
    private static WbAppActivator c;
    private Context b;
    private String d;
    private volatile ReentrantLock e = new ReentrantLock(true);
    private d f;
    private b g;

    private static class a {
        public static SharedPreferences getWeiboSdkSp(Context context) {
            return context.getSharedPreferences("com_sina_weibo_sdk", 0);
        }

        public static long getFrequency(Context context, SharedPreferences sharedPreferences) {
            if (sharedPreferences != null) {
                return sharedPreferences.getLong("frequency_get_cmd", TimeUtils.HOUR);
            }
            return TimeUtils.HOUR;
        }

        public static void saveFrequency(Context context, SharedPreferences sharedPreferences, long j) {
            if (sharedPreferences != null && j > 0) {
                Editor edit = sharedPreferences.edit();
                edit.putLong("frequency_get_cmd", j);
                edit.commit();
            }
        }

        public static long getLastTime(Context context, SharedPreferences sharedPreferences) {
            if (sharedPreferences != null) {
                return sharedPreferences.getLong("last_time_get_cmd", 0);
            }
            return 0;
        }

        public static void saveLastTime(Context context, SharedPreferences sharedPreferences, long j) {
            if (sharedPreferences != null) {
                Editor edit = sharedPreferences.edit();
                edit.putLong("last_time_get_cmd", j);
                edit.commit();
            }
        }
    }

    private WbAppActivator(Context context, String str) {
        this.b = context.getApplicationContext();
        this.f = new d(this.b);
        this.g = new b(this.b);
        this.d = str;
    }

    public static synchronized WbAppActivator getInstance(Context context, String str) {
        WbAppActivator wbAppActivator;
        synchronized (WbAppActivator.class) {
            if (c == null) {
                c = new WbAppActivator(context, str);
            }
            wbAppActivator = c;
        }
        return wbAppActivator;
    }

    public void activateApp() {
        SharedPreferences weiboSdkSp = a.getWeiboSdkSp(this.b);
        if (System.currentTimeMillis() - a.getLastTime(this.b, weiboSdkSp) < a.getFrequency(this.b, weiboSdkSp)) {
            LogUtil.v(a, String.format("it's only %d ms from last time get cmd", new Object[]{Long.valueOf(r4)}));
            return;
        }
        new Thread(new g(this, weiboSdkSp)).start();
    }

    private static String b(Context context, String str) {
        String str2 = "http://api.weibo.cn/2/client/common_config";
        str2 = context.getPackageName();
        String sign = Utility.getSign(context, str2);
        WeiboParameters weiboParameters = new WeiboParameters(str);
        weiboParameters.put("appkey", str);
        weiboParameters.put("packagename", str2);
        weiboParameters.put("key_hash", sign);
        weiboParameters.put("version", WBConstants.WEIBO_SDK_VERSION_CODE);
        return NetUtils.internalHttpRequest(context, "http://api.weibo.cn/2/client/common_config", "GET", weiboParameters);
    }

    private void a(List<a> list) {
        if (list != null) {
            this.g.start();
            for (a doExecutor : list) {
                this.g.doExecutor(doExecutor);
            }
            this.g.stop();
        }
    }

    private void b(List<c> list) {
        if (list != null) {
            for (c doExecutor : list) {
                this.f.doExecutor(doExecutor);
            }
        }
    }
}
