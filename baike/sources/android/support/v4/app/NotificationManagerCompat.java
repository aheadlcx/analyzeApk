package android.support.v4.app;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.INotificationSideChannel.Stub;
import android.util.Log;
import com.baidu.mobstat.Config;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class NotificationManagerCompat {
    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    private static final Object a = new Object();
    @GuardedBy("sEnabledNotificationListenersLock")
    private static String b;
    @GuardedBy("sEnabledNotificationListenersLock")
    private static Set<String> c = new HashSet();
    private static final Object f = new Object();
    @GuardedBy("sLock")
    private static d g;
    private final Context d;
    private final NotificationManager e = ((NotificationManager) this.d.getSystemService("notification"));

    private interface e {
        void send(INotificationSideChannel iNotificationSideChannel) throws RemoteException;
    }

    private static class a implements e {
        final String a;
        final int b;
        final String c;
        final boolean d;

        a(String str) {
            this.a = str;
            this.b = 0;
            this.c = null;
            this.d = true;
        }

        a(String str, int i, String str2) {
            this.a = str;
            this.b = i;
            this.c = str2;
            this.d = false;
        }

        public void send(INotificationSideChannel iNotificationSideChannel) throws RemoteException {
            if (this.d) {
                iNotificationSideChannel.cancelAll(this.a);
            } else {
                iNotificationSideChannel.cancel(this.a, this.b, this.c);
            }
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("CancelTask[");
            stringBuilder.append("packageName:").append(this.a);
            stringBuilder.append(", id:").append(this.b);
            stringBuilder.append(", tag:").append(this.c);
            stringBuilder.append(", all:").append(this.d);
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    private static class b implements e {
        final String a;
        final int b;
        final String c;
        final Notification d;

        b(String str, int i, String str2, Notification notification) {
            this.a = str;
            this.b = i;
            this.c = str2;
            this.d = notification;
        }

        public void send(INotificationSideChannel iNotificationSideChannel) throws RemoteException {
            iNotificationSideChannel.notify(this.a, this.b, this.c, this.d);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("NotifyTask[");
            stringBuilder.append("packageName:").append(this.a);
            stringBuilder.append(", id:").append(this.b);
            stringBuilder.append(", tag:").append(this.c);
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    private static class c {
        final ComponentName a;
        final IBinder b;

        c(ComponentName componentName, IBinder iBinder) {
            this.a = componentName;
            this.b = iBinder;
        }
    }

    private static class d implements ServiceConnection, Callback {
        private final Context a;
        private final HandlerThread b;
        private final Handler c;
        private final Map<ComponentName, a> d = new HashMap();
        private Set<String> e = new HashSet();

        private static class a {
            public boolean bound = false;
            public final ComponentName componentName;
            public int retryCount = 0;
            public INotificationSideChannel service;
            public LinkedList<e> taskQueue = new LinkedList();

            public a(ComponentName componentName) {
                this.componentName = componentName;
            }
        }

        public d(Context context) {
            this.a = context;
            this.b = new HandlerThread("NotificationManagerCompat");
            this.b.start();
            this.c = new Handler(this.b.getLooper(), this);
        }

        public void queueTask(e eVar) {
            this.c.obtainMessage(0, eVar).sendToTarget();
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    a((e) message.obj);
                    return true;
                case 1:
                    c cVar = (c) message.obj;
                    a(cVar.a, cVar.b);
                    return true;
                case 2:
                    a((ComponentName) message.obj);
                    return true;
                case 3:
                    b((ComponentName) message.obj);
                    return true;
                default:
                    return false;
            }
        }

        private void a(e eVar) {
            a();
            for (a aVar : this.d.values()) {
                aVar.taskQueue.add(eVar);
                d(aVar);
            }
        }

        private void a(ComponentName componentName, IBinder iBinder) {
            a aVar = (a) this.d.get(componentName);
            if (aVar != null) {
                aVar.service = Stub.asInterface(iBinder);
                aVar.retryCount = 0;
                d(aVar);
            }
        }

        private void a(ComponentName componentName) {
            a aVar = (a) this.d.get(componentName);
            if (aVar != null) {
                b(aVar);
            }
        }

        private void b(ComponentName componentName) {
            a aVar = (a) this.d.get(componentName);
            if (aVar != null) {
                d(aVar);
            }
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Connected to service " + componentName);
            }
            this.c.obtainMessage(1, new c(componentName, iBinder)).sendToTarget();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Disconnected from service " + componentName);
            }
            this.c.obtainMessage(2, componentName).sendToTarget();
        }

        private void a() {
            Set enabledListenerPackages = NotificationManagerCompat.getEnabledListenerPackages(this.a);
            if (!enabledListenerPackages.equals(this.e)) {
                this.e = enabledListenerPackages;
                List<ResolveInfo> queryIntentServices = this.a.getPackageManager().queryIntentServices(new Intent().setAction(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL), 0);
                Set<ComponentName> hashSet = new HashSet();
                for (ResolveInfo resolveInfo : queryIntentServices) {
                    if (enabledListenerPackages.contains(resolveInfo.serviceInfo.packageName)) {
                        ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
                        if (resolveInfo.serviceInfo.permission != null) {
                            Log.w("NotifManCompat", "Permission present on component " + componentName + ", not adding listener record.");
                        } else {
                            hashSet.add(componentName);
                        }
                    }
                }
                for (ComponentName componentName2 : hashSet) {
                    if (!this.d.containsKey(componentName2)) {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Adding listener record for " + componentName2);
                        }
                        this.d.put(componentName2, new a(componentName2));
                    }
                }
                Iterator it = this.d.entrySet().iterator();
                while (it.hasNext()) {
                    Entry entry = (Entry) it.next();
                    if (!hashSet.contains(entry.getKey())) {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Removing listener record for " + entry.getKey());
                        }
                        b((a) entry.getValue());
                        it.remove();
                    }
                }
            }
        }

        private boolean a(a aVar) {
            if (aVar.bound) {
                return true;
            }
            aVar.bound = this.a.bindService(new Intent(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL).setComponent(aVar.componentName), this, 33);
            if (aVar.bound) {
                aVar.retryCount = 0;
            } else {
                Log.w("NotifManCompat", "Unable to bind to listener " + aVar.componentName);
                this.a.unbindService(this);
            }
            return aVar.bound;
        }

        private void b(a aVar) {
            if (aVar.bound) {
                this.a.unbindService(this);
                aVar.bound = false;
            }
            aVar.service = null;
        }

        private void c(a aVar) {
            if (!this.c.hasMessages(3, aVar.componentName)) {
                aVar.retryCount++;
                if (aVar.retryCount > 6) {
                    Log.w("NotifManCompat", "Giving up on delivering " + aVar.taskQueue.size() + " tasks to " + aVar.componentName + " after " + aVar.retryCount + " retries");
                    aVar.taskQueue.clear();
                    return;
                }
                int i = (1 << (aVar.retryCount - 1)) * 1000;
                if (Log.isLoggable("NotifManCompat", 3)) {
                    Log.d("NotifManCompat", "Scheduling retry for " + i + " ms");
                }
                this.c.sendMessageDelayed(this.c.obtainMessage(3, aVar.componentName), (long) i);
            }
        }

        private void d(a aVar) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Processing component " + aVar.componentName + ", " + aVar.taskQueue.size() + " queued tasks");
            }
            if (!aVar.taskQueue.isEmpty()) {
                if (!a(aVar) || aVar.service == null) {
                    c(aVar);
                    return;
                }
                while (true) {
                    e eVar = (e) aVar.taskQueue.peek();
                    if (eVar == null) {
                        break;
                    }
                    try {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Sending task " + eVar);
                        }
                        eVar.send(aVar.service);
                        aVar.taskQueue.remove();
                    } catch (DeadObjectException e) {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Remote service has died: " + aVar.componentName);
                        }
                    } catch (Throwable e2) {
                        Log.w("NotifManCompat", "RemoteException communicating with " + aVar.componentName, e2);
                    }
                }
                if (!aVar.taskQueue.isEmpty()) {
                    c(aVar);
                }
            }
        }
    }

    @NonNull
    public static NotificationManagerCompat from(@NonNull Context context) {
        return new NotificationManagerCompat(context);
    }

    private NotificationManagerCompat(Context context) {
        this.d = context;
    }

    public void cancel(int i) {
        cancel(null, i);
    }

    public void cancel(@Nullable String str, int i) {
        this.e.cancel(str, i);
        if (VERSION.SDK_INT <= 19) {
            a(new a(this.d.getPackageName(), i, str));
        }
    }

    public void cancelAll() {
        this.e.cancelAll();
        if (VERSION.SDK_INT <= 19) {
            a(new a(this.d.getPackageName()));
        }
    }

    public void notify(int i, Notification notification) {
        notify(null, i, notification);
    }

    public void notify(@Nullable String str, int i, @NonNull Notification notification) {
        if (a(notification)) {
            a(new b(this.d.getPackageName(), i, str, notification));
            this.e.cancel(str, i);
            return;
        }
        this.e.notify(str, i, notification);
    }

    public boolean areNotificationsEnabled() {
        if (VERSION.SDK_INT >= 24) {
            return this.e.areNotificationsEnabled();
        }
        if (VERSION.SDK_INT < 19) {
            return true;
        }
        AppOpsManager appOpsManager = (AppOpsManager) this.d.getSystemService("appops");
        ApplicationInfo applicationInfo = this.d.getApplicationInfo();
        String packageName = this.d.getApplicationContext().getPackageName();
        int i = applicationInfo.uid;
        try {
            return ((Integer) Class.forName(AppOpsManager.class.getName()).getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(appOpsManager, new Object[]{Integer.valueOf(((Integer) Class.forName(AppOpsManager.class.getName()).getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName})).intValue() == 0;
        } catch (ClassNotFoundException e) {
            return true;
        } catch (NoSuchMethodException e2) {
            return true;
        } catch (NoSuchFieldException e3) {
            return true;
        } catch (InvocationTargetException e4) {
            return true;
        } catch (IllegalAccessException e5) {
            return true;
        } catch (RuntimeException e6) {
            return true;
        }
    }

    public int getImportance() {
        if (VERSION.SDK_INT >= 24) {
            return this.e.getImportance();
        }
        return IMPORTANCE_UNSPECIFIED;
    }

    @NonNull
    public static Set<String> getEnabledListenerPackages(@NonNull Context context) {
        String string = Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        synchronized (a) {
            if (string != null) {
                if (!string.equals(b)) {
                    String[] split = string.split(Config.TRACE_TODAY_VISIT_SPLIT);
                    Set hashSet = new HashSet(split.length);
                    for (String unflattenFromString : split) {
                        ComponentName unflattenFromString2 = ComponentName.unflattenFromString(unflattenFromString);
                        if (unflattenFromString2 != null) {
                            hashSet.add(unflattenFromString2.getPackageName());
                        }
                    }
                    c = hashSet;
                    b = string;
                }
            }
        }
        return c;
    }

    private static boolean a(Notification notification) {
        Bundle extras = NotificationCompat.getExtras(notification);
        return extras != null && extras.getBoolean(EXTRA_USE_SIDE_CHANNEL);
    }

    private void a(e eVar) {
        synchronized (f) {
            if (g == null) {
                g = new d(this.d.getApplicationContext());
            }
            g.queueTask(eVar);
        }
    }
}
