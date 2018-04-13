package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import com.alipay.sdk.util.h;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.k;
import com.tencent.smtt.utils.n;
import com.tencent.smtt.utils.p;
import com.tencent.smtt.utils.w;
import com.tencent.smtt.utils.x;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.http.entity.mime.MIME;
import org.json.JSONArray;

class TbsLogReport {
    private static TbsLogReport b;
    int a;
    private Context c;
    private long d;
    private String e;
    private String f;
    private int g;
    private int h;
    private int i;
    private int j;
    private String k;
    private int l;
    private int m;
    private long n;
    private long o;
    private int p;
    private String q;
    private String r;
    private long s;

    public enum EventType {
        TYPE_DOWNLOAD(0),
        TYPE_INSTALL(1),
        TYPE_LOAD(2);
        
        int a;

        private EventType(int i) {
            this.a = i;
        }
    }

    public static class ZipHelper {
        private final String a;
        private final String b;

        public ZipHelper(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        private static void a(File file) {
            Exception e;
            Throwable th;
            RandomAccessFile randomAccessFile;
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
                if (randomAccessFile != null) {
                    try {
                        int parseInt = Integer.parseInt("00001000", 2);
                        randomAccessFile.seek(7);
                        int read = randomAccessFile.read();
                        if ((read & parseInt) > 0) {
                            randomAccessFile.seek(7);
                            randomAccessFile.write(((parseInt ^ -1) & 255) & read);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                }
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                randomAccessFile = null;
                e.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile = null;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        }

        public void Zip() {
            FileOutputStream fileOutputStream;
            ZipOutputStream zipOutputStream;
            Exception e;
            FileInputStream fileInputStream;
            Throwable th;
            FileOutputStream fileOutputStream2;
            ZipOutputStream zipOutputStream2 = null;
            try {
                fileOutputStream = new FileOutputStream(this.b);
                try {
                    zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
                    try {
                        byte[] bArr = new byte[2048];
                        String str = this.a;
                        FileInputStream fileInputStream2;
                        BufferedInputStream bufferedInputStream;
                        try {
                            fileInputStream2 = new FileInputStream(str);
                            try {
                                bufferedInputStream = new BufferedInputStream(fileInputStream2, 2048);
                            } catch (Exception e2) {
                                e = e2;
                                bufferedInputStream = null;
                                fileInputStream = fileInputStream2;
                                try {
                                    e.printStackTrace();
                                    if (bufferedInputStream != null) {
                                        try {
                                            bufferedInputStream.close();
                                        } catch (IOException e3) {
                                            e3.printStackTrace();
                                        }
                                    }
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e32) {
                                            e32.printStackTrace();
                                        }
                                    }
                                    a(new File(this.b));
                                    if (zipOutputStream != null) {
                                        try {
                                            zipOutputStream.close();
                                        } catch (IOException e322) {
                                            e322.printStackTrace();
                                        }
                                    }
                                    if (fileOutputStream == null) {
                                        try {
                                            fileOutputStream.close();
                                        } catch (IOException e3222) {
                                            e3222.printStackTrace();
                                            return;
                                        }
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    fileInputStream2 = fileInputStream;
                                    if (bufferedInputStream != null) {
                                        try {
                                            bufferedInputStream.close();
                                        } catch (IOException e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                    if (fileInputStream2 != null) {
                                        try {
                                            fileInputStream2.close();
                                        } catch (IOException e42) {
                                            e42.printStackTrace();
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                bufferedInputStream = null;
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                                if (fileInputStream2 != null) {
                                    fileInputStream2.close();
                                }
                                throw th;
                            }
                            try {
                                zipOutputStream.putNextEntry(new ZipEntry(str.substring(str.lastIndexOf("/") + 1)));
                                while (true) {
                                    int read = bufferedInputStream.read(bArr, 0, 2048);
                                    if (read == -1) {
                                        break;
                                    }
                                    zipOutputStream.write(bArr, 0, read);
                                }
                                zipOutputStream.flush();
                                zipOutputStream.closeEntry();
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e32222) {
                                        e32222.printStackTrace();
                                    }
                                }
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                    } catch (IOException e322222) {
                                        e322222.printStackTrace();
                                    }
                                }
                            } catch (Exception e5) {
                                e = e5;
                                fileInputStream = fileInputStream2;
                                e.printStackTrace();
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                a(new File(this.b));
                                if (zipOutputStream != null) {
                                    zipOutputStream.close();
                                }
                                if (fileOutputStream == null) {
                                    fileOutputStream.close();
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                                if (fileInputStream2 != null) {
                                    fileInputStream2.close();
                                }
                                throw th;
                            }
                        } catch (Exception e6) {
                            e = e6;
                            bufferedInputStream = null;
                            e.printStackTrace();
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            a(new File(this.b));
                            if (zipOutputStream != null) {
                                zipOutputStream.close();
                            }
                            if (fileOutputStream == null) {
                                fileOutputStream.close();
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            bufferedInputStream = null;
                            fileInputStream2 = null;
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                            }
                            throw th;
                        }
                        a(new File(this.b));
                        if (zipOutputStream != null) {
                            zipOutputStream.close();
                        }
                        if (fileOutputStream == null) {
                            fileOutputStream.close();
                        }
                    } catch (Exception e7) {
                        e = e7;
                        zipOutputStream2 = zipOutputStream;
                        fileOutputStream2 = fileOutputStream;
                        try {
                            e.printStackTrace();
                            if (zipOutputStream2 != null) {
                                try {
                                    zipOutputStream2.close();
                                } catch (IOException e3222222) {
                                    e3222222.printStackTrace();
                                }
                            }
                            if (fileOutputStream2 == null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e32222222) {
                                    e32222222.printStackTrace();
                                }
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            zipOutputStream = zipOutputStream2;
                            fileOutputStream = fileOutputStream2;
                            if (zipOutputStream != null) {
                                try {
                                    zipOutputStream.close();
                                } catch (IOException e422) {
                                    e422.printStackTrace();
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e4222) {
                                    e4222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        if (zipOutputStream != null) {
                            zipOutputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Exception e8) {
                    e = e8;
                    fileOutputStream2 = fileOutputStream;
                    e.printStackTrace();
                    if (zipOutputStream2 != null) {
                        zipOutputStream2.close();
                    }
                    if (fileOutputStream2 == null) {
                        fileOutputStream2.close();
                    }
                } catch (Throwable th8) {
                    th = th8;
                    zipOutputStream = null;
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e9) {
                e = e9;
                fileOutputStream2 = null;
                e.printStackTrace();
                if (zipOutputStream2 != null) {
                    zipOutputStream2.close();
                }
                if (fileOutputStream2 == null) {
                    fileOutputStream2.close();
                }
            } catch (Throwable th9) {
                th = th9;
                zipOutputStream = null;
                fileOutputStream = null;
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }
    }

    private TbsLogReport(Context context) {
        this.c = context.getApplicationContext();
        e();
    }

    public static TbsLogReport a(Context context) {
        if (b == null) {
            synchronized (TbsLogReport.class) {
                if (b == null) {
                    b = new TbsLogReport(context);
                }
            }
        }
        return b;
    }

    private String e(long j) {
        String str = null;
        try {
            str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j));
        } catch (Exception e) {
        }
        return str;
    }

    private void e() {
        this.d = 0;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 2;
        this.k = "unknown";
        this.l = 0;
        this.m = 2;
        this.n = 0;
        this.o = 0;
        this.p = 1;
        this.a = 0;
        this.q = null;
        this.r = null;
        this.s = 0;
    }

    private String f(long j) {
        return j + "|";
    }

    private String f(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str == null) {
            str = "";
        }
        return stringBuilder.append(str).append("|").toString();
    }

    private JSONArray f() {
        String string = h().getString("tbs_download_upload", null);
        if (string == null) {
            return new JSONArray();
        }
        try {
            return new JSONArray(string);
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    private void g() {
        Editor edit = h().edit();
        edit.remove("tbs_download_upload");
        edit.commit();
    }

    private SharedPreferences h() {
        return this.c.getSharedPreferences("tbs_download_stat", 4);
    }

    private String i(int i) {
        return i + "|";
    }

    public void a() {
        File file;
        FileInputStream fileInputStream;
        Exception e;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = null;
        if (Apn.getApnType(this.c) == 3) {
            String tbsLogFilePath = TbsLog.getTbsLogFilePath();
            if (tbsLogFilePath != null) {
                String b = p.a().b();
                String c = b.c(this.c);
                String f = b.f(this.c);
                byte[] bytes = c.getBytes();
                byte[] bytes2 = f.getBytes();
                try {
                    bytes = p.a().a(bytes);
                    bytes2 = p.a().a(bytes2);
                } catch (Exception e2) {
                }
                String b2 = p.b(bytes);
                String str = x.a(this.c).e() + b2 + "&aid=" + p.b(bytes2);
                Map hashMap = new HashMap();
                hashMap.put(MIME.CONTENT_TYPE, FilePart.DEFAULT_CONTENT_TYPE);
                hashMap.put("Charset", "UTF-8");
                hashMap.put("QUA2", w.a(this.c));
                ByteArrayOutputStream byteArrayOutputStream2;
                try {
                    File file2 = new File(k.a);
                    new ZipHelper(tbsLogFilePath, k.a + "/tbslog_temp.zip").Zip();
                    file = new File(k.a, "tbslog_temp.zip");
                    try {
                        fileInputStream = new FileInputStream(file);
                        try {
                            bytes2 = new byte[8192];
                            byteArrayOutputStream2 = new ByteArrayOutputStream();
                            while (true) {
                                try {
                                    int read = fileInputStream.read(bytes2);
                                    if (read == -1) {
                                        break;
                                    }
                                    byteArrayOutputStream2.write(bytes2, 0, read);
                                } catch (Exception e3) {
                                    e = e3;
                                }
                            }
                            byteArrayOutputStream2.flush();
                            bytes2 = p.a().a(byteArrayOutputStream2.toByteArray());
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e4) {
                                }
                            }
                            if (byteArrayOutputStream2 != null) {
                                try {
                                    byteArrayOutputStream2.close();
                                } catch (Exception e5) {
                                }
                            }
                            if (file != null) {
                                file.delete();
                            }
                        } catch (Exception e6) {
                            e = e6;
                            byteArrayOutputStream2 = null;
                            try {
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (Exception e7) {
                                    }
                                }
                                if (byteArrayOutputStream2 != null) {
                                    try {
                                        byteArrayOutputStream2.close();
                                    } catch (Exception e8) {
                                    }
                                }
                                if (file != null) {
                                    bytes2 = null;
                                } else {
                                    file.delete();
                                    bytes2 = null;
                                }
                                n.a(str + "&ek=" + b, hashMap, bytes2, new as(this), false);
                            } catch (Throwable th2) {
                                th = th2;
                                byteArrayOutputStream = byteArrayOutputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (Exception e9) {
                                    }
                                }
                                if (byteArrayOutputStream != null) {
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (Exception e10) {
                                    }
                                }
                                if (file != null) {
                                    file.delete();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.close();
                            }
                            if (file != null) {
                                file.delete();
                            }
                            throw th;
                        }
                    } catch (Exception e11) {
                        e = e11;
                        byteArrayOutputStream2 = null;
                        fileInputStream = null;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (byteArrayOutputStream2 != null) {
                            byteArrayOutputStream2.close();
                        }
                        if (file != null) {
                            file.delete();
                            bytes2 = null;
                        } else {
                            bytes2 = null;
                        }
                        n.a(str + "&ek=" + b, hashMap, bytes2, new as(this), false);
                    } catch (Throwable th4) {
                        th = th4;
                        fileInputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        if (file != null) {
                            file.delete();
                        }
                        throw th;
                    }
                } catch (Exception e12) {
                    e = e12;
                    byteArrayOutputStream2 = null;
                    file = null;
                    fileInputStream = null;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                    if (file != null) {
                        file.delete();
                        bytes2 = null;
                    } else {
                        bytes2 = null;
                    }
                    n.a(str + "&ek=" + b, hashMap, bytes2, new as(this), false);
                } catch (Throwable th5) {
                    th = th5;
                    file = null;
                    fileInputStream = null;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    if (file != null) {
                        file.delete();
                    }
                    throw th;
                }
                n.a(str + "&ek=" + b, hashMap, bytes2, new as(this), false);
            }
        }
    }

    public void a(int i) {
        this.g = i;
    }

    public void a(int i, String str) {
        if (!(i == 200 || i == 220 || i == 221)) {
            TbsLog.i(TbsDownloader.LOGTAG, "error occured in installation, errorCode:" + i, true);
        }
        h(i);
        a(System.currentTimeMillis());
        e(str);
        QbSdk.j.onInstallFinish(i);
        a(EventType.TYPE_INSTALL);
    }

    public void a(int i, Throwable th) {
        a(th);
        a(i, this.r);
    }

    public void a(long j) {
        this.d = j;
    }

    public void a(EventType eventType) {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i(eventType.a));
        stringBuilder.append(f(b.c(this.c)));
        stringBuilder.append(f(w.a(this.c)));
        stringBuilder.append(i(aj.a().f(this.c)));
        String str2 = Build.MODEL;
        try {
            str = new String(str2.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception e) {
            str = str2;
        }
        stringBuilder.append(f(str));
        str = this.c.getPackageName();
        stringBuilder.append(f(str));
        if ("com.tencent.mm".equals(str)) {
            stringBuilder.append(f(b.a(this.c, "com.tencent.mm.BuildInfo.CLIENT_VERSION")));
        } else {
            stringBuilder.append(i(b.b(this.c)));
        }
        stringBuilder.append(f(e(this.d)));
        stringBuilder.append(f(this.e));
        stringBuilder.append(f(this.f));
        stringBuilder.append(i(this.g));
        stringBuilder.append(i(this.h));
        stringBuilder.append(i(this.i));
        stringBuilder.append(i(this.j));
        stringBuilder.append(f(this.k));
        stringBuilder.append(i(this.l));
        stringBuilder.append(i(this.m));
        stringBuilder.append(f(this.s));
        stringBuilder.append(f(this.n));
        stringBuilder.append(f(this.o));
        stringBuilder.append(i(this.p));
        stringBuilder.append(i(this.a));
        stringBuilder.append(f(this.q));
        stringBuilder.append(f(this.r));
        stringBuilder.append(i(TbsDownloadConfig.getInstance(this.c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0)));
        stringBuilder.append(f(b.f(this.c)));
        stringBuilder.append(f("3.1.0.1034_43100"));
        stringBuilder.append(false);
        SharedPreferences h = h();
        JSONArray f = f();
        f.put(stringBuilder.toString());
        Editor edit = h.edit();
        edit.putString("tbs_download_upload", f.toString());
        edit.commit();
        e();
        new Thread(new ar(this)).start();
    }

    public void a(String str) {
        if (this.e == null) {
            this.e = str;
        } else {
            this.e += h.b + str;
        }
    }

    public void a(Throwable th) {
        if (th == null) {
            this.r = "";
            return;
        }
        String stackTraceString = Log.getStackTraceString(th);
        if (stackTraceString.length() > 1024) {
            stackTraceString = stackTraceString.substring(0, 1024);
        }
        this.r = stackTraceString;
    }

    public void b() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] Run in UIThread, Report delay");
            return;
        }
        synchronized (this) {
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat]");
            JSONArray f = f();
            if (f == null || f.length() == 0) {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] no data");
                return;
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] jsonArray:" + f);
            try {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] response:" + n.a(x.a(this.c).c(), f.toString().getBytes("utf-8"), new at(this), true) + " testcase: " + -1);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void b(int i) {
        this.h = i;
    }

    public void b(int i, String str) {
        h(i);
        a(System.currentTimeMillis());
        e(str);
        a(EventType.TYPE_LOAD);
    }

    public void b(int i, Throwable th) {
        if (th != null) {
            String str = "msg: " + th.getMessage() + "; err: " + th + "; cause: " + Log.getStackTraceString(th.getCause());
            if (str.length() > 1024) {
                str = str.substring(0, 1024);
            }
            this.r = str;
        } else {
            this.r = "NULL";
        }
        b(i, this.r);
    }

    public void b(long j) {
        this.n = j;
    }

    public void b(String str) {
        this.f = str;
    }

    public int c() {
        return this.m;
    }

    public void c(int i) {
        this.i = i;
    }

    public void c(long j) {
        this.o += j;
    }

    public void c(String str) {
        this.k = str;
    }

    public void d() {
        try {
            e();
            Editor edit = h().edit();
            edit.clear();
            edit.commit();
        } catch (Exception e) {
        }
    }

    public void d(int i) {
        this.j = i;
    }

    public void d(long j) {
        this.s += j;
    }

    public void d(String str) {
        h(108);
        this.q = str;
    }

    public void e(int i) {
        this.l = i;
    }

    public void e(String str) {
        if (str != null) {
            if (str.length() > 1024) {
                str = str.substring(0, 1024);
            }
            this.r = str;
        }
    }

    public void f(int i) {
        this.m = i;
    }

    public void g(int i) {
        this.p = i;
    }

    public void h(int i) {
        if (!(i == 100 || i == 110 || i == 120 || i == 111 || i >= 400)) {
            TbsLog.i(TbsDownloader.LOGTAG, "error occured, errorCode:" + i, true);
        }
        if (i == 111) {
            TbsLog.i(TbsDownloader.LOGTAG, "you are not in wifi, downloading stoped", true);
        }
        this.a = i;
    }
}
