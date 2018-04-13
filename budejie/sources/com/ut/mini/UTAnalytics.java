package com.ut.mini;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.AppMonitor;
import com.alibaba.mtl.log.b;
import com.alibaba.mtl.log.c;
import com.alibaba.mtl.log.e.i;
import com.ut.mini.base.UTMIVariables;
import com.ut.mini.core.appstatus.UTMCAppStatusRegHelper;
import com.ut.mini.core.sign.IUTRequestAuthentication;
import com.ut.mini.core.sign.UTBaseRequestAuthentication;
import com.ut.mini.core.sign.UTSecuritySDKRequestAuthentication;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import com.ut.mini.internal.UTTeamWork;
import com.ut.mini.plugin.UTPlugin;
import com.ut.mini.plugin.UTPluginMgr;
import com.ut.mini.sdkevents.UTMI1010_2001Event;
import java.util.HashMap;
import java.util.Map;

public class UTAnalytics {
    private static UTAnalytics a = null;
    /* renamed from: a */
    private UTTracker f13a;
    private Map<String, UTTracker> u = new HashMap();

    private UTAnalytics() {
        if (VERSION.SDK_INT < 14) {
            UTPlugin uTMI1010_2001Event = new UTMI1010_2001Event();
            UTPluginMgr.getInstance().registerPlugin(uTMI1010_2001Event, false);
            UTMIVariables.getInstance().setUTMI1010_2001EventInstance(uTMI1010_2001Event);
            return;
        }
        Object uTMI1010_2001Event2 = new UTMI1010_2001Event();
        UTMCAppStatusRegHelper.registerAppStatusCallbacks(uTMI1010_2001Event2);
        UTMIVariables.getInstance().setUTMI1010_2001EventInstance(uTMI1010_2001Event2);
    }

    public void setContext(Context context) {
        b.a().setContext(context);
        if (context != null) {
            UTTeamWork.getInstance().initialized();
        }
    }

    public void setAppApplicationInstance(Application application) {
        b.a().setAppApplicationInstance(application);
        AppMonitor.init(application);
        if (application != null) {
            try {
                ApplicationInfo applicationInfo = application.getPackageManager().getApplicationInfo(application.getPackageName(), 128);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    Object obj = applicationInfo.metaData.get("com.alibaba.apmplus.app_key") + "";
                    String str = applicationInfo.metaData.get("com.alibaba.apmplus.app_secret") + "";
                    String str2 = applicationInfo.metaData.get("com.alibaba.apmplus.authcode") + "";
                    Object obj2 = applicationInfo.metaData.get("com.alibaba.apmplus.channel") + "";
                    if (!TextUtils.isEmpty(obj)) {
                        AppMonitor.setRequestAuthInfo(o(), obj, str, str2);
                    }
                    if (!TextUtils.isEmpty(obj2)) {
                        AppMonitor.setChannel(obj2);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private boolean o() {
        try {
            Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static synchronized UTAnalytics getInstance() {
        UTAnalytics uTAnalytics;
        synchronized (UTAnalytics.class) {
            if (a == null) {
                a = new UTAnalytics();
            }
            uTAnalytics = a;
        }
        return uTAnalytics;
    }

    public synchronized UTTracker getDefaultTracker() {
        if (this.f13a == null) {
            this.f13a = new UTTracker();
        }
        if (this.f13a == null) {
            i.a("getDefaultTracker error", (Object) "Fatal Error,must call setRequestAuthentication method first.");
        }
        return this.f13a;
    }

    public void setRequestAuthentication(IUTRequestAuthentication iUTRequestAuthentication) {
        if (iUTRequestAuthentication == null) {
            i.a("setRequestAuthentication", (Object) "Fatal Error,pRequestAuth must not be null.");
        }
        if (iUTRequestAuthentication instanceof UTBaseRequestAuthentication) {
            AppMonitor.setRequestAuthInfo(false, iUTRequestAuthentication.getAppkey(), ((UTBaseRequestAuthentication) iUTRequestAuthentication).getAppSecret(), null);
        } else {
            AppMonitor.setRequestAuthInfo(true, iUTRequestAuthentication.getAppkey(), null, ((UTSecuritySDKRequestAuthentication) iUTRequestAuthentication).getAuthCode());
        }
    }

    public void setAppVersion(String str) {
        b.a().setAppVersion(str);
    }

    public synchronized UTTracker getTracker(String str) {
        UTTracker uTTracker;
        if (TextUtils.isEmpty(str)) {
            i.a("getTracker", (Object) "TrackId is null.");
            uTTracker = null;
        } else if (this.u.containsKey(str)) {
            uTTracker = (UTTracker) this.u.get(str);
        } else {
            uTTracker = new UTTracker();
            uTTracker.p(str);
            this.u.put(str, uTTracker);
        }
        return uTTracker;
    }

    public void setChannel(String str) {
        AppMonitor.setChannel(str);
    }

    public void turnOnDebug() {
        b.a().turnOnDebug();
    }

    public void updateUserAccount(String str, String str2) {
        b.a().updateUserAccount(str, str2);
    }

    public void userRegister(String str) {
        if (TextUtils.isEmpty(str)) {
            i.a("userRegister", (Object) "Fatal Error,usernick can not be null or empty!");
            return;
        }
        UTTracker defaultTracker = getDefaultTracker();
        if (defaultTracker != null) {
            defaultTracker.send(new UTOriginalCustomHitBuilder("UT", 1006, str, null, null, null).build());
        } else {
            i.a("Record userRegister event error", (Object) "Fatal Error,must call setRequestAuthentication method first.");
        }
    }

    public void updateSessionProperties(Map<String, String> map) {
        Map a = c.a().a();
        Map hashMap = new HashMap();
        if (a != null) {
            hashMap.putAll(a);
        }
        hashMap.putAll(map);
        c.a().c(hashMap);
    }

    public void turnOffAutoPageTrack() {
        UTPageHitHelper.getInstance().turnOffAutoPageTrack();
    }
}
