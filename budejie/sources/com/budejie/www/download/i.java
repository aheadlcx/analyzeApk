package com.budejie.www.download;

import android.text.TextUtils;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class i {
    public static String a(String str) {
        return str.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
    }

    public static String b(String str) {
        MalformedURLException malformedURLException;
        Throwable th;
        IOException iOException;
        if (!TextUtils.isEmpty(str)) {
            HttpURLConnection httpURLConnection = null;
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(ErrorCode.SERVER_OPERATIONTYPEMISSED);
                    httpURLConnection2.setReadTimeout(ErrorCode.SERVER_OPERATIONTYPEMISSED);
                    httpURLConnection2.connect();
                    httpURLConnection2.getResponseCode();
                    str = httpURLConnection2.getURL().toString();
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                } catch (MalformedURLException e) {
                    MalformedURLException malformedURLException2 = e;
                    httpURLConnection = httpURLConnection2;
                    malformedURLException = malformedURLException2;
                    try {
                        malformedURLException.printStackTrace();
                        str = "";
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return str;
                    } catch (Throwable th2) {
                        th = th2;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (IOException e2) {
                    IOException iOException2 = e2;
                    httpURLConnection = httpURLConnection2;
                    iOException = iOException2;
                    iOException.printStackTrace();
                    str = "";
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return str;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    httpURLConnection = httpURLConnection2;
                    th = th4;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (MalformedURLException e3) {
                malformedURLException = e3;
                malformedURLException.printStackTrace();
                str = "";
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return str;
            } catch (IOException e4) {
                iOException = e4;
                iOException.printStackTrace();
                str = "";
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return str;
            }
        }
        return str;
    }
}
