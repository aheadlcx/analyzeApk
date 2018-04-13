package com.ishumei.d;

import android.content.Context;
import android.telephony.CellLocation;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import cn.v6.sixrooms.ui.phone.BundMobileDialogActivity;
import com.ishumei.b.d;
import java.lang.reflect.Method;
import java.util.List;
import net.vidageek.a.b.c;
import net.vidageek.a.c.b;

public class l {
    private static l c = null;
    private Object a = null;
    private Context b = null;

    private l() {
        try {
            if (d.a != null) {
                this.b = d.a;
                this.a = new c().a(d.a).a().a(com.ishumei.f.d.g("989a8bac868c8b9a92ac9a8d89969c9a")).a(new Object[]{com.ishumei.f.d.g("8f9790919a")});
            }
        } catch (Exception e) {
        }
    }

    private int a(Object obj, String str, Object... objArr) {
        Class cls = obj.getClass();
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return ((Integer) declaredMethod.invoke(obj, objArr)).intValue();
    }

    private CellLocation a(List<?> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int i;
        CellLocation cellLocation;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        CellLocation cellLocation2 = null;
        int i2 = 0;
        int i3 = 0;
        CellLocation cellLocation3 = null;
        while (i2 < list.size()) {
            CellLocation cellLocation4;
            Object obj = list.get(i2);
            if (obj != null) {
                try {
                    Class loadClass = systemClassLoader.loadClass(com.ishumei.f.d.g("9e919b8d90969bd18b9a939a8f97909186d1bc9a9393b6919990b88c92"));
                    Class loadClass2 = systemClassLoader.loadClass(com.ishumei.f.d.g("9e919b8d90969bd18b9a939a8f97909186d1bc9a9393b6919990a89c9b929e"));
                    Class loadClass3 = systemClassLoader.loadClass(com.ishumei.f.d.g("9e919b8d90969bd18b9a939a8f97909186d1bc9a9393b6919990b38b9a"));
                    Class loadClass4 = systemClassLoader.loadClass(com.ishumei.f.d.g("9e919b8d90969bd18b9a939a8f97909186d1bc9a9393b6919990bc9b929e"));
                    i = loadClass.isInstance(obj) ? 1 : loadClass2.isInstance(obj) ? 2 : loadClass3.isInstance(obj) ? 3 : loadClass4.isInstance(obj) ? 4 : 0;
                    if (i > 0) {
                        Object obj2 = null;
                        if (i == 1) {
                            try {
                                obj2 = loadClass.cast(obj);
                            } catch (Exception e) {
                                i3 = i;
                                cellLocation4 = cellLocation2;
                            }
                        } else if (i == 2) {
                            obj2 = loadClass2.cast(obj);
                        } else if (i == 3) {
                            obj2 = loadClass3.cast(obj);
                        } else if (i == 4) {
                            obj2 = loadClass4.cast(obj);
                        }
                        obj = b(obj2, com.ishumei.f.d.g("989a8bbc9a9393b69b9a918b968b86"), new Object[0]);
                        if (obj != null) {
                            if (i != 4) {
                                int a;
                                if (i != 3) {
                                    i3 = a(obj, com.ishumei.f.d.g("989a8bb39e9c"), new Object[0]);
                                    a = a(obj, com.ishumei.f.d.g("989a8bbc969b"), new Object[0]);
                                    cellLocation4 = new GsmCellLocation();
                                    cellLocation4.setLacAndCid(i3, a);
                                    cellLocation = cellLocation3;
                                    cellLocation3 = cellLocation4;
                                    break;
                                }
                                i3 = a(obj, com.ishumei.f.d.g("989a8bab9e9c"), new Object[0]);
                                a = a(obj, com.ishumei.f.d.g("989a8bbc96"), new Object[0]);
                                cellLocation4 = new GsmCellLocation();
                                try {
                                    cellLocation4.setLacAndCid(i3, a);
                                    cellLocation = cellLocation3;
                                    cellLocation3 = cellLocation4;
                                    break;
                                } catch (Exception e2) {
                                    i3 = i;
                                }
                            } else {
                                cellLocation = new CdmaCellLocation();
                                try {
                                    cellLocation.setCellLocationData(a(obj, com.ishumei.f.d.g("989a8bbd9e8c9a8c8b9e8b969091b69b"), new Object[0]), a(obj, com.ishumei.f.d.g("989a8bb39e8b968b8a9b9a"), new Object[0]), a(obj, com.ishumei.f.d.g("989a8bb3909198968b8a9b9a"), new Object[0]), a(obj, com.ishumei.f.d.g("989a8bac868c8b9a92b69b"), new Object[0]), a(obj, com.ishumei.f.d.g("989a8bb19a8b88908d94b69b"), new Object[0]));
                                    cellLocation3 = cellLocation2;
                                    break;
                                } catch (Exception e3) {
                                    cellLocation3 = cellLocation;
                                    cellLocation4 = cellLocation2;
                                    i3 = i;
                                }
                            }
                        } else {
                            i3 = i;
                            cellLocation4 = cellLocation2;
                        }
                    } else {
                        i3 = i;
                        cellLocation4 = cellLocation2;
                    }
                } catch (Exception e4) {
                    cellLocation4 = cellLocation2;
                }
            } else {
                cellLocation4 = cellLocation2;
            }
            i2++;
            cellLocation2 = cellLocation4;
        }
        i = i3;
        cellLocation = cellLocation3;
        cellLocation3 = cellLocation2;
        return i != 4 ? cellLocation3 : cellLocation;
    }

