package com.taobao.tao.remotebusiness.login;

import mtopsdk.common.util.m;

public class RemoteLogin {
    public static final String TAG = "mtop.rb-Login";
    private static IRemoteLogin login = null;

    public static IRemoteLogin getLogin() {
        if (login == null) {
            IRemoteLogin defaultLoginImpl = DefaultLoginImpl.getDefaultLoginImpl();
            login = defaultLoginImpl;
            if (defaultLoginImpl == null) {
                m.d(TAG, "login is null");
                throw new LoginNotImplementException("Login Not Implement!");
            }
        }
        return login;
    }

    public static LoginContext getLoginContext() {
        return getLogin().getLoginContext();
    }

    public static boolean isSessionValid() {
        IRemoteLogin login = getLogin();
        return login.isLogining() ? false : login.isSessionValid();
    }

    public static void login(boolean z) {
        login(z, null);
    }

    public static void login(boolean z, Object obj) {
        IRemoteLogin login = getLogin();
        if (!login.isLogining()) {
            m.b(TAG, "call login");
            if (obj != null && (login instanceof DefaultLoginImpl)) {
                ((DefaultLoginImpl) login).setSessionInvalid(obj);
            }
            login.login(LoginHandler.instance(), z);
            LoginHandler.instance().sendEmptyMessageDelayed(LoginHandler.LOGIN_TIMEOUT, 20000);
        }
    }

    public static void setLoginImpl(IRemoteLogin iRemoteLogin) {
        login = iRemoteLogin;
    }
}
