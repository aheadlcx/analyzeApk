package com.budejie.www.util;

import android.text.TextUtils;
import java.net.HttpURLConnection;
import java.net.URL;

public class y {
    public static boolean a(String str) {
        boolean z;
        Throwable th;
        boolean z2 = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        HttpURLConnection httpURLConnection = null;
        HttpURLConnection httpURLConnection2;
        try {
            httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                if (httpURLConnection2.getResponseCode() == 200) {
                    z2 = true;
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                    z = z2;
                    return z;
                }
            } catch (Exception e) {
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                    z = false;
                    return z;
                }
                z = z2;
                return z;
            } catch (Throwable th2) {
                httpURLConnection = httpURLConnection2;
                th = th2;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception e2) {
            httpURLConnection2 = null;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
                z = false;
                return z;
            }
            z = z2;
            return z;
        } catch (Throwable th3) {
            th = th3;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
        z = z2;
        return z;
    }
}
