package com.taobao.tao.remotebusiness.login;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.f;
import mtopsdk.xstate.a;

class LoginHandler extends Handler implements onLoginListener {
    public static final int LOGIN_CANCEL = 911103;
    public static final int LOGIN_FAILED = 911102;
    public static final int LOGIN_SUCCESS = 911101;
    public static final int LOGIN_TIMEOUT = 911104;
    private static final String TAG = "mtop.rb-LoginHandler";
    private static LoginHandler mHandler;

    private LoginHandler(Looper looper) {
        super(looper);
    }

    private static void checkXStateSessionInfo() {
        LoginContext loginContext = RemoteLogin.getLoginContext();
        if (loginContext != null) {
            try {
                if (l.a(loginContext.sid) && !loginContext.sid.equals(a.a())) {
                    mtopsdk.mtop.b.a.a(f.a().b()).a(loginContext.sid, loginContext.userId);
                    m.b(TAG, "[checkXStateSessionInfo] invoked");
                }
            } catch (Exception e) {
                m.d(TAG, "[checkXStateSessionInfo] error ---" + e.toString());
            }
        }
    }

    public static synchronized LoginHandler instance() {
        LoginHandler loginHandler;
        synchronized (LoginHandler.class) {
            if (mHandler == null) {
                mHandler = new LoginHandler(Looper.getMainLooper());
            }
            loginHandler = mHandler;
        }
        return loginHandler;
    }

    public void handleMessage(Message message) {
        m.a(TAG, "The RemoteBusiness handler message received.");
        switch (message.what) {
            case LOGIN_SUCCESS /*911101*/:
                m.b(TAG, "onReceive: NOTIFY_LOGIN_SUCCESS.");
                checkXStateSessionInfo();
                com.taobao.tao.remotebusiness.a.a();
                removeMessages(LOGIN_TIMEOUT);
                return;
            case LOGIN_FAILED /*911102*/:
                m.b(TAG, "onReceive: NOTIFY_LOGINFAILED.");
                com.taobao.tao.remotebusiness.a.a("FAIL_SYS_LOGIN_FAIL", UserTrackerConstants.EM_LOGIN_FAILURE);
                removeMessages(LOGIN_TIMEOUT);
                return;
            case LOGIN_CANCEL /*911103*/:
                m.b(TAG, "onReceive: NOTIFY_LOGINCANCEL.");
                com.taobao.tao.remotebusiness.a.a("FAIL_SYS_LOGIN_CANCEL", "登陆被取消");
                removeMessages(LOGIN_TIMEOUT);
                return;
            case LOGIN_TIMEOUT /*911104*/:
                if (RemoteLogin.isSessionValid()) {
                    m.b(TAG, "Session valid, Broadcast may missed!");
                    checkXStateSessionInfo();
                    com.taobao.tao.remotebusiness.a.a();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onLoginCancel() {
        sendEmptyMessage(LOGIN_CANCEL);
    }

    public void onLoginFail() {
        sendEmptyMessage(LOGIN_FAILED);
    }

    public void onLoginSuccess() {
        sendEmptyMessage(LOGIN_SUCCESS);
    }
}
