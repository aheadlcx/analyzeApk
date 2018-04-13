package cn.xiaochuankeji.tieba.background.h;

import android.os.Build.VERSION;
import android.os.Environment;
import android.text.TextUtils;
import cn.htjyb.c.a.b;
import cn.htjyb.c.e;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.a;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class f {
    private static int a = 10000000;
    private File A;
    private File B;
    private File C;
    private File D;
    private String E;
    private String F;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    private boolean M() {
        File externalStorageDirectory;
        if (F() && G()) {
            externalStorageDirectory = Environment.getExternalStorageDirectory();
        } else {
            Map H = H();
            if (H.isEmpty()) {
                externalStorageDirectory = null;
            } else {
                externalStorageDirectory = (File) H.get("sdCard");
            }
        }
        if (externalStorageDirectory == null || !externalStorageDirectory.exists()) {
            externalStorageDirectory = BaseApplication.getAppContext().getExternalCacheDir();
            if (externalStorageDirectory != null) {
                if (!externalStorageDirectory.exists()) {
                    externalStorageDirectory.mkdirs();
                }
                this.b = externalStorageDirectory.getAbsolutePath();
            } else {
                externalStorageDirectory = BaseApplication.getAppContext().getCacheDir();
                if (externalStorageDirectory != null) {
                    if (!externalStorageDirectory.exists()) {
                        externalStorageDirectory.mkdirs();
                    }
                    this.b = externalStorageDirectory.getAbsolutePath();
                }
            }
        } else {
            this.b = externalStorageDirectory.getPath();
            if (!this.b.endsWith("/")) {
                this.b += "/";
            }
            externalStorageDirectory = new File(this.b + BaseApplication.getAppContext().getPackageName());
            if (externalStorageDirectory.exists() && !externalStorageDirectory.isDirectory()) {
                externalStorageDirectory.delete();
            }
            this.b += BaseApplication.getAppContext().getPackageName() + "/";
            externalStorageDirectory = new File(this.b);
            if (externalStorageDirectory.exists() || externalStorageDirectory.mkdirs()) {
                e.a(this.b);
            } else {
                this.b = null;
            }
        }
        if (this.b != null) {
            return true;
        }
        return false;
    }

    private boolean N() {
        return this.b != null || M();
    }

    public String a() {
        if (N()) {
            return this.b;
        }
        return null;
    }

    public String b() {
        if (this.j == null && t() != null) {
            this.j = t() + "originavatar/";
            new File(this.j).mkdirs();
        }
        return this.j;
    }

    public String c() {
        if (this.j == null && t() != null) {
            this.j = t() + "avatar/";
            new File(this.j).mkdirs();
        }
        return this.j;
    }

    public String d() {
        if (this.k == null && t() != null) {
            this.k = t() + "pic228/";
            new File(this.k).mkdirs();
        }
        return this.k;
    }

    public String e() {
        if (this.l == null && t() != null) {
            this.l = t() + "pic480/";
            new File(this.l).mkdirs();
        }
        return this.l;
    }

    public String f() {
        if (this.m == null && t() != null) {
            this.m = t() + "pic600/";
            new File(this.m).mkdirs();
        }
        return this.m;
    }

    public String g() {
        if (this.n == null && t() != null) {
            this.n = t() + "picLarge/";
            new File(this.n).mkdirs();
        }
        return this.n;
    }

    public String h() {
        if (this.o == null && t() != null) {
            this.o = t() + "picGif/";
            new File(this.o).mkdirs();
        }
        return this.o;
    }

    public String i() {
        if (this.p == null && t() != null) {
            this.p = t() + "picVideo/";
            new File(this.p).mkdirs();
        }
        return this.p;
    }

    public String j() {
        if (this.q == null && t() != null) {
            this.q = t() + "ugcvideocache/";
            new File(this.q).mkdirs();
        }
        return this.q;
    }

    public String k() {
        if (this.r == null && t() != null) {
            this.r = t() + "picUrl/";
            new File(this.r).mkdirs();
        }
        return this.r;
    }

    public String l() {
        if (this.s == null && t() != null) {
            this.s = t() + "picShare/";
            new File(this.s).mkdirs();
        }
        if (!b.c(this.s)) {
            try {
                org.apache.commons.io.b.e(new File(this.s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.s;
    }

    public String m() {
        if (this.t == null && t() != null) {
            this.t = t() + "topicCover/";
            new File(this.t).mkdirs();
        }
        return this.t;
    }

    public String n() {
        if (this.u == null && t() != null) {
            this.u = t() + "topicCover280/";
            new File(this.u).mkdirs();
        }
        return this.u;
    }

    public String o() {
        if (this.z == null && t() != null) {
            this.z = t() + "chatImg/";
            new File(this.z).mkdirs();
        }
        return this.z;
    }

    public String p() {
        if (this.y == null && t() != null) {
            this.y = t() + "topicCategoryCover/";
            new File(this.y).mkdirs();
        }
        return this.y;
    }

    public String q() {
        return t() + "temp";
    }

    public String r() {
        if (this.v == null) {
            File filesDir = BaseApplication.getAppContext().getFilesDir();
            if (filesDir != null) {
                com.izuiyou.a.a.b.c("filesDir: " + filesDir.getPath());
                this.v = filesDir.getPath() + "/data/";
                new File(this.v).mkdirs();
            }
        }
        return this.v;
    }

    public String s() {
        if (this.w == null && N()) {
            this.w = this.b + "app/";
            new File(this.w).mkdirs();
        }
        return this.w;
    }

    public String t() {
        if (this.c == null && N()) {
            this.c = this.b + "pic/";
            new File(this.c).mkdirs();
        }
        return this.c;
    }

    public String u() {
        if (this.d == null && N()) {
            this.d = this.b + "ugcvideo/";
            new File(this.d).mkdirs();
        }
        return this.d;
    }

    public String v() {
        if (this.h == null && N()) {
            this.h = this.b + "tree/";
            new File(this.h).mkdirs();
        }
        return this.h;
    }

    public String w() {
        if (this.i == null && N()) {
            this.i = this.b + "voice/";
            new File(this.i).mkdirs();
        }
        return this.i;
    }

    public File x() {
        if (this.D == null && N()) {
            this.D = new File(this.b, "chat/");
            if (!this.D.exists()) {
                this.D.mkdirs();
            } else if (!this.D.isDirectory()) {
                this.D.delete();
            }
        }
        return this.D;
    }

    public String y() {
        if (this.e == null && N()) {
            this.e = u() + "stickerlist/";
            new File(this.e).mkdirs();
        }
        return this.e;
    }

    public String z() {
        if (this.f == null && N()) {
            this.f = this.b + "danmaku/";
            File file = new File(this.f);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!file.exists()) {
                if (BaseApplication.getAppContext().getExternalCacheDir() != null) {
                    this.f = BaseApplication.getAppContext().getExternalCacheDir().getAbsolutePath();
                } else {
                    this.f = BaseApplication.getAppContext().getCacheDir().getAbsolutePath();
                }
            }
        }
        return this.f;
    }

    public String A() {
        if (this.g == null && N()) {
            this.g = this.b + "picerror/";
            new File(this.g).mkdirs();
        }
        return this.g;
    }

    public String B() {
        if (this.E == null && N()) {
            this.E = this.b + "tmp/";
            new File(this.E).mkdirs();
        }
        return this.E;
    }

    public File C() {
        if (!N()) {
            return null;
        }
        File file = new File(this.b + "chatmsg/tbmsgcache/");
        return file.exists() ? file : null;
    }

    public String D() {
        if (VERSION.SDK_INT >= 21) {
            BaseApplication.getAppContext().getExternalMediaDirs();
        }
        File file;
        if (TextUtils.isEmpty(this.x) && N()) {
            String string = a.a().getString("key_download_path", "");
            if (TextUtils.isEmpty(string)) {
                this.x = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/zuiyou/";
                file = new File(this.x);
                if (!(file.exists() || file.mkdirs())) {
                    return null;
                }
            }
            File file2 = new File(string);
            if (!file2.exists() && !file2.mkdirs()) {
                return null;
            }
            this.x = string;
        } else {
            file = new File(this.x);
            if (!(file.exists() || file.mkdirs())) {
                return null;
            }
        }
        return this.x;
    }

    public void E() {
        this.x = null;
        D();
    }

    public static boolean F() {
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean G() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }

    public static Map<String, File> H() {
        File file;
        Scanner scanner;
        String nextLine;
        Map<String, File> hashMap = new HashMap(10);
        List<String> arrayList = new ArrayList(10);
        List arrayList2 = new ArrayList(10);
        arrayList.add("/mnt/sdcard");
        arrayList2.add("/mnt/sdcard");
        try {
            file = new File("/proc/mounts");
            if (file.exists()) {
                scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    nextLine = scanner.nextLine();
                    if (nextLine.startsWith("/dev/block/vold/")) {
                        nextLine = nextLine.split(" ")[1];
                        if (!nextLine.equals("/mnt/sdcard")) {
                            arrayList.add(nextLine);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            file = new File("/system/etc/vold.fstab");
            if (file.exists()) {
                scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    nextLine = scanner.nextLine();
                    if (nextLine.startsWith("dev_mount")) {
                        nextLine = nextLine.split(" ")[2];
                        if (nextLine.contains(":")) {
                            nextLine = nextLine.substring(0, nextLine.indexOf(":"));
                        }
                        if (!nextLine.equals("/mnt/sdcard")) {
                            arrayList2.add(nextLine);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        int i = 0;
        while (i < arrayList.size()) {
            if (!arrayList2.contains((String) arrayList.get(i))) {
                int i2 = i - 1;
                arrayList.remove(i);
                i = i2;
            }
            i++;
        }
        arrayList2.clear();
        List arrayList3 = new ArrayList(10);
        for (String nextLine2 : arrayList) {
            File file2 = new File(nextLine2);
            if (file2.exists() && file2.isDirectory() && file2.canWrite()) {
                File[] listFiles = file2.listFiles();
                nextLine2 = "[";
                if (listFiles != null) {
                    int length = listFiles.length;
                    i = 0;
                    while (i < length) {
                        File file3 = listFiles[i];
                        i++;
                        nextLine2 = nextLine2 + file3.getName().hashCode() + ":" + file3.length() + ", ";
                    }
                }
                String str = nextLine2 + "]";
                if (!arrayList3.contains(str)) {
                    Object obj = "sdCard_" + hashMap.size();
                    if (hashMap.size() == 0) {
                        obj = "sdCard";
                    } else if (hashMap.size() == 1) {
                        obj = "externalSdCard";
                    }
                    arrayList3.add(str);
                    hashMap.put(obj, file2);
                }
            }
        }
        arrayList.clear();
        return hashMap;
    }

    public File I() {
        if (this.A == null) {
            this.A = BaseApplication.getAppContext().getExternalFilesDir("stickers");
            if (this.A == null) {
                this.A = BaseApplication.getAppContext().getExternalCacheDir();
            }
            if (!this.A.exists()) {
                this.A.mkdirs();
            }
        }
        return this.A;
    }

    public File J() {
        if (this.B == null) {
            this.B = BaseApplication.getAppContext().getExternalFilesDir("magic");
            if (this.B == null) {
                this.B = BaseApplication.getAppContext().getExternalCacheDir();
            }
            if (!this.B.exists()) {
                this.B.mkdirs();
            }
        }
        return this.B;
    }

    public File K() {
        if (this.C == null) {
            this.C = BaseApplication.getAppContext().getExternalFilesDir("font");
            if (this.C == null) {
                this.C = BaseApplication.getAppContext().getExternalCacheDir();
            }
            if (!this.C.exists()) {
                this.C.mkdirs();
            }
        }
        return this.C;
    }

    public String L() {
        if (this.F == null && N()) {
            this.F = this.b + "log/";
            File file = new File(this.F);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return this.F;
    }
}
