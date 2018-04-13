package com.baidu.location.d;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.storage.StorageManager;
import com.baidu.location.f;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class i {
    public static String a = (a().b() + "/baidu/tempdata");
    private static volatile i c = null;
    private final List<h> b = new ArrayList();
    private Context d;

    private i(Context context) {
        this.d = context;
    }

    public static i a() {
        if (c == null) {
            synchronized (i.class) {
                if (c == null) {
                    c = new i(f.getServiceContext());
                }
            }
        }
        return c;
    }

    private boolean a(String str) {
        boolean createNewFile;
        Exception e;
        try {
            File file = new File(str, "test.0");
            if (file.exists()) {
                file.delete();
            }
            createNewFile = file.createNewFile();
            try {
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return createNewFile;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            createNewFile = false;
            e = exception;
            e.printStackTrace();
            return createNewFile;
        }
        return createNewFile;
    }

    @SuppressLint({"NewApi"})
    @TargetApi(14)
    private List<h> d() {
        try {
            StorageManager storageManager = (StorageManager) this.d.getSystemService("storage");
            Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
            Method method2 = storageManager.getClass().getMethod("getVolumeState", new Class[]{String.class});
            Class cls = Class.forName("android.os.storage.StorageVolume");
            Method method3 = cls.getMethod("isRemovable", new Class[0]);
            Method method4 = cls.getMethod("getPath", new Class[0]);
            Object[] objArr = (Object[]) method.invoke(storageManager, new Object[0]);
            if (objArr != null) {
                for (Object obj : objArr) {
                    Object obj2;
                    String str = (String) method4.invoke(obj2, new Object[0]);
                    if (str != null && str.length() > 0) {
                        if ("mounted".equals(method2.invoke(storageManager, new Object[]{str}))) {
                            obj2 = !((Boolean) method3.invoke(obj2, new Object[0])).booleanValue() ? 1 : null;
                            if (VERSION.SDK_INT <= 19 && a(str)) {
                                this.b.add(new h(str, obj2 == null, obj2 != null ? "Internal Storage" : "External Storage"));
                            }
                        }
                    }
                }
                if (VERSION.SDK_INT >= 19) {
                    File[] externalFilesDirs = this.d.getExternalFilesDirs(null);
                    Collection arrayList = new ArrayList();
                    arrayList.addAll(this.b);
                    int i = 0;
                    while (i < externalFilesDirs.length && externalFilesDirs[i] != null) {
                        Object obj3;
                        String absolutePath = externalFilesDirs[i].getAbsolutePath();
                        for (h a : this.b) {
                            if (absolutePath.startsWith(a.a())) {
                                obj3 = 1;
                                break;
                            }
                        }
                        obj3 = null;
                        if (obj3 == null && absolutePath.indexOf(this.d.getPackageName()) != -1) {
                            arrayList.add(new h(absolutePath, true, "External Storage"));
                        }
                        i++;
                    }
                    this.b.clear();
                    this.b.addAll(arrayList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error e2) {
            e2.printStackTrace();
        }
        return this.b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.baidu.location.d.h> e() {
        /*
        r9 = this;
        r2 = 0;
        r3 = new java.util.ArrayList;
        r3.<init>();
        r4 = new java.util.ArrayList;
        r4.<init>();
        r0 = new java.io.File;	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r1 = "/proc/mounts";
        r0.<init>(r1);	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r1 = r0.exists();	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        if (r1 == 0) goto L_0x0058;
    L_0x0018:
        r1 = new java.util.Scanner;	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
    L_0x001d:
        r0 = r1.hasNext();	 Catch:{ Exception -> 0x0049, all -> 0x0117 }
        if (r0 == 0) goto L_0x0055;
    L_0x0023:
        r0 = r1.nextLine();	 Catch:{ Exception -> 0x0049, all -> 0x0117 }
        r5 = "/dev/block/vold/";
        r5 = r0.startsWith(r5);	 Catch:{ Exception -> 0x0049, all -> 0x0117 }
        if (r5 == 0) goto L_0x001d;
    L_0x002f:
        r5 = 9;
        r6 = 32;
        r0 = r0.replace(r5, r6);	 Catch:{ Exception -> 0x0049, all -> 0x0117 }
        r5 = " ";
        r0 = r0.split(r5);	 Catch:{ Exception -> 0x0049, all -> 0x0117 }
        if (r0 == 0) goto L_0x001d;
    L_0x003f:
        r5 = r0.length;	 Catch:{ Exception -> 0x0049, all -> 0x0117 }
        if (r5 <= 0) goto L_0x001d;
    L_0x0042:
        r5 = 1;
        r0 = r0[r5];	 Catch:{ Exception -> 0x0049, all -> 0x0117 }
        r3.add(r0);	 Catch:{ Exception -> 0x0049, all -> 0x0117 }
        goto L_0x001d;
    L_0x0049:
        r0 = move-exception;
    L_0x004a:
        r0.printStackTrace();	 Catch:{ all -> 0x011a }
        if (r1 == 0) goto L_0x0052;
    L_0x004f:
        r1.close();
    L_0x0052:
        r0 = r9.b;
        return r0;
    L_0x0055:
        r1.close();	 Catch:{ Exception -> 0x0049, all -> 0x0117 }
    L_0x0058:
        r0 = new java.io.File;	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r1 = "/system/etc/vold.fstab";
        r0.<init>(r1);	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r1 = r0.exists();	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        if (r1 == 0) goto L_0x00b4;
    L_0x0065:
        r1 = new java.util.Scanner;	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
    L_0x006a:
        r0 = r1.hasNext();	 Catch:{ Exception -> 0x0049, all -> 0x00a9 }
        if (r0 == 0) goto L_0x00b1;
    L_0x0070:
        r0 = r1.nextLine();	 Catch:{ Exception -> 0x0049, all -> 0x00a9 }
        r5 = "dev_mount";
        r5 = r0.startsWith(r5);	 Catch:{ Exception -> 0x0049, all -> 0x00a9 }
        if (r5 == 0) goto L_0x006a;
    L_0x007c:
        r5 = 9;
        r6 = 32;
        r0 = r0.replace(r5, r6);	 Catch:{ Exception -> 0x0049, all -> 0x00a9 }
        r5 = " ";
        r0 = r0.split(r5);	 Catch:{ Exception -> 0x0049, all -> 0x00a9 }
        if (r0 == 0) goto L_0x006a;
    L_0x008c:
        r5 = r0.length;	 Catch:{ Exception -> 0x0049, all -> 0x00a9 }
        if (r5 <= 0) goto L_0x006a;
    L_0x008f:
        r5 = 2;
        r0 = r0[r5];	 Catch:{ Exception -> 0x0049, all -> 0x00a9 }
        r5 = ":";
        r5 = r0.contains(r5);	 Catch:{ Exception -> 0x0049, all -> 0x00a9 }
        if (r5 == 0) goto L_0x00a5;
    L_0x009a:
        r5 = 0;
        r6 = ":";
        r6 = r0.indexOf(r6);	 Catch:{ Exception -> 0x0049, all -> 0x00a9 }
        r0 = r0.substring(r5, r6);	 Catch:{ Exception -> 0x0049, all -> 0x00a9 }
    L_0x00a5:
        r4.add(r0);	 Catch:{ Exception -> 0x0049, all -> 0x00a9 }
        goto L_0x006a;
    L_0x00a9:
        r0 = move-exception;
        r2 = r1;
    L_0x00ab:
        if (r2 == 0) goto L_0x00b0;
    L_0x00ad:
        r2.close();
    L_0x00b0:
        throw r0;
    L_0x00b1:
        r1.close();	 Catch:{ Exception -> 0x0049, all -> 0x00a9 }
    L_0x00b4:
        r0 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r1 = r0.getAbsolutePath();	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r0 = r9.b;	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r5 = new com.baidu.location.d.h;	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r6 = 0;
        r7 = "Auto";
        r5.<init>(r1, r6, r7);	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r0.add(r5);	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r3 = r3.iterator();	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
    L_0x00cd:
        r0 = r3.hasNext();	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        if (r0 == 0) goto L_0x010e;
    L_0x00d3:
        r0 = r3.next();	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r5 = r4.contains(r0);	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        if (r5 == 0) goto L_0x00cd;
    L_0x00df:
        r5 = r0.equals(r1);	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        if (r5 != 0) goto L_0x00cd;
    L_0x00e5:
        r5 = new java.io.File;	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r5.<init>(r0);	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r6 = r5.exists();	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        if (r6 == 0) goto L_0x00cd;
    L_0x00f0:
        r6 = r5.isDirectory();	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        if (r6 == 0) goto L_0x00cd;
    L_0x00f6:
        r5 = r5.canWrite();	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        if (r5 == 0) goto L_0x00cd;
    L_0x00fc:
        r5 = r9.b;	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r6 = new com.baidu.location.d.h;	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r7 = 0;
        r8 = "Auto";
        r6.<init>(r0, r7, r8);	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        r5.add(r6);	 Catch:{ Exception -> 0x010a, all -> 0x0115 }
        goto L_0x00cd;
    L_0x010a:
        r0 = move-exception;
        r1 = r2;
        goto L_0x004a;
    L_0x010e:
        if (r2 == 0) goto L_0x0052;
    L_0x0110:
        r2.close();
        goto L_0x0052;
    L_0x0115:
        r0 = move-exception;
        goto L_0x00ab;
    L_0x0117:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00ab;
    L_0x011a:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00ab;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.i.e():java.util.List<com.baidu.location.d.h>");
    }

    public String b() {
        List c = c();
        return (c == null || c.size() == 0) ? null : ((h) c.get(0)).a();
    }

    public List<h> c() {
        List<h> list = null;
        if (VERSION.SDK_INT >= 14) {
            list = d();
        }
        return (list == null || list.size() <= 0) ? e() : list;
    }
}
