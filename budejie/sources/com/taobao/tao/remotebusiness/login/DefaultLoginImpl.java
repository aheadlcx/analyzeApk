package com.taobao.tao.remotebusiness.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.alibaba.fastjson.JSON;
import com.taobao.tao.remotebusiness.listener.c;
import java.lang.reflect.Method;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.f;
import mtopsdk.mtop.domain.MtopRequest;
import mtopsdk.mtop.domain.MtopResponse;

public final class DefaultLoginImpl implements IRemoteLogin {
    private static final String MTOP_API_REFERENCE = "apiReferer";
    private static final String TAG = "mtop.rb-DefaultLoginImpl";
    public static volatile DefaultLoginImpl instance = null;
    private static ThreadLocal threadLocal = new ThreadLocal();
    private Method checkSessionValidMethod;
    private Method getNickMethod;
    private Method getSidMethod;
    private Method getUserIdMethod;
    private Method isLoginingMethod;
    private Class loginBroadcastHelperCls = null;
    private Class loginCls = null;
    private LoginContext loginContext = new LoginContext();
    private Method loginMethod;
    private Class loginStatusCls = null;
    protected BroadcastReceiver receiver = null;
    private Method registerReceiverMethod;

    class SessionInvalidEvent {
        private static final String BUNDLE_KEY = "apiReferer";
        private static final String HEADER_KEY = "S";
        public String S_STATUS;
        public String apiName;
        public String eventName;
        public String long_nick;
        public String msgCode;
        public String v;

        public SessionInvalidEvent(MtopRequest mtopRequest) {
            this.apiName = mtopRequest.getApiName();
            this.v = mtopRequest.getVersion();
        }

        public SessionInvalidEvent(MtopResponse mtopResponse, String str) {
            this.eventName = "SESSION_INVALID";
            this.long_nick = str;
            this.apiName = mtopResponse.getApi();
            this.v = mtopResponse.getV();
            this.msgCode = mtopResponse.getRetCode();
            this.S_STATUS = c.a(mtopResponse.getHeaderFields(), HEADER_KEY);
        }

        public String toJSONString() {
            return JSON.toJSONString(this);
        }
    }

    private DefaultLoginImpl() {
        try {
            this.loginCls = Class.forName("com.taobao.login4android.Login");
        } catch (ClassNotFoundException e) {
            this.loginCls = Class.forName("com.taobao.login4android.api.Login");
        }
        this.loginMethod = this.loginCls.getDeclaredMethod("login", new Class[]{Boolean.TYPE, Bundle.class});
        this.checkSessionValidMethod = this.loginCls.getDeclaredMethod("checkSessionValid", new Class[0]);
        this.getSidMethod = this.loginCls.getDeclaredMethod("getSid", new Class[0]);
        this.getUserIdMethod = this.loginCls.getDeclaredMethod("getUserId", new Class[0]);
        this.getNickMethod = this.loginCls.getDeclaredMethod("getNick", new Class[0]);
        this.loginStatusCls = Class.forName("com.taobao.login4android.constants.LoginStatus");
        this.isLoginingMethod = this.loginStatusCls.getDeclaredMethod("isLogining", new Class[0]);
        this.loginBroadcastHelperCls = Class.forName("com.taobao.login4android.broadcast.LoginBroadcastHelper");
        this.registerReceiverMethod = this.loginBroadcastHelperCls.getMethod("registerLoginReceiver", new Class[]{Context.class, BroadcastReceiver.class});
        registerReceiver();
        m.b(TAG, "register login event receiver");
    }

    public static DefaultLoginImpl getDefaultLoginImpl() {
        if (instance == null) {
            synchronized (DefaultLoginImpl.class) {
                if (instance == null) {
                    try {
                        instance = new DefaultLoginImpl();
                    } catch (Throwable e) {
                        instance = null;
                        m.b(TAG, "DefaultLoginImpl instance error", e);
                    }
                }
            }
        }
        return instance;
    }

    private Object invokeMethod(Method method, Object... objArr) {
        if (method != null) {
            try {
                return method.invoke(this.loginCls, objArr);
            } catch (Throwable e) {
                m.b(TAG, "invokeMethod error", e);
            }
        }
        return null;
    }

