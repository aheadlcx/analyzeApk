package com.qiniu.android.dns.http;

import com.alipay.sdk.util.h;
import com.qiniu.android.dns.Domain;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.Record;
import com.qiniu.android.dns.util.Hex;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class DnspodEnterprise implements IResolver {
    private final String a;
    private final String b;
    private final SecretKeySpec c;
    private final int d;

    public DnspodEnterprise(String str, String str2, String str3) {
        this(str, str2, str3, 10);
    }

    public DnspodEnterprise(String str, String str2, String str3, int i) {
        this.a = str;
        this.b = str3;
        this.d = i;
        try {
            this.c = new SecretKeySpec(str2.getBytes("utf-8"), "DES");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public DnspodEnterprise(String str, String str2) {
        this(str, str2, "119.29.29.29");
    }

    public Record[] resolve(Domain domain, NetworkInfo networkInfo) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://" + this.b + "/d?ttl=1&dn=" + a(domain.domain) + "&id=" + this.a).openConnection();
        httpURLConnection.setConnectTimeout(3000);
        httpURLConnection.setReadTimeout(this.d * 1000);
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
        String[] split = b(new String(bArr, 0, read)).split(Constants.ACCEPT_TIME_SEPARATOR_SP);
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

    private String a(String str) {
        try {
            Cipher instance = Cipher.getInstance("DES/ECB/PKCS5Padding");
            instance.init(1, this.c);
            return Hex.encodeHexString(instance.doFinal(str.getBytes("utf-8"))) + "&id=" + this.a;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String b(String str) {
        try {
            Cipher instance = Cipher.getInstance("DES/ECB/PKCS5Padding");
            instance.init(2, this.c);
            return new String(instance.doFinal(Hex.decodeHex(str.toCharArray())));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
