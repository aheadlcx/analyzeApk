package com.loc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.TextUtils;
import com.loc.ak.a;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class af {
    private static Map<Class<? extends ae>, ae> d = new HashMap();
    private ak a;
    private SQLiteDatabase b;
    private ae c;

    public af(Context context, ae aeVar) {
        try {
            this.a = new ak(context.getApplicationContext(), aeVar.a(), aeVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.c = aeVar;
    }

    public af(Context context, ae aeVar, String str) {
        try {
            boolean equals = "mounted".equals(Environment.getExternalStorageState());
            if (!TextUtils.isEmpty(str) && equals) {
                context = new a(context.getApplicationContext(), str);
            }
            this.a = new ak(context, aeVar.a(), aeVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.c = aeVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.content.ContentValues a(java.lang.Object r8, com.loc.ag r9) {
        /*
        r3 = new android.content.ContentValues;
        r3.<init>();
        r0 = r8.getClass();
        r1 = r9.b();
        r4 = a(r0, r1);
        r5 = r4.length;
        r0 = 0;
        r2 = r0;
    L_0x0014:
        if (r2 >= r5) goto L_0x00a4;
    L_0x0016:
        r1 = r4[r2];
        r0 = 1;
        r1.setAccessible(r0);
        r0 = com.loc.ah.class;
        r0 = r1.getAnnotation(r0);
        if (r0 == 0) goto L_0x002d;
    L_0x0024:
        r0 = (com.loc.ah) r0;
        r6 = r0.b();
        switch(r6) {
            case 1: goto L_0x0046;
            case 2: goto L_0x0031;
            case 3: goto L_0x0084;
            case 4: goto L_0x0066;
            case 5: goto L_0x0056;
            case 6: goto L_0x0076;
            case 7: goto L_0x0094;
            default: goto L_0x002d;
        };
    L_0x002d:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0014;
    L_0x0031:
        r1 = r1.getInt(r8);	 Catch:{ IllegalAccessException -> 0x0041 }
        r0 = r0.a();	 Catch:{ IllegalAccessException -> 0x0041 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ IllegalAccessException -> 0x0041 }
        r3.put(r0, r1);	 Catch:{ IllegalAccessException -> 0x0041 }
        goto L_0x002d;
    L_0x0041:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x002d;
    L_0x0046:
        r1 = r1.getShort(r8);	 Catch:{ IllegalAccessException -> 0x0041 }
        r0 = r0.a();	 Catch:{ IllegalAccessException -> 0x0041 }
        r1 = java.lang.Short.valueOf(r1);	 Catch:{ IllegalAccessException -> 0x0041 }
        r3.put(r0, r1);	 Catch:{ IllegalAccessException -> 0x0041 }
        goto L_0x002d;
    L_0x0056:
        r6 = r1.getLong(r8);	 Catch:{ IllegalAccessException -> 0x0041 }
        r0 = r0.a();	 Catch:{ IllegalAccessException -> 0x0041 }
        r1 = java.lang.Long.valueOf(r6);	 Catch:{ IllegalAccessException -> 0x0041 }
        r3.put(r0, r1);	 Catch:{ IllegalAccessException -> 0x0041 }
        goto L_0x002d;
    L_0x0066:
        r6 = r1.getDouble(r8);	 Catch:{ IllegalAccessException -> 0x0041 }
        r0 = r0.a();	 Catch:{ IllegalAccessException -> 0x0041 }
        r1 = java.lang.Double.valueOf(r6);	 Catch:{ IllegalAccessException -> 0x0041 }
        r3.put(r0, r1);	 Catch:{ IllegalAccessException -> 0x0041 }
        goto L_0x002d;
    L_0x0076:
        r1 = r1.get(r8);	 Catch:{ IllegalAccessException -> 0x0041 }
        r1 = (java.lang.String) r1;	 Catch:{ IllegalAccessException -> 0x0041 }
        r0 = r0.a();	 Catch:{ IllegalAccessException -> 0x0041 }
        r3.put(r0, r1);	 Catch:{ IllegalAccessException -> 0x0041 }
        goto L_0x002d;
    L_0x0084:
        r1 = r1.getFloat(r8);	 Catch:{ IllegalAccessException -> 0x0041 }
        r0 = r0.a();	 Catch:{ IllegalAccessException -> 0x0041 }
        r1 = java.lang.Float.valueOf(r1);	 Catch:{ IllegalAccessException -> 0x0041 }
        r3.put(r0, r1);	 Catch:{ IllegalAccessException -> 0x0041 }
        goto L_0x002d;
    L_0x0094:
        r1 = r1.get(r8);	 Catch:{ IllegalAccessException -> 0x0041 }
        r1 = (byte[]) r1;	 Catch:{ IllegalAccessException -> 0x0041 }
        r1 = (byte[]) r1;	 Catch:{ IllegalAccessException -> 0x0041 }
        r0 = r0.a();	 Catch:{ IllegalAccessException -> 0x0041 }
        r3.put(r0, r1);	 Catch:{ IllegalAccessException -> 0x0041 }
        goto L_0x002d;
    L_0x00a4:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.af.a(java.lang.Object, com.loc.ag):android.content.ContentValues");
    }

    private SQLiteDatabase a() {
        try {
            if (this.b == null || this.b.isReadOnly()) {
                if (this.b != null) {
                    this.b.close();
                }
                this.b = this.a.getWritableDatabase();
            }
        } catch (Throwable th) {
            w.a(th, "DBOperation", "getWriteDatabase");
        }
        return this.b;
    }

    private SQLiteDatabase a(boolean z) {
        try {
            if (this.b == null) {
                this.b = this.a.getReadableDatabase();
            }
        } catch (Throwable th) {
            if (z) {
                th.printStackTrace();
            } else {
                w.a(th, "DBOperation", "getReadAbleDataBase");
            }
        }
        return this.b;
    }

    public static synchronized ae a(Class<? extends ae> cls) throws IllegalAccessException, InstantiationException {
        ae aeVar;
        synchronized (af.class) {
            if (d.get(cls) == null) {
                d.put(cls, cls.newInstance());
            }
            aeVar = (ae) d.get(cls);
        }
        return aeVar;
    }

    private static <T> T a(Cursor cursor, Class<T> cls, ag agVar) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field[] a = a((Class) cls, agVar.b());
        Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        declaredConstructor.setAccessible(true);
        T newInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : a) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(ah.class);
            if (annotation != null) {
                ah ahVar = (ah) annotation;
                int b = ahVar.b();
                int columnIndex = cursor.getColumnIndex(ahVar.a());
                switch (b) {
                    case 1:
                        field.set(newInstance, Short.valueOf(cursor.getShort(columnIndex)));
                        break;
                    case 2:
                        field.set(newInstance, Integer.valueOf(cursor.getInt(columnIndex)));
                        break;
                    case 3:
                        field.set(newInstance, Float.valueOf(cursor.getFloat(columnIndex)));
                        break;
                    case 4:
                        field.set(newInstance, Double.valueOf(cursor.getDouble(columnIndex)));
                        break;
                    case 5:
                        field.set(newInstance, Long.valueOf(cursor.getLong(columnIndex)));
                        break;
                    case 6:
                        field.set(newInstance, cursor.getString(columnIndex));
                        break;
                    case 7:
                        field.set(newInstance, cursor.getBlob(columnIndex));
                        break;
                    default:
                        break;
                }
            }
        }
        return newInstance;
    }

    private static <T> String a(ag agVar) {
        return agVar == null ? null : agVar.a();
    }

    public static String a(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (String str : map.keySet()) {
            if (obj != null) {
                stringBuilder.append(str).append(" = '").append((String) map.get(str)).append("'");
                obj = null;
            } else {
                stringBuilder.append(" and ").append(str).append(" = '").append((String) map.get(str)).append("'");
            }
        }
        return stringBuilder.toString();
    }

    private static Field[] a(Class<?> cls, boolean z) {
        return cls == null ? null : z ? cls.getSuperclass().getDeclaredFields() : cls.getDeclaredFields();
    }

    private static <T> ag b(Class<T> cls) {
        Annotation annotation = cls.getAnnotation(ag.class);
        return (annotation != null ? 1 : null) == null ? null : (ag) annotation;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> java.util.List<T> a(java.lang.String r13, java.lang.Class<T> r14, boolean r15) {
        /*
        r12 = this;
        r9 = 0;
        r10 = r12.c;
        monitor-enter(r10);
        r8 = new java.util.ArrayList;	 Catch:{ all -> 0x0094 }
        r8.<init>();	 Catch:{ all -> 0x0094 }
        r11 = b(r14);	 Catch:{ all -> 0x0094 }
        r1 = a(r11);	 Catch:{ all -> 0x0094 }
        r0 = r12.b;	 Catch:{ all -> 0x0094 }
        if (r0 != 0) goto L_0x001b;
    L_0x0015:
        r0 = r12.a(r15);	 Catch:{ all -> 0x0094 }
        r12.b = r0;	 Catch:{ all -> 0x0094 }
    L_0x001b:
        r0 = r12.b;	 Catch:{ all -> 0x0094 }
        if (r0 == 0) goto L_0x0027;
    L_0x001f:
        r0 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0094 }
        if (r0 != 0) goto L_0x0027;
    L_0x0025:
        if (r13 != 0) goto L_0x002a;
    L_0x0027:
        monitor-exit(r10);	 Catch:{ all -> 0x0094 }
        r0 = r8;
    L_0x0029:
        return r0;
    L_0x002a:
        r0 = r12.b;	 Catch:{ Throwable -> 0x0106, all -> 0x0080 }
        r2 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r3 = r13;
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0106, all -> 0x0080 }
        if (r1 != 0) goto L_0x0054;
    L_0x0038:
        r0 = r12.b;	 Catch:{ Throwable -> 0x0062 }
        r0.close();	 Catch:{ Throwable -> 0x0062 }
        r0 = 0;
        r12.b = r0;	 Catch:{ Throwable -> 0x0062 }
        if (r1 == 0) goto L_0x0045;
    L_0x0042:
        r1.close();	 Catch:{ Throwable -> 0x00c3 }
    L_0x0045:
        r0 = r12.b;	 Catch:{ Throwable -> 0x00cf }
        if (r0 == 0) goto L_0x0051;
    L_0x0049:
        r0 = r12.b;	 Catch:{ Throwable -> 0x00cf }
        r0.close();	 Catch:{ Throwable -> 0x00cf }
        r0 = 0;
        r12.b = r0;	 Catch:{ Throwable -> 0x00cf }
    L_0x0051:
        monitor-exit(r10);	 Catch:{ all -> 0x0094 }
        r0 = r8;
        goto L_0x0029;
    L_0x0054:
        r0 = r1.moveToNext();	 Catch:{ Throwable -> 0x0062 }
        if (r0 == 0) goto L_0x00db;
    L_0x005a:
        r0 = a(r1, r14, r11);	 Catch:{ Throwable -> 0x0062 }
        r8.add(r0);	 Catch:{ Throwable -> 0x0062 }
        goto L_0x0054;
    L_0x0062:
        r0 = move-exception;
    L_0x0063:
        if (r15 != 0) goto L_0x006c;
    L_0x0065:
        r2 = "DataBase";
        r3 = "searchListData";
        com.loc.w.a(r0, r2, r3);	 Catch:{ all -> 0x0103 }
    L_0x006c:
        if (r1 == 0) goto L_0x0071;
    L_0x006e:
        r1.close();	 Catch:{ Throwable -> 0x00ad }
    L_0x0071:
        r0 = r12.b;	 Catch:{ Throwable -> 0x00b8 }
        if (r0 == 0) goto L_0x007d;
    L_0x0075:
        r0 = r12.b;	 Catch:{ Throwable -> 0x00b8 }
        r0.close();	 Catch:{ Throwable -> 0x00b8 }
        r0 = 0;
        r12.b = r0;	 Catch:{ Throwable -> 0x00b8 }
    L_0x007d:
        monitor-exit(r10);	 Catch:{ all -> 0x0094 }
        r0 = r8;
        goto L_0x0029;
    L_0x0080:
        r0 = move-exception;
        r1 = r9;
    L_0x0082:
        if (r1 == 0) goto L_0x0087;
    L_0x0084:
        r1.close();	 Catch:{ Throwable -> 0x0097 }
    L_0x0087:
        r1 = r12.b;	 Catch:{ Throwable -> 0x00a2 }
        if (r1 == 0) goto L_0x0093;
    L_0x008b:
        r1 = r12.b;	 Catch:{ Throwable -> 0x00a2 }
        r1.close();	 Catch:{ Throwable -> 0x00a2 }
        r1 = 0;
        r12.b = r1;	 Catch:{ Throwable -> 0x00a2 }
    L_0x0093:
        throw r0;	 Catch:{ all -> 0x0094 }
    L_0x0094:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x0097:
        r1 = move-exception;
        if (r15 != 0) goto L_0x0087;
    L_0x009a:
        r2 = "DataBase";
        r3 = "searchListData";
        com.loc.w.a(r1, r2, r3);	 Catch:{ all -> 0x0094 }
        goto L_0x0087;
    L_0x00a2:
        r1 = move-exception;
        if (r15 != 0) goto L_0x0093;
    L_0x00a5:
        r2 = "DataBase";
        r3 = "searchListData";
        com.loc.w.a(r1, r2, r3);	 Catch:{ all -> 0x0094 }
        goto L_0x0093;
    L_0x00ad:
        r0 = move-exception;
        if (r15 != 0) goto L_0x0071;
    L_0x00b0:
        r1 = "DataBase";
        r2 = "searchListData";
        com.loc.w.a(r0, r1, r2);	 Catch:{ all -> 0x0094 }
        goto L_0x0071;
    L_0x00b8:
        r0 = move-exception;
        if (r15 != 0) goto L_0x007d;
    L_0x00bb:
        r1 = "DataBase";
        r2 = "searchListData";
        com.loc.w.a(r0, r1, r2);	 Catch:{ all -> 0x0094 }
        goto L_0x007d;
    L_0x00c3:
        r0 = move-exception;
        if (r15 != 0) goto L_0x0045;
    L_0x00c6:
        r1 = "DataBase";
        r2 = "searchListData";
        com.loc.w.a(r0, r1, r2);	 Catch:{ all -> 0x0094 }
        goto L_0x0045;
    L_0x00cf:
        r0 = move-exception;
        if (r15 != 0) goto L_0x0051;
    L_0x00d2:
        r1 = "DataBase";
        r2 = "searchListData";
        com.loc.w.a(r0, r1, r2);	 Catch:{ all -> 0x0094 }
        goto L_0x0051;
    L_0x00db:
        if (r1 == 0) goto L_0x00e0;
    L_0x00dd:
        r1.close();	 Catch:{ Throwable -> 0x00f8 }
    L_0x00e0:
        r0 = r12.b;	 Catch:{ Throwable -> 0x00ed }
        if (r0 == 0) goto L_0x007d;
    L_0x00e4:
        r0 = r12.b;	 Catch:{ Throwable -> 0x00ed }
        r0.close();	 Catch:{ Throwable -> 0x00ed }
        r0 = 0;
        r12.b = r0;	 Catch:{ Throwable -> 0x00ed }
        goto L_0x007d;
    L_0x00ed:
        r0 = move-exception;
        if (r15 != 0) goto L_0x007d;
    L_0x00f0:
        r1 = "DataBase";
        r2 = "searchListData";
        com.loc.w.a(r0, r1, r2);	 Catch:{ all -> 0x0094 }
        goto L_0x007d;
    L_0x00f8:
        r0 = move-exception;
        if (r15 != 0) goto L_0x00e0;
    L_0x00fb:
        r1 = "DataBase";
        r2 = "searchListData";
        com.loc.w.a(r0, r1, r2);	 Catch:{ all -> 0x0094 }
        goto L_0x00e0;
    L_0x0103:
        r0 = move-exception;
        goto L_0x0082;
    L_0x0106:
        r0 = move-exception;
        r1 = r9;
        goto L_0x0063;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.af.a(java.lang.String, java.lang.Class, boolean):java.util.List<T>");
    }

    public final <T> void a(T t) {
        b((Object) t);
    }

    public final void a(Object obj, String str) {
        synchronized (this.c) {
            List a = a(str, obj.getClass(), false);
            if (a == null || a.size() == 0) {
                b(obj);
            } else {
                a(str, obj, false);
            }
        }
    }

    public final <T> void a(String str, Class<T> cls) {
        synchronized (this.c) {
            Object a = a(b((Class) cls));
            if (TextUtils.isEmpty(a)) {
                return;
            }
            this.b = a();
            if (this.b == null) {
                return;
            }
            try {
                this.b.delete(a, str, null);
                if (this.b != null) {
                    this.b.close();
                    this.b = null;
                }
            } catch (Throwable th) {
                if (this.b != null) {
                    this.b.close();
                    this.b = null;
                }
            }
        }
    }

    public final <T> void a(String str, Object obj) {
        a(str, obj, false);
    }

    public final <T> void a(String str, Object obj, boolean z) {
        synchronized (this.c) {
            if (obj == null) {
                return;
            }
            ag b = b(obj.getClass());
            Object a = a(b);
            if (TextUtils.isEmpty(a)) {
                return;
            }
            ContentValues a2 = a(obj, b);
            if (a2 == null) {
                return;
            }
            this.b = a();
            if (this.b == null) {
                return;
            }
            try {
                this.b.update(a, a2, str, null);
                if (this.b != null) {
                    this.b.close();
                    this.b = null;
                }
            } catch (Throwable th) {
                if (this.b != null) {
                    this.b.close();
                    this.b = null;
                }
            }
        }
    }

    public final <T> List<T> b(String str, Class<T> cls) {
        return a(str, (Class) cls, false);
    }

    public final <T> void b(T t) {
        synchronized (this.c) {
            this.b = a();
            if (this.b == null) {
                return;
            }
            try {
                SQLiteDatabase sQLiteDatabase = this.b;
                ag b = b(t.getClass());
                Object a = a(b);
                if (!(TextUtils.isEmpty(a) || t == null || sQLiteDatabase == null)) {
                    ContentValues a2 = a((Object) t, b);
                    if (a2 != null) {
                        sQLiteDatabase.insert(a, null, a2);
                    }
                }
                if (this.b != null) {
                    this.b.close();
                    this.b = null;
                }
            } catch (Throwable th) {
                if (this.b != null) {
                    this.b.close();
                    this.b = null;
                }
            }
        }
    }
}
