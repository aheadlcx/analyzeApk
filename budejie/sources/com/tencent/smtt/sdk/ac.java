package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.sdk.TbsLogReport.EventType;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.aa;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.k;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

class ac {
    private static int d = 5;
    private static int e = 1;
    private static final String[] f = new String[]{"tbs_downloading_com.tencent.mtt", "tbs_downloading_com.tencent.mm", "tbs_downloading_com.tencent.mobileqq", "tbs_downloading_com.tencent.tbs", "tbs_downloading_com.qzone"};
    private Set<String> A;
    private int B = d;
    private boolean C;
    String a;
    String[] b = null;
    int c = 0;
    private Context g;
    private String h;
    private String i;
    private String j;
    private File k;
    private long l;
    private int m = 30000;
    private int n = 20000;
    private boolean o;
    private int p;
    private int q;
    private boolean r;
    private boolean s;
    private HttpURLConnection t;
    private String u;
    private TbsLogReport v;
    private String w;
    private int x;
    private boolean y;
    private Handler z;

    public ac(Context context) {
        this.g = context.getApplicationContext();
        this.v = TbsLogReport.a(this.g);
        this.A = new HashSet();
        this.u = "tbs_downloading_" + this.g.getPackageName();
        aj.a();
        this.k = aj.j(this.g);
        if (this.k == null) {
            throw new NullPointerException("TbsCorePrivateDir is null!");
        }
        e();
        this.w = null;
        this.x = -1;
    }

    private long a(long j, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        this.v.c(currentTimeMillis - j);
        this.v.d(j2);
        return currentTimeMillis;
    }

