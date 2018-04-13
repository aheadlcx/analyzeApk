package com.taobao.tao.remotebusiness.auth;

import com.taobao.tao.remotebusiness.a;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;

public class RemoteAuth {
    private static final String TAG = "mtop.rb-RemoteAuth";
    private static IRemoteAuth iRemoteAuth = null;

    class AuthHandler implements AuthListener {

        class Holder {
            static AuthHandler instance = new AuthHandler();

            private Holder() {
            }
        }

        private AuthHandler() {
        }

        public static AuthHandler instance() {
            return Holder.instance;
        }

        public void onAuthCancel(String str, String str2) {
            m.b(RemoteAuth.TAG, "auth cancel");
            a.a(str, str2);
        }

        public void onAuthFail(String str, String str2) {
            m.b(RemoteAuth.TAG, "auth fail");
            a.a(str, str2);
        }

        public void onAuthSuccess() {
            m.b(RemoteAuth.TAG, "auth success");
            mtopsdk.xstate.a.a("accessToken", RemoteAuth.getAuthToken());
            a.a();
        }
    }

    public static void authorize(String str, String str2, String str3, boolean z) {
        if (iRemoteAuth != null && !iRemoteAuth.isAuthorizing()) {
            if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                m.b(TAG, "call auth. bizId=" + str + " apiInfo=" + str2 + " failInfo=" + str3);
            }
            iRemoteAuth.authorize(str, str2, str3, z, AuthHandler.instance());
        }
    }

    public static String getAuthToken() {
        return iRemoteAuth == null ? null : iRemoteAuth.getAuthToken();
    }

    public static boolean isAuthInfoValid() {
        return iRemoteAuth == null ? true : iRemoteAuth.isAuthorizing() ? false : iRemoteAuth.isAuthInfoValid();
    }

    public static void setAuthImpl(IRemoteAuth iRemoteAuth) {
        m.a(TAG, new StringBuilder("set auth implement. remoteAuth=").append(iRemoteAuth).toString() != null ? iRemoteAuth.toString() : "null");
        iRemoteAuth = iRemoteAuth;
    }
}
