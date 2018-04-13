package qsbk.app.open.auth;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.TimeDelta;

public class Http {
    public String readStream(HttpURLConnection httpURLConnection) throws Exception {
        InputStream inputStream;
        InputStream inputStream2 = httpURLConnection.getInputStream();
        if (inputStream2 == null || !"gzip".equals(httpURLConnection.getContentEncoding())) {
            inputStream = inputStream2;
        } else {
            inputStream = new GZIPInputStream(inputStream2);
        }
        return HttpClient.readStream(inputStream);
    }

    public String request(HttpURLConnection httpURLConnection) throws Exception {
        httpURLConnection.getResponseCode();
        return readStream(httpURLConnection);
    }

    public HttpURLConnection get(String str) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setRequestMethod("GET");
        return httpURLConnection;
    }

    public HttpURLConnection post(String str, HashMap<String, ?> hashMap) throws Exception {
        return post(str, hashMap, null, true);
    }

    public HttpURLConnection post(String str, HashMap<String, ?> hashMap, HashMap<String, ?> hashMap2, boolean z) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Charset", "utf-8");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        if (hashMap2 != null) {
            for (Entry entry : hashMap2.entrySet()) {
                httpURLConnection.setRequestProperty((String) entry.getKey(), entry.getValue().toString());
            }
        }
        if (z) {
            httpURLConnection.setInstanceFollowRedirects(true);
        } else {
            httpURLConnection.setInstanceFollowRedirects(false);
        }
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        TimeDelta timeDelta = new TimeDelta();
        if (hashMap != null) {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(paramsToString(hashMap).toString().getBytes());
            outputStream.flush();
            outputStream.close();
        }
        return httpURLConnection;
    }

    public String paramsToString(HashMap<String, ?> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return "";
        }
        List linkedList = new LinkedList();
        for (Entry entry : hashMap.entrySet()) {
            LogUtil.d("key:" + ((String) entry.getKey()));
            LogUtil.d("value:" + entry.getValue());
            linkedList.add(((String) entry.getKey()) + "=" + URLEncoder.encode(entry.getValue().toString()));
        }
        return TextUtils.join(a.b, linkedList.toArray(new String[0]));
    }

    public String buildURL(String str, HashMap<String, ?> hashMap) {
        return str + ((str.indexOf("?") >= 0 ? 1 : null) != null ? a.b : "?") + paramsToString(hashMap);
    }
}