    @TargetApi(8)
    static File a(Context context) {
        try {
            File file = VERSION.SDK_INT >= 8 ? new File(k.a(context, 4)) : null;
            if (file == null || file.exists() || file.isDirectory()) {
                return file;
            }
            file.mkdirs();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e.getMessage());
            return null;
        }
    }

    private static File a(Context context, int i) {
        File file = new File(k.a(context, i));
        if (file == null || !file.exists() || !file.isDirectory()) {
            return null;
        }
        File file2 = new File(file, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        return (file2 == null || !file2.exists()) ? null : file;
    }

    private String a(Throwable th) {
        String stackTraceString = Log.getStackTraceString(th);
        return stackTraceString.length() > 1024 ? stackTraceString.substring(0, 1024) : stackTraceString;
    }

    private String a(URL url) {
        String str = "";
        try {
            str = InetAddress.getByName(url.getHost()).getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error e2) {
            e2.printStackTrace();
        }
        return str;
    }

    private void a(int i, String str, boolean z) {
        if (z || this.p > this.B) {
            this.v.h(i);
            this.v.e(str);
        }
    }

    private void a(long j) {
        this.p++;
        if (j <= 0) {
            try {
                j = m();
            } catch (Exception e) {
                return;
            }
        }
        Thread.sleep(j);
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static void a(File file, Context context) {
        if (file != null && file.exists()) {
            try {
                File a = a(context);
                if (a != null) {
                    File file2 = new File(a, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                    file2.delete();
                    k.b(file, file2);
                }
            } catch (Exception e) {
            }
        }
    }

    private void a(String str) {
        URL url = new URL(str);
        if (this.t != null) {
            try {
                this.t.disconnect();
            } catch (Throwable th) {
                TbsLog.e(TbsDownloader.LOGTAG, "[initHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
        }
        this.t = (HttpURLConnection) url.openConnection();
        this.t.setRequestProperty("User-Agent", TbsDownloader.a(this.g));
        this.t.setRequestProperty("Accept-Encoding", "identity");
        this.t.setRequestMethod("GET");
        this.t.setInstanceFollowRedirects(false);
        this.t.setConnectTimeout(this.n);
        this.t.setReadTimeout(this.m);
    }

    @TargetApi(8)
    static File b(Context context) {
        try {
            if (VERSION.SDK_INT < 8) {
                return null;
            }
            File a = a(context, 4);
            if (a == null) {
                a = a(context, 3);
            }
            if (a == null) {
                a = a(context, 2);
            }
            return a == null ? a(context, 1) : a;
        } catch (Exception e) {
            e.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e.getMessage());
            return null;
        }
    }

    private boolean b(boolean z, boolean z2) {
        long j = 0;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z);
        File file = new File(this.k, !z ? "x5.tbs" : "x5.tbs.temp");
        if (!file.exists()) {
            return false;
        }
        String string = TbsDownloadConfig.getInstance(this.g).mPreferences.getString(TbsConfigKey.KEY_TBSAPK_MD5, null);
        String a = a.a(file);
        if (string == null || !string.equals(a)) {
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z + " md5 failed");
            if (!z) {
                return false;
            }
            this.v.d("fileMd5 not match");
            return false;
        }
        boolean renameTo;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] md5(" + a + ") successful!");
        if (z) {
            long j2;
            long j3 = TbsDownloadConfig.getInstance(this.g).mPreferences.getLong(TbsConfigKey.KEY_TBSAPKFILESIZE, 0);
            if (file == null || !file.exists()) {
                j2 = 0;
            } else if (j3 > 0) {
                j2 = file.length();
                if (j3 == j2) {
                    j = j2;
                }
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z + " filelength failed");
            this.v.d("fileLength:" + j2 + ",contentLength:" + j3);
            return false;
        }
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] length(" + j + ") successful!");
        int i = -1;
        if (z2) {
            i = a.a(this.g, file);
            int i2 = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
            if (i2 != i) {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z + " versionCode failed");
                if (!z) {
                    return false;
                }
                this.v.d("fileVersion:" + i + ",configVersion:" + i2);
                return false;
            }
        }
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] tbsApkVersionCode(" + i + ") successful!");
        if (z2) {
            string = b.a(this.g, file);
            if (!"3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(string)) {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] isTempFile=" + z + " signature failed");
                if (!z) {
                    return false;
                }
                this.v.d("signature:" + (string == null ? "null" : Integer.valueOf(string.length())));
                return false;
            }
        }
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] signature successful!");
        if (z) {
            Throwable th;
            try {
                renameTo = file.renameTo(new File(this.k, "x5.tbs"));
                th = null;
            } catch (Throwable e) {
                th = e;
                renameTo = false;
            }
            if (!renameTo) {
                a(109, a(th), true);
                return false;
            }
        }
        renameTo = false;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.verifyTbsApk] rename(" + renameTo + ") successful!");
        return true;
    }

    public static void c(Context context) {
        try {
            aj.a();
            File j = aj.j(context);
            new File(j, "x5.tbs").delete();
            new File(j, "x5.tbs.temp").delete();
            j = a(context);
            if (j != null) {
                new File(j, "x5.tbs.org").delete();
                new File(j, "x5.oversea.tbs.org").delete();
            }
        } catch (Exception e) {
        }
    }

    private void c(boolean z) {
        aa.a(this.g);
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(this.g);
        instance.a.put(TbsConfigKey.KEY_FULL_PACKAGE, Boolean.valueOf(false));
        instance.a.put(TbsConfigKey.KEY_NEEDDOWNLOAD, Boolean.valueOf(false));
        instance.a.put(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, Integer.valueOf(-123));
        instance.commit();
        QbSdk.j.onDownloadFinish(z ? 100 : 120);
        int i = instance.mPreferences.getInt(TbsConfigKey.KEY_RESPONSECODE, 0);
        if (i == 3 || i > 10000) {
            File a = a(this.g);
            if (a != null) {
                File file = new File(a, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                int a2 = a.a(this.g, file);
                File file2 = new File(this.k, "x5.tbs");
                String absolutePath = file2.exists() ? file2.getAbsolutePath() : null;
                int i2 = instance.mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
                Bundle bundle = new Bundle();
                bundle.putInt("operation", i);
                bundle.putInt("old_core_ver", a2);
                bundle.putInt("new_core_ver", i2);
                bundle.putString("old_apk_location", file.getAbsolutePath());
                bundle.putString("new_apk_location", absolutePath);
                bundle.putString("diff_file_location", absolutePath);
                aj.a().b(this.g, bundle);
                return;
            }
            c();
            instance.a.put(TbsConfigKey.KEY_NEEDDOWNLOAD, Boolean.valueOf(true));
            instance.commit();
            return;
        }
        aj.a().a(this.g, new File(this.k, "x5.tbs").getAbsolutePath(), instance.mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
        a(new File(this.k, "x5.tbs"), this.g);
    }

    private boolean d(boolean z) {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.deleteFile] isApk=" + z);
        File file = z ? new File(this.k, "x5.tbs") : new File(this.k, "x5.tbs.temp");
        return (file == null || !file.exists()) ? true : file.delete();
    }

    private void e() {
        this.p = 0;
        this.q = 0;
        this.l = -1;
        this.j = null;
        this.o = false;
        this.r = false;
        this.s = false;
        this.y = false;
    }

    private void f() {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.closeHttpRequest]");
        if (this.t != null) {
            if (!this.r) {
                this.v.b(a(this.t.getURL()));
            }
            try {
                this.t.disconnect();
            } catch (Throwable th) {
                TbsLog.e(TbsDownloader.LOGTAG, "[closeHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
            this.t = null;
        }
        int i = this.v.a;
        if (this.r || !this.y) {
            TbsDownloader.a = false;
            return;
        }
        this.v.a(System.currentTimeMillis());
        String apnInfo = Apn.getApnInfo(this.g);
        if (apnInfo == null) {
            apnInfo = "";
        }
        int apnType = Apn.getApnType(this.g);
        this.v.c(apnInfo);
        this.v.e(apnType);
        if (!(apnType == this.x && apnInfo.equals(this.w))) {
            this.v.g(0);
        }
        if ((this.v.a == 0 || this.v.a == 107) && this.v.c() == 0) {
            if (!Apn.isNetworkAvailable(this.g)) {
                a(101, null, true);
            } else if (!l()) {
                a(101, null, true);
            }
        }
        this.v.a(EventType.TYPE_DOWNLOAD);
        if (i != 100) {
            QbSdk.j.onDownloadFinish(i);
        }
    }

    private boolean g() {
        try {
            File file = new File(this.k, "x5.tbs");
            File a = a(this.g);
            if (a != null) {
                File file2 = new File(a, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                file.delete();
                k.b(file2, file);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.copyTbsApkFromBackupToInstall] Exception is " + e.getMessage());
            return false;
        }
    }

    private boolean h() {
        File file = new File(k.a(this.g, 4), TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        int i = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsConfigKey.KEY_USE_BACKUP_VERSION, 0);
        if (i == 0) {
            i = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        }
        return a.a(this.g, file, 0, i);
    }

    private void i() {
        try {
            File file = new File(k.a(this.g, 4), TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            if (file != null && file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean j() {
        File file = new File(this.k, "x5.tbs.temp");
        return file != null && file.exists();
    }

    private long k() {
        File file = new File(this.k, "x5.tbs.temp");
        return (file == null || !file.exists()) ? 0 : file.length();
    }

    private boolean l() {
        Closeable inputStreamReader;
        Throwable th;
        Closeable closeable;
        Throwable th2;
        boolean z = false;
        Closeable closeable2 = null;
        Closeable inputStream;
        try {
            inputStream = Runtime.getRuntime().exec("ping " + "www.qq.com").getInputStream();
            try {
                inputStreamReader = new InputStreamReader(inputStream);
            } catch (Throwable th3) {
                th2 = th3;
                inputStreamReader = null;
                a(inputStream);
                a(inputStreamReader);
                a(closeable2);
                throw th2;
            }
            try {
                closeable = new BufferedReader(inputStreamReader);
                int i = 0;
                do {
                    try {
                        String readLine = closeable.readLine();
                        if (readLine == null) {
                            break;
                        } else if (readLine.contains("TTL") || readLine.contains("ttl")) {
                            z = true;
                            break;
                        } else {
                            i++;
                        }
                    } catch (Throwable th4) {
                        th2 = th4;
                        closeable2 = closeable;
                    }
                } while (i < 5);
                a(inputStream);
                a(inputStreamReader);
                a(closeable);
            } catch (Throwable th5) {
                th2 = th5;
                a(inputStream);
                a(inputStreamReader);
                a(closeable2);
                throw th2;
            }
        } catch (Throwable th6) {
            th2 = th6;
            inputStreamReader = null;
            inputStream = null;
            a(inputStream);
            a(inputStreamReader);
            a(closeable2);
            throw th2;
        }
        return z;
    }

    private long m() {
        switch (this.p) {
            case 1:
            case 2:
                return 20000 * ((long) this.p);
            case 3:
            case 4:
                return 20000 * 5;
            default:
                return 20000 * 10;
        }
    }

    private boolean n() {
        CharSequence charSequence;
        Object obj;
        HttpURLConnection httpURLConnection;
        Throwable th;
        HttpURLConnection httpURLConnection2 = null;
        boolean z = true;
        boolean z2 = false;
        boolean z3 = Apn.getApnType(this.g) == 3;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDwonloader.detectWifiNetworkAvailable] isWifi=" + z3);
        if (z3) {
            String wifiSSID = Apn.getWifiSSID(this.g);
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDwonloader.detectWifiNetworkAvailable] localBSSID=" + wifiSSID);
            try {
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL("http://pms.mb.qq.com/rsp204").openConnection();
                try {
                    httpURLConnection3.setInstanceFollowRedirects(false);
                    httpURLConnection3.setConnectTimeout(10000);
                    httpURLConnection3.setReadTimeout(10000);
                    httpURLConnection3.setUseCaches(false);
                    httpURLConnection3.getInputStream();
                    int responseCode = httpURLConnection3.getResponseCode();
                    TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDwonloader.detectWifiNetworkAvailable] responseCode=" + responseCode);
                    if (responseCode != 204) {
                        z = false;
                    }
                    if (httpURLConnection3 != null) {
                        try {
                            httpURLConnection3.disconnect();
                            charSequence = wifiSSID;
                            z2 = z;
                        } catch (Exception e) {
                            obj = wifiSSID;
                            z2 = z;
                        }
                    } else {
                        obj = wifiSSID;
                        z2 = z;
                    }
                } catch (Throwable th2) {
                    httpURLConnection2 = httpURLConnection3;
                    th = th2;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        }
        if (!(z2 || TextUtils.isEmpty(charSequence) || this.A.contains(charSequence))) {
            this.A.add(charSequence);
            o();
            this.z.sendMessageDelayed(this.z.obtainMessage(150, charSequence), 120000);
        }
        if (z2 && this.A.contains(charSequence)) {
            this.A.remove(charSequence);
        }
        return z2;
    }

    private void o() {
        if (this.z == null) {
            this.z = new ad(this, ah.a().getLooper());
        }
    }

    public int a() {
        File a = a(this.g);
        if (a == null) {
            return 0;
        }
        return a.a(this.g, new File(a, TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org"));
    }

    public void a(int i) {
        try {
            File file = new File(this.k, "x5.tbs");
            int a = a.a(this.g, file);
            if (-1 == a || (i > 0 && i == a)) {
                file.delete();
            }
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r33) {
        /*
        r32 = this;
        r4 = com.tencent.smtt.sdk.aj.a();
        r0 = r32;
        r5 = r0.g;
        r4 = r4.b(r5);
        if (r4 == 0) goto L_0x0021;
    L_0x000e:
        if (r33 != 0) goto L_0x0021;
    L_0x0010:
        r4 = 0;
        com.tencent.smtt.sdk.TbsDownloader.a = r4;
        r0 = r32;
        r4 = r0.g;
        r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4);
        r5 = -322; // 0xfffffffffffffebe float:NaN double:NaN;
        r4.setDownloadInterruptCode(r5);
    L_0x0020:
        return;
    L_0x0021:
        r0 = r32;
        r4 = r0.g;
        r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4);
        r4 = r4.mPreferences;
        r5 = "tbs_responsecode";
        r6 = 0;
        r4 = r4.getInt(r5, r6);
        r5 = 1;
        if (r4 == r5) goto L_0x003b;
    L_0x0035:
        r5 = 2;
        if (r4 == r5) goto L_0x003b;
    L_0x0038:
        r5 = 4;
        if (r4 != r5) goto L_0x004a;
    L_0x003b:
        r4 = 1;
    L_0x003c:
        r0 = r32;
        r1 = r33;
        r5 = r0.a(r1, r4);
        if (r5 == 0) goto L_0x004c;
    L_0x0046:
        r4 = 0;
        com.tencent.smtt.sdk.TbsDownloader.a = r4;
        goto L_0x0020;
    L_0x004a:
        r4 = 0;
        goto L_0x003c;
    L_0x004c:
        r0 = r33;
        r1 = r32;
        r1.C = r0;
        r0 = r32;
        r5 = r0.g;
        r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5);
        r5 = r5.mPreferences;
        r6 = "tbs_downloadurl";
        r7 = 0;
        r5 = r5.getString(r6, r7);
        r0 = r32;
        r0.h = r5;
        r0 = r32;
        r5 = r0.g;
        r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5);
        r5 = r5.mPreferences;
        r6 = "tbs_downloadurl_list";
        r7 = 0;
        r5 = r5.getString(r6, r7);
        r6 = "TbsDownload";
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "backupUrlStrings:";
        r7 = r7.append(r8);
        r7 = r7.append(r5);
        r7 = r7.toString();
        r8 = 1;
        com.tencent.smtt.utils.TbsLog.i(r6, r7, r8);
        r6 = 0;
        r0 = r32;
        r0.b = r6;
        r6 = 0;
        r0 = r32;
        r0.c = r6;
        if (r33 != 0) goto L_0x00b9;
    L_0x009d:
        if (r5 == 0) goto L_0x00b9;
    L_0x009f:
        r6 = "";
        r7 = r5.trim();
        r6 = r6.equals(r7);
        if (r6 != 0) goto L_0x00b9;
    L_0x00ab:
        r6 = r5.trim();
        r7 = ";";
        r6 = r6.split(r7);
        r0 = r32;
        r0.b = r6;
    L_0x00b9:
        r6 = "TbsDownload";
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "[TbsApkDownloader.startDownload] mDownloadUrl=";
        r7 = r7.append(r8);
        r0 = r32;
        r8 = r0.h;
        r7 = r7.append(r8);
        r8 = " backupUrlStrings=";
        r7 = r7.append(r8);
        r5 = r7.append(r5);
        r7 = " mLocation=";
        r5 = r5.append(r7);
        r0 = r32;
        r7 = r0.j;
        r5 = r5.append(r7);
        r7 = " mCanceled=";
        r5 = r5.append(r7);
        r0 = r32;
        r7 = r0.r;
        r5 = r5.append(r7);
        r7 = " mHttpRequest=";
        r5 = r5.append(r7);
        r0 = r32;
        r7 = r0.t;
        r5 = r5.append(r7);
        r5 = r5.toString();
        com.tencent.smtt.utils.TbsLog.i(r6, r5);
        r0 = r32;
        r5 = r0.h;
        if (r5 != 0) goto L_0x012b;
    L_0x010f:
        r0 = r32;
        r5 = r0.j;
        if (r5 != 0) goto L_0x012b;
    L_0x0115:
        r4 = com.tencent.smtt.sdk.QbSdk.j;
        r5 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        r4.onDownloadFinish(r5);
        r0 = r32;
        r4 = r0.g;
        r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4);
        r5 = -302; // 0xfffffffffffffed2 float:NaN double:NaN;
        r4.setDownloadInterruptCode(r5);
        goto L_0x0020;
    L_0x012b:
        r0 = r32;
        r5 = r0.t;
        if (r5 == 0) goto L_0x014d;
    L_0x0131:
        r0 = r32;
        r5 = r0.r;
        if (r5 != 0) goto L_0x014d;
    L_0x0137:
        r4 = com.tencent.smtt.sdk.QbSdk.j;
        r5 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        r4.onDownloadFinish(r5);
        r0 = r32;
        r4 = r0.g;
        r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4);
        r5 = -303; // 0xfffffffffffffed1 float:NaN double:NaN;
        r4.setDownloadInterruptCode(r5);
        goto L_0x0020;
    L_0x014d:
        if (r33 != 0) goto L_0x017e;
    L_0x014f:
        r0 = r32;
        r5 = r0.A;
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.utils.Apn.getWifiSSID(r6);
        r5 = r5.contains(r6);
        if (r5 == 0) goto L_0x017e;
    L_0x0161:
        r4 = "TbsDownload";
        r5 = "[TbsApkDownloader.startDownload] WIFI Unavailable";
        com.tencent.smtt.utils.TbsLog.i(r4, r5);
        r4 = com.tencent.smtt.sdk.QbSdk.j;
        r5 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        r4.onDownloadFinish(r5);
        r0 = r32;
        r4 = r0.g;
        r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4);
        r5 = -304; // 0xfffffffffffffed0 float:NaN double:NaN;
        r4.setDownloadInterruptCode(r5);
        goto L_0x0020;
    L_0x017e:
        r32.e();
        r5 = "TbsDownload";
        r6 = "STEP 1/2 begin downloading...";
        r7 = 1;
        com.tencent.smtt.utils.TbsLog.i(r5, r6, r7);
        r0 = r32;
        r5 = r0.g;
        r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5);
        r24 = r5.getDownloadMaxflow();
        r5 = 0;
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6 = r6.mPreferences;
        r7 = "tbs_downloadflow";
        r8 = 0;
        r6 = r6.getLong(r7, r8);
        if (r33 == 0) goto L_0x023c;
    L_0x01aa:
        r8 = e;
        r0 = r32;
        r0.B = r8;
    L_0x01b0:
        r0 = r32;
        r8 = r0.p;
        r0 = r32;
        r9 = r0.B;
        if (r8 <= r9) goto L_0x0244;
    L_0x01ba:
        r0 = r32;
        r6 = r0.r;
        if (r6 != 0) goto L_0x0237;
    L_0x01c0:
        r0 = r32;
        r6 = r0.s;
        if (r6 == 0) goto L_0x020a;
    L_0x01c6:
        r0 = r32;
        r6 = r0.b;
        if (r6 != 0) goto L_0x01d5;
    L_0x01cc:
        if (r5 != 0) goto L_0x01d5;
    L_0x01ce:
        r5 = 1;
        r0 = r32;
        r5 = r0.b(r5, r4);
    L_0x01d5:
        r0 = r32;
        r7 = r0.v;
        if (r5 == 0) goto L_0x0eb6;
    L_0x01db:
        r6 = 1;
    L_0x01dc:
        r7.d(r6);
        if (r4 != 0) goto L_0x0ebc;
    L_0x01e1:
        r0 = r32;
        r6 = r0.v;
        if (r5 == 0) goto L_0x0eb9;
    L_0x01e7:
        r4 = 1;
    L_0x01e8:
        r6.b(r4);
    L_0x01eb:
        if (r5 == 0) goto L_0x0ec6;
    L_0x01ed:
        r4 = 1;
        r0 = r32;
        r0.c(r4);
        r0 = r32;
        r4 = r0.g;
        r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4);
        r6 = -317; // 0xfffffffffffffec3 float:NaN double:NaN;
        r4.setDownloadInterruptCode(r6);
        r4 = 100;
        r6 = "success";
        r7 = 1;
        r0 = r32;
        r0.a(r4, r6, r7);
    L_0x020a:
        r0 = r32;
        r4 = r0.g;
        r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4);
        if (r5 == 0) goto L_0x0edb;
    L_0x0214:
        r6 = r4.mPreferences;
        r7 = "tbs_download_success_retrytimes";
        r8 = 0;
        r6 = r6.getInt(r7, r8);
        r7 = r4.a;
        r8 = "tbs_download_success_retrytimes";
        r6 = r6 + 1;
        r6 = java.lang.Integer.valueOf(r6);
        r7.put(r8, r6);
    L_0x022a:
        r4.commit();
        r0 = r32;
        r6 = r0.v;
        if (r5 == 0) goto L_0x0f01;
    L_0x0233:
        r4 = 1;
    L_0x0234:
        r6.f(r4);
    L_0x0237:
        r32.f();
        goto L_0x0020;
    L_0x023c:
        r8 = d;
        r0 = r32;
        r0.B = r8;
        goto L_0x01b0;
    L_0x0244:
        r0 = r32;
        r8 = r0.q;
        r9 = 8;
        if (r8 <= r9) goto L_0x0264;
    L_0x024c:
        r6 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        r7 = 0;
        r8 = 1;
        r0 = r32;
        r0.a(r6, r7, r8);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r7 = -306; // 0xfffffffffffffece float:NaN double:NaN;
        r6.setDownloadInterruptCode(r7);
        goto L_0x01ba;
    L_0x0264:
        r20 = java.lang.System.currentTimeMillis();
        if (r33 != 0) goto L_0x0368;
    L_0x026a:
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r8 = r8.mPreferences;	 Catch:{ Throwable -> 0x0564 }
        r9 = "tbs_downloadstarttime";
        r10 = 0;
        r8 = r8.getLong(r9, r10);	 Catch:{ Throwable -> 0x0564 }
        r8 = r20 - r8;
        r10 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r8 <= 0) goto L_0x030b;
    L_0x0285:
        r8 = "TbsDownload";
        r9 = "[TbsApkDownloader.startDownload] OVER DOWNLOAD_PERIOD";
        com.tencent.smtt.utils.TbsLog.i(r8, r9);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r8 = r8.a;	 Catch:{ Throwable -> 0x0564 }
        r9 = "tbs_downloadstarttime";
        r10 = java.lang.Long.valueOf(r20);	 Catch:{ Throwable -> 0x0564 }
        r8.put(r9, r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r8 = r8.a;	 Catch:{ Throwable -> 0x0564 }
        r9 = "tbs_downloadflow";
        r10 = 0;
        r10 = java.lang.Long.valueOf(r10);	 Catch:{ Throwable -> 0x0564 }
        r8.put(r9, r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r8.commit();	 Catch:{ Throwable -> 0x0564 }
        r8 = 0;
    L_0x02c1:
        r0 = r32;
        r6 = r0.g;	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
        r6 = com.tencent.smtt.utils.k.b(r6);	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
        if (r6 != 0) goto L_0x0367;
    L_0x02cb:
        r6 = "TbsDownload";
        r7 = "DownloadBegin FreeSpace too small";
        r10 = 1;
        com.tencent.smtt.utils.TbsLog.i(r6, r7, r10);	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
        r6 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        r7 = 0;
        r10 = 1;
        r0 = r32;
        r0.a(r6, r7, r10);	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
        r0 = r32;
        r6 = r0.g;	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
        r7 = -308; // 0xfffffffffffffecc float:NaN double:NaN;
        r6.setDownloadInterruptCode(r7);	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
        if (r33 != 0) goto L_0x01ba;
    L_0x02eb:
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6 = r6.a;
        r7 = "tbs_downloadflow";
        r8 = java.lang.Long.valueOf(r8);
        r6.put(r7, r8);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x030b:
        r8 = "TbsDownload";
        r9 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0564 }
        r9.<init>();	 Catch:{ Throwable -> 0x0564 }
        r10 = "[TbsApkDownloader.startDownload] downloadFlow=";
        r9 = r9.append(r10);	 Catch:{ Throwable -> 0x0564 }
        r9 = r9.append(r6);	 Catch:{ Throwable -> 0x0564 }
        r9 = r9.toString();	 Catch:{ Throwable -> 0x0564 }
        com.tencent.smtt.utils.TbsLog.i(r8, r9);	 Catch:{ Throwable -> 0x0564 }
        r8 = (r6 > r24 ? 1 : (r6 == r24 ? 0 : -1));
        if (r8 < 0) goto L_0x0f91;
    L_0x0327:
        r8 = "TbsDownload";
        r9 = "STEP 1/2 begin downloading...failed because you exceeded max flow!";
        r10 = 1;
        com.tencent.smtt.utils.TbsLog.i(r8, r9, r10);	 Catch:{ Throwable -> 0x0564 }
        r8 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        r9 = 0;
        r10 = 1;
        r0 = r32;
        r0.a(r8, r9, r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r9 = -307; // 0xfffffffffffffecd float:NaN double:NaN;
        r8.setDownloadInterruptCode(r9);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01ba;
    L_0x0347:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r6 = java.lang.Long.valueOf(r6);
        r8.put(r9, r6);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x0367:
        r6 = r8;
    L_0x0368:
        r8 = 1;
        r0 = r32;
        r0.y = r8;	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.j;	 Catch:{ Throwable -> 0x0564 }
        if (r8 == 0) goto L_0x0507;
    L_0x0373:
        r0 = r32;
        r8 = r0.j;	 Catch:{ Throwable -> 0x0564 }
    L_0x0377:
        r9 = "TbsDownload";
        r10 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0564 }
        r10.<init>();	 Catch:{ Throwable -> 0x0564 }
        r11 = "try url:";
        r10 = r10.append(r11);	 Catch:{ Throwable -> 0x0564 }
        r10 = r10.append(r8);	 Catch:{ Throwable -> 0x0564 }
        r11 = ",mRetryTimes:";
        r10 = r10.append(r11);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r11 = r0.p;	 Catch:{ Throwable -> 0x0564 }
        r10 = r10.append(r11);	 Catch:{ Throwable -> 0x0564 }
        r10 = r10.toString();	 Catch:{ Throwable -> 0x0564 }
        r11 = 1;
        com.tencent.smtt.utils.TbsLog.i(r9, r10, r11);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r9 = r0.i;	 Catch:{ Throwable -> 0x0564 }
        r9 = r8.equals(r9);	 Catch:{ Throwable -> 0x0564 }
        if (r9 != 0) goto L_0x03af;
    L_0x03a8:
        r0 = r32;
        r9 = r0.v;	 Catch:{ Throwable -> 0x0564 }
        r9.a(r8);	 Catch:{ Throwable -> 0x0564 }
    L_0x03af:
        r0 = r32;
        r0.i = r8;	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r0.a(r8);	 Catch:{ Throwable -> 0x0564 }
        r18 = 0;
        r0 = r32;
        r8 = r0.o;	 Catch:{ Throwable -> 0x0564 }
        if (r8 != 0) goto L_0x0427;
    L_0x03c0:
        r18 = r32.k();	 Catch:{ Throwable -> 0x0564 }
        r8 = "TbsDownload";
        r9 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0564 }
        r9.<init>();	 Catch:{ Throwable -> 0x0564 }
        r10 = "[TbsApkDownloader.startDownload] range=";
        r9 = r9.append(r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r18;
        r9 = r9.append(r0);	 Catch:{ Throwable -> 0x0564 }
        r9 = r9.toString();	 Catch:{ Throwable -> 0x0564 }
        com.tencent.smtt.utils.TbsLog.i(r8, r9);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.l;	 Catch:{ Throwable -> 0x0564 }
        r10 = 0;
        r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r8 > 0) goto L_0x050d;
    L_0x03e8:
        r8 = "TbsDownload";
        r9 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0564 }
        r9.<init>();	 Catch:{ Throwable -> 0x0564 }
        r10 = "STEP 1/2 begin downloading...current";
        r9 = r9.append(r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r18;
        r9 = r9.append(r0);	 Catch:{ Throwable -> 0x0564 }
        r9 = r9.toString();	 Catch:{ Throwable -> 0x0564 }
        r10 = 1;
        com.tencent.smtt.utils.TbsLog.i(r8, r9, r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.t;	 Catch:{ Throwable -> 0x0564 }
        r9 = "Range";
        r10 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0564 }
        r10.<init>();	 Catch:{ Throwable -> 0x0564 }
        r11 = "bytes=";
        r10 = r10.append(r11);	 Catch:{ Throwable -> 0x0564 }
        r0 = r18;
        r10 = r10.append(r0);	 Catch:{ Throwable -> 0x0564 }
        r11 = "-";
        r10 = r10.append(r11);	 Catch:{ Throwable -> 0x0564 }
        r10 = r10.toString();	 Catch:{ Throwable -> 0x0564 }
        r8.setRequestProperty(r9, r10);	 Catch:{ Throwable -> 0x0564 }
    L_0x0427:
        r0 = r32;
        r9 = r0.v;	 Catch:{ Throwable -> 0x0564 }
        r10 = 0;
        r8 = (r18 > r10 ? 1 : (r18 == r10 ? 0 : -1));
        if (r8 != 0) goto L_0x05dc;
    L_0x0431:
        r8 = 0;
    L_0x0432:
        r9.c(r8);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.utils.Apn.getApnType(r8);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r9 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r9 = com.tencent.smtt.utils.Apn.getApnInfo(r9);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r10 = r0.w;	 Catch:{ Throwable -> 0x0564 }
        if (r10 != 0) goto L_0x05df;
    L_0x044b:
        r0 = r32;
        r10 = r0.x;	 Catch:{ Throwable -> 0x0564 }
        r11 = -1;
        if (r10 != r11) goto L_0x05df;
    L_0x0452:
        r0 = r32;
        r0.w = r9;	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r0.x = r8;	 Catch:{ Throwable -> 0x0564 }
    L_0x045a:
        r0 = r32;
        r8 = r0.p;	 Catch:{ Throwable -> 0x0564 }
        r9 = 1;
        if (r8 < r9) goto L_0x046e;
    L_0x0461:
        r0 = r32;
        r8 = r0.t;	 Catch:{ Throwable -> 0x0564 }
        r9 = "Referer";
        r0 = r32;
        r10 = r0.h;	 Catch:{ Throwable -> 0x0564 }
        r8.addRequestProperty(r9, r10);	 Catch:{ Throwable -> 0x0564 }
    L_0x046e:
        r0 = r32;
        r8 = r0.t;	 Catch:{ Throwable -> 0x0564 }
        r8 = r8.getResponseCode();	 Catch:{ Throwable -> 0x0564 }
        r9 = "TbsDownload";
        r10 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0564 }
        r10.<init>();	 Catch:{ Throwable -> 0x0564 }
        r11 = "[TbsApkDownloader.startDownload] responseCode=";
        r10 = r10.append(r11);	 Catch:{ Throwable -> 0x0564 }
        r10 = r10.append(r8);	 Catch:{ Throwable -> 0x0564 }
        r10 = r10.toString();	 Catch:{ Throwable -> 0x0564 }
        com.tencent.smtt.utils.TbsLog.i(r9, r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r9 = r0.v;	 Catch:{ Throwable -> 0x0564 }
        r9.a(r8);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x04d2;
    L_0x0497:
        r0 = r32;
        r9 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r9 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r9);	 Catch:{ Throwable -> 0x0564 }
        if (r9 != 0) goto L_0x04d2;
    L_0x04a1:
        r0 = r32;
        r9 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r9 = com.tencent.smtt.utils.Apn.getApnType(r9);	 Catch:{ Throwable -> 0x0564 }
        r10 = 3;
        if (r9 == r10) goto L_0x04b2;
    L_0x04ac:
        r9 = com.tencent.smtt.sdk.QbSdk.getDownloadWithoutWifi();	 Catch:{ Throwable -> 0x0564 }
        if (r9 == 0) goto L_0x04bc;
    L_0x04b2:
        r0 = r32;
        r9 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r9 = com.tencent.smtt.utils.Apn.getApnType(r9);	 Catch:{ Throwable -> 0x0564 }
        if (r9 != 0) goto L_0x04d2;
    L_0x04bc:
        r32.b();	 Catch:{ Throwable -> 0x0564 }
        r9 = com.tencent.smtt.sdk.QbSdk.j;	 Catch:{ Throwable -> 0x0564 }
        if (r9 == 0) goto L_0x04ca;
    L_0x04c3:
        r9 = com.tencent.smtt.sdk.QbSdk.j;	 Catch:{ Throwable -> 0x0564 }
        r10 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        r9.onDownloadFinish(r10);	 Catch:{ Throwable -> 0x0564 }
    L_0x04ca:
        r9 = "TbsDownload";
        r10 = "Download is canceled due to NOT_WIFI error!";
        r11 = 0;
        com.tencent.smtt.utils.TbsLog.i(r9, r10, r11);	 Catch:{ Throwable -> 0x0564 }
    L_0x04d2:
        r0 = r32;
        r9 = r0.r;	 Catch:{ Throwable -> 0x0564 }
        if (r9 == 0) goto L_0x0623;
    L_0x04d8:
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r9 = -309; // 0xfffffffffffffecb float:NaN double:NaN;
        r8.setDownloadInterruptCode(r9);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01ba;
    L_0x04e7:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r6 = java.lang.Long.valueOf(r6);
        r8.put(r9, r6);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x0507:
        r0 = r32;
        r8 = r0.h;	 Catch:{ Throwable -> 0x0564 }
        goto L_0x0377;
    L_0x050d:
        r8 = "TbsDownload";
        r9 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0564 }
        r9.<init>();	 Catch:{ Throwable -> 0x0564 }
        r10 = "#1 STEP 1/2 begin downloading...current/total=";
        r9 = r9.append(r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r18;
        r9 = r9.append(r0);	 Catch:{ Throwable -> 0x0564 }
        r10 = "/";
        r9 = r9.append(r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r10 = r0.l;	 Catch:{ Throwable -> 0x0564 }
        r9 = r9.append(r10);	 Catch:{ Throwable -> 0x0564 }
        r9 = r9.toString();	 Catch:{ Throwable -> 0x0564 }
        r10 = 1;
        com.tencent.smtt.utils.TbsLog.i(r8, r9, r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.t;	 Catch:{ Throwable -> 0x0564 }
        r9 = "Range";
        r10 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0564 }
        r10.<init>();	 Catch:{ Throwable -> 0x0564 }
        r11 = "bytes=";
        r10 = r10.append(r11);	 Catch:{ Throwable -> 0x0564 }
        r0 = r18;
        r10 = r10.append(r0);	 Catch:{ Throwable -> 0x0564 }
        r11 = "-";
        r10 = r10.append(r11);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r12 = r0.l;	 Catch:{ Throwable -> 0x0564 }
        r10 = r10.append(r12);	 Catch:{ Throwable -> 0x0564 }
        r10 = r10.toString();	 Catch:{ Throwable -> 0x0564 }
        r8.setRequestProperty(r9, r10);	 Catch:{ Throwable -> 0x0564 }
        goto L_0x0427;
    L_0x0564:
        r8 = move-exception;
    L_0x0565:
        r9 = r8 instanceof javax.net.ssl.SSLHandshakeException;	 Catch:{ all -> 0x0601 }
        if (r9 == 0) goto L_0x0e69;
    L_0x0569:
        if (r33 != 0) goto L_0x0e69;
    L_0x056b:
        r0 = r32;
        r9 = r0.b;	 Catch:{ all -> 0x0601 }
        if (r9 == 0) goto L_0x0e69;
    L_0x0571:
        r9 = 0;
        r0 = r32;
        r9 = r0.b(r9);	 Catch:{ all -> 0x0601 }
        if (r9 == 0) goto L_0x0e69;
    L_0x057a:
        r9 = "TbsDownload";
        r10 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0601 }
        r10.<init>();	 Catch:{ all -> 0x0601 }
        r11 = "[startdownload]url:";
        r10 = r10.append(r11);	 Catch:{ all -> 0x0601 }
        r0 = r32;
        r11 = r0.j;	 Catch:{ all -> 0x0601 }
        r10 = r10.append(r11);	 Catch:{ all -> 0x0601 }
        r11 = " download exception：";
        r10 = r10.append(r11);	 Catch:{ all -> 0x0601 }
        r8 = r8.toString();	 Catch:{ all -> 0x0601 }
        r8 = r10.append(r8);	 Catch:{ all -> 0x0601 }
        r8 = r8.toString();	 Catch:{ all -> 0x0601 }
        com.tencent.smtt.utils.TbsLog.e(r9, r8);	 Catch:{ all -> 0x0601 }
        r8 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        r9 = 0;
        r10 = 1;
        r0 = r32;
        r0.a(r8, r9, r10);	 Catch:{ all -> 0x0601 }
    L_0x05ad:
        r0 = r32;
        r8 = r0.g;	 Catch:{ all -> 0x0601 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ all -> 0x0601 }
        r9 = -316; // 0xfffffffffffffec4 float:NaN double:NaN;
        r8.setDownloadInterruptCode(r9);	 Catch:{ all -> 0x0601 }
        if (r33 != 0) goto L_0x01b0;
    L_0x05bc:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r10 = java.lang.Long.valueOf(r6);
        r8.put(r9, r10);
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8.commit();
        goto L_0x01b0;
    L_0x05dc:
        r8 = 1;
        goto L_0x0432;
    L_0x05df:
        r0 = r32;
        r10 = r0.x;	 Catch:{ Throwable -> 0x0564 }
        if (r8 != r10) goto L_0x05ef;
    L_0x05e5:
        r0 = r32;
        r10 = r0.w;	 Catch:{ Throwable -> 0x0564 }
        r10 = r9.equals(r10);	 Catch:{ Throwable -> 0x0564 }
        if (r10 != 0) goto L_0x045a;
    L_0x05ef:
        r0 = r32;
        r10 = r0.v;	 Catch:{ Throwable -> 0x0564 }
        r11 = 0;
        r10.g(r11);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r0.w = r9;	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r0.x = r8;	 Catch:{ Throwable -> 0x0564 }
        goto L_0x045a;
    L_0x0601:
        r4 = move-exception;
    L_0x0602:
        if (r33 != 0) goto L_0x0622;
    L_0x0604:
        r0 = r32;
        r5 = r0.g;
        r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5);
        r5 = r5.a;
        r8 = "tbs_downloadflow";
        r6 = java.lang.Long.valueOf(r6);
        r5.put(r8, r6);
        r0 = r32;
        r5 = r0.g;
        r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5);
        r5.commit();
    L_0x0622:
        throw r4;
    L_0x0623:
        r9 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r8 == r9) goto L_0x062b;
    L_0x0627:
        r9 = 206; // 0xce float:2.89E-43 double:1.02E-321;
        if (r8 != r9) goto L_0x0baa;
    L_0x062b:
        r0 = r32;
        r8 = r0.t;	 Catch:{ Throwable -> 0x0564 }
        r8 = r8.getContentLength();	 Catch:{ Throwable -> 0x0564 }
        r8 = (long) r8;	 Catch:{ Throwable -> 0x0564 }
        r8 = r8 + r18;
        r0 = r32;
        r0.l = r8;	 Catch:{ Throwable -> 0x0564 }
        r8 = "TbsDownload";
        r9 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0564 }
        r9.<init>();	 Catch:{ Throwable -> 0x0564 }
        r10 = "[TbsApkDownloader.startDownload] mContentLength=";
        r9 = r9.append(r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r10 = r0.l;	 Catch:{ Throwable -> 0x0564 }
        r9 = r9.append(r10);	 Catch:{ Throwable -> 0x0564 }
        r9 = r9.toString();	 Catch:{ Throwable -> 0x0564 }
        com.tencent.smtt.utils.TbsLog.i(r8, r9);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.v;	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r10 = r0.l;	 Catch:{ Throwable -> 0x0564 }
        r8.b(r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r8 = r8.mPreferences;	 Catch:{ Throwable -> 0x0564 }
        r9 = "tbs_apkfilesize";
        r10 = 0;
        r8 = r8.getLong(r9, r10);	 Catch:{ Throwable -> 0x0564 }
        r10 = 0;
        r10 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r10 == 0) goto L_0x0761;
    L_0x0679:
        r0 = r32;
        r10 = r0.l;	 Catch:{ Throwable -> 0x0564 }
        r10 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1));
        if (r10 == 0) goto L_0x0761;
    L_0x0681:
        r10 = "TbsDownload";
        r11 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0564 }
        r11.<init>();	 Catch:{ Throwable -> 0x0564 }
        r12 = "DownloadBegin tbsApkFileSize=";
        r11 = r11.append(r12);	 Catch:{ Throwable -> 0x0564 }
        r11 = r11.append(r8);	 Catch:{ Throwable -> 0x0564 }
        r12 = "  but contentLength=";
        r11 = r11.append(r12);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r12 = r0.l;	 Catch:{ Throwable -> 0x0564 }
        r11 = r11.append(r12);	 Catch:{ Throwable -> 0x0564 }
        r11 = r11.toString();	 Catch:{ Throwable -> 0x0564 }
        r12 = 1;
        com.tencent.smtt.utils.TbsLog.i(r10, r11, r12);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x0749;
    L_0x06aa:
        r10 = r32.n();	 Catch:{ Throwable -> 0x0564 }
        if (r10 != 0) goto L_0x06c0;
    L_0x06b0:
        r10 = com.tencent.smtt.sdk.QbSdk.getDownloadWithoutWifi();	 Catch:{ Throwable -> 0x0564 }
        if (r10 == 0) goto L_0x0749;
    L_0x06b6:
        r0 = r32;
        r10 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r10 = com.tencent.smtt.utils.Apn.isNetworkAvailable(r10);	 Catch:{ Throwable -> 0x0564 }
        if (r10 == 0) goto L_0x0749;
    L_0x06c0:
        r0 = r32;
        r10 = r0.b;	 Catch:{ Throwable -> 0x0564 }
        if (r10 == 0) goto L_0x06f1;
    L_0x06c6:
        r10 = 0;
        r0 = r32;
        r10 = r0.b(r10);	 Catch:{ Throwable -> 0x0564 }
        if (r10 == 0) goto L_0x06f1;
    L_0x06cf:
        if (r33 != 0) goto L_0x01b0;
    L_0x06d1:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r10 = java.lang.Long.valueOf(r6);
        r8.put(r9, r10);
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8.commit();
        goto L_0x01b0;
    L_0x06f1:
        r10 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        r11 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0564 }
        r11.<init>();	 Catch:{ Throwable -> 0x0564 }
        r12 = "tbsApkFileSize=";
        r11 = r11.append(r12);	 Catch:{ Throwable -> 0x0564 }
        r8 = r11.append(r8);	 Catch:{ Throwable -> 0x0564 }
        r9 = "  but contentLength=";
        r8 = r8.append(r9);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r12 = r0.l;	 Catch:{ Throwable -> 0x0564 }
        r8 = r8.append(r12);	 Catch:{ Throwable -> 0x0564 }
        r8 = r8.toString();	 Catch:{ Throwable -> 0x0564 }
        r9 = 1;
        r0 = r32;
        r0.a(r10, r8, r9);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r9 = -310; // 0xfffffffffffffeca float:NaN double:NaN;
        r8.setDownloadInterruptCode(r9);	 Catch:{ Throwable -> 0x0564 }
    L_0x0727:
        if (r33 != 0) goto L_0x01ba;
    L_0x0729:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r6 = java.lang.Long.valueOf(r6);
        r8.put(r9, r6);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x0749:
        r8 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r9 = "WifiNetworkUnAvailable";
        r10 = 1;
        r0 = r32;
        r0.a(r8, r9, r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r9 = -304; // 0xfffffffffffffed0 float:NaN double:NaN;
        r8.setDownloadInterruptCode(r9);	 Catch:{ Throwable -> 0x0564 }
        goto L_0x0727;
    L_0x0761:
        r11 = 0;
        r10 = 0;
        r9 = 0;
        r8 = "TbsDownload";
        r12 = "[TbsApkDownloader.startDownload] begin readResponse";
        com.tencent.smtt.utils.TbsLog.i(r8, r12);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.t;	 Catch:{ IOException -> 0x0f52, all -> 0x0f10 }
        r15 = r8.getInputStream();	 Catch:{ IOException -> 0x0f52, all -> 0x0f10 }
        if (r15 == 0) goto L_0x0f8b;
    L_0x0775:
        r0 = r32;
        r8 = r0.t;	 Catch:{ IOException -> 0x081e, all -> 0x0f1d }
        r8 = r8.getContentEncoding();	 Catch:{ IOException -> 0x081e, all -> 0x0f1d }
        if (r8 == 0) goto L_0x0807;
    L_0x077f:
        r10 = "gzip";
        r10 = r8.contains(r10);	 Catch:{ IOException -> 0x081e, all -> 0x0f1d }
        if (r10 == 0) goto L_0x0807;
    L_0x0787:
        r14 = new java.util.zip.GZIPInputStream;	 Catch:{ IOException -> 0x081e, all -> 0x0f1d }
        r14.<init>(r15);	 Catch:{ IOException -> 0x081e, all -> 0x0f1d }
    L_0x078c:
        r8 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r0 = new byte[r8];	 Catch:{ IOException -> 0x0f55, all -> 0x0f29 }
        r26 = r0;
        r16 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0f55, all -> 0x0f29 }
        r8 = new java.io.File;	 Catch:{ IOException -> 0x0f55, all -> 0x0f29 }
        r0 = r32;
        r9 = r0.k;	 Catch:{ IOException -> 0x0f55, all -> 0x0f29 }
        r10 = "x5.tbs.temp";
        r8.<init>(r9, r10);	 Catch:{ IOException -> 0x0f55, all -> 0x0f29 }
        r9 = 1;
        r0 = r16;
        r0.<init>(r8, r9);	 Catch:{ IOException -> 0x0f55, all -> 0x0f29 }
        r12 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x0f5a, all -> 0x0f34 }
        r17 = 0;
        r10 = r20;
        r8 = r6;
        r20 = r18;
        r6 = r18;
    L_0x07b2:
        r0 = r32;
        r0 = r0.r;	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r18 = r0;
        if (r18 == 0) goto L_0x087b;
    L_0x07ba:
        r6 = "TbsDownload";
        r7 = "STEP 1/2 begin downloading...Canceled!";
        r10 = 1;
        com.tencent.smtt.utils.TbsLog.i(r6, r7, r10);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r0 = r32;
        r6 = r0.g;	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r7 = -309; // 0xfffffffffffffecb float:NaN double:NaN;
        r6.setDownloadInterruptCode(r7);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r6 = r8;
        r8 = r17;
    L_0x07d2:
        if (r8 == 0) goto L_0x0a63;
    L_0x07d4:
        r0 = r32;
        r1 = r16;
        r0.a(r1);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r0.a(r14);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r0.a(r15);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01b0;
    L_0x07e7:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r10 = java.lang.Long.valueOf(r6);
        r8.put(r9, r10);
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8.commit();
        goto L_0x01b0;
    L_0x0807:
        if (r8 == 0) goto L_0x0878;
    L_0x0809:
        r10 = "deflate";
        r8 = r8.contains(r10);	 Catch:{ IOException -> 0x081e, all -> 0x0f1d }
        if (r8 == 0) goto L_0x0878;
    L_0x0811:
        r14 = new java.util.zip.InflaterInputStream;	 Catch:{ IOException -> 0x081e, all -> 0x0f1d }
        r8 = new java.util.zip.Inflater;	 Catch:{ IOException -> 0x081e, all -> 0x0f1d }
        r10 = 1;
        r8.<init>(r10);	 Catch:{ IOException -> 0x081e, all -> 0x0f1d }
        r14.<init>(r15, r8);	 Catch:{ IOException -> 0x081e, all -> 0x0f1d }
        goto L_0x078c;
    L_0x081e:
        r8 = move-exception;
        r10 = r15;
    L_0x0820:
        r8.printStackTrace();	 Catch:{ all -> 0x0b84 }
        r12 = r8 instanceof java.net.SocketTimeoutException;	 Catch:{ all -> 0x0b84 }
        if (r12 != 0) goto L_0x082b;
    L_0x0827:
        r12 = r8 instanceof java.net.SocketException;	 Catch:{ all -> 0x0b84 }
        if (r12 == 0) goto L_0x0aaa;
    L_0x082b:
        r12 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
        r0 = r32;
        r0.m = r12;	 Catch:{ all -> 0x0b84 }
        r12 = 0;
        r0 = r32;
        r0.a(r12);	 Catch:{ all -> 0x0b84 }
        r12 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        r0 = r32;
        r8 = r0.a(r8);	 Catch:{ all -> 0x0b84 }
        r13 = 0;
        r0 = r32;
        r0.a(r12, r8, r13);	 Catch:{ all -> 0x0b84 }
        r0 = r32;
        r0.a(r11);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r0.a(r9);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r0.a(r10);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01b0;
    L_0x0858:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r10 = java.lang.Long.valueOf(r6);
        r8.put(r9, r10);
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8.commit();
        goto L_0x01b0;
    L_0x0878:
        r14 = r15;
        goto L_0x078c;
    L_0x087b:
        r18 = 0;
        r19 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r0 = r26;
        r1 = r18;
        r2 = r19;
        r27 = r14.read(r0, r1, r2);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        if (r27 > 0) goto L_0x08d7;
    L_0x088b:
        r0 = r32;
        r6 = r0.b;	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        if (r6 == 0) goto L_0x08b8;
    L_0x0891:
        r6 = 1;
        r0 = r32;
        r6 = r0.b(r6, r4);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        if (r6 != 0) goto L_0x08b8;
    L_0x089a:
        if (r33 != 0) goto L_0x08ad;
    L_0x089c:
        r6 = 0;
        r0 = r32;
        r6 = r0.b(r6);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        if (r6 == 0) goto L_0x08ad;
    L_0x08a5:
        r6 = 1;
        r30 = r6;
        r6 = r8;
        r8 = r30;
        goto L_0x07d2;
    L_0x08ad:
        r6 = 1;
        r0 = r32;
        r0.s = r6;	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r5 = 0;
        r6 = r8;
        r8 = r17;
        goto L_0x07d2;
    L_0x08b8:
        r6 = 1;
        r0 = r32;
        r0.s = r6;	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r0 = r32;
        r6 = r0.b;	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        if (r6 == 0) goto L_0x0f88;
    L_0x08c3:
        r6 = 1;
    L_0x08c4:
        r0 = r32;
        r5 = r0.g;	 Catch:{ IOException -> 0x0f6d, all -> 0x0f45 }
        r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5);	 Catch:{ IOException -> 0x0f6d, all -> 0x0f45 }
        r7 = -311; // 0xfffffffffffffec9 float:NaN double:NaN;
        r5.setDownloadInterruptCode(r7);	 Catch:{ IOException -> 0x0f6d, all -> 0x0f45 }
        r5 = r6;
        r6 = r8;
        r8 = r17;
        goto L_0x07d2;
    L_0x08d7:
        r18 = 0;
        r0 = r16;
        r1 = r26;
        r2 = r18;
        r3 = r27;
        r0.write(r1, r2, r3);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r16.flush();	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        if (r33 != 0) goto L_0x0990;
    L_0x08e9:
        r0 = r27;
        r0 = (long) r0;	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r18 = r0;
        r8 = r8 + r18;
        r18 = (r8 > r24 ? 1 : (r8 == r24 ? 0 : -1));
        if (r18 < 0) goto L_0x0935;
    L_0x08f4:
        r6 = "TbsDownload";
        r7 = "STEP 1/2 begin downloading...failed because you exceeded max flow!";
        r10 = 1;
        com.tencent.smtt.utils.TbsLog.i(r6, r7, r10);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r6 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        r7 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r7.<init>();	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r10 = "downloadFlow=";
        r7 = r7.append(r10);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r7 = r7.append(r8);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r10 = " downloadMaxflow=";
        r7 = r7.append(r10);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r0 = r24;
        r7 = r7.append(r0);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r7 = r7.toString();	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r10 = 1;
        r0 = r32;
        r0.a(r6, r7, r10);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r0 = r32;
        r6 = r0.g;	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r7 = -307; // 0xfffffffffffffecd float:NaN double:NaN;
        r6.setDownloadInterruptCode(r7);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r6 = r8;
        r8 = r17;
        goto L_0x07d2;
    L_0x0935:
        r0 = r32;
        r0 = r0.g;	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r18 = r0;
        r18 = com.tencent.smtt.utils.k.b(r18);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        if (r18 != 0) goto L_0x0990;
    L_0x0941:
        r6 = "TbsDownload";
        r7 = "DownloadEnd FreeSpace too small ";
        r10 = 1;
        com.tencent.smtt.utils.TbsLog.i(r6, r7, r10);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r6 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        r7 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r7.<init>();	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r10 = "freespace=";
        r7 = r7.append(r10);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r10 = com.tencent.smtt.utils.aa.a();	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r7 = r7.append(r10);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r10 = ",and minFreeSpace=";
        r7 = r7.append(r10);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r0 = r32;
        r10 = r0.g;	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r10 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r10 = r10.getDownloadMinFreeSpace();	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r7 = r7.append(r10);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r7 = r7.toString();	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r10 = 1;
        r0 = r32;
        r0.a(r6, r7, r10);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r0 = r32;
        r6 = r0.g;	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r7 = -308; // 0xfffffffffffffecc float:NaN double:NaN;
        r6.setDownloadInterruptCode(r7);	 Catch:{ IOException -> 0x0f61, all -> 0x0f3d }
        r6 = r8;
        r8 = r17;
        goto L_0x07d2;
    L_0x0990:
        r18 = r8;
        r0 = r27;
        r8 = (long) r0;
        r0 = r32;
        r22 = r0.a(r10, r8);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r0 = r27;
        r8 = (long) r0;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r8 = r8 + r20;
        r20 = r10 - r12;
        r28 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r20 = (r20 > r28 ? 1 : (r20 == r28 ? 0 : -1));
        if (r20 <= 0) goto L_0x0f84;
    L_0x09ac:
        r12 = "TbsDownload";
        r13 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r13.<init>();	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r20 = "#2 STEP 1/2 begin downloading...current/total=";
        r0 = r20;
        r13 = r13.append(r0);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r13 = r13.append(r8);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r20 = "/";
        r0 = r20;
        r13 = r13.append(r0);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r0 = r32;
        r0 = r0.l;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r20 = r0;
        r0 = r20;
        r13 = r13.append(r0);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r13 = r13.toString();	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r20 = 1;
        r0 = r20;
        com.tencent.smtt.utils.TbsLog.i(r12, r13, r0);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r12 = com.tencent.smtt.sdk.QbSdk.j;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        if (r12 == 0) goto L_0x09fa;
    L_0x09e2:
        r12 = (double) r8;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r0 = r32;
        r0 = r0.l;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r20 = r0;
        r0 = r20;
        r0 = (double) r0;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r20 = r0;
        r12 = r12 / r20;
        r20 = 4636737291354636288; // 0x4059000000000000 float:0.0 double:100.0;
        r12 = r12 * r20;
        r12 = (int) r12;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r13 = com.tencent.smtt.sdk.QbSdk.j;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r13.onDownloadProgress(r12);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
    L_0x09fa:
        if (r33 != 0) goto L_0x0a54;
    L_0x09fc:
        r12 = r8 - r6;
        r20 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r12 = (r12 > r20 ? 1 : (r12 == r20 ? 0 : -1));
        if (r12 <= 0) goto L_0x0a54;
    L_0x0a05:
        r0 = r32;
        r6 = r0.g;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r6 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r6);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        if (r6 != 0) goto L_0x0a53;
    L_0x0a0f:
        r0 = r32;
        r6 = r0.g;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r6 = com.tencent.smtt.utils.Apn.getApnType(r6);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r7 = 3;
        if (r6 == r7) goto L_0x0a20;
    L_0x0a1a:
        r6 = com.tencent.smtt.sdk.QbSdk.getDownloadWithoutWifi();	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        if (r6 == 0) goto L_0x0a2a;
    L_0x0a20:
        r0 = r32;
        r6 = r0.g;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r6 = com.tencent.smtt.utils.Apn.getApnType(r6);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        if (r6 != 0) goto L_0x0a53;
    L_0x0a2a:
        r32.b();	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r6 = com.tencent.smtt.sdk.QbSdk.j;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        if (r6 == 0) goto L_0x0a38;
    L_0x0a31:
        r6 = com.tencent.smtt.sdk.QbSdk.j;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r7 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        r6.onDownloadFinish(r7);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
    L_0x0a38:
        r6 = "TbsDownload";
        r7 = "Download is paused due to NOT_WIFI error!";
        r8 = 0;
        com.tencent.smtt.utils.TbsLog.i(r6, r7, r8);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r0 = r32;
        r6 = r0.g;	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r7 = -304; // 0xfffffffffffffed0 float:NaN double:NaN;
        r6.setDownloadInterruptCode(r7);	 Catch:{ IOException -> 0x0f7a, all -> 0x0f48 }
        r8 = r17;
        r6 = r18;
        goto L_0x07d2;
    L_0x0a53:
        r6 = r8;
    L_0x0a54:
        r30 = r10;
        r10 = r6;
        r6 = r30;
    L_0x0a59:
        r12 = r6;
        r20 = r8;
        r6 = r10;
        r8 = r18;
        r10 = r22;
        goto L_0x07b2;
    L_0x0a63:
        r8 = r6;
    L_0x0a64:
        r0 = r32;
        r1 = r16;
        r0.a(r1);	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
        r0 = r32;
        r0.a(r14);	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
        r0 = r32;
        r0.a(r15);	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
        r0 = r32;
        r6 = r0.s;	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
        if (r6 != 0) goto L_0x0a88;
    L_0x0a7b:
        r0 = r32;
        r6 = r0.g;	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
        r7 = -319; // 0xfffffffffffffec1 float:NaN double:NaN;
        r6.setDownloadInterruptCode(r7);	 Catch:{ Throwable -> 0x0f08, all -> 0x0f04 }
    L_0x0a88:
        if (r33 != 0) goto L_0x01ba;
    L_0x0a8a:
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6 = r6.a;
        r7 = "tbs_downloadflow";
        r8 = java.lang.Long.valueOf(r8);
        r6.put(r7, r8);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x0aaa:
        if (r33 != 0) goto L_0x0b29;
    L_0x0aac:
        r0 = r32;
        r12 = r0.g;	 Catch:{ all -> 0x0b84 }
        r12 = com.tencent.smtt.utils.k.b(r12);	 Catch:{ all -> 0x0b84 }
        if (r12 != 0) goto L_0x0b29;
    L_0x0ab6:
        r8 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        r12 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0b84 }
        r12.<init>();	 Catch:{ all -> 0x0b84 }
        r13 = "freespace=";
        r12 = r12.append(r13);	 Catch:{ all -> 0x0b84 }
        r14 = com.tencent.smtt.utils.aa.a();	 Catch:{ all -> 0x0b84 }
        r12 = r12.append(r14);	 Catch:{ all -> 0x0b84 }
        r13 = ",and minFreeSpace=";
        r12 = r12.append(r13);	 Catch:{ all -> 0x0b84 }
        r0 = r32;
        r13 = r0.g;	 Catch:{ all -> 0x0b84 }
        r13 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ all -> 0x0b84 }
        r14 = r13.getDownloadMinFreeSpace();	 Catch:{ all -> 0x0b84 }
        r12 = r12.append(r14);	 Catch:{ all -> 0x0b84 }
        r12 = r12.toString();	 Catch:{ all -> 0x0b84 }
        r13 = 1;
        r0 = r32;
        r0.a(r8, r12, r13);	 Catch:{ all -> 0x0b84 }
        r0 = r32;
        r8 = r0.g;	 Catch:{ all -> 0x0b84 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ all -> 0x0b84 }
        r12 = -308; // 0xfffffffffffffecc float:NaN double:NaN;
        r8.setDownloadInterruptCode(r12);	 Catch:{ all -> 0x0b84 }
        r0 = r32;
        r0.a(r11);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r0.a(r9);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r0.a(r10);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01ba;
    L_0x0b09:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r6 = java.lang.Long.valueOf(r6);
        r8.put(r9, r6);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x0b29:
        r12 = 0;
        r0 = r32;
        r0.a(r12);	 Catch:{ all -> 0x0b84 }
        r12 = r32.j();	 Catch:{ all -> 0x0b84 }
        if (r12 != 0) goto L_0x0b75;
    L_0x0b36:
        r12 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        r0 = r32;
        r8 = r0.a(r8);	 Catch:{ all -> 0x0b84 }
        r13 = 0;
        r0 = r32;
        r0.a(r12, r8, r13);	 Catch:{ all -> 0x0b84 }
    L_0x0b44:
        r0 = r32;
        r0.a(r11);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r0.a(r9);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r0.a(r10);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01b0;
    L_0x0b55:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r10 = java.lang.Long.valueOf(r6);
        r8.put(r9, r10);
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8.commit();
        goto L_0x01b0;
    L_0x0b75:
        r12 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        r0 = r32;
        r8 = r0.a(r8);	 Catch:{ all -> 0x0b84 }
        r13 = 0;
        r0 = r32;
        r0.a(r12, r8, r13);	 Catch:{ all -> 0x0b84 }
        goto L_0x0b44;
    L_0x0b84:
        r8 = move-exception;
        r14 = r9;
        r15 = r10;
        r16 = r11;
        r30 = r8;
        r8 = r6;
        r6 = r5;
        r5 = r30;
    L_0x0b8f:
        r0 = r32;
        r1 = r16;
        r0.a(r1);	 Catch:{ Throwable -> 0x0ba1, all -> 0x0f04 }
        r0 = r32;
        r0.a(r14);	 Catch:{ Throwable -> 0x0ba1, all -> 0x0f04 }
        r0 = r32;
        r0.a(r15);	 Catch:{ Throwable -> 0x0ba1, all -> 0x0f04 }
        throw r5;	 Catch:{ Throwable -> 0x0ba1, all -> 0x0f04 }
    L_0x0ba1:
        r5 = move-exception;
        r30 = r5;
        r5 = r6;
        r6 = r8;
        r8 = r30;
        goto L_0x0565;
    L_0x0baa:
        r9 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r8 < r9) goto L_0x0c2a;
    L_0x0bae:
        r9 = 307; // 0x133 float:4.3E-43 double:1.517E-321;
        if (r8 > r9) goto L_0x0c2a;
    L_0x0bb2:
        r0 = r32;
        r8 = r0.t;	 Catch:{ Throwable -> 0x0564 }
        r9 = "Location";
        r8 = r8.getHeaderField(r9);	 Catch:{ Throwable -> 0x0564 }
        r9 = android.text.TextUtils.isEmpty(r8);	 Catch:{ Throwable -> 0x0564 }
        if (r9 != 0) goto L_0x0bf2;
    L_0x0bc2:
        r0 = r32;
        r0.j = r8;	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.q;	 Catch:{ Throwable -> 0x0564 }
        r8 = r8 + 1;
        r0 = r32;
        r0.q = r8;	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01b0;
    L_0x0bd2:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r10 = java.lang.Long.valueOf(r6);
        r8.put(r9, r10);
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8.commit();
        goto L_0x01b0;
    L_0x0bf2:
        r8 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        r9 = 0;
        r10 = 1;
        r0 = r32;
        r0.a(r8, r9, r10);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r9 = -312; // 0xfffffffffffffec8 float:NaN double:NaN;
        r8.setDownloadInterruptCode(r9);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01ba;
    L_0x0c0a:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r6 = java.lang.Long.valueOf(r6);
        r8.put(r9, r6);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x0c2a:
        r9 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        r10 = java.lang.String.valueOf(r8);	 Catch:{ Throwable -> 0x0564 }
        r11 = 0;
        r0 = r32;
        r0.a(r9, r10, r11);	 Catch:{ Throwable -> 0x0564 }
        r9 = 416; // 0x1a0 float:5.83E-43 double:2.055E-321;
        if (r8 != r9) goto L_0x0ca8;
    L_0x0c3a:
        r8 = 1;
        r0 = r32;
        r8 = r0.b(r8, r4);	 Catch:{ Throwable -> 0x0564 }
        if (r8 == 0) goto L_0x0c73;
    L_0x0c43:
        r5 = 1;
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r9 = -214; // 0xffffffffffffff2a float:NaN double:NaN;
        r8.setDownloadInterruptCode(r9);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01ba;
    L_0x0c53:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r6 = java.lang.Long.valueOf(r6);
        r8.put(r9, r6);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x0c73:
        r8 = 0;
        r0 = r32;
        r0.d(r8);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r9 = -313; // 0xfffffffffffffec7 float:NaN double:NaN;
        r8.setDownloadInterruptCode(r9);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01ba;
    L_0x0c88:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r6 = java.lang.Long.valueOf(r6);
        r8.put(r9, r6);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x0ca8:
        r9 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r8 == r9) goto L_0x0cb0;
    L_0x0cac:
        r9 = 406; // 0x196 float:5.69E-43 double:2.006E-321;
        if (r8 != r9) goto L_0x0ce9;
    L_0x0cb0:
        r0 = r32;
        r10 = r0.l;	 Catch:{ Throwable -> 0x0564 }
        r12 = -1;
        r9 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r9 != 0) goto L_0x0ce9;
    L_0x0cba:
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r9 = -314; // 0xfffffffffffffec6 float:NaN double:NaN;
        r8.setDownloadInterruptCode(r9);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01ba;
    L_0x0cc9:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r6 = java.lang.Long.valueOf(r6);
        r8.put(r9, r6);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x0ce9:
        r9 = 202; // 0xca float:2.83E-43 double:1.0E-321;
        if (r8 != r9) goto L_0x0d0f;
    L_0x0ced:
        if (r33 != 0) goto L_0x01b0;
    L_0x0cef:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r10 = java.lang.Long.valueOf(r6);
        r8.put(r9, r10);
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8.commit();
        goto L_0x01b0;
    L_0x0d0f:
        r0 = r32;
        r9 = r0.p;	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r10 = r0.B;	 Catch:{ Throwable -> 0x0564 }
        if (r9 >= r10) goto L_0x0d87;
    L_0x0d19:
        r9 = 503; // 0x1f7 float:7.05E-43 double:2.485E-321;
        if (r8 != r9) goto L_0x0d87;
    L_0x0d1d:
        r0 = r32;
        r8 = r0.t;	 Catch:{ Throwable -> 0x0564 }
        r9 = "Retry-After";
        r8 = r8.getHeaderField(r9);	 Catch:{ Throwable -> 0x0564 }
        r8 = java.lang.Long.parseLong(r8);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r0.a(r8);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.r;	 Catch:{ Throwable -> 0x0564 }
        if (r8 == 0) goto L_0x0d65;
    L_0x0d36:
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r9 = -309; // 0xfffffffffffffecb float:NaN double:NaN;
        r8.setDownloadInterruptCode(r9);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01ba;
    L_0x0d45:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r6 = java.lang.Long.valueOf(r6);
        r8.put(r9, r6);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x0d65:
        if (r33 != 0) goto L_0x01b0;
    L_0x0d67:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r10 = java.lang.Long.valueOf(r6);
        r8.put(r9, r10);
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8.commit();
        goto L_0x01b0;
    L_0x0d87:
        r0 = r32;
        r9 = r0.p;	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r10 = r0.B;	 Catch:{ Throwable -> 0x0564 }
        if (r9 >= r10) goto L_0x0dff;
    L_0x0d91:
        r9 = 408; // 0x198 float:5.72E-43 double:2.016E-321;
        if (r8 == r9) goto L_0x0da1;
    L_0x0d95:
        r9 = 504; // 0x1f8 float:7.06E-43 double:2.49E-321;
        if (r8 == r9) goto L_0x0da1;
    L_0x0d99:
        r9 = 502; // 0x1f6 float:7.03E-43 double:2.48E-321;
        if (r8 == r9) goto L_0x0da1;
    L_0x0d9d:
        r9 = 408; // 0x198 float:5.72E-43 double:2.016E-321;
        if (r8 != r9) goto L_0x0dff;
    L_0x0da1:
        r8 = 0;
        r0 = r32;
        r0.a(r8);	 Catch:{ Throwable -> 0x0564 }
        r0 = r32;
        r8 = r0.r;	 Catch:{ Throwable -> 0x0564 }
        if (r8 == 0) goto L_0x0ddd;
    L_0x0dae:
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r9 = -309; // 0xfffffffffffffecb float:NaN double:NaN;
        r8.setDownloadInterruptCode(r9);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01ba;
    L_0x0dbd:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r6 = java.lang.Long.valueOf(r6);
        r8.put(r9, r6);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x0ddd:
        if (r33 != 0) goto L_0x01b0;
    L_0x0ddf:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r10 = java.lang.Long.valueOf(r6);
        r8.put(r9, r10);
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8.commit();
        goto L_0x01b0;
    L_0x0dff:
        r10 = r32.k();	 Catch:{ Throwable -> 0x0564 }
        r12 = 0;
        r9 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r9 > 0) goto L_0x0e3a;
    L_0x0e09:
        r0 = r32;
        r9 = r0.o;	 Catch:{ Throwable -> 0x0564 }
        if (r9 != 0) goto L_0x0e3a;
    L_0x0e0f:
        r9 = 410; // 0x19a float:5.75E-43 double:2.026E-321;
        if (r8 == r9) goto L_0x0e3a;
    L_0x0e13:
        r8 = 1;
        r0 = r32;
        r0.o = r8;	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01b0;
    L_0x0e1a:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r10 = java.lang.Long.valueOf(r6);
        r8.put(r9, r10);
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8.commit();
        goto L_0x01b0;
    L_0x0e3a:
        r0 = r32;
        r8 = r0.g;	 Catch:{ Throwable -> 0x0564 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ Throwable -> 0x0564 }
        r9 = -315; // 0xfffffffffffffec5 float:NaN double:NaN;
        r8.setDownloadInterruptCode(r9);	 Catch:{ Throwable -> 0x0564 }
        if (r33 != 0) goto L_0x01ba;
    L_0x0e49:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r6 = java.lang.Long.valueOf(r6);
        r8.put(r9, r6);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x0e69:
        r8.printStackTrace();	 Catch:{ all -> 0x0601 }
        r10 = 0;
        r0 = r32;
        r0.a(r10);	 Catch:{ all -> 0x0601 }
        r9 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        r0 = r32;
        r8 = r0.a(r8);	 Catch:{ all -> 0x0601 }
        r10 = 0;
        r0 = r32;
        r0.a(r9, r8, r10);	 Catch:{ all -> 0x0601 }
        r0 = r32;
        r8 = r0.r;	 Catch:{ all -> 0x0601 }
        if (r8 == 0) goto L_0x05ad;
    L_0x0e87:
        r0 = r32;
        r8 = r0.g;	 Catch:{ all -> 0x0601 }
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);	 Catch:{ all -> 0x0601 }
        r9 = -309; // 0xfffffffffffffecb float:NaN double:NaN;
        r8.setDownloadInterruptCode(r9);	 Catch:{ all -> 0x0601 }
        if (r33 != 0) goto L_0x01ba;
    L_0x0e96:
        r0 = r32;
        r8 = r0.g;
        r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8);
        r8 = r8.a;
        r9 = "tbs_downloadflow";
        r6 = java.lang.Long.valueOf(r6);
        r8.put(r9, r6);
        r0 = r32;
        r6 = r0.g;
        r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6);
        r6.commit();
        goto L_0x01ba;
    L_0x0eb6:
        r6 = 0;
        goto L_0x01dc;
    L_0x0eb9:
        r4 = 2;
        goto L_0x01e8;
    L_0x0ebc:
        r0 = r32;
        r4 = r0.v;
        r6 = 0;
        r4.b(r6);
        goto L_0x01eb;
    L_0x0ec6:
        r0 = r32;
        r4 = r0.g;
        r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4);
        r6 = -318; // 0xfffffffffffffec2 float:NaN double:NaN;
        r4.setDownloadInterruptCode(r6);
        r4 = 0;
        r0 = r32;
        r0.d(r4);
        goto L_0x020a;
    L_0x0edb:
        r6 = r4.mPreferences;
        r7 = "tbs_download_failed_retrytimes";
        r8 = 0;
        r6 = r6.getInt(r7, r8);
        r7 = r4.a;
        r8 = "tbs_download_failed_retrytimes";
        r6 = r6 + 1;
        r9 = java.lang.Integer.valueOf(r6);
        r7.put(r8, r9);
        r7 = r4.getDownloadFailedMaxRetrytimes();
        if (r6 != r7) goto L_0x022a;
    L_0x0ef7:
        r0 = r32;
        r6 = r0.v;
        r7 = 2;
        r6.c(r7);
        goto L_0x022a;
    L_0x0f01:
        r4 = 0;
        goto L_0x0234;
    L_0x0f04:
        r4 = move-exception;
        r6 = r8;
        goto L_0x0602;
    L_0x0f08:
        r6 = move-exception;
        r30 = r6;
        r6 = r8;
        r8 = r30;
        goto L_0x0565;
    L_0x0f10:
        r8 = move-exception;
        r14 = r9;
        r15 = r10;
        r16 = r11;
        r30 = r8;
        r8 = r6;
        r6 = r5;
        r5 = r30;
        goto L_0x0b8f;
    L_0x0f1d:
        r8 = move-exception;
        r14 = r9;
        r16 = r11;
        r30 = r8;
        r8 = r6;
        r6 = r5;
        r5 = r30;
        goto L_0x0b8f;
    L_0x0f29:
        r8 = move-exception;
        r16 = r11;
        r30 = r8;
        r8 = r6;
        r6 = r5;
        r5 = r30;
        goto L_0x0b8f;
    L_0x0f34:
        r8 = move-exception;
        r30 = r8;
        r8 = r6;
        r6 = r5;
        r5 = r30;
        goto L_0x0b8f;
    L_0x0f3d:
        r6 = move-exception;
        r30 = r6;
        r6 = r5;
        r5 = r30;
        goto L_0x0b8f;
    L_0x0f45:
        r5 = move-exception;
        goto L_0x0b8f;
    L_0x0f48:
        r6 = move-exception;
        r8 = r18;
        r30 = r6;
        r6 = r5;
        r5 = r30;
        goto L_0x0b8f;
    L_0x0f52:
        r8 = move-exception;
        goto L_0x0820;
    L_0x0f55:
        r8 = move-exception;
        r9 = r14;
        r10 = r15;
        goto L_0x0820;
    L_0x0f5a:
        r8 = move-exception;
        r9 = r14;
        r10 = r15;
        r11 = r16;
        goto L_0x0820;
    L_0x0f61:
        r6 = move-exception;
        r10 = r15;
        r11 = r16;
        r30 = r6;
        r6 = r8;
        r9 = r14;
        r8 = r30;
        goto L_0x0820;
    L_0x0f6d:
        r5 = move-exception;
        r10 = r15;
        r11 = r16;
        r30 = r5;
        r5 = r6;
        r6 = r8;
        r9 = r14;
        r8 = r30;
        goto L_0x0820;
    L_0x0f7a:
        r6 = move-exception;
        r8 = r6;
        r9 = r14;
        r10 = r15;
        r11 = r16;
        r6 = r18;
        goto L_0x0820;
    L_0x0f84:
        r10 = r6;
        r6 = r12;
        goto L_0x0a59;
    L_0x0f88:
        r6 = r5;
        goto L_0x08c4;
    L_0x0f8b:
        r14 = r9;
        r16 = r11;
        r8 = r6;
        goto L_0x0a64;
    L_0x0f91:
        r8 = r6;
        goto L_0x02c1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ac.a(boolean):void");
    }

    public boolean a(boolean z, boolean z2) {
        int i;
        int i2 = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsConfigKey.KEY_USE_BACKUP_VERSION, 0);
        int d = aj.a().d(this.g);
        if (i2 == 0) {
            i2 = TbsDownloadConfig.getInstance(this.g).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
            this.a = "by default key";
            i = i2;
        } else {
            this.a = "by new key";
            i = i2;
        }
        if (i == 0 || i == d) {
            return false;
        }
        if (z2) {
            boolean z3;
            File a = TbsDownloader.a(i);
            if (a != null && a.exists()) {
                try {
                    k.b(a, new File(k.a(this.g, 4), TbsDownloader.getOverSea(this.g) ? "x5.oversea.tbs.org" : "x5.tbs.org"));
                    z3 = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (h()) {
                    i();
                    if (!a.a(this.g, a, 0, i)) {
                        k.b(a);
                    }
                } else if (g()) {
                    TbsDownloadConfig.getInstance(this.g).a.put(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, Integer.valueOf(-214));
                    TbsDownloadConfig.getInstance(this.g).setDownloadInterruptCode(-214);
                    c(false);
                    if (z3) {
                        return true;
                    }
                    a(100, "use local backup apk in startDownload" + this.a, true);
                    this.v.a(EventType.TYPE_DOWNLOAD);
                    return true;
                }
            }
            z3 = false;
            if (h()) {
                i();
                if (a.a(this.g, a, 0, i)) {
                    k.b(a);
                }
            } else if (g()) {
                TbsDownloadConfig.getInstance(this.g).a.put(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, Integer.valueOf(-214));
                TbsDownloadConfig.getInstance(this.g).setDownloadInterruptCode(-214);
                c(false);
                if (z3) {
                    return true;
                }
                a(100, "use local backup apk in startDownload" + this.a, true);
                this.v.a(EventType.TYPE_DOWNLOAD);
                return true;
            }
        }
        if (b(false, z2)) {
            TbsDownloadConfig.getInstance(this.g).a.put(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, Integer.valueOf(-214));
            TbsDownloadConfig.getInstance(this.g).setDownloadInterruptCode(-214);
            c(false);
            return true;
        }
        if (!(d(true) || d(true))) {
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader] delete file failed!");
            TbsDownloadConfig.getInstance(this.g).setDownloadInterruptCode(-301);
        }
        return false;
    }

    public void b() {
        this.r = true;
        if (TbsShareManager.isThirdPartyApp(this.g)) {
            TbsLogReport.a(this.g).h(-309);
            TbsLogReport.a(this.g).a(new Exception());
            TbsLogReport.a(this.g).a(EventType.TYPE_DOWNLOAD);
        }
    }

    public boolean b(boolean z) {
        if ((z && !n() && (!QbSdk.getDownloadWithoutWifi() || !Apn.isNetworkAvailable(this.g))) || this.b == null || this.c < 0 || this.c >= this.b.length) {
            return false;
        }
        String[] strArr = this.b;
        int i = this.c;
        this.c = i + 1;
        this.j = strArr[i];
        this.p = 0;
        this.q = 0;
        this.l = -1;
        this.o = false;
        this.r = false;
        this.s = false;
        this.y = false;
        return true;
    }

    public void c() {
        b();
        d(false);
        d(true);
    }

    public boolean d() {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.isDownloadForeground] mIsDownloadForeground=" + this.C);
        return this.C;
    }
}
