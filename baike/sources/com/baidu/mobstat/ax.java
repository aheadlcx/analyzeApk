package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.jar.JarFile;

class ax {
    private static volatile DexClassLoader a;
    private static volatile boolean b = false;

    public static Class<?> a(Context context, String str) {
        DexClassLoader a = a(context);
        return a == null ? null : a.loadClass(str);
    }

    private static synchronized DexClassLoader a(Context context) {
        DexClassLoader dexClassLoader = null;
        synchronized (ax.class) {
            if (a != null) {
                dexClassLoader = a;
            } else {
                File fileStreamPath = context.getFileStreamPath(".remote.jar");
                if (fileStreamPath == null || fileStreamPath.isFile()) {
                    if (!b(context, fileStreamPath.getAbsolutePath())) {
                        bd.a("remote jar version lower than min limit, need delete");
                        if (fileStreamPath.isFile()) {
                            fileStreamPath.delete();
                        }
                    } else if (c(context, fileStreamPath.getAbsolutePath())) {
                        try {
                            a = new DexClassLoader(fileStreamPath.getAbsolutePath(), context.getDir("outdex", 0).getAbsolutePath(), null, context.getClassLoader());
                        } catch (Throwable e) {
                            bd.a(e);
                        }
                        dexClassLoader = a;
                    } else {
                        bd.a("remote jar md5 is not right, need delete");
                        if (fileStreamPath.isFile()) {
                            fileStreamPath.delete();
                        }
                    }
                }
            }
        }
        return dexClassLoader;
    }

    private static boolean b(Context context, String str) {
        Object b = b(str);
        if (TextUtils.isEmpty(b)) {
            return false;
        }
        int intValue;
        try {
            intValue = Integer.valueOf(b).intValue();
        } catch (Throwable e) {
            bd.b(e);
            intValue = 0;
        }
        if (intValue >= 4) {
            return true;
        }
        return false;
    }

    public static synchronized void a(Context context, l lVar) {
        synchronized (ax.class) {
            if (!b) {
                if (!de.n(context)) {
                    bd.a("isWifiAvailable = false, will not to update");
                } else if (lVar.a(context)) {
                    bd.a("can start update config");
                    new ay(context, lVar).start();
                    b = true;
                } else {
                    bd.a("check time, will not to update");
                }
            }
        }
    }

    private static boolean c(Context context, String str) {
        Object a = cz.a(new File(str));
        bd.a("remote.jar local file digest value digest = " + a);
        if (TextUtils.isEmpty(a)) {
            bd.a("remote.jar local file digest value fail");
            return false;
        }
        Object b = b(str);
        bd.a("remote.jar local file digest value version = " + b);
        if (TextUtils.isEmpty(b)) {
            return false;
        }
        CharSequence d = d(context, b);
        bd.a("remote.jar config digest value remoteJarMd5 = " + d);
        if (!TextUtils.isEmpty(d)) {
            return a.equals(d);
        }
        bd.a("remote.jar config digest value lost");
        return false;
    }

    private static String d(Context context, String str) {
        return az.a(context).c(str);
    }

    private static String b(String str) {
        Throwable e;
        JarFile jarFile = null;
        try {
            File file = new File(str);
            if (file.exists()) {
                bd.b("file size: " + file.length());
            }
            JarFile jarFile2 = new JarFile(str);
            try {
                String value = jarFile2.getManifest().getMainAttributes().getValue("Plugin-Version");
                if (jarFile2 == null) {
                    return value;
                }
                try {
                    jarFile2.close();
                    return value;
                } catch (Exception e2) {
                    return value;
                }
            } catch (Exception e3) {
                e = e3;
                jarFile = jarFile2;
                try {
                    bd.a(e);
                    bd.a("baidu remote sdk is not ready" + str);
                    if (jarFile != null) {
                        try {
                            jarFile.close();
                        } catch (Exception e4) {
                        }
                    }
                    return "";
                } catch (Throwable th) {
                    e = th;
                    if (jarFile != null) {
                        try {
                            jarFile.close();
                        } catch (Exception e5) {
                        }
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                jarFile = jarFile2;
                if (jarFile != null) {
                    jarFile.close();
                }
                throw e;
            }
        } catch (Exception e6) {
            e = e6;
            bd.a(e);
            bd.a("baidu remote sdk is not ready" + str);
            if (jarFile != null) {
                jarFile.close();
            }
            return "";
        }
    }
}
