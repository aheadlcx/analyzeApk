package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.RemoteViews;
import com.xiaomi.channel.commonutils.android.g;
import com.xiaomi.xmpush.thrift.ab;
import com.xiaomi.xmpush.thrift.r;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONObject;

public class ac {
    public static long a = 0;
    private static final LinkedList<Pair<Integer, ab>> b = new LinkedList();

    public static class a {
        Notification a;
        long b = 0;
    }

    public static class b {
        public String a;
        public long b = 0;
    }

    private static int a(Context context, String str, String str2) {
        return str.equals(context.getPackageName()) ? context.getResources().getIdentifier(str2, "drawable", str) : 0;
    }

    private static Notification a(Notification notification) {
        Object a = com.xiaomi.channel.commonutils.reflect.a.a((Object) notification, "extraNotification");
        if (a != null) {
            com.xiaomi.channel.commonutils.reflect.a.a(a, "setCustomizedIcon", Boolean.valueOf(true));
        }
        return notification;
    }

    private static Notification a(Notification notification, String str) {
        try {
            Field declaredField = Notification.class.getDeclaredField("extraNotification");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(notification);
            Method declaredMethod = obj.getClass().getDeclaredMethod("setTargetPkg", new Class[]{CharSequence.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, new Object[]{str});
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
        return notification;
    }

    private static PendingIntent a(Context context, ab abVar, r rVar, byte[] bArr) {
        Intent intent;
        if (rVar != null && !TextUtils.isEmpty(rVar.g)) {
            intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(rVar.g));
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            return PendingIntent.getActivity(context, 0, intent, 134217728);
        } else if (b(abVar)) {
            intent = new Intent();
            intent.setComponent(new ComponentName("com.xiaomi.xmsf", "com.xiaomi.mipush.sdk.PushMessageHandler"));
            intent.putExtra("mipush_payload", bArr);
            intent.putExtra("mipush_notified", true);
            intent.addCategory(String.valueOf(rVar.q()));
            return PendingIntent.getService(context, 0, intent, 134217728);
        } else {
            intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
            intent.setComponent(new ComponentName(abVar.f, "com.xiaomi.mipush.sdk.PushMessageHandler"));
            intent.putExtra("mipush_payload", bArr);
            intent.putExtra("mipush_notified", true);
            intent.addCategory(String.valueOf(rVar.q()));
            return PendingIntent.getService(context, 0, intent, 134217728);
        }
    }

    private static Bitmap a(Context context, int i) {
        return a(context.getResources().getDrawable(i));
    }

    public static Bitmap a(Drawable drawable) {
        int i = 1;
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight > 0) {
            i = intrinsicHeight;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    @SuppressLint({"NewApi"})
    private static a a(Context context, ab abVar, byte[] bArr, RemoteViews remoteViews, PendingIntent pendingIntent) {
        Object obj;
        String str;
        Object obj2;
        boolean parseBoolean;
        long currentTimeMillis;
        Notification notification;
        a aVar = new a();
        r m = abVar.m();
        String a = a(abVar);
        Map s = m.s();
        Object builder = new Builder(context);
        String[] a2 = a(context, m);
        builder.setContentTitle(a2[0]);
        builder.setContentText(a2[1]);
        if (remoteViews != null) {
            builder.setContent(remoteViews);
        } else if (VERSION.SDK_INT >= 16) {
            builder.setStyle(new BigTextStyle().bigText(a2[1]));
        }
        builder.setWhen(System.currentTimeMillis());
        if (s == null) {
            obj = null;
        } else {
            str = (String) s.get("notification_show_when");
        }
        if (!TextUtils.isEmpty(obj)) {
            builder.setShowWhen(Boolean.parseBoolean(obj));
        } else if (VERSION.SDK_INT >= 24) {
            builder.setShowWhen(true);
        }
        builder.setContentIntent(pendingIntent);
        int a3 = a(context, a, "mipush_notification");
        int a4 = a(context, a, "mipush_small_notification");
        if (a3 <= 0 || a4 <= 0) {
            builder.setSmallIcon(f(context, a));
        } else {
            builder.setLargeIcon(a(context, a3));
            builder.setSmallIcon(a4);
        }
        String str2 = s == null ? null : (String) s.get("__dynamic_icon_uri");
        obj = (s == null || !Boolean.parseBoolean((String) s.get("__adiom"))) ? null : 1;
        obj = (obj != null || g.b()) ? 1 : null;
        if (!(str2 == null || obj == null)) {
            Bitmap bitmap = null;
            if (str2.startsWith("http")) {
                com.xiaomi.push.service.ag.b a5 = ag.a(context, str2);
                if (a5 != null) {
                    bitmap = a5.a;
                    aVar.b = a5.b;
                }
            } else {
                bitmap = ag.b(context, str2);
            }
            if (bitmap != null) {
                builder.setLargeIcon(bitmap);
                obj2 = 1;
                if (s != null && VERSION.SDK_INT >= 24) {
                    str = (String) s.get("notification_group");
                    parseBoolean = Boolean.parseBoolean((String) s.get("notification_is_summary"));
                    com.xiaomi.channel.commonutils.reflect.a.a(builder, "setGroup", str);
                    com.xiaomi.channel.commonutils.reflect.a.a(builder, "setGroupSummary", Boolean.valueOf(parseBoolean));
                }
                builder.setAutoCancel(true);
                currentTimeMillis = System.currentTimeMillis();
                if (s != null && s.containsKey("ticker")) {
                    builder.setTicker((CharSequence) s.get("ticker"));
                }
                if (currentTimeMillis - a > 10000) {
                    a = currentTimeMillis;
                    a4 = e(context, a) ? c(context, a) : m.f;
                    builder.setDefaults(a4);
                    if (!(s == null || (a4 & 1) == 0)) {
                        str = (String) s.get("sound_uri");
                        if (!TextUtils.isEmpty(str) && str.startsWith("android.resource://" + a)) {
                            builder.setDefaults(a4 ^ 1);
                            builder.setSound(Uri.parse(str));
                        }
                    }
                }
                notification = builder.getNotification();
                if (obj2 != null && g.a()) {
                    a(notification);
                }
                aVar.a = notification;
                return aVar;
            }
        }
        obj2 = null;
        str = (String) s.get("notification_group");
        parseBoolean = Boolean.parseBoolean((String) s.get("notification_is_summary"));
        com.xiaomi.channel.commonutils.reflect.a.a(builder, "setGroup", str);
        com.xiaomi.channel.commonutils.reflect.a.a(builder, "setGroupSummary", Boolean.valueOf(parseBoolean));
        builder.setAutoCancel(true);
        currentTimeMillis = System.currentTimeMillis();
        builder.setTicker((CharSequence) s.get("ticker"));
        if (currentTimeMillis - a > 10000) {
            a = currentTimeMillis;
            if (e(context, a)) {
            }
            builder.setDefaults(a4);
            str = (String) s.get("sound_uri");
            builder.setDefaults(a4 ^ 1);
            builder.setSound(Uri.parse(str));
        }
        notification = builder.getNotification();
        a(notification);
        aVar.a = notification;
        return aVar;
    }

    public static b a(Context context, ab abVar, byte[] bArr) {
        b bVar = new b();
        if (com.xiaomi.channel.commonutils.android.b.d(context, a(abVar)) == com.xiaomi.channel.commonutils.android.b.a.NOT_ALLOWED) {
            com.xiaomi.channel.commonutils.logger.b.a("Do not notify because user block " + a(abVar) + "‘s notification");
            return bVar;
        } else if (ay.a(context, abVar)) {
            com.xiaomi.channel.commonutils.logger.b.a("Do not notify because user block " + ay.a(abVar) + "‘s notification");
            return bVar;
        } else {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            r m = abVar.m();
            RemoteViews b = b(context, abVar, bArr);
            PendingIntent a = a(context, abVar, m, bArr);
            if (a == null) {
                com.xiaomi.channel.commonutils.logger.b.a("The click PendingIntent is null. ");
                return bVar;
            }
            Notification notification;
            String str;
            if (VERSION.SDK_INT >= 11) {
                a a2 = a(context, abVar, bArr, b, a);
                bVar.b = a2.b;
                bVar.a = a(abVar);
                notification = a2.a;
            } else {
                Notification notification2 = new Notification(f(context, a(abVar)), null, System.currentTimeMillis());
                String[] a3 = a(context, m);
                try {
                    notification2.getClass().getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification2, new Object[]{context, a3[0], a3[1], a});
                } catch (Throwable e) {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                } catch (Throwable e2) {
                    com.xiaomi.channel.commonutils.logger.b.a(e2);
                } catch (Throwable e22) {
                    com.xiaomi.channel.commonutils.logger.b.a(e22);
                } catch (Throwable e222) {
                    com.xiaomi.channel.commonutils.logger.b.a(e222);
                }
                Map s = m.s();
                if (s != null && s.containsKey("ticker")) {
                    notification2.tickerText = (CharSequence) s.get("ticker");
                }
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - a > 10000) {
                    a = currentTimeMillis;
                    int c = e(context, a(abVar)) ? c(context, a(abVar)) : m.f;
                    notification2.defaults = c;
                    if (!(s == null || (c & 1) == 0)) {
                        str = (String) s.get("sound_uri");
                        if (!TextUtils.isEmpty(str) && str.startsWith("android.resource://" + a(abVar))) {
                            notification2.defaults = c ^ 1;
                            notification2.sound = Uri.parse(str);
                        }
                    }
                }
                notification2.flags |= 16;
                if (b != null) {
                    notification2.contentView = b;
                }
                notification = notification2;
            }
            if (g.a() && VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(m.b())) {
                notification.extras.putString("message_id", m.b());
            }
            str = m.s() == null ? null : (String) m.s().get("message_count");
            if (g.a() && str != null) {
                try {
                    a(notification, Integer.parseInt(str));
                } catch (Throwable e2222) {
                    com.xiaomi.channel.commonutils.logger.b.a(e2222);
                }
            }
            if ("com.xiaomi.xmsf".equals(context.getPackageName())) {
                a(notification, a(abVar));
            }
            if ("com.xiaomi.xmsf".equals(a(abVar))) {
                ay.a(context, abVar, notification);
            }
            int q = m.q() + ((a(abVar).hashCode() / 10) * 10);
            notificationManager.notify(q, notification);
            Pair pair = new Pair(Integer.valueOf(q), abVar);
            synchronized (b) {
                b.add(pair);
                if (b.size() > 100) {
                    b.remove();
                }
            }
            return bVar;
        }
    }

