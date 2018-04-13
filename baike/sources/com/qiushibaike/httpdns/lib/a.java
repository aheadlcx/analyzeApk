package com.qiushibaike.httpdns.lib;

import android.os.AsyncTask;
import android.text.TextUtils;

class a extends AsyncTask<Void, Void, Object> {
    final /* synthetic */ String a;
    final /* synthetic */ HttpDNSManager b;

    a(HttpDNSManager httpDNSManager, String str) {
        this.b = httpDNSManager;
        this.a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected Object a(Void... voidArr) {
        int i = 0;
        Object obj = null;
        while (i < 2) {
            try {
                Object a = this.b.a(this.a);
                if (TextUtils.isEmpty(a)) {
                    return null;
                }
                DomainRecord domainRecord = new DomainRecord();
                domainRecord.domain = this.a;
                domainRecord.ip = a;
                domainRecord.ttl = AppContext.getTTL();
                synchronized (this.b.b) {
                    this.b.b.put(this.a, domainRecord);
                }
                return domainRecord;
            } catch (Exception e) {
                obj = e;
                obj.printStackTrace();
                i++;
            }
        }
        return obj;
    }

    protected void onPostExecute(Object obj) {
        super.onPostExecute(obj);
        this.b.getFetcherInner();
        if (this.b.e.getResultListener() != null) {
            FetchResultListener resultListener = this.b.e.getResultListener();
            if (obj instanceof DomainRecord) {
                resultListener.onSuccess((DomainRecord) obj);
            } else {
                resultListener.onFailure(this.a, obj instanceof Exception ? (Exception) obj : null);
            }
            this.b.c.remove(this.a);
        }
    }
}
