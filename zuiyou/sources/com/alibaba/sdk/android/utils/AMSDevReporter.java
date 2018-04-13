package com.alibaba.sdk.android.utils;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import com.meizu.cloud.pushsdk.networking.common.ANConstants;
import com.ut.device.UTDevice;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class AMSDevReporter {
    private static Context a;
    /* renamed from: a */
    private static c f10a = new c("AMSDevReporter");
    /* renamed from: a */
    private static ConcurrentHashMap<AMSSdkTypeEnum, AMSReportStatusEnum> f11a = new ConcurrentHashMap();
    /* renamed from: a */
    private static final ExecutorService f12a = Executors.newSingleThreadExecutor(new a());
    /* renamed from: a */
    private static boolean f13a = false;

    public enum AMSReportStatusEnum {
        UNREPORTED,
        REPORTED
    }

    public enum AMSSdkExtInfoKeyEnum {
        AMS_EXTINFO_KEY_VERSION("SdkVersion"),
        AMS_EXTINFO_KEY_PACKAGE("PackageName");
        
        private String description;

        private AMSSdkExtInfoKeyEnum(String str) {
            this.description = str;
        }

        public String toString() {
            return this.description;
        }
    }

    public enum AMSSdkTypeEnum {
        AMS_MAN("MAN"),
        AMS_HTTPDNS("HTTPDNS"),
        AMS_MPUSH("MPUSH"),
        AMS_MAC("MAC"),
        AMS_API("API"),
        AMS_HOTFIX("HOTFIX"),
        AMS_FEEDBACK("FEEDBACK"),
        AMS_IM("IM");
        
        private String description;

        private AMSSdkTypeEnum(String str) {
            this.description = str;
        }

        public String toString() {
            return this.description;
        }
    }

    static {
        int i = 0;
        AMSSdkTypeEnum[] values = AMSSdkTypeEnum.values();
        int length = values.length;
        while (i < length) {
            f11a.put(values[i], AMSReportStatusEnum.UNREPORTED);
            i++;
        }
    }

    public static void setLogEnabled(boolean z) {
        f10a.setLogEnabled(z);
    }

    public static AMSReportStatusEnum getReportStatus(AMSSdkTypeEnum aMSSdkTypeEnum) {
        return (AMSReportStatusEnum) f11a.get(aMSSdkTypeEnum);
    }

    public static void asyncReport(Context context, AMSSdkTypeEnum aMSSdkTypeEnum) {
        asyncReport(context, aMSSdkTypeEnum, null);
    }

    public static void asyncReport(Context context, final AMSSdkTypeEnum aMSSdkTypeEnum, final Map<String, Object> map) {
        if (context == null) {
            f10a.c("Context is null, return.");
            return;
        }
        a = context;
        f10a.b("Add [" + aMSSdkTypeEnum.toString() + "] to report queue.");
        f13a = false;
        f12a.execute(new Runnable() {
            public void run() {
                if (AMSDevReporter.f10a) {
                    AMSDevReporter.f10a.c("Unable to execute remain task in queue, return.");
                    return;
                }
                AMSDevReporter.f10a.b("Get [" + aMSSdkTypeEnum.toString() + "] from report queue.");
                AMSDevReporter.a(aMSSdkTypeEnum, map);
            }
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(com.alibaba.sdk.android.utils.AMSDevReporter.AMSSdkTypeEnum r7, java.util.Map<java.lang.String, java.lang.Object> r8) {
        /*
        r1 = 60;
        r2 = 0;
        r0 = 5;
        r3 = r7.toString();
        r4 = f11a;
        r4 = r4.get(r7);
        r5 = com.alibaba.sdk.android.utils.AMSDevReporter.AMSReportStatusEnum.UNREPORTED;
        if (r4 == r5) goto L_0x0033;
    L_0x0012:
        r0 = f10a;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "[";
        r1 = r1.append(r2);
        r1 = r1.append(r3);
        r2 = "] already reported, return.";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.b(r1);
    L_0x0032:
        return;
    L_0x0033:
        r4 = f10a;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Report [";
        r5 = r5.append(r6);
        r5 = r5.append(r3);
        r6 = "], times: [";
        r5 = r5.append(r6);
        r6 = r2 + 1;
        r5 = r5.append(r6);
        r6 = "].";
        r5 = r5.append(r6);
        r5 = r5.toString();
        r4.b(r5);
        r4 = a(r7, r8);
        if (r4 != 0) goto L_0x00f1;
    L_0x0066:
        r2 = r2 + 1;
        r4 = 10;
        if (r2 > r4) goto L_0x00a1;
    L_0x006c:
        r4 = f10a;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Report [";
        r5 = r5.append(r6);
        r5 = r5.append(r3);
        r6 = "] failed, wait for [";
        r5 = r5.append(r6);
        r5 = r5.append(r0);
        r6 = "] seconds.";
        r5 = r5.append(r6);
        r5 = r5.toString();
        r4.b(r5);
        r4 = (double) r0;
        com.alibaba.sdk.android.utils.d.a(r4);
        r0 = r0 * 2;
        if (r0 < r1) goto L_0x0033;
    L_0x009f:
        r0 = r1;
        goto L_0x0033;
    L_0x00a1:
        r0 = f10a;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Report [";
        r1 = r1.append(r2);
        r1 = r1.append(r3);
        r2 = "] stat failed, exceed max retry times, return.";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.c(r1);
        r0 = f11a;
        r1 = com.alibaba.sdk.android.utils.AMSDevReporter.AMSReportStatusEnum.UNREPORTED;
        r0.put(r7, r1);
        r0 = 1;
        f13a = r0;
    L_0x00cb:
        r0 = f13a;
        if (r0 == 0) goto L_0x0032;
    L_0x00cf:
        r0 = f10a;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Report [";
        r1 = r1.append(r2);
        r1 = r1.append(r3);
        r2 = "] failed, clear remain report in queue.";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.c(r1);
        goto L_0x0032;
    L_0x00f1:
        r0 = f10a;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Report [";
        r1 = r1.append(r2);
        r1 = r1.append(r3);
        r2 = "] stat success.";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.b(r1);
        r0 = f11a;
        r1 = com.alibaba.sdk.android.utils.AMSDevReporter.AMSReportStatusEnum.REPORTED;
        r0.put(r7, r1);
        goto L_0x00cb;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.utils.AMSDevReporter.a(com.alibaba.sdk.android.utils.AMSDevReporter$AMSSdkTypeEnum, java.util.Map):void");
    }

    /* renamed from: a */
    private static boolean m9a(AMSSdkTypeEnum aMSSdkTypeEnum, Map<String, Object> map) {
        OutputStream outputStream;
        HttpURLConnection httpURLConnection;
        Throwable th;
        OutputStream outputStream2;
        DataInputStream dataInputStream;
        DataInputStream dataInputStream2;
        OutputStream outputStream3 = null;
        try {
            if (VERSION.SDK_INT >= 14) {
                TrafficStats.setThreadStatsTag(40965);
            }
            String utdid = UTDevice.getUtdid(a);
            f10a.b("stat: " + utdid);
            String a = a(aMSSdkTypeEnum, utdid, map);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL("http://adash.man.aliyuncs.com:80/man/api?ak=23356390&s=" + d.b("16594f72217bece5a457b4803a48f2da" + d.a("23356390Raw" + d.a(a)) + "16594f72217bece5a457b4803a48f2da")).openConnection();
            try {
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setUseCaches(false);
                httpURLConnection2.setConnectTimeout(15000);
                String str = "===" + System.currentTimeMillis() + "===";
                httpURLConnection2.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + str);
                a = "--" + str + "\r\nContent-Disposition: form-data; name=\"" + "Raw" + "\"\r\nContent-Type: text/plain; charset=UTF-8\r\n\r\n" + a + "\r\n--" + str + "--\r\n";
                outputStream = httpURLConnection2.getOutputStream();
            } catch (Throwable e) {
                httpURLConnection = httpURLConnection2;
                th = e;
                outputStream2 = null;
                try {
                    f10a.a(th);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (Throwable th2) {
                            f10a.a(th2);
                        }
                    }
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                    return false;
                } catch (Throwable th3) {
                    th2 = th3;
                    dataInputStream2 = dataInputStream;
                    outputStream3 = outputStream2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (outputStream3 != null) {
                        try {
                            outputStream3.close();
                        } catch (Throwable e2) {
                            f10a.a(e2);
                            throw th2;
                        }
                    }
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                    throw th2;
                }
            } catch (Throwable e22) {
                dataInputStream2 = null;
                httpURLConnection = httpURLConnection2;
                th2 = e22;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (outputStream3 != null) {
                    outputStream3.close();
                }
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                throw th2;
            }
            try {
                outputStream.write(a.getBytes());
                int responseCode = httpURLConnection2.getResponseCode();
                if (responseCode == 200) {
                    dataInputStream2 = new DataInputStream(httpURLConnection2.getInputStream());
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = dataInputStream2.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            stringBuilder.append(new String(bArr, 0, read));
                        }
                        f10a.a("Get MAN response: " + stringBuilder.toString());
                        try {
                            if (((String) new JSONObject(stringBuilder.toString()).get(ANConstants.SUCCESS)).equals(ANConstants.SUCCESS)) {
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                                if (outputStream != null) {
                                    try {
                                        outputStream.close();
                                    } catch (Throwable th22) {
                                        f10a.a(th22);
                                    }
                                }
                                if (dataInputStream2 != null) {
                                    dataInputStream2.close();
                                }
                                return true;
                            }
                        } catch (Throwable e222) {
                            f10a.a(e222);
                        }
                    } catch (Throwable e2222) {
                        dataInputStream = dataInputStream2;
                        httpURLConnection = httpURLConnection2;
                        th22 = e2222;
                        outputStream2 = outputStream;
                        f10a.a(th22);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        if (outputStream2 != null) {
                            outputStream2.close();
                        }
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                        return false;
                    } catch (Throwable e22222) {
                        outputStream3 = outputStream;
                        httpURLConnection = httpURLConnection2;
                        th22 = e22222;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        if (outputStream3 != null) {
                            outputStream3.close();
                        }
                        if (dataInputStream2 != null) {
                            dataInputStream2.close();
                        }
                        throw th22;
                    }
                }
                f10a.c("MAN API error, response code: " + responseCode);
                dataInputStream2 = null;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th222) {
                        f10a.a(th222);
                    }
                }
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
            } catch (Throwable e222222) {
                httpURLConnection = httpURLConnection2;
                th222 = e222222;
                outputStream2 = outputStream;
                f10a.a(th222);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (outputStream2 != null) {
                    outputStream2.close();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                return false;
            } catch (Throwable e2222222) {
                dataInputStream2 = null;
                httpURLConnection = httpURLConnection2;
                th222 = e2222222;
                outputStream3 = outputStream;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (outputStream3 != null) {
                    outputStream3.close();
                }
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                throw th222;
            }
        } catch (Exception e3) {
            th222 = e3;
            outputStream2 = null;
            httpURLConnection = null;
            f10a.a(th222);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (outputStream2 != null) {
                outputStream2.close();
            }
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            return false;
        } catch (Throwable th4) {
            th222 = th4;
            dataInputStream2 = null;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (outputStream3 != null) {
                outputStream3.close();
            }
            if (dataInputStream2 != null) {
                dataInputStream2.close();
            }
            throw th222;
        }
        return false;
    }

    private static String a(AMSSdkTypeEnum aMSSdkTypeEnum, String str, Map<String, Object> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(aMSSdkTypeEnum).append("-").append(str);
        if (map != null) {
            String str2 = (String) map.get(AMSSdkExtInfoKeyEnum.AMS_EXTINFO_KEY_VERSION.toString());
            if (!d.a(str2)) {
                stringBuilder.append("-").append(str2);
            }
            str2 = (String) map.get(AMSSdkExtInfoKeyEnum.AMS_EXTINFO_KEY_PACKAGE.toString());
            if (!d.a(str2)) {
                stringBuilder.append("-").append(str2);
            }
        }
        return stringBuilder.toString();
    }
}
