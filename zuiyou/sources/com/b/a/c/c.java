package com.b.a.c;

import com.b.a.b.a.a;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public final class c {
    public static boolean a(String str, byte[] bArr) {
        HttpURLConnection httpURLConnection;
        IOException iOException;
        Throwable th;
        OutputStream outputStream = null;
        boolean z = true;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                a.h();
                httpURLConnection2.setRequestMethod(Constants.HTTP_POST);
                httpURLConnection2.setConnectTimeout(5000);
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                httpURLConnection2.setRequestProperty("Content-Length", String.valueOf(bArr.length));
                outputStream = httpURLConnection2.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                int responseCode = httpURLConnection2.getResponseCode();
                a.h();
                if (responseCode != 200) {
                    z = false;
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (httpURLConnection2 == null) {
                    return z;
                }
                httpURLConnection2.disconnect();
                a.h();
                return z;
            } catch (IOException e2) {
                IOException iOException2 = e2;
                httpURLConnection = httpURLConnection2;
                iOException = iOException2;
                try {
                    new StringBuilder("IOException:").append(iOException.getMessage());
                    a.h();
                    iOException.printStackTrace();
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException iOException3) {
                            iOException3.printStackTrace();
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                        a.h();
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                        a.h();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                httpURLConnection = httpURLConnection2;
                th = th4;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                    a.h();
                }
                throw th;
            }
        } catch (IOException e4) {
            iOException3 = e4;
            httpURLConnection = null;
            new StringBuilder("IOException:").append(iOException3.getMessage());
            a.h();
            iOException3.printStackTrace();
            if (outputStream != null) {
                outputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
                a.h();
            }
            return false;
        } catch (Throwable th5) {
            th = th5;
            httpURLConnection = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
                a.h();
            }
            throw th;
        }
    }
}
