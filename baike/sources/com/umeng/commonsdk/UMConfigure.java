package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.mobstat.Config;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.statistics.b;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.d;
import com.umeng.commonsdk.utils.UMUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UMConfigure {
    public static final int DEVICE_TYPE_BOX = 2;
    public static final int DEVICE_TYPE_PHONE = 1;
    private static boolean a = false;

    private static Class<?> a(String str) {
        Class<?> cls = null;
        try {
            cls = Class.forName(str);
        } catch (ClassNotFoundException e) {
        }
        return cls;
    }

    private static Object a(Class<?> cls) {
        Object newInstance;
        Constructor constructor = null;
        if (cls != null) {
            Constructor declaredConstructor;
            try {
                declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            } catch (NoSuchMethodException e) {
                declaredConstructor = constructor;
            }
            if (declaredConstructor != null) {
                declaredConstructor.setAccessible(true);
                try {
                    newInstance = declaredConstructor.newInstance(new Object[0]);
                } catch (IllegalArgumentException e2) {
                } catch (InstantiationException e3) {
                } catch (IllegalAccessException e4) {
                } catch (InvocationTargetException e5) {
                }
            }
        }
        return newInstance;
    }

    private static Method a(Class<?> cls, String str, Class<?>[] clsArr) {
        Method method = null;
        if (cls != null) {
            try {
                method = cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
            }
            if (method != null) {
                method.setAccessible(true);
            }
        }
        return method;
    }

    private static void a(Method method, Object obj, Object[] objArr) {
        if (method != null && obj != null) {
            try {
                method.invoke(obj, objArr);
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e2) {
            } catch (InvocationTargetException e3) {
            }
        }
    }

    private static void a(Class<?> cls, String str, String str2) {
        if (cls != null) {
            try {
                cls.getField(str).set(str, str2);
            } catch (Exception e) {
            }
        }
    }

    private static void a(Class<?> cls, String str, boolean z) {
        if (cls != null) {
            try {
                cls.getField(str).set(str, Boolean.valueOf(z));
            } catch (Exception e) {
            }
        }
    }

    private static void a() {
        StringBuffer stringBuffer = new StringBuffer();
        if (a("com.umeng.analytics.vismode.V") != null) {
            stringBuffer.append("v");
        } else if (a("com.umeng.analytics.MobclickAgent") != null) {
            stringBuffer.append("a");
        }
        if (a("com.umeng.visual.UMVisualAgent") != null) {
            stringBuffer.append("x");
        }
        if (a("com.umeng.message.PushAgent") != null) {
            stringBuffer.append("p");
        }
        if (a("com.umeng.socialize.UMShareAPI") != null) {
            stringBuffer.append("s");
        }
        if (a("com.umeng.error.UMError") != null) {
            stringBuffer.append(Config.SESSTION_END_TIME);
        }
        stringBuffer.append("i");
        if (!(d.b == 1 || a("com.umeng.commonsdk.internal.UMOplus") == null)) {
            stringBuffer.append(Config.OS);
        }
        if (!TextUtils.isEmpty(stringBuffer)) {
            b.a = stringBuffer.toString();
            UMSLEnvelopeBuild.module = stringBuffer.toString();
        }
    }

    public static void init(Context context, int i, String str) {
        init(context, null, null, i, str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void init(android.content.Context r9, java.lang.String r10, java.lang.String r11, int r12, java.lang.String r13) {
        /*
        r8 = 1;
        r0 = a;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r0 == 0) goto L_0x0026;
    L_0x0005:
        r0 = "UMConfigure";
        r1 = "common version is 1.4.1";
        android.util.Log.i(r0, r1);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r0 = "UMConfigure";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r1.<init>();	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r2 = "common type is ";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r2 = com.umeng.commonsdk.statistics.d.b;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        android.util.Log.i(r0, r1);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
    L_0x0026:
        if (r9 != 0) goto L_0x0034;
    L_0x0028:
        r0 = a;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r0 == 0) goto L_0x0033;
    L_0x002c:
        r0 = "UMConfigure";
        r1 = "context is null !!!";
        android.util.Log.e(r0, r1);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
    L_0x0033:
        return;
    L_0x0034:
        r1 = r9.getApplicationContext();	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r0 = android.text.TextUtils.isEmpty(r10);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r0 == 0) goto L_0x0042;
    L_0x003e:
        r10 = com.umeng.commonsdk.utils.UMUtils.getAppkeyByXML(r1);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
    L_0x0042:
        r0 = android.text.TextUtils.isEmpty(r11);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r0 == 0) goto L_0x0334;
    L_0x0048:
        r0 = com.umeng.commonsdk.utils.UMUtils.getChannelByXML(r1);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
    L_0x004c:
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r2 == 0) goto L_0x0054;
    L_0x0052:
        r0 = "Unknown";
    L_0x0054:
        com.umeng.commonsdk.utils.UMUtils.setChannel(r1, r0);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r2 = a;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r2 == 0) goto L_0x0073;
    L_0x005b:
        r2 = "UMConfigure";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r3.<init>();	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r4 = "channel is ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        android.util.Log.i(r2, r3);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
    L_0x0073:
        a();	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r2 = "com.umeng.message.PushAgent";
        r2 = java.lang.Class.forName(r2);	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        if (r2 == 0) goto L_0x00c2;
    L_0x007e:
        r3 = "getInstance";
        r4 = 1;
        r4 = new java.lang.Class[r4];	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        r5 = 0;
        r6 = android.content.Context.class;
        r4[r5] = r6;	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        r3 = r2.getDeclaredMethod(r3, r4);	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        if (r3 == 0) goto L_0x00c2;
    L_0x008e:
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        r5 = 0;
        r4[r5] = r1;	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        r3 = r3.invoke(r2, r4);	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        if (r3 == 0) goto L_0x00c2;
    L_0x009a:
        r4 = "setAppkey";
        r5 = 1;
        r5 = new java.lang.Class[r5];	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        r6 = 0;
        r7 = java.lang.String.class;
        r5[r6] = r7;	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        r2 = r2.getDeclaredMethod(r4, r5);	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        if (r2 == 0) goto L_0x00c2;
    L_0x00aa:
        r4 = 1;
        r2.setAccessible(r4);	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        r5 = 0;
        r4[r5] = r10;	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        r2.invoke(r3, r4);	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        r2 = a;	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
        if (r2 == 0) goto L_0x00c2;
    L_0x00bb:
        r2 = "UMConfigure";
        r3 = "---->>>> init Push app key is OK ~~";
        android.util.Log.i(r2, r3);	 Catch:{ Exception -> 0x0331, Throwable -> 0x02f8 }
    L_0x00c2:
        r2 = "com.umeng.message.PushAgent";
        r2 = java.lang.Class.forName(r2);	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        if (r2 == 0) goto L_0x010e;
    L_0x00ca:
        r3 = "getInstance";
        r4 = 1;
        r4 = new java.lang.Class[r4];	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        r5 = 0;
        r6 = android.content.Context.class;
        r4[r5] = r6;	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        r3 = r2.getDeclaredMethod(r3, r4);	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        if (r3 == 0) goto L_0x010e;
    L_0x00da:
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        r5 = 0;
        r4[r5] = r1;	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        r3 = r3.invoke(r2, r4);	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        if (r3 == 0) goto L_0x010e;
    L_0x00e6:
        r4 = "setMessageChannel";
        r5 = 1;
        r5 = new java.lang.Class[r5];	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        r6 = 0;
        r7 = java.lang.String.class;
        r5[r6] = r7;	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        r2 = r2.getDeclaredMethod(r4, r5);	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        if (r2 == 0) goto L_0x010e;
    L_0x00f6:
        r4 = 1;
        r2.setAccessible(r4);	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        r5 = 0;
        r4[r5] = r0;	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        r2.invoke(r3, r4);	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        r0 = a;	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
        if (r0 == 0) goto L_0x010e;
    L_0x0107:
        r0 = "UMConfigure";
        r2 = "---->>>> init Push channel is OK ~~";
        android.util.Log.i(r0, r2);	 Catch:{ Exception -> 0x032e, Throwable -> 0x02f8 }
    L_0x010e:
        r0 = "com.umeng.socialize.UMShareAPI";
        r0 = a(r0);	 Catch:{ Throwable -> 0x032b, Exception -> 0x015e }
        r2 = "APPKEY";
        a(r0, r2, r10);	 Catch:{ Throwable -> 0x032b, Exception -> 0x015e }
        if (r0 == 0) goto L_0x014b;
    L_0x011b:
        r2 = "init";
        r3 = 2;
        r3 = new java.lang.Class[r3];	 Catch:{ Throwable -> 0x032b, Exception -> 0x015e }
        r4 = 0;
        r5 = android.content.Context.class;
        r3[r4] = r5;	 Catch:{ Throwable -> 0x032b, Exception -> 0x015e }
        r4 = 1;
        r5 = java.lang.String.class;
        r3[r4] = r5;	 Catch:{ Throwable -> 0x032b, Exception -> 0x015e }
        r2 = r0.getDeclaredMethod(r2, r3);	 Catch:{ Throwable -> 0x032b, Exception -> 0x015e }
        if (r2 == 0) goto L_0x014b;
    L_0x0130:
        r3 = 1;
        r2.setAccessible(r3);	 Catch:{ Throwable -> 0x032b, Exception -> 0x015e }
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x032b, Exception -> 0x015e }
        r4 = 0;
        r3[r4] = r1;	 Catch:{ Throwable -> 0x032b, Exception -> 0x015e }
        r4 = 1;
        r3[r4] = r10;	 Catch:{ Throwable -> 0x032b, Exception -> 0x015e }
        r2.invoke(r0, r3);	 Catch:{ Throwable -> 0x032b, Exception -> 0x015e }
        r0 = a;	 Catch:{ Throwable -> 0x032b, Exception -> 0x015e }
        if (r0 == 0) goto L_0x014b;
    L_0x0144:
        r0 = "UMConfigure";
        r2 = "---->>>> init share appkey is OK ~~";
        android.util.Log.i(r0, r2);	 Catch:{ Throwable -> 0x032b, Exception -> 0x015e }
    L_0x014b:
        r0 = android.text.TextUtils.isEmpty(r10);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r0 == 0) goto L_0x017d;
    L_0x0151:
        r0 = a;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r0 == 0) goto L_0x0033;
    L_0x0155:
        r0 = "UMConfigure";
        r1 = "appkey is null !!!";
        android.util.Log.e(r0, r1);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        goto L_0x0033;
    L_0x015e:
        r0 = move-exception;
        r1 = a;
        if (r1 == 0) goto L_0x0033;
    L_0x0163:
        r1 = "UMConfigure";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "init e is ";
        r2 = r2.append(r3);
        r0 = r2.append(r0);
        r0 = r0.toString();
        android.util.Log.e(r1, r0);
        goto L_0x0033;
    L_0x017d:
        com.umeng.commonsdk.utils.UMUtils.setAppkey(r1, r10);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r0 = com.umeng.commonsdk.utils.UMUtils.getLastAppkey(r1);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r2 = android.text.TextUtils.isEmpty(r10);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r2 != 0) goto L_0x01a4;
    L_0x018a:
        r2 = r10.equals(r0);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r2 != 0) goto L_0x01a4;
    L_0x0190:
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r2 != 0) goto L_0x01a1;
    L_0x0196:
        r2 = a;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r2 == 0) goto L_0x01a1;
    L_0x019a:
        r2 = "UMConfigure";
        r3 = "appkey is change !!!";
        android.util.Log.i(r2, r3);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
    L_0x01a1:
        com.umeng.commonsdk.utils.UMUtils.setLastAppkey(r1, r10);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
    L_0x01a4:
        r2 = a;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r2 == 0) goto L_0x01ca;
    L_0x01a8:
        r2 = "UMConfigure";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r3.<init>();	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r4 = "current appkey is ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r3 = r3.append(r10);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r4 = ", last appkey is ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        android.util.Log.i(r2, r0);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
    L_0x01ca:
        com.umeng.commonsdk.statistics.AnalyticsConstants.setDeviceType(r12);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r0 = android.text.TextUtils.isEmpty(r13);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r0 == 0) goto L_0x0282;
    L_0x01d3:
        r0 = a;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r0 == 0) goto L_0x01d7;
    L_0x01d7:
        r0 = "com.umeng.error.UMError";
        r0 = java.lang.Class.forName(r0);	 Catch:{ Throwable -> 0x0328, Exception -> 0x015e }
        if (r0 == 0) goto L_0x0207;
    L_0x01df:
        r2 = "init";
        r3 = 1;
        r3 = new java.lang.Class[r3];	 Catch:{ Throwable -> 0x0328, Exception -> 0x015e }
        r4 = 0;
        r5 = android.content.Context.class;
        r3[r4] = r5;	 Catch:{ Throwable -> 0x0328, Exception -> 0x015e }
        r2 = r0.getDeclaredMethod(r2, r3);	 Catch:{ Throwable -> 0x0328, Exception -> 0x015e }
        if (r2 == 0) goto L_0x0207;
    L_0x01ef:
        r3 = 1;
        r2.setAccessible(r3);	 Catch:{ Throwable -> 0x0328, Exception -> 0x015e }
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0328, Exception -> 0x015e }
        r4 = 0;
        r3[r4] = r1;	 Catch:{ Throwable -> 0x0328, Exception -> 0x015e }
        r2.invoke(r0, r3);	 Catch:{ Throwable -> 0x0328, Exception -> 0x015e }
        r0 = a;	 Catch:{ Throwable -> 0x0328, Exception -> 0x015e }
        if (r0 == 0) goto L_0x0207;
    L_0x0200:
        r0 = "UMConfigure";
        r2 = "---->>>> init um e is ok ~~";
        android.util.Log.i(r0, r2);	 Catch:{ Throwable -> 0x0328, Exception -> 0x015e }
    L_0x0207:
        r0 = com.umeng.commonsdk.statistics.d.b;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r0 == r8) goto L_0x02f3;
    L_0x020b:
        r0 = "com.umeng.commonsdk.UMConfigureImpl";
        r0 = java.lang.Class.forName(r0);	 Catch:{ Throwable -> 0x0325, Exception -> 0x015e }
        if (r0 == 0) goto L_0x0230;
    L_0x0213:
        r2 = "init";
        r3 = 1;
        r3 = new java.lang.Class[r3];	 Catch:{ Throwable -> 0x0325, Exception -> 0x015e }
        r4 = 0;
        r5 = android.content.Context.class;
        r3[r4] = r5;	 Catch:{ Throwable -> 0x0325, Exception -> 0x015e }
        r2 = r0.getDeclaredMethod(r2, r3);	 Catch:{ Throwable -> 0x0325, Exception -> 0x015e }
        if (r2 == 0) goto L_0x0230;
    L_0x0223:
        r3 = 1;
        r2.setAccessible(r3);	 Catch:{ Throwable -> 0x0325, Exception -> 0x015e }
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0325, Exception -> 0x015e }
        r4 = 0;
        r3[r4] = r1;	 Catch:{ Throwable -> 0x0325, Exception -> 0x015e }
        r2.invoke(r0, r3);	 Catch:{ Throwable -> 0x0325, Exception -> 0x015e }
    L_0x0230:
        r0 = "com.umeng.visual.UMVisualAgent";
        r0 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x025e, Throwable -> 0x0322, Exception -> 0x015e }
        r1 = "init";
        r2 = 2;
        r2 = new java.lang.Class[r2];	 Catch:{ ClassNotFoundException -> 0x025e, Throwable -> 0x0322, Exception -> 0x015e }
        r3 = 0;
        r4 = android.content.Context.class;
        r2[r3] = r4;	 Catch:{ ClassNotFoundException -> 0x025e, Throwable -> 0x0322, Exception -> 0x015e }
        r3 = 1;
        r4 = java.lang.String.class;
        r2[r3] = r4;	 Catch:{ ClassNotFoundException -> 0x025e, Throwable -> 0x0322, Exception -> 0x015e }
        r0 = r0.getMethod(r1, r2);	 Catch:{ ClassNotFoundException -> 0x025e, Throwable -> 0x0322, Exception -> 0x015e }
        r1 = android.text.TextUtils.isEmpty(r10);	 Catch:{ ClassNotFoundException -> 0x025e, Throwable -> 0x0322, Exception -> 0x015e }
        if (r1 != 0) goto L_0x0317;
    L_0x024f:
        r1 = 0;
        r2 = 2;
        r2 = new java.lang.Object[r2];	 Catch:{ ClassNotFoundException -> 0x025e, Throwable -> 0x0322, Exception -> 0x015e }
        r3 = 0;
        r2[r3] = r9;	 Catch:{ ClassNotFoundException -> 0x025e, Throwable -> 0x0322, Exception -> 0x015e }
        r3 = 1;
        r2[r3] = r10;	 Catch:{ ClassNotFoundException -> 0x025e, Throwable -> 0x0322, Exception -> 0x015e }
        r0.invoke(r1, r2);	 Catch:{ ClassNotFoundException -> 0x025e, Throwable -> 0x0322, Exception -> 0x015e }
        goto L_0x0033;
    L_0x025e:
        r0 = move-exception;
        r0 = "com.umeng.analytics.vismode.event.VisualHelper";
        r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x027f, Throwable -> 0x02f8 }
        r1 = "init";
        r2 = 1;
        r2 = new java.lang.Class[r2];	 Catch:{ Exception -> 0x027f, Throwable -> 0x02f8 }
        r3 = 0;
        r4 = android.content.Context.class;
        r2[r3] = r4;	 Catch:{ Exception -> 0x027f, Throwable -> 0x02f8 }
        r0 = r0.getMethod(r1, r2);	 Catch:{ Exception -> 0x027f, Throwable -> 0x02f8 }
        r1 = 0;
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x027f, Throwable -> 0x02f8 }
        r3 = 0;
        r2[r3] = r9;	 Catch:{ Exception -> 0x027f, Throwable -> 0x02f8 }
        r0.invoke(r1, r2);	 Catch:{ Exception -> 0x027f, Throwable -> 0x02f8 }
        goto L_0x0033;
    L_0x027f:
        r0 = move-exception;
        goto L_0x0033;
    L_0x0282:
        r0 = a;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        if (r0 == 0) goto L_0x029e;
    L_0x0286:
        r0 = "UMConfigure";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r2.<init>();	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r3 = "push secret is ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r2 = r2.append(r13);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        android.util.Log.i(r0, r2);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
    L_0x029e:
        r0 = "com.umeng.message.PushAgent";
        r0 = java.lang.Class.forName(r0);	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        if (r0 == 0) goto L_0x01d7;
    L_0x02a6:
        r2 = "getInstance";
        r3 = 1;
        r3 = new java.lang.Class[r3];	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        r4 = 0;
        r5 = android.content.Context.class;
        r3[r4] = r5;	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        r2 = r0.getDeclaredMethod(r2, r3);	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        if (r2 == 0) goto L_0x01d7;
    L_0x02b6:
        r3 = 1;
        r2.setAccessible(r3);	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        r4 = 0;
        r3[r4] = r1;	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        r2 = r2.invoke(r0, r3);	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        if (r2 == 0) goto L_0x01d7;
    L_0x02c6:
        r3 = "setSecret";
        r4 = 1;
        r4 = new java.lang.Class[r4];	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        r5 = 0;
        r6 = java.lang.String.class;
        r4[r5] = r6;	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        r0 = r0.getDeclaredMethod(r3, r4);	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        if (r0 == 0) goto L_0x01d7;
    L_0x02d6:
        r3 = 1;
        r0.setAccessible(r3);	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        r4 = 0;
        r3[r4] = r13;	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        r0.invoke(r2, r3);	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        r0 = a;	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        if (r0 == 0) goto L_0x01d7;
    L_0x02e7:
        r0 = "UMConfigure";
        r2 = "---->>>> init push secret is OK ~~";
        android.util.Log.i(r0, r2);	 Catch:{ Throwable -> 0x02f0, Exception -> 0x015e }
        goto L_0x01d7;
    L_0x02f0:
        r0 = move-exception;
        goto L_0x01d7;
    L_0x02f3:
        com.umeng.commonsdk.a.a(r1);	 Catch:{ Exception -> 0x015e, Throwable -> 0x02f8 }
        goto L_0x0230;
    L_0x02f8:
        r0 = move-exception;
        r1 = a;
        if (r1 == 0) goto L_0x0033;
    L_0x02fd:
        r1 = "UMConfigure";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "init e is ";
        r2 = r2.append(r3);
        r0 = r2.append(r0);
        r0 = r0.toString();
        android.util.Log.e(r1, r0);
        goto L_0x0033;
    L_0x0317:
        r0 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;	 Catch:{ ClassNotFoundException -> 0x025e, Throwable -> 0x0322, Exception -> 0x015e }
        if (r0 == 0) goto L_0x0033;
    L_0x031b:
        r0 = "initDebugSDK appkey  is null";
        com.umeng.commonsdk.statistics.common.MLog.e(r0);	 Catch:{ ClassNotFoundException -> 0x025e, Throwable -> 0x0322, Exception -> 0x015e }
        goto L_0x0033;
    L_0x0322:
        r0 = move-exception;
        goto L_0x0033;
    L_0x0325:
        r0 = move-exception;
        goto L_0x0230;
    L_0x0328:
        r0 = move-exception;
        goto L_0x0207;
    L_0x032b:
        r0 = move-exception;
        goto L_0x014b;
    L_0x032e:
        r0 = move-exception;
        goto L_0x010e;
    L_0x0331:
        r2 = move-exception;
        goto L_0x00c2;
    L_0x0334:
        r0 = r11;
        goto L_0x004c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.UMConfigure.init(android.content.Context, java.lang.String, java.lang.String, int, java.lang.String):void");
    }

    public static void setLogEnabled(boolean z) {
        try {
            a = z;
            MLog.DEBUG = z;
            Class a = a("com.umeng.message.PushAgent");
            Object a2 = a(a);
            a(a(a, "setDebugMode", new Class[]{Boolean.TYPE}), a2, new Object[]{Boolean.valueOf(z)});
            a(a("com.umeng.socialize.Config"), "DEBUG", z);
        } catch (Exception e) {
            if (a) {
                Log.e("UMConfigure", "set log enabled e is " + e);
            }
        } catch (Throwable th) {
            if (a) {
                Log.e("UMConfigure", "set log enabled e is " + th);
            }
        }
    }

    public static void setEncryptEnabled(boolean z) {
        b.a(z);
        UMSLEnvelopeBuild.setEncryptEnabled(z);
    }

    public static String getUMIDString(Context context) {
        if (context != null) {
            return UMUtils.getUMId(context.getApplicationContext());
        }
        return null;
    }

    public static String[] getTestDeviceInfo(Context context) {
        String[] strArr = new String[2];
        if (context != null) {
            try {
                strArr[0] = DeviceConfig.getDeviceIdForGeneral(context);
                strArr[1] = DeviceConfig.getMac(context);
            } catch (Exception e) {
            }
        }
        return strArr;
    }
}
