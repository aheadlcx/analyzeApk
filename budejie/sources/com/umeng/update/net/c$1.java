package com.umeng.update.net;

import com.ali.auth.third.login.LoginConstants;
import java.util.Map;
import java.util.Random;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import u.upd.b;
import u.upd.m;

class c$1 implements Runnable {
    final /* synthetic */ c a;
    private final /* synthetic */ String[] b;
    private final /* synthetic */ boolean c;
    private final /* synthetic */ Map d;

    c$1(c cVar, String[] strArr, boolean z, Map map) {
        this.a = cVar;
        this.b = strArr;
        this.c = z;
        this.d = map;
    }

    public void run() {
        int nextInt = new Random().nextInt(1000);
        if (this.b == null) {
            b.a(c.a(), new StringBuilder(String.valueOf(nextInt)).append("service report: urls is null").toString());
            return;
        }
        String[] strArr = this.b;
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            String a = m.a();
            String str2 = a.split(" ")[0];
            a = a.split(" ")[1];
            long currentTimeMillis = System.currentTimeMillis();
            StringBuilder stringBuilder = new StringBuilder(str);
            stringBuilder.append("&data=").append(str2);
            stringBuilder.append("&time=").append(a);
            stringBuilder.append("&ts=").append(currentTimeMillis);
            if (this.c) {
                stringBuilder.append("&action_type=").append(1);
            } else {
                stringBuilder.append("&action_type=").append(-2);
            }
            if (this.d != null) {
                for (String a2 : this.d.keySet()) {
                    stringBuilder.append("&").append(a2).append(LoginConstants.EQUAL).append((String) this.d.get(a2));
                }
            }
            try {
                b.a(c.a(), new StringBuilder(String.valueOf(nextInt)).append(": service report:\tget: ").append(stringBuilder.toString()).toString());
                HttpUriRequest httpGet = new HttpGet(stringBuilder.toString());
                HttpParams basicHttpParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
                HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
                HttpResponse execute = new DefaultHttpClient(basicHttpParams).execute(httpGet);
                b.a(c.a(), new StringBuilder(String.valueOf(nextInt)).append(": service report:status code:  ").append(execute.getStatusLine().getStatusCode()).toString());
                if (execute.getStatusLine().getStatusCode() != 200) {
                    i++;
                } else {
                    return;
                }
            } catch (Exception e) {
                b.c(c.a(), new StringBuilder(String.valueOf(nextInt)).append(": service report:\tClientProtocolException,Failed to send message.").append(str).toString(), e);
            } catch (Exception e2) {
                b.c(c.a(), new StringBuilder(String.valueOf(nextInt)).append(": service report:\tIOException,Failed to send message.").append(str).toString(), e2);
            }
        }
    }
}
