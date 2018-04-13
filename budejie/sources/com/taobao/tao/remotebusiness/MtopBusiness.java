package com.taobao.tao.remotebusiness;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.tao.remotebusiness.auth.RemoteAuth;
import com.taobao.tao.remotebusiness.listener.c;
import com.taobao.tao.remotebusiness.login.LoginContext;
import com.taobao.tao.remotebusiness.login.RemoteLogin;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.f;
import mtopsdk.mtop.b.b;
import mtopsdk.mtop.common.a;
import mtopsdk.mtop.common.k;
import mtopsdk.mtop.domain.MtopRequest;
import mtopsdk.mtop.domain.MtopResponse;

public class MtopBusiness extends b {
    public static final int MAX_RETRY_TIMES = 3;
    private static final String TAG = "mtop.rb-RemoteBusiness";
    private static AtomicInteger seqGen = new AtomicInteger(0);
    private a apiID;
    public String authParam = null;
    public Class clazz;
    public boolean isCached = false;
    private boolean isCanceled = false;
    private boolean isErrorNotifyAfterCache = false;
    public k listener;
    private MtopResponse mtopResponse = null;
    public long onBgFinishTime = 0;
    public long reqStartTime = 0;
    protected int requestType = 0;
    protected int retryTime = 0;
    public long sendStartTime = 0;
    private final String seqNo = genSeqNo();
    public boolean showAuthUI = true;
    private boolean showLoginUI = true;
    private CountDownLatch syncRequestLatch = null;

    protected MtopBusiness(MtopRequest mtopRequest, String str) {
        super(mtopRequest, str);
    }

    protected MtopBusiness(mtopsdk.mtop.domain.b bVar, String str) {
        super(bVar, str);
    }

    public static MtopBusiness build(MtopRequest mtopRequest) {
        return new MtopBusiness(mtopRequest, null);
    }

    public static MtopBusiness build(MtopRequest mtopRequest, String str) {
        return new MtopBusiness(mtopRequest, str);
    }

    public static MtopBusiness build(mtopsdk.mtop.domain.b bVar) {
        return new MtopBusiness(bVar, null);
    }

    public static MtopBusiness build(mtopsdk.mtop.domain.b bVar, String str) {
        return new MtopBusiness(bVar, str);
    }

    private void cancelRequest(boolean z) {
        if (z) {
            m.b(TAG, this.seqNo, c.a("cancelRequest.", this, false, null));
        }
        this.isCanceled = true;
        if (this.apiID != null) {
            try {
                this.apiID.b();
            } catch (Throwable th) {
                m.a(TAG, this.seqNo, c.a("Cancel request task failed.", this, true, null), th);
            }
        }
        a.b(this);
    }

    private void doQuery() {
        boolean isNeedEcode = this.request.isNeedEcode();
        boolean isNeedAuth = isNeedAuth();
        if (!isNeedEcode || RemoteLogin.isSessionValid()) {
            if (isNeedEcode) {
                try {
                    if (l.b(mtopsdk.xstate.a.a())) {
                        m.c(TAG, this.seqNo, "[doQuery] session in loginContext is valid but XState's sid is null");
                        LoginContext loginContext = RemoteLogin.getLoginContext();
                        if (loginContext == null || l.b(loginContext.sid)) {
                            a.a(this);
                            RemoteLogin.login(this.showLoginUI, this.request);
                            return;
                        }
                        mtopsdk.mtop.b.a.a(f.a().b()).a(loginContext.sid, loginContext.userId);
                    }
                } catch (Exception e) {
                    m.b(TAG, this.seqNo, "error happens in confirming session info");
                }
            }
            if (isNeedEcode && isNeedAuth) {
                if (RemoteAuth.isAuthInfoValid()) {
                    Object authToken = RemoteAuth.getAuthToken();
                    if (TextUtils.isEmpty(authToken)) {
                        a.a(this);
                        RemoteAuth.authorize(this.authParam, this.request.getKey(), null, this.showAuthUI);
                        return;
                    }
                    mtopsdk.xstate.a.a("accessToken", authToken);
                } else {
                    a.a(this);
                    RemoteAuth.authorize(this.authParam, this.request.getKey(), null, this.showAuthUI);
                    return;
                }
            }
            this.sendStartTime = System.currentTimeMillis();
            this.apiID = super.asyncRequest();
            return;
        }
        a.a(this);
        RemoteLogin.login(this.showLoginUI, this.request);
    }

