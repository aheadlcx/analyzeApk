package com.loc;

import android.content.Context;
import android.text.TextUtils;
import dalvik.system.DexFile;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.List;

public final class ay {

    public static class a {
        public static bc a(af afVar, String str) {
            List b = afVar.b(bc.b(str), bc.class);
            return (b == null || b.size() <= 0) ? null : (bc) b.get(0);
        }

        public static List<bc> a(af afVar, String str, String str2) {
            return afVar.b(bc.b(str, str2), bc.class);
        }
    }

    static String a(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "pngex";
    }

    static String a(Context context, af afVar, s sVar) {
        List b = afVar.b(bc.b(sVar.a(), "copy"), bc.class);
        if (b == null || b.size() == 0) {
            return null;
        }
        bd.a(b);
        int i = 0;
        while (i < b.size()) {
            bc bcVar = (bc) b.get(i);
            String a = bcVar.a();
            if (bd.a(afVar, a, a(context, a), sVar)) {
                try {
                    a(context, afVar, sVar, a(context, bcVar.a()), bcVar.e());
                    return bcVar.e();
                } catch (Throwable th) {
                    w.a(th, "FileManager", "loadAvailableD");
                }
            } else {
                c(context, afVar, bcVar.a());
                i++;
            }
        }
        return null;
    }

    public static String a(Context context, String str) {
        return a(context) + File.separator + str;
    }

    static String a(Context context, String str, String str2) {
        return p.b(str + str2 + n.q(context)) + ".jar";
    }

    static String a(String str) {
        return str + ".o";
    }

    public static void a(Context context, af afVar, s sVar, String str, String str2) throws Throwable {
        Throwable th;
        Closeable closeable;
        Closeable closeable2;
        Closeable fileInputStream;
        Closeable randomAccessFile;
        try {
            File file;
            String a = sVar.a();
            String a2 = a(context, a, sVar.b());
            a(context, afVar, a2);
            fileInputStream = new FileInputStream(new File(str));
            try {
                fileInputStream.read(new byte[32]);
                file = new File(b(context, a, sVar.b()));
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = null;
                try {
                    bd.a(fileInputStream);
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
                try {
                    bd.a(randomAccessFile);
                } catch (Throwable th32) {
                    th32.printStackTrace();
                }
                throw th;
            }
            try {
                Object obj = new byte[1024];
                int i = 0;
                while (true) {
                    int read = fileInputStream.read(obj);
                    if (read <= 0) {
                        break;
                    }
                    if (read == 1024) {
                        randomAccessFile.seek((long) i);
                        randomAccessFile.write(obj);
                    } else {
                        Object obj2 = new byte[read];
                        System.arraycopy(obj, 0, obj2, 0, read);
                        randomAccessFile.seek((long) i);
                        randomAccessFile.write(obj2);
                    }
                    i += read;
                }
                Object a3 = new bc$a(a2, p.a(file.getAbsolutePath()), a, sVar.b(), str2).a("used").a();
                afVar.a(a3, bc.b(a3.a()));
                try {
                    bd.a(fileInputStream);
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
                try {
                    bd.a(randomAccessFile);
                } catch (Throwable th42) {
                    th42.printStackTrace();
                }
            } catch (Throwable th5) {
                th42 = th5;
                bd.a(fileInputStream);
                bd.a(randomAccessFile);
                throw th42;
            }
        } catch (Throwable th6) {
            th42 = th6;
            randomAccessFile = null;
            fileInputStream = null;
            bd.a(fileInputStream);
            bd.a(randomAccessFile);
            throw th42;
        }
    }

    static void a(Context context, af afVar, String str) {
        c(context, afVar, str);
        c(context, afVar, a(str));
    }

    public static void a(Context context, s sVar) {
        try {
            String b = b(context, sVar.a(), sVar.b());
            if (!TextUtils.isEmpty(b)) {
                File file = new File(b);
                File parentFile = file.getParentFile();
                if (file.exists()) {
                    String a = a(context, a(file.getName()));
                    DexFile loadDex = DexFile.loadDex(b, a, 0);
                    if (loadDex != null) {
                        loadDex.close();
                        Object obj = null;
                        af afVar = new af(context, bb.b());
                        bc a2 = a.a(afVar, file.getName());
                        if (a2 != null) {
                            obj = a2.e();
                        }
                        File file2 = new File(a);
                        if (!TextUtils.isEmpty(obj) && file2.exists()) {
                            a = p.a(a);
                            String name = file2.getName();
                            afVar.a(new bc$a(name, a, sVar.a(), sVar.b(), obj).a("useod").a(), bc.b(name));
                        }
                    }
                } else if (parentFile != null && parentFile.exists()) {
                    c(context, sVar.a(), sVar.b());
                }
            }
        } catch (Throwable th) {
            w.a(th, "BaseClassLoader", "getInstanceByThread()");
        }
    }

    static void a(af afVar, Context context, String str) {
        List<bc> a = a.a(afVar, str, "used");
        if (a != null && a.size() > 0) {
            for (bc bcVar : a) {
                if (bcVar != null && bcVar.c().equals(str)) {
                    a(context, afVar, bcVar.a());
                    List b = afVar.b(bc.a(str, bcVar.e()), bc.class);
                    if (b != null && b.size() > 0) {
                        Object obj = (bc) b.get(0);
                        obj.c("errorstatus");
                        afVar.a(obj, bc.b(obj.a()));
                        File file = new File(a(context, obj.a()));
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
            }
        }
    }

    static String b(Context context, String str, String str2) {
        return a(context, a(context, str, str2));
    }

    static void b(Context context, String str) {
        af afVar = new af(context, bb.b());
        List a = a.a(afVar, str, "copy");
        bd.a(a);
        if (a != null && a.size() > 1) {
            int size = a.size();
            for (int i = 1; i < size; i++) {
                c(context, afVar, ((bc) a.get(i)).a());
            }
        }
    }

    private static void c(Context context, af afVar, String str) {
        File file = new File(a(context, str));
        if (file.exists()) {
            file.delete();
        }
        afVar.a(bc.b(str), bc.class);
    }

    static void c(Context context, String str, String str2) {
        z.b().submit(new dh(context, str, str2));
    }
}
