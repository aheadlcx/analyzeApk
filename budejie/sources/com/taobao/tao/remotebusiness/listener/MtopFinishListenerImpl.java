package com.taobao.tao.remotebusiness.listener;

import com.taobao.tao.remotebusiness.IRemoteParserListener;
import com.taobao.tao.remotebusiness.MtopBusiness;
import com.taobao.tao.remotebusiness.a;
import com.taobao.tao.remotebusiness.a.b;
import com.taobao.tao.remotebusiness.auth.RemoteAuth;
import com.taobao.tao.remotebusiness.login.RemoteLogin;
import mtopsdk.common.util.m;
import mtopsdk.mtop.common.e;
import mtopsdk.mtop.common.i;
import mtopsdk.mtop.common.k;
import mtopsdk.mtop.domain.MtopResponse;
import mtopsdk.mtop.util.h;
import mtopsdk.mtop.util.j;

class MtopFinishListenerImpl extends b implements e {
    private static final String TAG = "mtop.rb-FinishListener";

    public MtopFinishListenerImpl(MtopBusiness mtopBusiness, k kVar) {
        super(mtopBusiness, kVar);
    }

    public void onFinished(i iVar, Object obj) {
        m.b(TAG, this.mtopBusiness.getSeqNo(), "Mtop onFinish event received.");
        if (this.mtopBusiness.isTaskCanceled()) {
            m.a(TAG, this.mtopBusiness.getSeqNo(), "The request of RemoteBusiness is canceled.");
            return;
        }
        MtopResponse a = iVar.a();
        if (a == null) {
            m.a(TAG, this.mtopBusiness.getSeqNo(), "The MtopResponse is null.");
        } else if (a.isSessionInvalid() && this.mtopBusiness.request.isNeedEcode() && this.mtopBusiness.getRetryTime() == 0) {
            a.a(this.mtopBusiness);
            RemoteLogin.login(this.mtopBusiness.isShowLoginUI(), a);
        } else {
            long j;
            h mtopStat;
            String retCode = a.getRetCode();
            if (("FAIL_SYS_ACCESS_TOKEN_EXPIRED".equalsIgnoreCase(retCode) || "FAIL_SYS_ILLEGAL_ACCESS_TOKEN".equalsIgnoreCase(retCode)) && this.mtopBusiness.isNeedAuth()) {
                int retryTime = this.mtopBusiness.getRetryTime();
                MtopBusiness mtopBusiness = this.mtopBusiness;
                if (retryTime < 3) {
                    a.a(this.mtopBusiness);
                    RemoteAuth.authorize(this.mtopBusiness.authParam, this.mtopBusiness.request.getKey(), c.a(a.getHeaderFields(), "x-act-hint"), this.mtopBusiness.showAuthUI);
                    return;
                }
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (this.listener instanceof IRemoteParserListener) {
                ((IRemoteParserListener) this.listener).parseResponse(iVar.a());
            }
            b a2 = com.taobao.tao.remotebusiness.a.a.a(this.listener, iVar, this.mtopBusiness);
            a2.e = a;
            long currentTimeMillis2 = System.currentTimeMillis();
            if (a != null) {
                if (!a.isApiSuccess() || this.mtopBusiness.clazz == null) {
                    j = currentTimeMillis2;
                } else {
                    a2.c = mtopsdk.mtop.util.b.a(a, this.mtopBusiness.clazz);
                    j = System.currentTimeMillis();
                }
                mtopStat = a.getMtopStat();
                if (mtopStat == null) {
                    mtopStat = new h();
                    a.setMtopStat(mtopStat);
                }
            } else {
                mtopStat = null;
                j = currentTimeMillis2;
            }
            this.mtopBusiness.onBgFinishTime = System.currentTimeMillis();
            if (mtopStat != null) {
                j i = mtopStat.i();
                i.b = this.mtopBusiness.sendStartTime - this.mtopBusiness.reqStartTime;
                i.a = currentTimeMillis - this.mtopBusiness.sendStartTime;
                i.c = this.mtopBusiness.onBgFinishTime - currentTimeMillis;
                i.f = currentTimeMillis2 - currentTimeMillis;
                i.e = j - currentTimeMillis2;
                i.d = this.mtopBusiness.onBgFinishTime - this.mtopBusiness.reqStartTime;
            }
            com.taobao.tao.remotebusiness.a.a.a().obtainMessage(3, a2).sendToTarget();
        }
    }
}
