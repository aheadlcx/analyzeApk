package cn.tatagou.sdk.c;

import cn.tatagou.sdk.c.a.c;
import cn.tatagou.sdk.c.a.d;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.zip.Deflater;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.entity.mime.MIME;

public class a {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f = "http://";

    public a(String str, String str2, String str3, String str4) {
        if (str != "") {
            this.a = str;
            if (this.a.startsWith("http://")) {
                this.a = this.a.substring(7);
            } else if (this.a.startsWith("https://")) {
                this.a = this.a.substring(8);
                this.f = "https://";
            }
            while (this.a.endsWith("/")) {
                this.a = this.a.substring(0, this.a.length() - 1);
            }
            if (str2 != "") {
                this.b = str2;
                if (str3 != "") {
                    this.c = str3;
                    if (str4 != "") {
                        this.e = str4;
                        this.d = "";
                        return;
                    }
                    throw new NullPointerException("projectName is null");
                }
                throw new NullPointerException("accessKeySecret is null");
            }
            throw new NullPointerException("accessKeyID is null");
        }
        throw new NullPointerException("endpoint is null");
    }

    public void SetToken(String str) {
        this.d = str;
    }

    public String GetToken() {
        return this.d;
    }

    public String GetEndPoint() {
        return this.a;
    }

    public String GetKeyID() {
        return this.b;
    }

    public String GetKeySecret() {
        return this.c;
    }

    public void PostLog(d dVar, String str) throws c {
        String str2 = this.f + this.e + "." + this.a + "/logstores/" + str + "/shards/lb";
        try {
            byte[] bytes = dVar.LogGroupToJsonString().getBytes("UTF-8");
            byte[] b = b(bytes);
            HttpPostRequest(str2, GetHttpHeadersFrom(str, bytes, b), b);
        } catch (Throwable e) {
            throw new c("LogClientError", "Failed to pass log to utf-8 bytes", e, "");
        }
    }

