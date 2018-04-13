package com.tencent.open.b;

import android.os.Bundle;
import com.tencent.open.a.f;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.d;
import com.tencent.open.utils.e;
import com.tencent.open.utils.i;
import cz.msebera.android.httpclient.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;

class k implements Runnable {
    final /* synthetic */ g a;

    k(g gVar) {
        this.a = gVar;
    }

    public void run() {
        Object obj = null;
        Bundle c = this.a.c();
        if (c != null) {
            int a = e.a(d.a(), null).a("Common_HttpRetryCount");
            int i = a == 0 ? 3 : a;
            f.b("openSDK_LOG.ReportManager", "-->doReportCgi, retryCount: " + i);
            a = 0;
            do {
                a++;
                try {
                    HttpClient httpClient = HttpUtils.getHttpClient(d.a(), null, "http://wspeed.qq.com/w.cgi");
                    HttpUriRequest httpPost = new HttpPost("http://wspeed.qq.com/w.cgi");
                    httpPost.addHeader(HttpHeaders.ACCEPT_ENCODING, "gzip");
                    httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    httpPost.setEntity(new ByteArrayEntity(i.i(HttpUtils.encodeUrl(c))));
                    int statusCode = httpClient.execute(httpPost).getStatusLine().getStatusCode();
                    f.b("openSDK_LOG.ReportManager", "-->doReportCgi, statusCode: " + statusCode);
                    if (statusCode == 200) {
                        f.a().b("report_cgi");
                        obj = 1;
                    }
                } catch (Throwable e) {
                    try {
                        f.b("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception", e);
                        continue;
                    } catch (Throwable e2) {
                        f.b("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception out.", e2);
                        return;
                    }
                } catch (Throwable e3) {
                    f.b("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception", e3);
                    continue;
                } catch (Throwable e22) {
                    f.b("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception", e22);
                }
                if (obj == null) {
                    f.a().a("report_cgi", this.a.c);
                }
                this.a.c.clear();
            } while (a < i);
            if (obj == null) {
                f.a().a("report_cgi", this.a.c);
            }
            this.a.c.clear();
        }
    }
}
