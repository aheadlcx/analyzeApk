package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.umeng.commonsdk.proguard.b;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import qsbk.app.utils.HttpUtils;

public class e {
    private String a = HttpUtils.PROXY_IP;
    private int b = 80;
    private Context c;

    public e(Context context) {
        this.c = context;
    }

    private boolean a() {
        if (this.c != null) {
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
            } catch (Throwable th) {
                b.a(this.c, th);
            }
        }
        return false;
    }

    public boolean a(byte[] bArr, String str) {
        Throwable e;
        OutputStream outputStream = null;
        if (bArr == null || str == null) {
            com.umeng.commonsdk.statistics.common.e.a("walle", "[stateless] sendMessage, envelopeByte == null || path == null ");
            return false;
        }
        HttpsURLConnection httpsURLConnection;
        try {
            if (a()) {
                httpsURLConnection = (HttpsURLConnection) new URL("https://plbslog.umeng.com/" + str).openConnection(new Proxy(Type.HTTP, new InetSocketAddress(this.a, this.b)));
            } else {
                httpsURLConnection = (HttpsURLConnection) new URL("https://plbslog.umeng.com/" + str).openConnection();
            }
            try {
                boolean z;
                HttpsURLConnection.setDefaultHostnameVerifier(new j(this));
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, null, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
                httpsURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpsURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpsURLConnection.setConnectTimeout(30000);
                httpsURLConnection.setReadTimeout(30000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setUseCaches(false);
                outputStream = httpsURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                httpsURLConnection.connect();
                if (httpsURLConnection.getResponseCode() == 200) {
                    z = true;
                } else {
                    z = false;
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e2) {
                    }
                }
                if (httpsURLConnection == null) {
                    return z;
                }
                httpsURLConnection.disconnect();
                return z;
            } catch (SSLHandshakeException e3) {
                e = e3;
                try {
                    MLog.e("SSLHandshakeException, Failed to send message.", e);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e4) {
                        }
                    }
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                        return false;
                    }
                    return false;
                } catch (Throwable th) {
                    e = th;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e5) {
                        }
                    }
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                MLog.e("Exception,Failed to send message.", e);
                b.a(this.c, e);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e6) {
                    }
                }
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                    return false;
                }
                return false;
            }
        } catch (SSLHandshakeException e7) {
            e = e7;
            httpsURLConnection = null;
            MLog.e("SSLHandshakeException, Failed to send message.", e);
            if (outputStream != null) {
                outputStream.close();
            }
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
                return false;
            }
            return false;
        } catch (Throwable th3) {
            e = th3;
            httpsURLConnection = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
            throw e;
        }
    }

    public boolean b(byte[] bArr, String str) {
        HttpURLConnection httpURLConnection;
        Throwable th;
        OutputStream outputStream = null;
        if (bArr == null || str == null) {
            return false;
        }
        try {
            if (a()) {
                httpURLConnection = (HttpURLConnection) new URL("https://plbslog.umeng.com/" + str).openConnection(new Proxy(Type.HTTP, new InetSocketAddress(this.a, this.b)));
            } else {
                httpURLConnection = (HttpURLConnection) new URL("https://plbslog.umeng.com/" + str).openConnection();
            }
            try {
                boolean z;
                httpURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpURLConnection.setConnectTimeout(30000);
                httpURLConnection.setReadTimeout(30000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    z = true;
                } else {
                    z = false;
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e) {
                    }
                }
                if (httpURLConnection == null) {
                    return z;
                }
                httpURLConnection.disconnect();
                return z;
            } catch (Throwable th2) {
                th = th2;
                try {
                    b.a(this.c, th);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e2) {
                        }
                    }
                    if (httpURLConnection != null) {
                        return false;
                    }
                    httpURLConnection.disconnect();
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