    public void HttpPostRequest(String str, Map<String, String> map, byte[] bArr) throws c {
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    for (Entry entry : map.entrySet()) {
                        httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                    try {
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        dataOutputStream.write(bArr);
                        dataOutputStream.flush();
                        dataOutputStream.close();
                        try {
                            int responseCode = httpURLConnection.getResponseCode();
                            String headerField = httpURLConnection.getHeaderField("x-log-requestid");
                            if (headerField == null) {
                                headerField = "";
                            }
                            if (responseCode != 200) {
                                InputStream errorStream = httpURLConnection.getErrorStream();
                                if (errorStream != null) {
                                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
                                    StringBuffer stringBuffer = new StringBuffer();
                                    while (true) {
                                        String readLine = bufferedReader.readLine();
                                        if (readLine != null) {
                                            stringBuffer.append(readLine);
                                        } else {
                                            bufferedReader.close();
                                            a(stringBuffer.toString(), headerField);
                                            throw new c("LogServerError", "Response code:" + String.valueOf(responseCode) + "\nMessage:" + stringBuffer.toString(), headerField);
                                        }
                                    }
                                }
                                throw new c("LogServerError", "Response code:" + String.valueOf(responseCode) + "\nMessage: fail to connect to the server", headerField);
                            }
                        } catch (IOException e) {
                            throw new c("LogServerError", "Failed to parse response data", "");
                        }
                    } catch (Throwable e2) {
                        throw new c("LogClientError", "fail to post data to URL:" + str, e2, "");
                    }
                } catch (Throwable e22) {
                    throw new c("LogClientError", "fail to set http request method to  POST", e22, "");
                }
            } catch (Throwable e222) {
                throw new c("LogClientError", "fail to create HttpURLConnection", e222, "");
            }
        } catch (Throwable e2222) {
            throw new c("LogClientError", "illegal post url", e2222, "");
        }
    }

    private void a(String str, String str2) throws c {
        try {
            JSONObject parseObject = JSON.parseObject(str);
            if (parseObject != null && parseObject.containsKey(INoCaptchaComponent.errorCode) && parseObject.containsKey("errorMessage")) {
                throw new c(parseObject.getString(INoCaptchaComponent.errorCode), parseObject.getString("errorMessage"), str2);
            }
        } catch (JSONException e) {
        }
    }

    public Map<String, String> GetHttpHeadersFrom(String str, byte[] bArr, byte[] bArr2) throws c {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("x-log-apiversion", "0.6.0");
        hashMap.put("x-log-signaturemethod", "hmac-sha1");
        hashMap.put(MIME.CONTENT_TYPE, FastJsonJsonView.DEFAULT_CONTENT_TYPE);
        hashMap.put("Date", GetMGTTime());
        hashMap.put("Content-MD5", a(bArr2));
        hashMap.put("Content-Length", String.valueOf(bArr2.length));
        hashMap.put("x-log-bodyrawsize", String.valueOf(bArr.length));
        hashMap.put("x-log-compresstype", "deflate");
        hashMap.put("Host", this.e + "." + this.a);
        StringBuilder append = new StringBuilder("POST\n").append(((String) hashMap.get("Content-MD5")) + "\n").append(((String) hashMap.get(MIME.CONTENT_TYPE)) + "\n").append(((String) hashMap.get("Date")) + "\n");
        String str2 = this.d;
        if (!(str2 == null || str2 == "")) {
            hashMap.put("x-acs-security-token", str2);
            append.append("x-acs-security-token:" + ((String) hashMap.get("x-acs-security-token")) + "\n");
        }
        append.append("x-log-apiversion:0.6.0\n").append("x-log-bodyrawsize:" + ((String) hashMap.get("x-log-bodyrawsize")) + "\n").append("x-log-compresstype:deflate\n").append("x-log-signaturemethod:hmac-sha1\n").append("/logstores/" + str + "/shards/lb");
        try {
            hashMap.put("Authorization", "LOG " + this.b + ":" + hmac_sha1(append.toString(), this.c));
            return hashMap;
        } catch (Throwable e) {
            throw new c("LogClientError", "fail to get encode signature", e, "");
        }
    }

    public static String GetMGTTime() {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(instance.getTime());
    }

    public static String hmac_sha1(String str, String str2) throws Exception {
        byte[] bytes = str2.getBytes("UTF-8");
        byte[] bytes2 = str.getBytes("UTF-8");
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(new SecretKeySpec(bytes, "HmacSHA1"));
        return new String(cn.tatagou.sdk.c.a.a.encode(instance.doFinal(bytes2)));
    }

    private String a(byte[] bArr) throws c {
        try {
            String toUpperCase = new BigInteger(1, MessageDigest.getInstance("MD5").digest(bArr)).toString(16).toUpperCase();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; toUpperCase.length() + i < 32; i++) {
                stringBuilder.append("0");
            }
            return stringBuilder.toString() + toUpperCase;
        } catch (Throwable e) {
            throw new c("LogClientError", "Not Supported signature method MD5", e, "");
        }
    }

    private byte[] b(byte[] bArr) throws c {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = null;
        Deflater deflater = new Deflater();
        ByteArrayOutputStream byteArrayOutputStream2;
        try {
            byteArrayOutputStream2 = new ByteArrayOutputStream(bArr.length);
            try {
                deflater.setInput(bArr);
                deflater.finish();
                byte[] bArr2 = new byte[10240];
                while (!deflater.finished()) {
                    byteArrayOutputStream2.write(bArr2, 0, deflater.deflate(bArr2));
                }
                bArr2 = byteArrayOutputStream2.toByteArray();
                deflater.end();
                try {
                    if (byteArrayOutputStream2.size() != 0) {
                        byteArrayOutputStream2.close();
                    }
                } catch (IOException e) {
                }
                return bArr2;
            } catch (Exception e2) {
                try {
                    throw new c("LogClientError", "fail to zip data", "");
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    th = th3;
                    deflater.end();
                    try {
                        if (byteArrayOutputStream.size() != 0) {
                            byteArrayOutputStream.close();
                        }
                    } catch (IOException e3) {
                    }
                    throw th;
                }
            }
        } catch (Exception e4) {
            byteArrayOutputStream2 = null;
            throw new c("LogClientError", "fail to zip data", "");
        } catch (Throwable th4) {
            th = th4;
            deflater.end();
            if (byteArrayOutputStream.size() != 0) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }
}