    private String genSeqNo() {
        StringBuilder stringBuilder = new StringBuilder(16);
        stringBuilder.append("RB").append(seqGen.getAndIncrement()).append('.').append(this.stat.g());
        return stringBuilder.toString();
    }

    public static void init(Context context, String str) {
        mtopsdk.mtop.b.a.a(context, str);
    }

    private void onErrorCallback(MtopResponse mtopResponse, boolean z) {
        IRemoteBaseListener iRemoteBaseListener = (IRemoteBaseListener) this.listener;
        if (z) {
            try {
                iRemoteBaseListener.onSystemError(this.requestType, mtopResponse, getReqContext());
            } catch (Throwable th) {
                m.b(TAG, this.seqNo, "listener onError callback error", th);
            }
        } else {
            iRemoteBaseListener.onError(this.requestType, mtopResponse, getReqContext());
        }
        if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
            m.b(TAG, this.seqNo, "listener onError callback, " + (z ? "sys error" : "biz error"));
        }
    }

    private void resetMtopListener() {
        if (!this.isCanceled && this.listener != null) {
            super.addListener(c.a(this, this.listener));
        }
    }

    public MtopBusiness addListener(k kVar) {
        return registeListener(kVar);
    }

    public a asyncRequest() {
        startRequest();
        return this.apiID;
    }

    public void cancelRequest() {
        cancelRequest(true);
    }

    public void doFinish(MtopResponse mtopResponse, mtopsdk.mtop.domain.a aVar) {
        if (this.syncRequestLatch != null) {
            this.mtopResponse = mtopResponse;
            this.syncRequestLatch.countDown();
        }
        if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("doFinish api=[").append(this.request.getApiName()).append("]");
            if (mtopResponse != null) {
                stringBuilder.append(" retCode=").append(mtopResponse.getRetCode());
                stringBuilder.append(" retMsg=").append(mtopResponse.getRetCode());
            }
            m.b(TAG, this.seqNo, stringBuilder.toString());
        }
        if (this.isCanceled || !(this.listener instanceof IRemoteBaseListener)) {
            m.a(TAG, this.seqNo, "doFinish no callback.");
            return;
        }
        IRemoteBaseListener iRemoteBaseListener = (IRemoteBaseListener) this.listener;
        if (mtopResponse == null) {
            m.b(TAG, this.seqNo, "response is null.");
            onErrorCallback(mtopResponse, false);
        } else if (mtopResponse != null && mtopResponse.isApiSuccess()) {
            try {
                iRemoteBaseListener.onSuccess(this.requestType, mtopResponse, aVar, getReqContext());
            } catch (Throwable th) {
                m.b(TAG, this.seqNo, "listener onSuccess callback error", th);
            }
            m.b(TAG, this.seqNo, "listener onSuccess callback.");
        } else if (this.isCached && !this.isErrorNotifyAfterCache) {
            m.a(TAG, this.seqNo, "listenr onCached callback,doNothing in doFinish()");
        } else if (mtopResponse.isSessionInvalid()) {
            if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                m.b(TAG, this.seqNo, c.a("尝试登录后仍session失效，或用户取消登录。", this, false, null));
                m.b(TAG, this.seqNo, "response.isSessionInvalid().");
            }
            onErrorCallback(mtopResponse, true);
        } else if (mtopResponse.isMtopServerError() || mtopResponse.isMtopSdkError() || mtopResponse.isNetworkError() || mtopResponse.isSystemError() || mtopResponse.isExpiredRequest() || mtopResponse.is41XResult() || mtopResponse.isApiLockedResult()) {
            onErrorCallback(mtopResponse, true);
        } else {
            onErrorCallback(mtopResponse, false);
        }
    }

    public int getRequestType() {
        return this.requestType;
    }

    public int getRetryTime() {
        return this.retryTime;
    }

    public String getSeqNo() {
        return this.seqNo;
    }

    public boolean isNeedAuth() {
        return this.authParam != null;
    }

    public boolean isShowLoginUI() {
        return this.showLoginUI;
    }

    public boolean isTaskCanceled() {
        return this.isCanceled;
    }

    public MtopBusiness registeListener(k kVar) {
        this.listener = kVar;
        return this;
    }

    void retryRequest() {
        if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
            m.b(TAG, this.seqNo, c.a("retryRequest.", this, false, null));
        }
        if (this.retryTime >= 3) {
            this.retryTime = 0;
            doFinish(null, null);
            return;
        }
        cancelRequest(false);
        startRequest(this.requestType, this.clazz);
        this.retryTime++;
    }

    public MtopBusiness setBizId(int i) {
        return (MtopBusiness) super.setBizId(i);
    }

    public MtopBusiness setErrorNotifyAfterCache(boolean z) {
        this.isErrorNotifyAfterCache = z;
        return this;
    }

    public MtopBusiness setNeedAuth(String str, boolean z) {
        if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
            m.a(TAG, "setNeedAuth. authParam" + str);
        }
        this.authParam = str;
        this.showAuthUI = z;
        return this;
    }

    public MtopBusiness showLoginUI(boolean z) {
        this.showLoginUI = z;
        return this;
    }

    public void startRequest() {
        startRequest(0, null);
    }

    public void startRequest(int i, Class cls) {
        if (this.request == null) {
            m.d(TAG, this.seqNo, "request is null!!!");
            return;
        }
        if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
            m.b(TAG, this.seqNo, "start request api=[" + this.request.getApiName() + "]");
        }
        this.reqStartTime = System.currentTimeMillis();
        this.isCanceled = false;
        this.isCached = false;
        this.clazz = cls;
        this.requestType = i;
        resetMtopListener();
        mtopCommitStatData(false);
        doQuery();
    }

    public void startRequest(Class cls) {
        startRequest(0, cls);
    }

    public MtopResponse syncRequest() {
        m.b(TAG, this.seqNo, "syncRequest");
        this.syncRequestLatch = new CountDownLatch(1);
        if (this.listener == null) {
            this.listener = new IRemoteBaseListener() {
                public void onError(int i, MtopResponse mtopResponse, Object obj) {
                }

                public void onSuccess(int i, MtopResponse mtopResponse, mtopsdk.mtop.domain.a aVar, Object obj) {
                }

                public void onSystemError(int i, MtopResponse mtopResponse, Object obj) {
                }
            };
        }
        startRequest();
        try {
            if (!this.syncRequestLatch.await(120, TimeUnit.SECONDS)) {
                m.c(TAG, this.seqNo, new StringBuilder("syncRequest timeout . apiKey=").append(this.request).toString() != null ? this.request.getKey() : "");
                cancelRequest();
            }
        } catch (InterruptedException e) {
            if (m.a(TBSdkLog$LogEnable.ErrorEnable)) {
                m.d(TAG, this.seqNo, new StringBuilder("SyncRequest InterruptedException. apiKey=").append(this.request).toString() != null ? this.request.getKey() : "");
            }
        }
        if (this.mtopResponse == null) {
            this.mtopResponse = new MtopResponse(this.request.getApiName(), this.request.getVersion(), "ANDROID_SYS_MTOP_APICALL_ASYNC_TIMEOUT", "MTOP异步调用超时");
        }
        return this.mtopResponse;
    }
}
