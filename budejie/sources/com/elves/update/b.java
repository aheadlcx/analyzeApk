package com.elves.update;

import android.content.Context;
import android.text.TextUtils;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.util.aa;
import com.umeng.update.UpdateConfig;
import java.io.IOException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class b {
    public static void a(Context context, int i, String str, c cVar) {
        new b$2(context, "http://app.spriteapp.com/updateapp/get/" + i + "/" + "bec82b8c-3062-11e6-983d-842b2b4c75ab" + "/" + str + "/", new b$1(cVar)).start();
    }

    private static String b(Context context, String str) {
        HttpClient defaultHttpClient = new DefaultHttpClient();
        HttpUriRequest httpGet = new HttpGet(str);
        if (!TextUtils.isEmpty(NetWorkUtil.b(context))) {
            httpGet.setHeader("Cookie", NetWorkUtil.b(context));
        }
        String str2 = "";
        try {
            httpGet.getParams().setIntParameter("http.socket.timeout", 10000);
            httpGet.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 10000);
            HttpResponse execute = defaultHttpClient.execute(httpGet);
            if (execute != null && execute.getStatusLine().getStatusCode() == 200) {
                str2 = EntityUtils.toString(execute.getEntity());
            }
        } catch (ClientProtocolException e) {
            aa.a("Update", "ClientProtocolException: " + e.getMessage());
        } catch (IOException e2) {
            aa.e("download", "IoException : " + e2.getMessage());
        } catch (Exception e3) {
            aa.e(UpdateConfig.a, "UnknownHostException : " + e3.getMessage());
        }
        return str2;
    }
}
