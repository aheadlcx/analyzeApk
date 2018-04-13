package com.alibaba.baichuan.android.auth;

import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.adapter.ut.AlibcUserTracker;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.taobao.tao.remotebusiness.auth.AuthListener;
import com.taobao.tao.remotebusiness.auth.IRemoteAuth;
import java.util.List;

public class f implements IRemoteAuth {
    private boolean a;

    private static class a {
        public static f a = new f();
    }

    class b implements e {
        AuthListener a;
        final /* synthetic */ f b;

        b(f fVar, AuthListener authListener) {
            this.b = fVar;
            this.a = authListener;
        }

        public void a() {
            this.b.a(false);
            if (this.a != null) {
                this.a.onAuthSuccess();
            }
        }

        public void a(String str, String str2) {
            this.b.a(false);
            if (this.a != null) {
                this.a.onAuthFail(str, str2);
            }
        }

        public void b() {
            this.b.a(false);
            if (this.a != null) {
                this.a.onAuthCancel("FAIL_SYS_ACCESS_TOKEN_CANCEL", "用户取消授权");
            }
        }

        public void c() {
            this.b.a(true);
        }
    }

    private f() {
        this.a = false;
    }

    public static f a() {
        return a.a;
    }

    private synchronized void a(boolean z) {
        this.a = z;
    }

    public void authorize(String str, String str2, String str3, boolean z, AuthListener authListener) {
        AlibcLogger.d("Alibc", "call authorize authParam = " + str + " api_v = " + str2 + " errorInfo = " + str3);
        a(true);
        if (TextUtils.isEmpty(str)) {
            AlibcAuth.auth(str2, str3, z, new b(this, authListener));
        } else {
            List a = AlibcAuth.a(str);
            AlibcAuth.postHintList(str, str3);
            AlibcAuth.auth(a, str3, z, new b(this, authListener));
        }
        if (!TextUtils.isEmpty(str3)) {
            AlibcUserTracker.getInstance().sendUseabilityFailure(UserTrackerConstants.P_BCPCSDK, "Hint_List_Error", "190101", "权限列表配置错误");
        }
    }

    public String getAuthToken() {
        String b = d.a().b();
        AlibcLogger.d("Alibc", "getAuthToken = " + b);
        return b;
    }

    public boolean isAuthInfoValid() {
        boolean d = d.a().d();
        AlibcLogger.d("Alibc", "isAuthInfoValid = " + d);
        return d;
    }

    public synchronized boolean isAuthorizing() {
        AlibcLogger.d("Alibc", "isAuthorizing = " + this.a);
        return this.a;
    }
}
