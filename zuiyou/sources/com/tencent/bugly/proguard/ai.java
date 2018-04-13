package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import com.tencent.bugly.BuglyStrategy$a;
import com.tencent.bugly.beta.tinker.TinkerReport;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.connect.common.Constants;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class ai {
    private static ai c;
    protected Context a;
    public Map<String, String> b = null;

    private ai(Context context) {
        this.a = context;
    }

    public static ai a(Context context) {
        if (c == null) {
            c = new ai(context);
        }
        return c;
    }

    public byte[] a(String str, byte[] bArr, al alVar, Map<String, String> map) {
        Throwable th;
        int i;
        int i2;
        if (str == null) {
            an.e("Failed for no URL.", new Object[0]);
            return null;
        }
        long j;
        int i3 = 0;
        int i4 = 0;
        if (bArr == null) {
            j = 0;
        } else {
            j = (long) bArr.length;
        }
        an.c("request: %s, send: %d (pid=%d | tid=%d)", new Object[]{str, Long.valueOf(j), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())});
        Object obj = null;
        String str2 = str;
        while (i3 < 1 && i4 < 1) {
            if (obj != null) {
                obj = null;
            } else {
                i3++;
                if (i3 > 1) {
                    an.c("try time: " + i3, new Object[0]);
                    SystemClock.sleep(((long) new Random(System.currentTimeMillis()).nextInt(10000)) + 10000);
                }
            }
            String f = b.f(this.a);
            if (f == null) {
                an.d("Failed to request for network not avail", new Object[0]);
            } else {
                alVar.a(str2, j, f);
                HttpURLConnection a = a(str2, bArr, f, (Map) map);
                if (a != null) {
                    try {
                        int responseCode = a.getResponseCode();
                        if (responseCode == 200) {
                            long j2;
                            this.b = b(a);
                            byte[] a2 = a(a);
                            if (a2 == null) {
                                j2 = 0;
                            } else {
                                j2 = (long) a2.length;
                            }
                            alVar.a(j2);
                            try {
                                a.disconnect();
                            } catch (Throwable th2) {
                                if (!an.a(th2)) {
                                    th2.printStackTrace();
                                }
                            }
                            return a2;
                        }
                        if (a(responseCode)) {
                            try {
                                String headerField = a.getHeaderField("Location");
                                if (headerField == null) {
                                    an.e("Failed to redirect: %d" + responseCode, new Object[0]);
                                    try {
                                        a.disconnect();
                                        return null;
                                    } catch (Throwable th3) {
                                        if (an.a(th3)) {
                                            return null;
                                        }
                                        th3.printStackTrace();
                                        return null;
                                    }
                                }
                                i4++;
                                try {
                                    an.c("redirect code: %d ,to:%s", new Object[]{Integer.valueOf(responseCode), headerField});
                                    obj = 1;
                                    str2 = headerField;
                                    i3 = i4;
                                    i4 = 0;
                                } catch (Throwable e) {
                                    str2 = headerField;
                                    th = e;
                                    i = 1;
                                    i3 = i4;
                                    i4 = 0;
                                    try {
                                        if (!an.a(th)) {
                                            th.printStackTrace();
                                        }
                                        try {
                                            a.disconnect();
                                        } catch (Throwable th4) {
                                            if (!an.a(th4)) {
                                                th4.printStackTrace();
                                            }
                                        }
                                        i2 = i3;
                                        i3 = i4;
                                        i4 = i2;
                                    } catch (Throwable th32) {
                                        if (!an.a(th32)) {
                                            th32.printStackTrace();
                                        }
                                    }
                                }
                            } catch (Throwable e2) {
                                Throwable th5 = e2;
                                i = 1;
                                th4 = th5;
                                int i5 = i4;
                                i4 = i3;
                                i3 = i5;
                                if (an.a(th4)) {
                                    th4.printStackTrace();
                                }
                                a.disconnect();
                                i2 = i3;
                                i3 = i4;
                                i4 = i2;
                            }
                        } else {
                            i2 = i4;
                            i4 = i3;
                            i3 = i2;
                        }
                        try {
                            an.d("response code " + responseCode, new Object[0]);
                            long contentLength = (long) a.getContentLength();
                            if (contentLength < 0) {
                                contentLength = 0;
                            }
                            alVar.a(contentLength);
                            try {
                                a.disconnect();
                            } catch (Throwable th42) {
                                if (!an.a(th42)) {
                                    th42.printStackTrace();
                                }
                            }
                        } catch (IOException e3) {
                            th42 = e3;
                            if (an.a(th42)) {
                                th42.printStackTrace();
                            }
                            a.disconnect();
                            i2 = i3;
                            i3 = i4;
                            i4 = i2;
                        }
                    } catch (IOException e4) {
                        th42 = e4;
                        i2 = i4;
                        i4 = i3;
                        i3 = i2;
                    }
                } else {
                    an.c("Failed to execute post.", new Object[0]);
                    alVar.a(0);
                    i2 = i4;
                    i4 = i3;
                    i3 = i2;
                }
                i2 = i3;
                i3 = i4;
                i4 = i2;
            }
        }
        return null;
    }

    private Map<String, String> b(HttpURLConnection httpURLConnection) {
        HashMap hashMap = new HashMap();
        Map headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null || headerFields.size() == 0) {
            return null;
        }
        for (String str : headerFields.keySet()) {
            List list = (List) headerFields.get(str);
            if (list.size() >= 1) {
                hashMap.put(str, list.get(0));
            }
        }
        return hashMap;
    }

    protected byte[] a(HttpURLConnection httpURLConnection) {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        Throwable th2;
        byte[] bArr = null;
        if (httpURLConnection != null) {
            try {
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int read = bufferedInputStream.read(bArr2);
                        if (read <= 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    byteArrayOutputStream.flush();
                    bArr = byteArrayOutputStream.toByteArray();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                        }
                    }
                } catch (Throwable th4) {
                    th3 = th4;
                    try {
                        if (!an.a(th3)) {
                            th3.printStackTrace();
                        }
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Throwable th32) {
                                th32.printStackTrace();
                            }
                        }
                        return bArr;
                    } catch (Throwable th5) {
                        th2 = th5;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Throwable th322) {
                                th322.printStackTrace();
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th3222) {
                bufferedInputStream = bArr;
                th2 = th3222;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th2;
            }
        }
        return bArr;
    }

    protected HttpURLConnection a(String str, byte[] bArr, String str2, Map<String, String> map) {
        if (str == null) {
            an.e("destUrl is null.", new Object[0]);
            return null;
        }
        HttpURLConnection a = a(str2, str);
        if (a == null) {
            an.e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            a.setRequestProperty("wup_version", "3.0");
            if (map != null && map.size() > 0) {
                for (Entry entry : map.entrySet()) {
                    a.setRequestProperty((String) entry.getKey(), URLEncoder.encode((String) entry.getValue(), "utf-8"));
                }
            }
            a.setRequestProperty("A37", URLEncoder.encode(str2, "utf-8"));
            a.setRequestProperty("A38", URLEncoder.encode(str2, "utf-8"));
            OutputStream outputStream = a.getOutputStream();
            if (bArr == null) {
                outputStream.write(0);
            } else {
                outputStream.write(bArr);
            }
            return a;
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            an.e("Failed to upload, please check your network.", new Object[0]);
            return null;
        }
    }

    protected HttpURLConnection a(String str, String str2) {
        try {
            HttpURLConnection httpURLConnection;
            URL url = new URL(str2);
            if (str == null || !str.toLowerCase(Locale.US).contains("wap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")))));
            }
            httpURLConnection.setConnectTimeout(BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod(Constants.HTTP_POST);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    protected boolean a(int i) {
        return i == 301 || i == 302 || i == 303 || i == TinkerReport.KEY_LOADED_MISSING_DEX_OPT;
    }
}
