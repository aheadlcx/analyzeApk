package com.ali.auth.third.core.rpc;

import android.text.TextUtils;
import com.ali.auth.third.core.config.Environment;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.model.InternalSession;
import com.ali.auth.third.core.rpc.protocol.RpcException;
import com.ali.auth.third.core.rpc.protocol.SecurityKey;
import com.ali.auth.third.core.rpc.protocol.SecurityThreadLocal;
import com.ali.auth.third.core.rpc.safe.TriDes;
import com.ali.auth.third.core.service.impl.CredentialManager;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.core.util.IOUtils;
import com.ali.auth.third.core.util.RSAKey;
import com.ali.auth.third.core.util.Rsa;
import com.alipay.sdk.util.j;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.entity.mime.MIME;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpConnectionHelper {
    private static final String TAG = HttpConnectionHelper.class.getSimpleName();

    private static void filterResponseCode(int i) {
        if (i != 200) {
            throw new RuntimeException("http request exception, response code: " + i);
        }
    }

    private static void log(Map<String, String> map, String str) {
        if (SDKLogger.isDebugEnabled()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("request ").append(str).append('\n');
            if (map == null || map.size() == 0) {
                stringBuilder.append("with no param");
            } else {
                for (Entry entry : map.entrySet()) {
                    stringBuilder.append((String) entry.getKey()).append('=').append((String) entry.getValue()).append('\n');
                }
            }
            SDKLogger.d(TAG, stringBuilder.toString());
        }
    }

    public static String post(String str, String str2, String str3) {
        HttpURLConnection httpURLConnection;
        Closeable outputStreamWriter;
        Throwable th;
        Closeable closeable;
        Object obj;
        Closeable closeable2 = null;
        SDKLogger.d(TAG, "post target = " + str + " params" + str3);
        try {
            if (KernelContext.getEnvironment() == Environment.PRE) {
                httpURLConnection = (HttpURLConnection) new URL("http://hz.pre.tbusergw.taobao.net/gw.do").openConnection();
            } else if (KernelContext.getEnvironment() == Environment.TEST) {
                httpURLConnection = (HttpURLConnection) new URL("http://hz.tbusergw.taobao.net/gw.do").openConnection();
            } else {
                httpURLConnection = (HttpURLConnection) new URL("https://mgw.m.taobao.com/gw.do").openConnection();
            }
            try {
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, PostMethod.FORM_URL_ENCODED_CONTENT_TYPE);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setReadTimeout(15000);
                InternalSession internalSession = CredentialManager.INSTANCE.getInternalSession();
                if (!(internalSession.user == null || TextUtils.isEmpty(internalSession.user.userId))) {
                    httpURLConnection.setRequestProperty("userid", internalSession.user.userId);
                }
                outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly(closeable2);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
            try {
                outputStreamWriter.write(encodeRequest(str, str2, str3));
                outputStreamWriter.flush();
                filterResponseCode(httpURLConnection.getResponseCode());
                String decodeResponse = decodeResponse(httpURLConnection.getInputStream(), getCharset(httpURLConnection.getContentType()));
                IOUtils.closeQuietly(outputStreamWriter);
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception e) {
                    }
                }
                return decodeResponse;
            } catch (Throwable th3) {
                th = th3;
                closeable2 = outputStreamWriter;
                IOUtils.closeQuietly(closeable2);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
            IOUtils.closeQuietly(closeable2);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    public static String encodeRequest(String str, String str2, String str3) {
        String randomString = SecurityKey.getRandomString(24);
        SecurityThreadLocal.set(randomString);
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("id=1&");
            stringBuilder.append("apiName=" + str + "&");
            stringBuilder.append("apiVersion=" + str2 + "&");
            stringBuilder.append("requestData=" + URLEncoder.encode(getEncryptedData(randomString, str3), "UTF-8"));
            return stringBuilder.toString();
        } catch (Throwable e) {
            SDKLogger.e(TAG, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String getEncryptedData(String str, String str2) {
        Object rsaPubkey = RSAKey.getRsaPubkey();
        if (TextUtils.isEmpty(rsaPubkey)) {
            throw new IllegalArgumentException("get rsa from server failed!!!");
        }
        String encrypt = Rsa.encrypt(str, rsaPubkey);
        String encrypt2 = TriDes.encrypt(str, str2);
        return String.format(Locale.getDefault(), "%08X%s%08X%s", new Object[]{Integer.valueOf(encrypt.length()), encrypt, Integer.valueOf(encrypt2.length()), encrypt2});
    }

    private static String decodeResponse(InputStream inputStream, String str) throws UnsupportedEncodingException {
        int read;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        String str2 = new String(byteArrayOutputStream.toByteArray(), str);
        try {
            str2 = TriDes.decrypt(SecurityThreadLocal.get(), str2);
            SDKLogger.e(TAG, "gateway response=" + str2);
            JSONObject jSONObject = new JSONObject(str2);
            read = jSONObject.getInt(j.a);
            if (read == 1000) {
                return jSONObject.getString(j.c);
            }
            throw new RpcException(Integer.valueOf(read), j.b);
        } catch (JSONException e2) {
            throw new RpcException(Integer.valueOf(10), new StringBuilder().append("response  =").append(str2).append(":").append(e2).toString() == null ? "" : e2.getMessage());
        } catch (Exception e3) {
            e3.printStackTrace();
            throw new RpcException("net work error");
        }
    }

    public static String getCharset(String str) {
        return "utf-8";
    }
}
