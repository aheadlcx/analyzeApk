package com.qiniu.utils;

import android.os.AsyncTask;
import com.qiniu.auth.Authorizer;
import com.qiniu.resumableio.SliceUploadTask.Block;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadCallRet;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.IOException;

public abstract class UploadTask extends AsyncTask<Object, Object, CallRet> {
    protected final CallBack a;
    protected final long b;
    protected final String c;
    protected volatile HttpPost d;
    protected Authorizer e;
    protected InputStreamAt f;
    protected PutExtra g;
    private volatile boolean h = false;

    protected abstract CallRet a(Object... objArr);

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return b(objArr);
    }

    protected /* synthetic */ void onCancelled(Object obj) {
        a((CallRet) obj);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        b((CallRet) obj);
    }

    public UploadTask(Authorizer authorizer, InputStreamAt inputStreamAt, String str, PutExtra putExtra, CallBack callBack) throws IOException {
        this.a = callBack;
        this.e = authorizer;
        this.f = inputStreamAt;
        this.b = this.f.length();
        this.c = str;
        if (putExtra == null) {
            putExtra = new PutExtra();
        }
        this.g = putExtra;
    }

    protected HttpClient b() {
        return Http.getHttpClient();
    }

    protected final CallRet b(Object... objArr) {
        try {
            CallRet a = a(objArr);
            return a;
        } finally {
            a();
        }
    }

    protected void a() {
        this.f.close();
        this.f = null;
        this.g = null;
        this.e = null;
        this.d = null;
    }

    protected void onProgressUpdate(Object... objArr) {
        try {
            if (objArr[0] instanceof Long) {
                this.a.onProcess(((Long) objArr[0]).longValue(), ((Long) objArr[1]).longValue());
            } else if (objArr[0] instanceof Block) {
                this.a.onBlockSuccess((Block) objArr[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void a(CallRet callRet) {
    }

    protected void b(CallRet callRet) {
        if (callRet == null) {
            try {
                this.a.onFailure(new CallRet(0, "", "result is null"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (callRet.getException() != null) {
            this.a.onFailure(callRet);
        } else if (callRet.isOk()) {
            this.a.onSuccess(new UploadCallRet(callRet));
        } else {
            this.a.onFailure(callRet);
        }
    }

    public void cancel() {
        this.h = true;
        c();
        cancel(true);
        c();
        cancel(true);
    }

    public boolean isUpCancelled() {
        return this.h;
    }

    private void c() {
        if (this.d != null) {
            try {
                this.d.abort();
            } catch (Exception e) {
            }
        }
    }
}
