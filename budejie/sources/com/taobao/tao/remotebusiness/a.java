package com.taobao.tao.remotebusiness;

import com.taobao.tao.remotebusiness.a.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;
import mtopsdk.mtop.domain.MtopResponse;

public final class a {
    private static List a = new ArrayList();
    private static Lock b = new ReentrantLock();

    public static void a() {
        b.lock();
        try {
            if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                m.b("mtop.rb-RequestPool", "retry all request, current size=" + a.size());
            }
            Iterator it = new ArrayList(a).iterator();
            while (it.hasNext()) {
                ((MtopBusiness) it.next()).retryRequest();
            }
        } finally {
            b.unlock();
        }
    }

    public static void a(MtopBusiness mtopBusiness) {
        b.lock();
        try {
            a.add(mtopBusiness);
            m.b("mtop.rb-RequestPool", mtopBusiness.getSeqNo(), "request add to request pool");
        } finally {
            b.unlock();
        }
    }

    public static void a(String str, String str2) {
        b.lock();
        try {
            m.b("mtop.rb-RequestPool", "session fail  all request");
            for (MtopBusiness mtopBusiness : a) {
                MtopResponse mtopResponse = mtopBusiness.request != null ? new MtopResponse(mtopBusiness.request.getApiName(), mtopBusiness.request.getVersion(), str, str2) : new MtopResponse(str, str2);
                b a = com.taobao.tao.remotebusiness.a.a.a(null, null, mtopBusiness);
                a.e = mtopResponse;
                com.taobao.tao.remotebusiness.a.a.a().obtainMessage(3, a).sendToTarget();
            }
            a.clear();
        } finally {
            b.unlock();
        }
    }

    public static void b(MtopBusiness mtopBusiness) {
        b.lock();
        try {
            m.b("mtop.rb-RequestPool", mtopBusiness.getSeqNo(), "request remove from request pool");
            a.remove(mtopBusiness);
        } catch (Exception e) {
        } finally {
            b.unlock();
        }
    }
}
