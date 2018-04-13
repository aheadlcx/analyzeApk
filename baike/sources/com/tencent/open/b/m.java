package com.tencent.open.b;

import android.os.Bundle;
import com.tencent.open.a.f;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.d;
import com.tencent.open.utils.i;
import cz.msebera.android.httpclient.HttpHeaders;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;

class m implements Runnable {
    final /* synthetic */ Bundle a;
    final /* synthetic */ String b;
    final /* synthetic */ boolean c;
    final /* synthetic */ String d;
    final /* synthetic */ g e;

    m(g gVar, Bundle bundle, String str, boolean z, String str2) {
        this.e = gVar;
        this.a = bundle;
        this.b = str;
        this.c = z;
        this.d = str2;
    }

    public void run() {
        int i;
        Object obj = null;
        if (this.a == null) {
            f.e("openSDK_LOG.ReportManager", "-->httpRequest, params is null!");
            return;
        }
        String encode;
        HttpUriRequest httpGet;
        int a = e.a();
        int i2 = a == 0 ? 3 : a;
        f.b("openSDK_LOG.ReportManager", "-->httpRequest, retryCount: " + i2);
        HttpClient httpClient = HttpUtils.getHttpClient(d.a(), null, this.b);
        String encodeUrl = HttpUtils.encodeUrl(this.a);
        if (this.c) {
            encode = URLEncoder.encode(encodeUrl);
        } else {
            encode = encodeUrl;
        }
        if (this.d.toUpperCase().equals("GET")) {
            StringBuffer stringBuffer = new StringBuffer(this.b);
            stringBuffer.append(encode);
            httpGet = new HttpGet(stringBuffer.toString());
        } else if (this.d.toUpperCase().equals("POST")) {
            HttpPost httpPost = new HttpPost(this.b);
            httpPost.setEntity(new ByteArrayEntity(i.i(encode)));
            Object obj2 = httpPost;
        } else {
            f.e("openSDK_LOG.ReportManager", "-->httpRequest unkonw request method return.");
            return;
        }
        httpGet.addHeader(HttpHeaders.ACCEPT_ENCODING, "gzip");
        httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
        a = 0;
        do {
            a++;
            try {
                int statusCode = httpClient.execute(httpGet).getStatusLine().getStatusCode();
                f.b("openSDK_LOG.ReportManager", "-->httpRequest, statusCode: " + statusCode);
                if (statusCode != 200) {
                    f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest : HttpStatuscode != 200");
                    break;
                }
                try {
                    f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread success");
                    i = 1;
                    break;
                } catch (ConnectTimeoutException e) {
                    i = 1;
                } catch (SocketTimeoutException e2) {
                    i = 1;
                    f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest SocketTimeoutException");
                    continue;
                    if (a >= i2) {
                        if (obj == 1) {
                            f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread request failed");
                        } else {
                            f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread request success");
                        }
                    }
                } catch (Exception e3) {
                    i = 1;
                }
            } catch (ConnectTimeoutException e4) {
                try {
                    f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest ConnectTimeoutException");
                    continue;
                    if (a >= i2) {
                        if (obj == 1) {
                            f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread request failed");
                        } else {
                            f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread request success");
                        }
                    }
                } catch (Exception e5) {
                    f.b("openSDK_LOG.ReportManager", "-->httpRequest, exception in serial executor.");
                    return;
                }
            } catch (SocketTimeoutException e6) {
                f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest SocketTimeoutException");
                continue;
                if (a >= i2) {
                    if (obj == 1) {
                        f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread request success");
                    } else {
                        f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread request failed");
                    }
                }
            } catch (Exception e7) {
            }
        } while (a >= i2);
        if (obj == 1) {
            f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread request success");
        } else {
            f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread request failed");
        }
        f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Exception");
        if (obj == 1) {
            f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread request success");
        } else {
            f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread request failed");
        }
    }
}