    private void registerReceiver() {
        if (this.receiver == null) {
            if (f.a().b() == null) {
                m.c(TAG, "Context is null, register receiver fail.");
                return;
            }
            synchronized (DefaultLoginImpl.class) {
                if (this.receiver == null) {
                    this.receiver = new BroadcastReceiver() {
                        public void onReceive(Context context, Intent intent) {
                            if (intent != null) {
                                String action = intent.getAction();
                                if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                                    m.b(DefaultLoginImpl.TAG, "Login Broadcast Received. action=" + action);
                                }
                                if ("NOTIFY_LOGIN_SUCCESS".equals(action)) {
                                    LoginHandler.instance().onLoginSuccess();
                                } else if ("NOTIFY_LOGIN_FAILED".equals(action)) {
                                    LoginHandler.instance().onLoginFail();
                                } else if ("NOTIFY_LOGIN_CANCEL".equals(action)) {
                                    LoginHandler.instance().onLoginCancel();
                                }
                            }
                        }
                    };
                    invokeMethod(this.registerReceiverMethod, r0, this.receiver);
                }
            }
        }
    }

    public final LoginContext getLoginContext() {
        this.loginContext.sid = (String) invokeMethod(this.getSidMethod, new Object[0]);
        this.loginContext.userId = (String) invokeMethod(this.getUserIdMethod, new Object[0]);
        this.loginContext.nickname = (String) invokeMethod(this.getNickMethod, new Object[0]);
        return this.loginContext;
    }

    public final boolean isLogining() {
        Boolean bool = (Boolean) invokeMethod(this.isLoginingMethod, new Object[0]);
        return bool != null ? bool.booleanValue() : false;
    }

    public final boolean isSessionValid() {
        Boolean bool = (Boolean) invokeMethod(this.checkSessionValidMethod, new Object[0]);
        return bool != null ? bool.booleanValue() : false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void login(com.taobao.tao.remotebusiness.login.onLoginListener r6, boolean r7) {
        /*
        r5 = this;
        r0 = "mtop.rb-DefaultLoginImpl";
        r1 = "call login";
        mtopsdk.common.util.m.b(r0, r1);
        r2 = 0;
        r0 = threadLocal;
        r0 = r0.get();
        r0 = (com.taobao.tao.remotebusiness.login.DefaultLoginImpl.SessionInvalidEvent) r0;
        if (r0 == 0) goto L_0x006a;
    L_0x0012:
        r1 = new android.os.Bundle;	 Catch:{ Exception -> 0x0058, all -> 0x0060 }
        r1.<init>();	 Catch:{ Exception -> 0x0058, all -> 0x0060 }
        r0 = r0.toJSONString();	 Catch:{ Exception -> 0x0067, all -> 0x0060 }
        r2 = mtopsdk.common.util.TBSdkLog$LogEnable.InfoEnable;	 Catch:{ Exception -> 0x0067, all -> 0x0060 }
        r2 = mtopsdk.common.util.m.a(r2);	 Catch:{ Exception -> 0x0067, all -> 0x0060 }
        if (r2 == 0) goto L_0x0037;
    L_0x0023:
        r2 = "mtop.rb-DefaultLoginImpl";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0067, all -> 0x0060 }
        r4 = "apiRefer=";
        r3.<init>(r4);	 Catch:{ Exception -> 0x0067, all -> 0x0060 }
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x0067, all -> 0x0060 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0067, all -> 0x0060 }
        mtopsdk.common.util.m.b(r2, r3);	 Catch:{ Exception -> 0x0067, all -> 0x0060 }
    L_0x0037:
        r2 = "apiReferer";
        r1.putString(r2, r0);	 Catch:{ Exception -> 0x0067, all -> 0x0060 }
        r0 = threadLocal;
        r0.remove();
        r0 = r1;
    L_0x0042:
        r5.registerReceiver();
        r1 = r5.loginMethod;
        r2 = 2;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = java.lang.Boolean.valueOf(r7);
        r2[r3] = r4;
        r3 = 1;
        r2[r3] = r0;
        r5.invokeMethod(r1, r2);
        return;
    L_0x0058:
        r0 = move-exception;
        r0 = r2;
    L_0x005a:
        r1 = threadLocal;
        r1.remove();
        goto L_0x0042;
    L_0x0060:
        r0 = move-exception;
        r1 = threadLocal;
        r1.remove();
        throw r0;
    L_0x0067:
        r0 = move-exception;
        r0 = r1;
        goto L_0x005a;
    L_0x006a:
        r0 = r2;
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.tao.remotebusiness.login.DefaultLoginImpl.login(com.taobao.tao.remotebusiness.login.onLoginListener, boolean):void");
    }

    public final void setSessionInvalid(Object obj) {
        if (obj instanceof MtopResponse) {
            threadLocal.set(new SessionInvalidEvent((MtopResponse) obj, (String) invokeMethod(this.getNickMethod, new Object[0])));
        } else if (obj instanceof MtopRequest) {
            threadLocal.set(new SessionInvalidEvent((MtopRequest) obj));
        }
    }
}
