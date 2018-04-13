package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.b;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.noise.ABTest;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import qsbk.app.utils.HttpUtils;

public class c {
    private static boolean e = false;
    private String a = HttpUtils.PROXY_IP;
    private int b = 80;
    private Context c;
    private b d;

    public c(Context context) {
        this.c = context;
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    private void a() {
        Object imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "domain_p", "");
        Object imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "domain_s", "");
        if (!TextUtils.isEmpty(imprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(imprintProperty);
        }
        if (!TextUtils.isEmpty(imprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(imprintProperty2);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
        int testPolicy = ABTest.getService(this.c).getTestPolicy();
        if (testPolicy == -1) {
            return;
        }
        if (testPolicy == 0) {
            AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
        } else if (testPolicy == 1) {
            AnalyticsConstants.APPLOG_URL_LIST = new String[]{UMServerURL.SECONDARY_URL, UMServerURL.DEFAULT_URL};
        }
    }

    public byte[] a(byte[] bArr, boolean z) {
        byte[] bArr2 = null;
        a();
        for (String a : AnalyticsConstants.APPLOG_URL_LIST) {
            bArr2 = a(bArr, a);
            if (bArr2 != null) {
                if (this.d != null) {
                    this.d.onRequestSucceed(z);
                }
                return bArr2;
            }
            if (this.d != null) {
                this.d.onRequestFailed();
            }
        }
        return bArr2;
    }

    private boolean b() {
        if (this.c.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.c.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.c.getSystemService("connectivity");
            if (!DeviceConfig.checkPermission(this.c, "android.permission.ACCESS_NETWORK_STATE")) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (!(activeNetworkInfo == null || activeNetworkInfo.getType() == 1)) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (extraInfo != null && (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap"))) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            b.a(this.c, th);
        }
    }

    public byte[] a(byte[] bArr, String str) {
        HttpsURLConnection httpsURLConnection;
        OutputStream outputStream;
        HttpsURLConnection httpsURLConnection2;
        Throwable e;
        InputStream inputStream;
        OutputStream outputStream2 = null;
        OutputStream outputStream3;
        try {
            if (this.d != null) {
                this.d.onRequestStart();
            }
            if (b()) {
                httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection(new Proxy(Type.HTTP, new InetSocketAddress(this.a, this.b)));
            } else {
                httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
            }
            try {
                if (!e) {
                    HttpsURLConnection.setDefaultHostnameVerifier(new g(this));
                    SSLContext instance = SSLContext.getInstance("TLS");
                    instance.init(null, null, new SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
                    e = true;
                }
                httpsURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpsURLConnection.setRequestProperty("X-Umeng-Sdk", a.a(this.c).b());
                httpsURLConnection.setRequestProperty("Content-Type", a.a(this.c).a());
                httpsURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpsURLConnection.setConnectTimeout(30000);
                httpsURLConnection.setReadTimeout(30000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setUseCaches(false);
                outputStream3 = httpsURLConnection.getOutputStream();
            } catch (SSLHandshakeException e2) {
                outputStream = null;
                httpsURLConnection2 = httpsURLConnection;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable e3) {
                        b.a(this.c, e3);
                    }
                }
                if (httpsURLConnection2 != null) {
                    httpsURLConnection2.disconnect();
                }
                return null;
            } catch (Throwable th) {
                e3 = th;
                if (outputStream2 != null) {
                    outputStream2.close();
                }
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
                throw e3;
            }
            try {
                boolean z;
                outputStream3.write(bArr);
                outputStream3.flush();
                httpsURLConnection.connect();
                if (this.d != null) {
                    this.d.onRequestEnd();
                }
                int responseCode = httpsURLConnection.getResponseCode();
                Object headerField = httpsURLConnection.getHeaderField("Content-Type");
                if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("application/thrift")) {
                    z = false;
                } else {
                    z = true;
                }
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.d("status code : " + responseCode + "; isThrifit:" + z);
                }
                if (responseCode == 200 && z) {
                    MLog.i("Send message to server. status code is: " + responseCode);
                    inputStream = httpsURLConnection.getInputStream();
                    byte[] readStreamToByteArray = HelperUtils.readStreamToByteArray(inputStream);
                    HelperUtils.safeClose(inputStream);
                    if (outputStream3 != null) {
                        try {
                            outputStream3.close();
                        } catch (Throwable e4) {
                            b.a(this.c, e4);
                        }
                    }
                    if (httpsURLConnection == null) {
                        return readStreamToByteArray;
                    }
                    httpsURLConnection.disconnect();
                    return readStreamToByteArray;
                }
                if (outputStream3 != null) {
                    try {
                        outputStream3.close();
                    } catch (Throwable e32) {
                        b.a(this.c, e32);
                    }
                }
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
                return null;
            } catch (SSLHandshakeException e5) {
                outputStream = outputStream3;
                httpsURLConnection2 = httpsURLConnection;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (httpsURLConnection2 != null) {
                    httpsURLConnection2.disconnect();
                }
                return null;
            } catch (Throwable th2) {
                e32 = th2;
                b.a(this.c, e32);
                if (outputStream3 != null) {
                    outputStream3.close();
                }
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
                return null;
            }
        } catch (SSLHandshakeException e6) {
            outputStream = null;
            httpsURLConnection2 = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (httpsURLConnection2 != null) {
                httpsURLConnection2.disconnect();
            }
            return null;
        } catch (Throwable th3) {
            e32 = th3;
            httpsURLConnection = null;
            if (outputStream2 != null) {
                outputStream2.close();
            }
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
            throw e32;
        }
    }
}
