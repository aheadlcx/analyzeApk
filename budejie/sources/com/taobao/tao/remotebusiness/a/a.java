package com.taobao.tao.remotebusiness.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.taobao.tao.remotebusiness.IRemoteProcessListener;
import com.taobao.tao.remotebusiness.MtopBusiness;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;
import mtopsdk.mtop.common.h;
import mtopsdk.mtop.common.j;
import mtopsdk.mtop.common.k;
import mtopsdk.mtop.common.n;

public final class a extends Handler {
    private static Handler a;

    private a(Looper looper) {
        super(looper);
    }

    public static synchronized Handler a() {
        Handler handler;
        synchronized (a.class) {
            if (a == null) {
                a = new a(Looper.getMainLooper());
            }
            handler = a;
        }
        return handler;
    }

    public static b a(k kVar, h hVar, MtopBusiness mtopBusiness) {
        return new b(kVar, hVar, mtopBusiness);
    }

    private static boolean a(b bVar) {
        if (bVar == null) {
            m.d("mtop.rb-HandlerMgr", bVar.d.getSeqNo(), "HandlerMsg is null.");
            return false;
        } else if (!bVar.d.isTaskCanceled()) {
            return true;
        } else {
            m.b("mtop.rb-HandlerMgr", bVar.d.getSeqNo(), "The request of RemoteBusiness is canceled.");
            return false;
        }
    }

    public final void handleMessage(Message message) {
        b bVar;
        switch (message.what) {
            case 1:
                bVar = (b) message.obj;
                if (a(bVar)) {
                    m.b("mtop.rb-HandlerMgr", bVar.d.getSeqNo(), "onReceive: ON_DATA_RECEIVED.");
                    ((IRemoteProcessListener) bVar.a).onDataReceived((n) bVar.b, bVar.d.getReqContext());
                    break;
                }
                return;
            case 2:
                bVar = (b) message.obj;
                if (a(bVar)) {
                    m.b("mtop.rb-HandlerMgr", bVar.d.getSeqNo(), "onReceive: ON_HEADER.");
                    try {
                        ((IRemoteProcessListener) bVar.a).onHeader((j) bVar.b, bVar.d.getReqContext());
                        break;
                    } catch (Throwable th) {
                        m.b("mtop.rb-HandlerMgr", bVar.d.getSeqNo(), "listener onHeader callback error.", th);
                        break;
                    }
                }
                return;
            case 3:
                bVar = (b) message.obj;
                if (a(bVar)) {
                    mtopsdk.mtop.util.h mtopStat;
                    mtopsdk.mtop.util.j i;
                    m.b("mtop.rb-HandlerMgr", bVar.d.getSeqNo(), "onReceive: ON_FINISHED.");
                    long currentTimeMillis = System.currentTimeMillis();
                    long j = 0;
                    if (bVar.e != null) {
                        mtopStat = bVar.e.getMtopStat();
                        if (mtopStat != null) {
                            i = mtopStat.i();
                            i.g = currentTimeMillis - bVar.d.onBgFinishTime;
                            if (bVar.e.getBytedata() != null) {
                                j = (long) bVar.e.getBytedata().length;
                            }
                        } else {
                            i = null;
                        }
                    } else {
                        i = null;
                        mtopStat = null;
                    }
                    bVar.d.doFinish(bVar.e, bVar.c);
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("doFinishTime=").append(currentTimeMillis2 - currentTimeMillis).append("; dataSize=").append(j).append("; ");
                        if (i != null) {
                            stringBuilder.append(i.toString());
                        }
                        m.b("mtop.rb-HandlerMgr", bVar.d.getSeqNo(), "onReceive: ON_FINISHED. " + stringBuilder.toString());
                    }
                    if (mtopStat != null) {
                        mtopStat.a(true);
                        break;
                    }
                }
                return;
                break;
        }
        message.obj = null;
    }
}
