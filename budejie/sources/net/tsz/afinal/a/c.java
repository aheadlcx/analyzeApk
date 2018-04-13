package net.tsz.afinal.a;

import android.os.SystemClock;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import net.tsz.afinal.a.a.a;
import net.tsz.afinal.a.a.b;
import net.tsz.afinal.core.AsyncTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

public class c<T> extends AsyncTask<Object, Object, Object> implements a {
    private final AbstractHttpClient d;
    private final HttpContext e;
    private final net.tsz.afinal.a.a.c f = new net.tsz.afinal.a.a.c();
    private final b g = new b();
    private final a<T> h;
    private int i = 0;
    private String j = null;
    private boolean k = false;
    private String l;
    private long m;

    public c(AbstractHttpClient abstractHttpClient, HttpContext httpContext, a<T> aVar, String str) {
        this.d = abstractHttpClient;
        this.e = httpContext;
        this.h = aVar;
        this.l = str;
    }

    private void a(HttpUriRequest httpUriRequest) throws IOException {
        IOException iOException;
        int i;
        IOException iOException2;
        if (this.k && this.j != null) {
            long length;
            File file = new File(this.j);
            if (file.isFile() && file.exists()) {
                length = file.length();
            } else {
                length = 0;
            }
            if (length > 0) {
                httpUriRequest.setHeader("RANGE", "bytes=" + length + "-");
            }
        }
        IOException iOException3 = null;
        HttpRequestRetryHandler httpRequestRetryHandler = this.d.getHttpRequestRetryHandler();
        boolean z = true;
        while (z) {
            try {
                if (!c()) {
                    HttpResponse execute = this.d.execute(httpUriRequest, this.e);
                    if (!c()) {
                        a(execute);
                        return;
                    }
                    return;
                }
                return;
            } catch (UnknownHostException e) {
                c(Integer.valueOf(3), e, Integer.valueOf(0), "unknownHostException：can't resolve host");
                return;
            } catch (IOException e2) {
                iOException3 = e2;
                int i2 = this.i + 1;
                this.i = i2;
                z = httpRequestRetryHandler.retryRequest(iOException3, i2, this.e);
            } catch (NullPointerException e3) {
                iOException = new IOException("NPE in HttpClient" + e3.getMessage());
                i = this.i + 1;
                this.i = i;
                iOException2 = iOException;
                z = httpRequestRetryHandler.retryRequest(iOException, i, this.e);
                iOException3 = iOException2;
            } catch (Exception e4) {
                iOException = new IOException("Exception" + e4.getMessage());
                i = this.i + 1;
                this.i = i;
                iOException2 = iOException;
                z = httpRequestRetryHandler.retryRequest(iOException, i, this.e);
                iOException3 = iOException2;
            }
        }
        if (iOException3 != null) {
            throw iOException3;
        }
        throw new IOException("未知网络错误");
    }

    protected Object a(Object... objArr) {
        if (objArr != null && objArr.length == 3) {
            this.j = String.valueOf(objArr[1]);
            this.k = ((Boolean) objArr[2]).booleanValue();
        }
        try {
            c(Integer.valueOf(1));
            a((HttpUriRequest) objArr[0]);
        } catch (IOException e) {
            c(Integer.valueOf(3), e, Integer.valueOf(0), e.getMessage());
        }
        return null;
    }

    protected void b(Object... objArr) {
        switch (Integer.valueOf(String.valueOf(objArr[0])).intValue()) {
            case 1:
                if (this.h != null) {
                    this.h.onStart();
                    break;
                }
                break;
            case 2:
                if (this.h != null) {
                    this.h.onLoading(Long.valueOf(String.valueOf(objArr[1])).longValue(), Long.valueOf(String.valueOf(objArr[2])).longValue());
                    break;
                }
                break;
            case 3:
                if (this.h != null) {
                    this.h.onFailure((Throwable) objArr[1], ((Integer) objArr[2]).intValue(), (String) objArr[3]);
                    break;
                }
                break;
            case 4:
                if (this.h != null) {
                    this.h.onSuccess(objArr[1]);
                    break;
                }
                break;
        }
        super.b(objArr);
    }

    private void a(HttpResponse httpResponse) {
        StatusLine statusLine = httpResponse.getStatusLine();
        if (statusLine.getStatusCode() >= 300) {
            String str = "response status error code:" + statusLine.getStatusCode();
            if (statusLine.getStatusCode() == 416 && this.k) {
                str = new StringBuilder(String.valueOf(str)).append(" \n maybe you have download complete.").toString();
            }
            c(Integer.valueOf(3), new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()), Integer.valueOf(statusLine.getStatusCode()), str);
            return;
        }
        try {
            HttpEntity entity = httpResponse.getEntity();
            Object obj = null;
            if (entity != null) {
                this.m = SystemClock.uptimeMillis();
                if (this.j != null) {
                    obj = this.g.a(entity, this, this.j, this.k);
                } else {
                    obj = this.f.a(entity, this, this.l);
                }
            }
            c(Integer.valueOf(4), obj);
        } catch (IOException e) {
            c(Integer.valueOf(3), e, Integer.valueOf(0), e.getMessage());
        }
    }

    public void a(long j, long j2, boolean z) {
        if (this.h != null && this.h.isProgress()) {
            if (z) {
                c(Integer.valueOf(2), Long.valueOf(j), Long.valueOf(j2));
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            if (uptimeMillis - this.m >= ((long) this.h.getRate())) {
                this.m = uptimeMillis;
                c(Integer.valueOf(2), Long.valueOf(j), Long.valueOf(j2));
            }
        }
    }
}
