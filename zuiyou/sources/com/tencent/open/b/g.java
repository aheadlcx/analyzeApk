package com.tencent.open.b;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.iflytek.speech.UtilityConfig;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.open.utils.d;
import com.tencent.open.utils.e;
import com.tencent.open.utils.h;
import com.tencent.open.utils.i;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONObject;

public class g {
    protected static g a;
    protected Random b = new Random();
    protected List<Serializable> c = Collections.synchronizedList(new ArrayList());
    protected List<Serializable> d = Collections.synchronizedList(new ArrayList());
    protected HandlerThread e = null;
    protected Handler f;
    protected Executor g = h.b();
    protected Executor h = h.b();

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            if (a == null) {
                a = new g();
            }
            gVar = a;
        }
        return gVar;
    }

    private g() {
        if (this.e == null) {
            this.e = new HandlerThread("opensdk.report.handlerthread", 10);
            this.e.start();
        }
        if (this.e.isAlive() && this.e.getLooper() != null) {
            this.f = new Handler(this, this.e.getLooper()) {
                final /* synthetic */ g a;

                public void handleMessage(Message message) {
                    switch (message.what) {
                        case 1000:
                            this.a.b();
                            break;
                        case 1001:
                            this.a.e();
                            break;
                    }
                    super.handleMessage(message);
                }
            };
        }
    }

    public void a(final Bundle bundle, String str, final boolean z) {
        if (bundle != null) {
            f.a("openSDK_LOG.ReportManager", "-->reportVia, bundle: " + bundle.toString());
            if (a("report_via", str) || z) {
                this.g.execute(new Runnable(this) {
                    final /* synthetic */ g c;

                    public void run() {
                        try {
                            Bundle bundle = new Bundle();
                            bundle.putString("uin", Constants.DEFAULT_UIN);
                            bundle.putString("imei", c.b(d.a()));
                            bundle.putString("imsi", c.c(d.a()));
                            bundle.putString("android_id", c.d(d.a()));
                            bundle.putString("mac", c.a());
                            bundle.putString("platform", "1");
                            bundle.putString("os_ver", VERSION.RELEASE);
                            bundle.putString(RequestParameters.POSITION, i.c(d.a()));
                            bundle.putString("network", a.a(d.a()));
                            bundle.putString("language", c.b());
                            bundle.putString(com.umeng.analytics.b.g.r, c.a(d.a()));
                            bundle.putString("apn", a.b(d.a()));
                            bundle.putString("model_name", Build.MODEL);
                            bundle.putString(com.umeng.analytics.b.g.E, TimeZone.getDefault().getID());
                            bundle.putString("sdk_ver", Constants.SDK_VERSION);
                            bundle.putString("qz_ver", i.d(d.a(), Constants.PACKAGE_QZONE));
                            bundle.putString("qq_ver", i.c(d.a(), "com.tencent.mobileqq"));
                            bundle.putString("qua", i.e(d.a(), d.b()));
                            bundle.putString("packagename", d.b());
                            bundle.putString("app_ver", i.d(d.a(), d.b()));
                            if (bundle != null) {
                                bundle.putAll(bundle);
                            }
                            this.c.d.add(new b(bundle));
                            int size = this.c.d.size();
                            int a = e.a(d.a(), null).a("Agent_ReportTimeInterval");
                            if (a == 0) {
                                a = 10000;
                            }
                            if (this.c.a("report_via", size) || z) {
                                this.c.e();
                                this.c.f.removeMessages(1001);
                            } else if (!this.c.f.hasMessages(1001)) {
                                Message obtain = Message.obtain();
                                obtain.what = 1001;
                                this.c.f.sendMessageDelayed(obtain, (long) a);
                            }
                        } catch (Throwable e) {
                            f.b("openSDK_LOG.ReportManager", "--> reporVia, exception in sub thread.", e);
                        }
                    }
                });
            }
        }
    }

    public void a(String str, long j, long j2, long j3, int i) {
        a(str, j, j2, j3, i, "", false);
    }

    public void a(String str, long j, long j2, long j3, int i, String str2, boolean z) {
        f.a("openSDK_LOG.ReportManager", "-->reportCgi, command: " + str + " | startTime: " + j + " | reqSize:" + j2 + " | rspSize: " + j3 + " | responseCode: " + i + " | detail: " + str2);
        if (a("report_cgi", "" + i) || z) {
            final long j4 = j;
            final String str3 = str;
            final String str4 = str2;
            final int i2 = i;
            final long j5 = j2;
            final long j6 = j3;
            final boolean z2 = z;
            this.h.execute(new Runnable(this) {
                final /* synthetic */ g h;

                public void run() {
                    int i = 1;
                    try {
                        long elapsedRealtime = SystemClock.elapsedRealtime() - j4;
                        Bundle bundle = new Bundle();
                        String a = a.a(d.a());
                        bundle.putString("apn", a);
                        bundle.putString("appid", "1000067");
                        bundle.putString("commandid", str3);
                        bundle.putString("detail", str4);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("network=").append(a).append('&');
                        stringBuilder.append("sdcard=").append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0).append('&');
                        stringBuilder.append("wifi=").append(a.e(d.a()));
                        bundle.putString("deviceInfo", stringBuilder.toString());
                        int a2 = 100 / this.h.a(i2);
                        if (a2 > 0) {
                            if (a2 > 100) {
                                i = 100;
                            } else {
                                i = a2;
                            }
                        }
                        bundle.putString("frequency", i + "");
                        bundle.putString("reqSize", j5 + "");
                        bundle.putString("resultCode", i2 + "");
                        bundle.putString("rspSize", j6 + "");
                        bundle.putString("timeCost", elapsedRealtime + "");
                        bundle.putString("uin", Constants.DEFAULT_UIN);
                        this.h.c.add(new b(bundle));
                        int size = this.h.c.size();
                        i = e.a(d.a(), null).a("Agent_ReportTimeInterval");
                        if (i == 0) {
                            i = 10000;
                        }
                        if (this.h.a("report_cgi", size) || z2) {
                            this.h.b();
                            this.h.f.removeMessages(1000);
                        } else if (!this.h.f.hasMessages(1000)) {
                            Message obtain = Message.obtain();
                            obtain.what = 1000;
                            this.h.f.sendMessageDelayed(obtain, (long) i);
                        }
                    } catch (Throwable e) {
                        f.b("openSDK_LOG.ReportManager", "--> reportCGI, exception in sub thread.", e);
                    }
                }
            });
        }
    }

    protected void b() {
        this.h.execute(new Runnable(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r8 = this;
                r1 = 0;
                r0 = r8.a;	 Catch:{ Exception -> 0x00bf }
                r4 = r0.c();	 Catch:{ Exception -> 0x00bf }
                if (r4 != 0) goto L_0x000a;
            L_0x0009:
                return;
            L_0x000a:
                r0 = com.tencent.open.utils.d.a();	 Catch:{ Exception -> 0x00bf }
                r2 = 0;
                r0 = com.tencent.open.utils.e.a(r0, r2);	 Catch:{ Exception -> 0x00bf }
                r2 = "Common_HttpRetryCount";
                r0 = r0.a(r2);	 Catch:{ Exception -> 0x00bf }
                if (r0 != 0) goto L_0x00cb;
            L_0x001c:
                r0 = 3;
                r3 = r0;
            L_0x001e:
                r0 = "openSDK_LOG.ReportManager";
                r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00bf }
                r2.<init>();	 Catch:{ Exception -> 0x00bf }
                r5 = "-->doReportCgi, retryCount: ";
                r2 = r2.append(r5);	 Catch:{ Exception -> 0x00bf }
                r2 = r2.append(r3);	 Catch:{ Exception -> 0x00bf }
                r2 = r2.toString();	 Catch:{ Exception -> 0x00bf }
                com.tencent.open.a.f.b(r0, r2);	 Catch:{ Exception -> 0x00bf }
                r0 = r1;
            L_0x0039:
                r0 = r0 + 1;
                r2 = com.tencent.open.utils.d.a();	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r5 = 0;
                r6 = "http://wspeed.qq.com/w.cgi";
                r2 = com.tencent.open.utils.HttpUtils.getHttpClient(r2, r5, r6);	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r5 = new org.apache.http.client.methods.HttpPost;	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r6 = "http://wspeed.qq.com/w.cgi";
                r5.<init>(r6);	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r6 = "Accept-Encoding";
                r7 = "gzip";
                r5.addHeader(r6, r7);	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r6 = "Content-Type";
                r7 = "application/x-www-form-urlencoded";
                r5.setHeader(r6, r7);	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r6 = com.tencent.open.utils.HttpUtils.encodeUrl(r4);	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r6 = com.tencent.open.utils.i.i(r6);	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r7 = new org.apache.http.entity.ByteArrayEntity;	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r7.<init>(r6);	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r5.setEntity(r7);	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r2 = r2.execute(r5);	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r2 = r2.getStatusLine();	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r2 = r2.getStatusCode();	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r5 = "openSDK_LOG.ReportManager";
                r6 = new java.lang.StringBuilder;	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r6.<init>();	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r7 = "-->doReportCgi, statusCode: ";
                r6 = r6.append(r7);	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r6 = r6.append(r2);	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r6 = r6.toString();	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                com.tencent.open.a.f.b(r5, r6);	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                if (r2 != r5) goto L_0x00a6;
            L_0x009b:
                r2 = com.tencent.open.b.f.a();	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r5 = "report_cgi";
                r2.b(r5);	 Catch:{ ConnectTimeoutException -> 0x00ce, SocketTimeoutException -> 0x00db, Exception -> 0x00e6 }
                r1 = 1;
            L_0x00a6:
                if (r1 != 0) goto L_0x00b6;
            L_0x00a8:
                r0 = com.tencent.open.b.f.a();	 Catch:{ Exception -> 0x00bf }
                r1 = "report_cgi";
                r2 = r8.a;	 Catch:{ Exception -> 0x00bf }
                r2 = r2.c;	 Catch:{ Exception -> 0x00bf }
                r0.a(r1, r2);	 Catch:{ Exception -> 0x00bf }
            L_0x00b6:
                r0 = r8.a;	 Catch:{ Exception -> 0x00bf }
                r0 = r0.c;	 Catch:{ Exception -> 0x00bf }
                r0.clear();	 Catch:{ Exception -> 0x00bf }
                goto L_0x0009;
            L_0x00bf:
                r0 = move-exception;
                r1 = "openSDK_LOG.ReportManager";
                r2 = "-->doReportCgi, doupload exception out.";
                com.tencent.open.a.f.b(r1, r2, r0);
                goto L_0x0009;
            L_0x00cb:
                r3 = r0;
                goto L_0x001e;
            L_0x00ce:
                r2 = move-exception;
                r5 = "openSDK_LOG.ReportManager";
                r6 = "-->doReportCgi, doupload exception";
                com.tencent.open.a.f.b(r5, r6, r2);	 Catch:{ Exception -> 0x00bf }
            L_0x00d8:
                if (r0 < r3) goto L_0x0039;
            L_0x00da:
                goto L_0x00a6;
            L_0x00db:
                r2 = move-exception;
                r5 = "openSDK_LOG.ReportManager";
                r6 = "-->doReportCgi, doupload exception";
                com.tencent.open.a.f.b(r5, r6, r2);	 Catch:{ Exception -> 0x00bf }
                goto L_0x00d8;
            L_0x00e6:
                r0 = move-exception;
                r2 = "openSDK_LOG.ReportManager";
                r3 = "-->doReportCgi, doupload exception";
                com.tencent.open.a.f.b(r2, r3, r0);	 Catch:{ Exception -> 0x00bf }
                goto L_0x00a6;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.g.4.run():void");
            }
        });
    }

    protected boolean a(String str, String str2) {
        boolean z = true;
        boolean z2 = false;
        f.b("openSDK_LOG.ReportManager", "-->availableFrequency, report: " + str + " | ext: " + str2);
        if (!TextUtils.isEmpty(str)) {
            int i;
            int a;
            if (str.equals("report_cgi")) {
                try {
                    a = a(Integer.parseInt(str2));
                    if (this.b.nextInt(100) >= a) {
                        z = false;
                    }
                    z2 = z;
                    i = a;
                } catch (Exception e) {
                }
            } else if (str.equals("report_via")) {
                a = e.a(str2);
                if (this.b.nextInt(100) < a) {
                    z2 = true;
                    i = a;
                } else {
                    i = a;
                }
            } else {
                i = 100;
            }
            f.b("openSDK_LOG.ReportManager", "-->availableFrequency, result: " + z2 + " | frequency: " + i);
        }
        return z2;
    }

    protected boolean a(String str, int i) {
        int i2 = 5;
        int a;
        if (str.equals("report_cgi")) {
            a = e.a(d.a(), null).a("Common_CGIReportMaxcount");
            if (a != 0) {
                i2 = a;
            }
        } else if (str.equals("report_via")) {
            a = e.a(d.a(), null).a("Agent_ReportBatchCount");
            if (a != 0) {
                i2 = a;
            }
        } else {
            i2 = 0;
        }
        f.b("openSDK_LOG.ReportManager", "-->availableCount, report: " + str + " | dataSize: " + i + " | maxcount: " + i2);
        if (i >= i2) {
            return true;
        }
        return false;
    }

    protected int a(int i) {
        int a;
        if (i == 0) {
            a = e.a(d.a(), null).a("Common_CGIReportFrequencySuccess");
            if (a == 0) {
                return 10;
            }
            return a;
        }
        a = e.a(d.a(), null).a("Common_CGIReportFrequencyFailed");
        if (a == 0) {
            return 100;
        }
        return a;
    }

    protected Bundle c() {
        if (this.c.size() == 0) {
            return null;
        }
        b bVar = (b) this.c.get(0);
        if (bVar == null) {
            f.b("openSDK_LOG.ReportManager", "-->prepareCgiData, the 0th cgireportitem is null.");
            return null;
        }
        String str = (String) bVar.a.get("appid");
        Collection a = f.a().a("report_cgi");
        if (a != null) {
            this.c.addAll(a);
        }
        f.b("openSDK_LOG.ReportManager", "-->prepareCgiData, mCgiList size: " + this.c.size());
        if (this.c.size() == 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        try {
            bundle.putString("appid", str);
            bundle.putString("releaseversion", Constants.SDK_VERSION_REPORT);
            bundle.putString(UtilityConfig.KEY_DEVICE_INFO, Build.DEVICE);
            bundle.putString("qua", Constants.SDK_QUA);
            bundle.putString("key", "apn,frequency,commandid,resultcode,tmcost,reqsize,rspsize,detail,touin,deviceinfo");
            for (int i = 0; i < this.c.size(); i++) {
                bVar = (b) this.c.get(i);
                bundle.putString(i + "_1", (String) bVar.a.get("apn"));
                bundle.putString(i + "_2", (String) bVar.a.get("frequency"));
                bundle.putString(i + "_3", (String) bVar.a.get("commandid"));
                bundle.putString(i + "_4", (String) bVar.a.get("resultCode"));
                bundle.putString(i + "_5", (String) bVar.a.get("timeCost"));
                bundle.putString(i + "_6", (String) bVar.a.get("reqSize"));
                bundle.putString(i + "_7", (String) bVar.a.get("rspSize"));
                bundle.putString(i + "_8", (String) bVar.a.get("detail"));
                bundle.putString(i + "_9", (String) bVar.a.get("uin"));
                bundle.putString(i + "_10", c.e(d.a()) + "&" + ((String) bVar.a.get("deviceInfo")));
            }
            f.a("openSDK_LOG.ReportManager", "-->prepareCgiData, end. params: " + bundle.toString());
            return bundle;
        } catch (Throwable e) {
            f.b("openSDK_LOG.ReportManager", "-->prepareCgiData, exception.", e);
            return null;
        }
    }

    protected Bundle d() {
        Collection a = f.a().a("report_via");
        if (a != null) {
            this.d.addAll(a);
        }
        f.b("openSDK_LOG.ReportManager", "-->prepareViaData, mViaList size: " + this.d.size());
        if (this.d.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Serializable serializable : this.d) {
            JSONObject jSONObject = new JSONObject();
            b bVar = (b) serializable;
            for (String str : bVar.a.keySet()) {
                try {
                    Object obj = (String) bVar.a.get(str);
                    if (obj == null) {
                        obj = "";
                    }
                    jSONObject.put(str, obj);
                } catch (Throwable e) {
                    f.b("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e);
                }
            }
            jSONArray.put(jSONObject);
        }
        f.a("openSDK_LOG.ReportManager", "-->prepareViaData, JSONArray array: " + jSONArray.toString());
        Bundle bundle = new Bundle();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("data", jSONArray);
            bundle.putString("data", jSONObject2.toString());
            return bundle;
        } catch (Throwable e2) {
            f.b("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e2);
            return null;
        }
    }

    protected void e() {
        this.g.execute(new Runnable(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r18 = this;
                r0 = r18;
                r2 = r0.a;	 Catch:{ Exception -> 0x00ac }
                r14 = r2.d();	 Catch:{ Exception -> 0x00ac }
                if (r14 != 0) goto L_0x000b;
            L_0x000a:
                return;
            L_0x000b:
                r2 = "openSDK_LOG.ReportManager";
                r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ac }
                r3.<init>();	 Catch:{ Exception -> 0x00ac }
                r4 = "-->doReportVia, params: ";
                r3 = r3.append(r4);	 Catch:{ Exception -> 0x00ac }
                r4 = r14.toString();	 Catch:{ Exception -> 0x00ac }
                r3 = r3.append(r4);	 Catch:{ Exception -> 0x00ac }
                r3 = r3.toString();	 Catch:{ Exception -> 0x00ac }
                com.tencent.open.a.f.a(r2, r3);	 Catch:{ Exception -> 0x00ac }
                r11 = com.tencent.open.b.e.a();	 Catch:{ Exception -> 0x00ac }
                r10 = 0;
                r3 = 0;
                r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x00ac }
                r6 = 0;
                r4 = 0;
                r2 = 0;
            L_0x0038:
                r10 = r10 + 1;
                r12 = com.tencent.open.utils.d.a();	 Catch:{ ConnectTimeoutException -> 0x00bb, SocketTimeoutException -> 0x00cb, JSONException -> 0x00d6, NetworkUnavailableException -> 0x00dd, HttpStatusException -> 0x00f2, IOException -> 0x0113, Exception -> 0x011e }
                r13 = "http://appsupport.qq.com/cgi-bin/appstage/mstats_batch_report";
                r15 = "POST";
                r15 = com.tencent.open.utils.HttpUtils.openUrl2(r12, r13, r15, r14);	 Catch:{ ConnectTimeoutException -> 0x00bb, SocketTimeoutException -> 0x00cb, JSONException -> 0x00d6, NetworkUnavailableException -> 0x00dd, HttpStatusException -> 0x00f2, IOException -> 0x0113, Exception -> 0x011e }
                r12 = r15.a;	 Catch:{ ConnectTimeoutException -> 0x00bb, SocketTimeoutException -> 0x00cb, JSONException -> 0x00d6, NetworkUnavailableException -> 0x00dd, HttpStatusException -> 0x00f2, IOException -> 0x0113, Exception -> 0x011e }
                r12 = com.tencent.open.utils.i.d(r12);	 Catch:{ ConnectTimeoutException -> 0x00bb, SocketTimeoutException -> 0x00cb, JSONException -> 0x00d6, NetworkUnavailableException -> 0x00dd, HttpStatusException -> 0x00f2, IOException -> 0x0113, Exception -> 0x011e }
                r13 = "ret";
                r12 = r12.getInt(r13);	 Catch:{ JSONException -> 0x00b8, ConnectTimeoutException -> 0x00bb, SocketTimeoutException -> 0x00cb, NetworkUnavailableException -> 0x00dd, HttpStatusException -> 0x00f2, IOException -> 0x0113, Exception -> 0x011e }
            L_0x0055:
                if (r12 == 0) goto L_0x005f;
            L_0x0057:
                r12 = r15.a;	 Catch:{ ConnectTimeoutException -> 0x00bb, SocketTimeoutException -> 0x00cb, JSONException -> 0x00d6, NetworkUnavailableException -> 0x00dd, HttpStatusException -> 0x00f2, IOException -> 0x0113, Exception -> 0x011e }
                r12 = android.text.TextUtils.isEmpty(r12);	 Catch:{ ConnectTimeoutException -> 0x00bb, SocketTimeoutException -> 0x00cb, JSONException -> 0x00d6, NetworkUnavailableException -> 0x00dd, HttpStatusException -> 0x00f2, IOException -> 0x0113, Exception -> 0x011e }
                if (r12 != 0) goto L_0x0061;
            L_0x005f:
                r3 = 1;
                r10 = r11;
            L_0x0061:
                r12 = r15.b;	 Catch:{ ConnectTimeoutException -> 0x00bb, SocketTimeoutException -> 0x00cb, JSONException -> 0x00d6, NetworkUnavailableException -> 0x00dd, HttpStatusException -> 0x00f2, IOException -> 0x0113, Exception -> 0x011e }
                r4 = r15.c;	 Catch:{ ConnectTimeoutException -> 0x00bb, SocketTimeoutException -> 0x00cb, JSONException -> 0x00d6, NetworkUnavailableException -> 0x00dd, HttpStatusException -> 0x013b, IOException -> 0x0113, Exception -> 0x011e }
                r6 = r12;
            L_0x0066:
                if (r10 < r11) goto L_0x0038;
            L_0x0068:
                r10 = r2;
                r13 = r3;
                r16 = r8;
                r8 = r4;
                r4 = r16;
            L_0x006f:
                r0 = r18;
                r2 = r0.a;	 Catch:{ Exception -> 0x00ac }
                r3 = "mapp_apptrace_sdk";
                r11 = 0;
                r12 = 0;
                r2.a(r3, r4, r6, r8, r10, r11, r12);	 Catch:{ Exception -> 0x00ac }
                if (r13 == 0) goto L_0x0127;
            L_0x007d:
                r2 = com.tencent.open.b.f.a();	 Catch:{ Exception -> 0x00ac }
                r3 = "report_via";
                r2.b(r3);	 Catch:{ Exception -> 0x00ac }
            L_0x0087:
                r0 = r18;
                r2 = r0.a;	 Catch:{ Exception -> 0x00ac }
                r2 = r2.d;	 Catch:{ Exception -> 0x00ac }
                r2.clear();	 Catch:{ Exception -> 0x00ac }
                r2 = "openSDK_LOG.ReportManager";
                r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ac }
                r3.<init>();	 Catch:{ Exception -> 0x00ac }
                r4 = "-->doReportVia, uploadSuccess: ";
                r3 = r3.append(r4);	 Catch:{ Exception -> 0x00ac }
                r3 = r3.append(r13);	 Catch:{ Exception -> 0x00ac }
                r3 = r3.toString();	 Catch:{ Exception -> 0x00ac }
                com.tencent.open.a.f.b(r2, r3);	 Catch:{ Exception -> 0x00ac }
                goto L_0x000a;
            L_0x00ac:
                r2 = move-exception;
                r3 = "openSDK_LOG.ReportManager";
                r4 = "-->doReportVia, exception in serial executor.";
                com.tencent.open.a.f.b(r3, r4, r2);
                goto L_0x000a;
            L_0x00b8:
                r12 = move-exception;
                r12 = -4;
                goto L_0x0055;
            L_0x00bb:
                r2 = move-exception;
                r2 = r10;
                r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x00ac }
                r12 = 0;
                r6 = 0;
                r4 = -7;
                r10 = r2;
                r2 = r4;
                r4 = r6;
                r6 = r12;
                goto L_0x0066;
            L_0x00cb:
                r2 = move-exception;
                r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x00ac }
                r6 = 0;
                r4 = 0;
                r2 = -8;
                goto L_0x0066;
            L_0x00d6:
                r2 = move-exception;
                r6 = 0;
                r4 = 0;
                r2 = -4;
                goto L_0x0066;
            L_0x00dd:
                r2 = move-exception;
                r0 = r18;
                r2 = r0.a;	 Catch:{ Exception -> 0x00ac }
                r2 = r2.d;	 Catch:{ Exception -> 0x00ac }
                r2.clear();	 Catch:{ Exception -> 0x00ac }
                r2 = "openSDK_LOG.ReportManager";
                r3 = "doReportVia, NetworkUnavailableException.";
                com.tencent.open.a.f.b(r2, r3);	 Catch:{ Exception -> 0x00ac }
                goto L_0x000a;
            L_0x00f2:
                r10 = move-exception;
                r16 = r10;
                r10 = r3;
                r3 = r16;
            L_0x00f8:
                r3 = r3.getMessage();	 Catch:{ Exception -> 0x0139 }
                r11 = "http status code error:";
                r12 = "";
                r3 = r3.replace(r11, r12);	 Catch:{ Exception -> 0x0139 }
                r2 = java.lang.Integer.parseInt(r3);	 Catch:{ Exception -> 0x0139 }
            L_0x010a:
                r13 = r10;
                r10 = r2;
                r16 = r8;
                r8 = r4;
                r4 = r16;
                goto L_0x006f;
            L_0x0113:
                r2 = move-exception;
                r6 = 0;
                r4 = 0;
                r2 = com.tencent.open.utils.HttpUtils.getErrorCodeFromException(r2);	 Catch:{ Exception -> 0x00ac }
                goto L_0x0066;
            L_0x011e:
                r2 = move-exception;
                r6 = 0;
                r4 = 0;
                r2 = -6;
                r10 = r11;
                goto L_0x0066;
            L_0x0127:
                r2 = com.tencent.open.b.f.a();	 Catch:{ Exception -> 0x00ac }
                r3 = "report_via";
                r0 = r18;
                r4 = r0.a;	 Catch:{ Exception -> 0x00ac }
                r4 = r4.d;	 Catch:{ Exception -> 0x00ac }
                r2.a(r3, r4);	 Catch:{ Exception -> 0x00ac }
                goto L_0x0087;
            L_0x0139:
                r3 = move-exception;
                goto L_0x010a;
            L_0x013b:
                r6 = move-exception;
                r10 = r3;
                r3 = r6;
                r6 = r12;
                goto L_0x00f8;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.g.5.run():void");
            }
        });
    }

    public void a(String str, String str2, Bundle bundle, boolean z) {
        final Bundle bundle2 = bundle;
        final String str3 = str;
        final boolean z2 = z;
        final String str4 = str2;
        h.a(new Runnable(this) {
            final /* synthetic */ g e;

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r10 = this;
                r2 = 1;
                r0 = 0;
                r1 = r2;	 Catch:{ Exception -> 0x00c4 }
                if (r1 != 0) goto L_0x0010;
            L_0x0006:
                r0 = "openSDK_LOG.ReportManager";
                r1 = "-->httpRequest, params is null!";
                com.tencent.open.a.f.e(r0, r1);	 Catch:{ Exception -> 0x00c4 }
            L_0x000f:
                return;
            L_0x0010:
                r1 = com.tencent.open.b.e.a();	 Catch:{ Exception -> 0x00c4 }
                if (r1 != 0) goto L_0x00d0;
            L_0x0016:
                r1 = 3;
                r4 = r1;
            L_0x0018:
                r1 = "openSDK_LOG.ReportManager";
                r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00c4 }
                r3.<init>();	 Catch:{ Exception -> 0x00c4 }
                r5 = "-->httpRequest, retryCount: ";
                r3 = r3.append(r5);	 Catch:{ Exception -> 0x00c4 }
                r3 = r3.append(r4);	 Catch:{ Exception -> 0x00c4 }
                r3 = r3.toString();	 Catch:{ Exception -> 0x00c4 }
                com.tencent.open.a.f.b(r1, r3);	 Catch:{ Exception -> 0x00c4 }
                r1 = com.tencent.open.utils.d.a();	 Catch:{ Exception -> 0x00c4 }
                r3 = 0;
                r5 = r3;	 Catch:{ Exception -> 0x00c4 }
                r5 = com.tencent.open.utils.HttpUtils.getHttpClient(r1, r3, r5);	 Catch:{ Exception -> 0x00c4 }
                r1 = r2;	 Catch:{ Exception -> 0x00c4 }
                r1 = com.tencent.open.utils.HttpUtils.encodeUrl(r1);	 Catch:{ Exception -> 0x00c4 }
                r3 = r4;	 Catch:{ Exception -> 0x00c4 }
                if (r3 == 0) goto L_0x0145;
            L_0x0047:
                r1 = java.net.URLEncoder.encode(r1);	 Catch:{ Exception -> 0x00c4 }
                r3 = r1;
            L_0x004c:
                r1 = r5;	 Catch:{ Exception -> 0x00c4 }
                r1 = r1.toUpperCase();	 Catch:{ Exception -> 0x00c4 }
                r6 = "GET";
                r1 = r1.equals(r6);	 Catch:{ Exception -> 0x00c4 }
                if (r1 == 0) goto L_0x00d3;
            L_0x005b:
                r6 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x00c4 }
                r1 = r3;	 Catch:{ Exception -> 0x00c4 }
                r6.<init>(r1);	 Catch:{ Exception -> 0x00c4 }
                r6.append(r3);	 Catch:{ Exception -> 0x00c4 }
                r1 = new org.apache.http.client.methods.HttpGet;	 Catch:{ Exception -> 0x00c4 }
                r3 = r6.toString();	 Catch:{ Exception -> 0x00c4 }
                r1.<init>(r3);	 Catch:{ Exception -> 0x00c4 }
                r3 = r1;
            L_0x006f:
                r1 = "Accept-Encoding";
                r6 = "gzip";
                r3.addHeader(r1, r6);	 Catch:{ Exception -> 0x00c4 }
                r1 = "Content-Type";
                r6 = "application/x-www-form-urlencoded";
                r3.addHeader(r1, r6);	 Catch:{ Exception -> 0x00c4 }
                r1 = r0;
            L_0x0082:
                r1 = r1 + 1;
                r6 = r5.execute(r3);	 Catch:{ ConnectTimeoutException -> 0x010e, SocketTimeoutException -> 0x011b, Exception -> 0x0126 }
                r6 = r6.getStatusLine();	 Catch:{ ConnectTimeoutException -> 0x010e, SocketTimeoutException -> 0x011b, Exception -> 0x0126 }
                r6 = r6.getStatusCode();	 Catch:{ ConnectTimeoutException -> 0x010e, SocketTimeoutException -> 0x011b, Exception -> 0x0126 }
                r7 = "openSDK_LOG.ReportManager";
                r8 = new java.lang.StringBuilder;	 Catch:{ ConnectTimeoutException -> 0x010e, SocketTimeoutException -> 0x011b, Exception -> 0x0126 }
                r8.<init>();	 Catch:{ ConnectTimeoutException -> 0x010e, SocketTimeoutException -> 0x011b, Exception -> 0x0126 }
                r9 = "-->httpRequest, statusCode: ";
                r8 = r8.append(r9);	 Catch:{ ConnectTimeoutException -> 0x010e, SocketTimeoutException -> 0x011b, Exception -> 0x0126 }
                r8 = r8.append(r6);	 Catch:{ ConnectTimeoutException -> 0x010e, SocketTimeoutException -> 0x011b, Exception -> 0x0126 }
                r8 = r8.toString();	 Catch:{ ConnectTimeoutException -> 0x010e, SocketTimeoutException -> 0x011b, Exception -> 0x0126 }
                com.tencent.open.a.f.b(r7, r8);	 Catch:{ ConnectTimeoutException -> 0x010e, SocketTimeoutException -> 0x011b, Exception -> 0x0126 }
                r7 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                if (r6 == r7) goto L_0x0103;
            L_0x00ae:
                r6 = "openSDK_LOG.ReportManager";
                r7 = "-->ReportCenter httpRequest : HttpStatuscode != 200";
                com.tencent.open.a.f.b(r6, r7);	 Catch:{ ConnectTimeoutException -> 0x010e, SocketTimeoutException -> 0x011b, Exception -> 0x0126 }
            L_0x00b7:
                if (r0 != r2) goto L_0x0131;
            L_0x00b9:
                r0 = "openSDK_LOG.ReportManager";
                r1 = "-->ReportCenter httpRequest Thread request success";
                com.tencent.open.a.f.b(r0, r1);	 Catch:{ Exception -> 0x00c4 }
                goto L_0x000f;
            L_0x00c4:
                r0 = move-exception;
                r0 = "openSDK_LOG.ReportManager";
                r1 = "-->httpRequest, exception in serial executor.";
                com.tencent.open.a.f.b(r0, r1);
                goto L_0x000f;
            L_0x00d0:
                r4 = r1;
                goto L_0x0018;
            L_0x00d3:
                r1 = r5;	 Catch:{ Exception -> 0x00c4 }
                r1 = r1.toUpperCase();	 Catch:{ Exception -> 0x00c4 }
                r6 = "POST";
                r1 = r1.equals(r6);	 Catch:{ Exception -> 0x00c4 }
                if (r1 == 0) goto L_0x00f8;
            L_0x00e2:
                r1 = new org.apache.http.client.methods.HttpPost;	 Catch:{ Exception -> 0x00c4 }
                r6 = r3;	 Catch:{ Exception -> 0x00c4 }
                r1.<init>(r6);	 Catch:{ Exception -> 0x00c4 }
                r3 = com.tencent.open.utils.i.i(r3);	 Catch:{ Exception -> 0x00c4 }
                r6 = new org.apache.http.entity.ByteArrayEntity;	 Catch:{ Exception -> 0x00c4 }
                r6.<init>(r3);	 Catch:{ Exception -> 0x00c4 }
                r1.setEntity(r6);	 Catch:{ Exception -> 0x00c4 }
                r3 = r1;
                goto L_0x006f;
            L_0x00f8:
                r0 = "openSDK_LOG.ReportManager";
                r1 = "-->httpRequest unkonw request method return.";
                com.tencent.open.a.f.e(r0, r1);	 Catch:{ Exception -> 0x00c4 }
                goto L_0x000f;
            L_0x0103:
                r0 = "openSDK_LOG.ReportManager";
                r6 = "-->ReportCenter httpRequest Thread success";
                com.tencent.open.a.f.b(r0, r6);	 Catch:{ ConnectTimeoutException -> 0x0142, SocketTimeoutException -> 0x013f, Exception -> 0x013c }
                r0 = r2;
                goto L_0x00b7;
            L_0x010e:
                r6 = move-exception;
            L_0x010f:
                r6 = "openSDK_LOG.ReportManager";
                r7 = "-->ReportCenter httpRequest ConnectTimeoutException";
                com.tencent.open.a.f.b(r6, r7);	 Catch:{ Exception -> 0x00c4 }
            L_0x0118:
                if (r1 < r4) goto L_0x0082;
            L_0x011a:
                goto L_0x00b7;
            L_0x011b:
                r6 = move-exception;
            L_0x011c:
                r6 = "openSDK_LOG.ReportManager";
                r7 = "-->ReportCenter httpRequest SocketTimeoutException";
                com.tencent.open.a.f.b(r6, r7);	 Catch:{ Exception -> 0x00c4 }
                goto L_0x0118;
            L_0x0126:
                r1 = move-exception;
            L_0x0127:
                r1 = "openSDK_LOG.ReportManager";
                r3 = "-->ReportCenter httpRequest Exception";
                com.tencent.open.a.f.b(r1, r3);	 Catch:{ Exception -> 0x00c4 }
                goto L_0x00b7;
            L_0x0131:
                r0 = "openSDK_LOG.ReportManager";
                r1 = "-->ReportCenter httpRequest Thread request failed";
                com.tencent.open.a.f.b(r0, r1);	 Catch:{ Exception -> 0x00c4 }
                goto L_0x000f;
            L_0x013c:
                r0 = move-exception;
                r0 = r2;
                goto L_0x0127;
            L_0x013f:
                r0 = move-exception;
                r0 = r2;
                goto L_0x011c;
            L_0x0142:
                r0 = move-exception;
                r0 = r2;
                goto L_0x010f;
            L_0x0145:
                r3 = r1;
                goto L_0x004c;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.g.6.run():void");
            }
        });
    }
}
