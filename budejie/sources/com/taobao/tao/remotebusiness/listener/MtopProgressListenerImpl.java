package com.taobao.tao.remotebusiness.listener;

import com.taobao.tao.remotebusiness.IRemoteProcessListener;
import com.taobao.tao.remotebusiness.MtopBusiness;
import com.taobao.tao.remotebusiness.a.a;
import mtopsdk.common.util.m;
import mtopsdk.mtop.common.f;
import mtopsdk.mtop.common.g;
import mtopsdk.mtop.common.j;
import mtopsdk.mtop.common.k;
import mtopsdk.mtop.common.n;

class MtopProgressListenerImpl extends b implements f, g {
    private static final String TAG = "mtop.rb-ProgressListener";

    public MtopProgressListenerImpl(MtopBusiness mtopBusiness, k kVar) {
        super(mtopBusiness, kVar);
    }

    public void onDataReceived(n nVar, Object obj) {
        m.b(TAG, this.mtopBusiness.getSeqNo(), "Mtop onDataReceived event received.");
        if (this.mtopBusiness.isTaskCanceled()) {
            m.a(TAG, this.mtopBusiness.getSeqNo(), "The request of RemoteBusiness is canceled.");
        } else if (this.listener == null) {
            m.a(TAG, this.mtopBusiness.getSeqNo(), "The listener of RemoteBusiness is null.");
        } else if (this.listener instanceof IRemoteProcessListener) {
            a.a().obtainMessage(1, a.a(this.listener, nVar, this.mtopBusiness)).sendToTarget();
        }
    }

    public void onHeader(j jVar, Object obj) {
        m.b(TAG, this.mtopBusiness.getSeqNo(), "Mtop onHeader event received.");
        if (this.mtopBusiness.isTaskCanceled()) {
            m.a(TAG, this.mtopBusiness.getSeqNo(), "The request of RemoteBusiness is canceled.");
        } else if (this.listener == null) {
            m.a(TAG, this.mtopBusiness.getSeqNo(), "The listener of RemoteBusiness is null.");
        } else if (this.listener instanceof IRemoteProcessListener) {
            a.a().obtainMessage(2, a.a(this.listener, jVar, this.mtopBusiness)).sendToTarget();
        }
    }
}