    static String a(ab abVar) {
        if ("com.xiaomi.xmsf".equals(abVar.f)) {
            r m = abVar.m();
            if (!(m == null || m.s() == null)) {
                String str = (String) m.s().get("miui_package_name");
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
            }
        }
        return abVar.f;
    }

    private static void a(Notification notification, int i) {
        Object a = com.xiaomi.channel.commonutils.reflect.a.a((Object) notification, "extraNotification");
        if (a != null) {
            com.xiaomi.channel.commonutils.reflect.a.a(a, "setMessageCount", Integer.valueOf(i));
        }
    }

    public static void a(Context context, String str, int i) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        int hashCode = ((str.hashCode() / 10) * 10) + i;
        LinkedList linkedList = new LinkedList();
        if (i >= 0) {
            notificationManager.cancel(hashCode);
        }
        synchronized (b) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                ab abVar = (ab) pair.second;
                if (abVar != null) {
                    CharSequence a = a(abVar);
                    if (i >= 0) {
                        if (hashCode == ((Integer) pair.first).intValue() && TextUtils.equals(a, str)) {
                            linkedList.add(pair);
                        }
                    } else if (i != -1) {
                        continue;
                    } else if (TextUtils.equals(a, str)) {
                        notificationManager.cancel(((Integer) pair.first).intValue());
                        linkedList.add(pair);
                    }
                }
            }
            if (b != null) {
                b.removeAll(linkedList);
                a(linkedList);
            }
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            LinkedList linkedList = new LinkedList();
            synchronized (b) {
                Iterator it = b.iterator();
                while (it.hasNext()) {
                    Pair pair = (Pair) it.next();
                    ab abVar = (ab) pair.second;
                    if (abVar != null) {
                        CharSequence a = a(abVar);
                        r m = abVar.m();
                        if (m != null && TextUtils.equals(a, str)) {
                            String h = m.h();
                            String j = m.j();
                            if (!TextUtils.isEmpty(h) && !TextUtils.isEmpty(j) && a(str2, h) && a(str3, j)) {
                                notificationManager.cancel(((Integer) pair.first).intValue());
                                linkedList.add(pair);
                            }
                        }
                    }
                }
                if (b != null) {
                    b.removeAll(linkedList);
                    a(linkedList);
                }
            }
        }
    }

    private static void a(LinkedList<Pair<Integer, ab>> linkedList) {
        if (linkedList != null && linkedList.size() > 0) {
            aw.a().a("category_clear_notification", "clear_notification", (long) linkedList.size(), "");
        }
    }

    public static boolean a(Context context, String str) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100 && Arrays.asList(runningAppProcessInfo.pkgList).contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean a(String str, String str2) {
        return TextUtils.isEmpty(str) || str2.contains(str);
    }

    public static boolean a(Map<String, String> map) {
        if (map == null || !map.containsKey("notify_foreground")) {
            return true;
        }
        return "1".equals((String) map.get("notify_foreground"));
    }

    private static String[] a(Context context, r rVar) {
        String h = rVar.h();
        String j = rVar.j();
        Map s = rVar.s();
        if (s != null) {
            int intValue = Float.valueOf((((float) context.getResources().getDisplayMetrics().widthPixels) / context.getResources().getDisplayMetrics().density) + 0.5f).intValue();
            String str;
            if (intValue <= 320) {
                str = (String) s.get("title_short");
                if (!TextUtils.isEmpty(str)) {
                    h = str;
                }
                CharSequence charSequence = (String) s.get("description_short");
                if (TextUtils.isEmpty(charSequence)) {
                    Object obj = j;
                }
                j = charSequence;
            } else if (intValue > 360) {
                str = (String) s.get("title_long");
                if (!TextUtils.isEmpty(str)) {
                    h = str;
                }
                str = (String) s.get("description_long");
                if (!TextUtils.isEmpty(str)) {
                    j = str;
                }
            }
        }
        return new String[]{h, j};
    }

    private static RemoteViews b(Context context, ab abVar, byte[] bArr) {
        r m = abVar.m();
        String a = a(abVar);
        Map s = m.s();
        if (s == null) {
            return null;
        }
        String str = (String) s.get("layout_name");
        String str2 = (String) s.get("layout_value");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(a);
            int identifier = resourcesForApplication.getIdentifier(str, "layout", a);
            if (identifier == 0) {
                return null;
            }
            RemoteViews remoteViews = new RemoteViews(a, identifier);
            try {
                JSONObject jSONObject;
                Iterator keys;
                JSONObject jSONObject2 = new JSONObject(str2);
                if (jSONObject2.has("text")) {
                    jSONObject = jSONObject2.getJSONObject("text");
                    keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        CharSequence string = jSONObject.getString(str);
                        identifier = resourcesForApplication.getIdentifier(str, "id", a);
                        if (identifier > 0) {
                            remoteViews.setTextViewText(identifier, string);
                        }
                    }
                }
                if (jSONObject2.has("image")) {
                    jSONObject = jSONObject2.getJSONObject("image");
                    keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        String string2 = jSONObject.getString(str);
                        identifier = resourcesForApplication.getIdentifier(str, "id", a);
                        int identifier2 = resourcesForApplication.getIdentifier(string2, "drawable", a);
                        if (identifier > 0) {
                            remoteViews.setImageViewResource(identifier, identifier2);
                        }
                    }
                }
                if (jSONObject2.has("time")) {
                    jSONObject2 = jSONObject2.getJSONObject("time");
                    keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        str2 = jSONObject2.getString(str);
                        if (str2.length() == 0) {
                            str2 = "yy-MM-dd hh:mm";
                        }
                        identifier = resourcesForApplication.getIdentifier(str, "id", a);
                        if (identifier > 0) {
                            remoteViews.setTextViewText(identifier, new SimpleDateFormat(str2).format(new Date(System.currentTimeMillis())));
                        }
                    }
                }
                return remoteViews;
            } catch (Throwable e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
                return null;
            }
        } catch (Throwable e2) {
            com.xiaomi.channel.commonutils.logger.b.a(e2);
            return null;
        }
    }

    public static void b(Context context, String str) {
        a(context, str, -1);
    }

    static void b(Context context, String str, int i) {
        context.getSharedPreferences("pref_notify_type", 0).edit().putInt(str, i).commit();
    }

    public static boolean b(ab abVar) {
        r m = abVar.m();
        return m != null && m.v();
    }

    static int c(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).getInt(str, Integer.MAX_VALUE);
    }

    static void d(Context context, String str) {
        context.getSharedPreferences("pref_notify_type", 0).edit().remove(str).commit();
    }

    static boolean e(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).contains(str);
    }

    private static int f(Context context, String str) {
        int a = a(context, str, "mipush_notification");
        int a2 = a(context, str, "mipush_small_notification");
        if (a <= 0) {
            a = a2 > 0 ? a2 : context.getApplicationInfo().icon;
        }
        return (a != 0 || VERSION.SDK_INT < 9) ? a : context.getApplicationInfo().logo;
    }
}