    public static l a() {
        if (c == null) {
            synchronized (l.class) {
                if (c == null) {
                    c = new l();
                }
            }
        }
        return c;
    }

    private Object a(String str) {
        Object obj = null;
        try {
            if (this.b != null) {
                obj = new c().a(this.b.getApplicationContext()).a().a(com.ishumei.f.d.g("989a8bac868c8b9a92ac9a8d89969c9a")).a(new Object[]{str});
            }
        } catch (Exception e) {
        }
        return obj;
    }

    private Object b(Object obj, String str, Object... objArr) {
        Class cls = obj.getClass();
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return declaredMethod.invoke(obj, objArr);
    }

    private int j() {
        try {
            Class.forName(com.ishumei.f.d.g("9e919b8d90969bd18b9a939a8f97909186d1b2ac9692ab9a939a8f97909186b29e919e989a8d"));
            return 1;
        } catch (Exception e) {
            try {
                Class.forName(com.ishumei.f.d.g("9e919b8d90969bd18b9a939a8f97909186d1ab9a939a8f97909186b29e919e989a8dcd"));
                return 2;
            } catch (SecurityException e2) {
                return BundMobileDialogActivity.IS_SUCCEED;
            } catch (Exception e3) {
                return 0;
            }
        }
    }

    private Object k() {
        switch (j()) {
            case 0:
                return a(com.ishumei.f.d.g("8f9790919a"));
            case 1:
                return a(com.ishumei.f.d.g("8f9790919aa0928c9692"));
            case 2:
                return a(com.ishumei.f.d.g("8f9790919acd"));
            default:
                return null;
        }
    }

    private Class<?> l() {
        String g;
        Class<?> cls = null;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        switch (j()) {
            case 0:
                g = com.ishumei.f.d.g("9e919b8d90969bd18b9a939a8f97909186d1ab9a939a8f97909186b29e919e989a8d");
                break;
            case 1:
                g = com.ishumei.f.d.g("9e919b8d90969bd18b9a939a8f97909186d1b2ac9692ab9a939a8f97909186b29e919e989a8d");
                break;
            case 2:
                g = com.ishumei.f.d.g("9e919b8d90969bd18b9a939a8f97909186d1ab9a939a8f97909186b29e919e989a8dcd");
                break;
            default:
                g = cls;
                break;
        }
        try {
            cls = systemClassLoader.loadClass(g);
        } catch (Exception e) {
        }
        return cls;
    }

