package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import javax.net.ssl.HttpsURLConnection;

class l implements Callable {
    private static Context a;
    private static a hostManager = a.a();
    /* renamed from: a */
    private p f0a;
    /* renamed from: a */
    private String f1a;
    private int d = 1;
    private String[] e = c.d;
    private String f = null;

    l(String str, p pVar) {
        this.f1a = str;
        this.f0a = pVar;
    }

    static void setContext(Context context) {
        a = context;
    }

    public void a(int i) {
        if (i >= 0) {
            this.d = i;
        }
    }

    public String[] b() {
        InputStream inputStream;
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        InputStream inputStream2;
        BufferedReader bufferedReader2;
        HttpURLConnection httpURLConnection2;
        Throwable th;
        BufferedReader bufferedReader3;
        BufferedReader bufferedReader4 = null;
        try {
            if (VERSION.SDK_INT >= 14) {
                TrafficStats.setThreadStatsTag(40965);
            }
            hostManager.a(this.f1a);
            this.f = u.a(this.f0a);
            if (this.f == null) {
                f.d("serverIp is null, give up query for hostname:" + this.f1a);
                inputStream = null;
                httpURLConnection = null;
            } else {
                httpURLConnection = (HttpURLConnection) new URL(c.PROTOCOL + this.f + ":" + c.c + "/" + c.b + "/d?host=" + this.f1a).openConnection();
                try {
                    httpURLConnection.setConnectTimeout(c.a);
                    httpURLConnection.setReadTimeout(c.a);
                    if (httpURLConnection instanceof HttpsURLConnection) {
                        ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new m(this));
                    }
                    StringBuilder stringBuilder;
                    String readLine;
                    if (httpURLConnection.getResponseCode() != 200) {
                        inputStream = httpURLConnection.getErrorStream();
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                            try {
                                stringBuilder = new StringBuilder();
                                while (true) {
                                    readLine = bufferedReader.readLine();
                                    if (readLine != null) {
                                        stringBuilder.append(readLine);
                                    } else {
                                        f.e("response code is " + httpURLConnection.getResponseCode() + " expect 200. response body is " + stringBuilder.toString());
                                        throw new e(httpURLConnection.getResponseCode(), new d(httpURLConnection.getResponseCode(), stringBuilder.toString()).a());
                                    }
                                }
                            } catch (Throwable th2) {
                                bufferedReader4 = bufferedReader;
                                httpURLConnection2 = httpURLConnection;
                                th = th2;
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (bufferedReader4 != null) {
                                    bufferedReader4.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th22) {
                            httpURLConnection2 = httpURLConnection;
                            th = th22;
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (bufferedReader4 != null) {
                                bufferedReader4.close();
                            }
                            throw th;
                        }
                    }
                    inputStream = httpURLConnection.getInputStream();
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    try {
                        stringBuilder = new StringBuilder();
                        while (true) {
                            readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuilder.append(readLine);
                        }
                        f.d("resolve host: " + this.f1a + ", return: " + stringBuilder.toString());
                        b bVar = new b(stringBuilder.toString());
                        if (hostManager.a() < 100) {
                            hostManager.a(this.f1a, bVar);
                            u.a(this.f1a, this.f);
                            hostManager.b(this.f1a);
                            this.e = bVar.a();
                            bufferedReader4 = bufferedReader;
                        } else {
                            throw new Exception("the total number of hosts is exceed 100");
                        }
                    } catch (Throwable th222) {
                        bufferedReader4 = bufferedReader;
                        httpURLConnection2 = httpURLConnection;
                        th = th222;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (bufferedReader4 != null) {
                            bufferedReader4.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2222) {
                    inputStream = null;
                    httpURLConnection2 = httpURLConnection;
                    th = th2222;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (bufferedReader4 != null) {
                        bufferedReader4.close();
                    }
                    throw th;
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th3) {
                    f.a(th3);
                }
            }
            if (bufferedReader4 != null) {
                bufferedReader4.close();
            }
        } catch (Throwable th4) {
            th3 = th4;
            inputStream = null;
            httpURLConnection2 = null;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (bufferedReader4 != null) {
                bufferedReader4.close();
            }
            throw th3;
        }
        hostManager.b(this.f1a);
        return this.e;
    }

    public /* synthetic */ Object call() {
        return b();
    }
}
