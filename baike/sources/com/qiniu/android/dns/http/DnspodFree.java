package com.qiniu.android.dns.http;

import com.alipay.sdk.util.h;
import com.qiniu.android.dns.Domain;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.Record;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public final class DnspodFree implements IResolver {
    private final String a;
    private final int b;

    public DnspodFree(String str) {
        this(str, 10);
    }

    public DnspodFree(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public DnspodFree() {
        this("119.29.29.29");
    }

    public Record[] resolve(Domain domain, NetworkInfo networkInfo) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://" + this.a + "/d?ttl=1&dn=" + domain.domain).openConnection();
        httpURLConnection.setConnectTimeout(3000);
        httpURLConnection.setReadTimeout(this.b * 1000);
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        int contentLength = httpURLConnection.getContentLength();
        if (contentLength <= 0 || contentLength > 1024) {
            return null;
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        byte[] bArr = new byte[contentLength];
        int read = inputStream.read(bArr);
        inputStream.close();
        if (read <= 0) {
            return null;
        }
        String[] split = new String(bArr, 0, read).split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        if (split.length != 2) {
            return null;
        }
        try {
            contentLength = Integer.parseInt(split[1]);
            String[] split2 = split[0].split(h.b);
            if (split2.length == 0) {
                return null;
            }
            Record[] recordArr = new Record[split2.length];
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            for (int i = 0; i < split2.length; i++) {
                recordArr[i] = new Record(split2[i], 1, contentLength, currentTimeMillis);
            }
            return recordArr;
        } catch (Exception e) {
            return null;
        }
    }
}