    public String a(int i) {
        String str = "";
        try {
            com.ishumei.f.d.g("b1b0afbaadb2");
            if (this.a != null) {
                return (String) new c().a(this.a).a().a(com.ishumei.f.d.g("989a8bbb9a89969c9ab69b")).a(new Object[]{Integer.valueOf(i)});
            }
        } catch (SecurityException e) {
            return "";
        } catch (b e2) {
            return "";
        } catch (Exception e3) {
        }
        return str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String b() {
        /*
        r3 = this;
        r1 = "";
        r0 = "b1b0afbaadb2";
        com.ishumei.f.d.g(r0);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r3.a;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        if (r0 == 0) goto L_0x003c;
    L_0x000b:
        r0 = new net.vidageek.a.b.c;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0.<init>();	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r2 = r3.a;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a(r2);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a();	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r2 = "989a8bb396919aceb18a929d9a8d";
        r2 = com.ishumei.f.d.g(r2);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a(r2);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a();	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = (java.lang.String) r0;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        if (r0 != 0) goto L_0x002e;
    L_0x002c:
        r0 = "";
    L_0x002e:
        return r0;
    L_0x002f:
        r0 = move-exception;
        r0 = "";
        goto L_0x002e;
    L_0x0033:
        r0 = move-exception;
        r0 = "";
        goto L_0x002e;
    L_0x0037:
        r0 = move-exception;
        r0 = r1;
        goto L_0x002e;
    L_0x003a:
        r1 = move-exception;
        goto L_0x002e;
    L_0x003c:
        r0 = r1;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.d.l.b():java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String c() {
        /*
        r3 = this;
        r1 = "";
        r0 = "b1b0afbaadb2";
        com.ishumei.f.d.g(r0);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r3.a;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        if (r0 == 0) goto L_0x003c;
    L_0x000b:
        r0 = new net.vidageek.a.b.c;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0.<init>();	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r2 = r3.a;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a(r2);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a();	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r2 = "989a8bbb9a89969c9ab69b";
        r2 = com.ishumei.f.d.g(r2);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a(r2);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a();	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = (java.lang.String) r0;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        if (r0 != 0) goto L_0x002e;
    L_0x002c:
        r0 = "";
    L_0x002e:
        return r0;
    L_0x002f:
        r0 = move-exception;
        r0 = "";
        goto L_0x002e;
    L_0x0033:
        r0 = move-exception;
        r0 = "";
        goto L_0x002e;
    L_0x0037:
        r0 = move-exception;
        r0 = r1;
        goto L_0x002e;
    L_0x003a:
        r1 = move-exception;
        goto L_0x002e;
    L_0x003c:
        r0 = r1;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.d.l.c():java.lang.String");
    }

    public String d() {
        String str = "";
        try {
            if (this.a == null) {
                return str;
            }
            String str2 = (String) new c().a(this.a).a().a(com.ishumei.f.d.g("989a8bac9692b08f9a8d9e8b908d")).a();
            if (str2 != null) {
                try {
                    if (!str2.isEmpty()) {
                        return str2;
                    }
                } catch (Exception e) {
                    return str2;
                }
            }
            str2 = (String) new c().a(this.a).a().a(com.ishumei.f.d.g("989a8bb19a8b88908d94b08f9a8d9e8b908db19e929a")).a();
            return str2 == null ? "" : str2;
        } catch (Exception e2) {
            return str;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String e() {
        /*
        r3 = this;
        r1 = "";
        r0 = "b1b0afbaadb2";
        com.ishumei.f.d.g(r0);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r3.a;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        if (r0 == 0) goto L_0x003c;
    L_0x000b:
        r0 = new net.vidageek.a.b.c;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0.<init>();	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r2 = r3.a;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a(r2);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a();	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r2 = "989a8bac8a9d8c9c8d969d9a8db69b";
        r2 = com.ishumei.f.d.g(r2);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a(r2);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a();	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = (java.lang.String) r0;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        if (r0 != 0) goto L_0x002e;
    L_0x002c:
        r0 = "";
    L_0x002e:
        return r0;
    L_0x002f:
        r0 = move-exception;
        r0 = "";
        goto L_0x002e;
    L_0x0033:
        r0 = move-exception;
        r0 = "";
        goto L_0x002e;
    L_0x0037:
        r0 = move-exception;
        r0 = r1;
        goto L_0x002e;
    L_0x003a:
        r1 = move-exception;
        goto L_0x002e;
    L_0x003c:
        r0 = r1;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.d.l.e():java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String f() {
        /*
        r3 = this;
        r1 = "";
        r0 = "b1b0afbaadb2";
        com.ishumei.f.d.g(r0);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r3.a;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        if (r0 == 0) goto L_0x003c;
    L_0x000b:
        r0 = new net.vidageek.a.b.c;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0.<init>();	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r2 = r3.a;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a(r2);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a();	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r2 = "989a8bac9692ac9a8d969e93b18a929d9a8d";
        r2 = com.ishumei.f.d.g(r2);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a(r2);	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = r0.a();	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        r0 = (java.lang.String) r0;	 Catch:{ SecurityException -> 0x002f, b -> 0x0033, Exception -> 0x0037 }
        if (r0 != 0) goto L_0x002e;
    L_0x002c:
        r0 = "";
    L_0x002e:
        return r0;
    L_0x002f:
        r0 = move-exception;
        r0 = "";
        goto L_0x002e;
    L_0x0033:
        r0 = move-exception;
        r0 = "";
        goto L_0x002e;
    L_0x0037:
        r0 = move-exception;
        r0 = r1;
        goto L_0x002e;
    L_0x003a:
        r1 = move-exception;
        goto L_0x002e;
    L_0x003c:
        r0 = r1;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.d.l.f():java.lang.String");
    }

    public int g() {
        try {
            if (this.a != null) {
                return ((Integer) new c().a(this.a).a().a(com.ishumei.f.d.g("989a8bac9692ac8b9e8b9a")).a()).intValue();
            }
        } catch (SecurityException e) {
            return BundMobileDialogActivity.IS_SUCCEED;
        } catch (b e2) {
            return BundMobileDialogActivity.IS_SUCCEED;
        } catch (Exception e3) {
        }
        return 0;
    }

    public CellLocation h() {
        Throwable th;
        List list;
        try {
            Object k = k();
            if (k == null) {
                return null;
            }
            CellLocation cellLocation;
            Class l = l();
            if (l.isInstance(k)) {
                Object b;
                Object cast = l.cast(k);
                String g = com.ishumei.f.d.g("989a8bbc9a9393b3909c9e8b969091");
                try {
                    b = b(cast, g, new Object[0]);
                } catch (Exception e) {
                    b = null;
                }
                if (b == null) {
                    try {
                        k = b(cast, g, Integer.valueOf(0));
                        if (k == null) {
                            try {
                                k = b(cast, g, Integer.valueOf(1));
                            } catch (Throwable e2) {
                                Throwable th2 = e2;
                                b = k;
                                th = th2;
                                com.ishumei.f.c.a(th);
                                k = b;
                                if (k == null) {
                                    try {
                                        k = b(cast, com.ishumei.f.d.g("989a8bbc9a9393b3909c9e8b969091b89a92969196"), Integer.valueOf(1));
                                    } catch (Exception e3) {
                                    }
                                }
                                if (k == null) {
                                    try {
                                        list = (List) b(cast, com.ishumei.f.d.g("989a8bbe9393bc9a9393b6919990"), new Object[0]);
                                    } catch (Exception e4) {
                                        list = null;
                                    }
                                    k = a(list);
                                }
                                if (k != null) {
                                    cellLocation = (CellLocation) k;
                                    return cellLocation;
                                }
                                cellLocation = null;
                                return cellLocation;
                            }
                        }
                    } catch (Exception e5) {
                        th = e5;
                        com.ishumei.f.c.a(th);
                        k = b;
                        if (k == null) {
                            k = b(cast, com.ishumei.f.d.g("989a8bbc9a9393b3909c9e8b969091b89a92969196"), Integer.valueOf(1));
                        }
                        if (k == null) {
                            list = (List) b(cast, com.ishumei.f.d.g("989a8bbe9393bc9a9393b6919990"), new Object[0]);
                            k = a(list);
                        }
                        if (k != null) {
                            cellLocation = (CellLocation) k;
                            return cellLocation;
                        }
                        cellLocation = null;
                        return cellLocation;
                    }
                    if (k == null) {
                        k = b(cast, com.ishumei.f.d.g("989a8bbc9a9393b3909c9e8b969091b89a92969196"), Integer.valueOf(1));
                    }
                    if (k == null) {
                        list = (List) b(cast, com.ishumei.f.d.g("989a8bbe9393bc9a9393b6919990"), new Object[0]);
                        k = a(list);
                    }
                }
                k = b;
                if (k == null) {
                    k = b(cast, com.ishumei.f.d.g("989a8bbc9a9393b3909c9e8b969091b89a92969196"), Integer.valueOf(1));
                }
                if (k == null) {
                    list = (List) b(cast, com.ishumei.f.d.g("989a8bbe9393bc9a9393b6919990"), new Object[0]);
                    k = a(list);
                }
            } else {
                k = null;
            }
            if (k != null) {
                cellLocation = (CellLocation) k;
                return cellLocation;
            }
            cellLocation = null;
            return cellLocation;
        } catch (Exception e6) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap<java.lang.String, java.lang.String> i() {
        /*
        r5 = this;
        r2 = new java.util.HashMap;
        r2.<init>();
        r0 = "";
        r1 = "b1b0afbaadb2";
        r1 = com.ishumei.f.d.g(r1);	 Catch:{ b -> 0x00dd, Exception -> 0x00db }
        r0 = r5.h();	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        if (r0 != 0) goto L_0x0036;
    L_0x0013:
        r3 = r5.a;	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        if (r3 == 0) goto L_0x0036;
    L_0x0017:
        r0 = new net.vidageek.a.b.c;	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r0.<init>();	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r3 = r5.a;	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r0 = r0.a(r3);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r0 = r0.a();	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r3 = "989a8bbc9a9393b3909c9e8b969091";
        r3 = com.ishumei.f.d.g(r3);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r0 = r0.a(r3);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r0 = r0.a();	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r0 = (android.telephony.CellLocation) r0;	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
    L_0x0036:
        if (r0 == 0) goto L_0x006b;
    L_0x0038:
        r3 = r0 instanceof android.telephony.gsm.GsmCellLocation;	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        if (r3 == 0) goto L_0x006c;
    L_0x003c:
        r3 = "type";
        r4 = "988c92";
        r4 = com.ishumei.f.d.g(r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r2.put(r3, r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r0 = (android.telephony.gsm.GsmCellLocation) r0;	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r3 = "9c969b";
        r3 = com.ishumei.f.d.g(r3);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r4 = r0.getCid();	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r2.put(r3, r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r3 = "939e9c";
        r3 = com.ishumei.f.d.g(r3);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r0 = r0.getLac();	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r2.put(r3, r0);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
    L_0x006b:
        return r2;
    L_0x006c:
        r3 = r0 instanceof android.telephony.cdma.CdmaCellLocation;	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        if (r3 == 0) goto L_0x006b;
    L_0x0070:
        r3 = "type";
        r4 = "9c9b929e";
        r4 = com.ishumei.f.d.g(r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r2.put(r3, r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r0 = (android.telephony.cdma.CdmaCellLocation) r0;	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r3 = "9d969b";
        r3 = com.ishumei.f.d.g(r3);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r4 = r0.getBaseStationId();	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r2.put(r3, r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r3 = "91969b";
        r3 = com.ishumei.f.d.g(r3);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r4 = r0.getNetworkId();	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r2.put(r3, r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r3 = "8c969b";
        r3 = com.ishumei.f.d.g(r3);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r4 = r0.getSystemId();	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r2.put(r3, r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r3 = "939e8b";
        r3 = com.ishumei.f.d.g(r3);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r4 = r0.getBaseStationLatitude();	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r2.put(r3, r4);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r3 = "939198";
        r3 = com.ishumei.f.d.g(r3);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r0 = r0.getBaseStationLongitude();	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        r2.put(r3, r0);	 Catch:{ b -> 0x00d3, Exception -> 0x00db }
        goto L_0x006b;
    L_0x00d3:
        r0 = move-exception;
        r0 = r1;
    L_0x00d5:
        r1 = "type";
        r2.put(r1, r0);
        goto L_0x006b;
    L_0x00db:
        r0 = move-exception;
        goto L_0x006b;
    L_0x00dd:
        r1 = move-exception;
        goto L_0x00d5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.d.l.i():java.util.HashMap<java.lang.String, java.lang.String>");
    }
}
