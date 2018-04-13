package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat.Action;
import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiresApi(16)
class NotificationCompatJellybean {
    public static final String TAG = "NotificationCompat";
    private static final Object a = new Object();
    private static Field b;
    private static boolean c;
    private static final Object d = new Object();
    private static Class<?> e;
    private static Field f;
    private static Field g;
    private static Field h;
    private static Field i;
    private static boolean j;

    NotificationCompatJellybean() {
    }

    public static SparseArray<Bundle> buildActionExtrasMap(List<Bundle> list) {
        SparseArray<Bundle> sparseArray = null;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Bundle bundle = (Bundle) list.get(i);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                sparseArray.put(i, bundle);
            }
        }
        return sparseArray;
    }

    public static Bundle getExtras(Notification notification) {
        synchronized (a) {
            if (c) {
                return null;
            }
            try {
                if (b == null) {
                    Field declaredField = Notification.class.getDeclaredField("extras");
                    if (Bundle.class.isAssignableFrom(declaredField.getType())) {
                        declaredField.setAccessible(true);
                        b = declaredField;
                    } else {
                        Log.e(TAG, "Notification.extras field is not of type Bundle");
                        c = true;
                        return null;
                    }
                }
                Bundle bundle = (Bundle) b.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    b.set(notification, bundle);
                }
                return bundle;
            } catch (Throwable e) {
                Log.e(TAG, "Unable to access notification extras", e);
                c = true;
                return null;
            } catch (Throwable e2) {
                Log.e(TAG, "Unable to access notification extras", e2);
                c = true;
                return null;
            }
        }
    }

    public static Action readAction(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle) {
        RemoteInput[] a;
        RemoteInput[] remoteInputArr = null;
        boolean z = false;
        if (bundle != null) {
            a = a(a(bundle, NotificationCompatExtras.EXTRA_REMOTE_INPUTS));
            remoteInputArr = a(a(bundle, "android.support.dataRemoteInputs"));
            z = bundle.getBoolean("android.support.allowGeneratedReplies");
        } else {
            a = null;
        }
        return new Action(i, charSequence, pendingIntent, bundle, a, remoteInputArr, z);
    }

    public static Bundle writeActionAndGetExtras(Builder builder, Action action) {
        builder.addAction(action.getIcon(), action.getTitle(), action.getActionIntent());
        Bundle bundle = new Bundle(action.getExtras());
        if (action.getRemoteInputs() != null) {
            bundle.putParcelableArray(NotificationCompatExtras.EXTRA_REMOTE_INPUTS, a(action.getRemoteInputs()));
        }
        if (action.getDataOnlyRemoteInputs() != null) {
            bundle.putParcelableArray("android.support.dataRemoteInputs", a(action.getDataOnlyRemoteInputs()));
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        return bundle;
    }

    public static int getActionCount(Notification notification) {
        int length;
        synchronized (d) {
            Object[] a = a(notification);
            length = a != null ? a.length : 0;
        }
        return length;
    }

    public static Action getAction(Notification notification, int i) {
        synchronized (d) {
            try {
                Object[] a = a(notification);
                if (a != null) {
                    Bundle bundle;
                    Action readAction;
                    Object obj = a[i];
                    Bundle extras = getExtras(notification);
                    if (extras != null) {
                        SparseArray sparseParcelableArray = extras.getSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS);
                        if (sparseParcelableArray != null) {
                            bundle = (Bundle) sparseParcelableArray.get(i);
                            readAction = readAction(g.getInt(obj), (CharSequence) h.get(obj), (PendingIntent) i.get(obj), bundle);
                            return readAction;
                        }
                    }
                    bundle = null;
                    readAction = readAction(g.getInt(obj), (CharSequence) h.get(obj), (PendingIntent) i.get(obj), bundle);
                    return readAction;
                }
            } catch (Throwable e) {
                Log.e(TAG, "Unable to access notification actions", e);
                j = true;
            }
        }
        return null;
    }

    private static Object[] a(Notification notification) {
        synchronized (d) {
            if (a()) {
                try {
                    Object[] objArr = (Object[]) f.get(notification);
                    return objArr;
                } catch (Throwable e) {
                    Log.e(TAG, "Unable to access notification actions", e);
                    j = true;
                    return null;
                }
            }
            return null;
        }
    }

    private static boolean a() {
        boolean z = true;
        if (j) {
            return false;
        }
        try {
            if (f == null) {
                e = Class.forName("android.app.Notification$Action");
                g = e.getDeclaredField("icon");
                h = e.getDeclaredField("title");
                i = e.getDeclaredField("actionIntent");
                f = Notification.class.getDeclaredField("actions");
                f.setAccessible(true);
            }
        } catch (Throwable e) {
            Log.e(TAG, "Unable to access notification actions", e);
            j = true;
        } catch (Throwable e2) {
            Log.e(TAG, "Unable to access notification actions", e2);
            j = true;
        }
        if (j) {
            z = false;
        }
        return z;
    }

    static Action a(Bundle bundle) {
        boolean z = false;
        Bundle bundle2 = bundle.getBundle("extras");
        if (bundle2 != null) {
            z = bundle2.getBoolean("android.support.allowGeneratedReplies", false);
        }
        return new Action(bundle.getInt("icon"), bundle.getCharSequence("title"), (PendingIntent) bundle.getParcelable("actionIntent"), bundle.getBundle("extras"), a(a(bundle, "remoteInputs")), a(a(bundle, "dataOnlyRemoteInputs")), z);
    }

    static Bundle a(Action action) {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        bundle2.putInt("icon", action.getIcon());
        bundle2.putCharSequence("title", action.getTitle());
        bundle2.putParcelable("actionIntent", action.getActionIntent());
        if (action.getExtras() != null) {
            bundle = new Bundle(action.getExtras());
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        bundle2.putBundle("extras", bundle);
        bundle2.putParcelableArray("remoteInputs", a(action.getRemoteInputs()));
        return bundle2;
    }

    private static RemoteInput b(Bundle bundle) {
        ArrayList stringArrayList = bundle.getStringArrayList("allowedDataTypes");
        Set hashSet = new HashSet();
        if (stringArrayList != null) {
            Iterator it = stringArrayList.iterator();
            while (it.hasNext()) {
                hashSet.add((String) it.next());
            }
        }
        return new RemoteInput(bundle.getString("resultKey"), bundle.getCharSequence("label"), bundle.getCharSequenceArray("choices"), bundle.getBoolean("allowFreeFormInput"), bundle.getBundle("extras"), hashSet);
    }

    private static Bundle a(RemoteInput remoteInput) {
        Bundle bundle = new Bundle();
        bundle.putString("resultKey", remoteInput.getResultKey());
        bundle.putCharSequence("label", remoteInput.getLabel());
        bundle.putCharSequenceArray("choices", remoteInput.getChoices());
        bundle.putBoolean("allowFreeFormInput", remoteInput.getAllowFreeFormInput());
        bundle.putBundle("extras", remoteInput.getExtras());
        Set<String> allowedDataTypes = remoteInput.getAllowedDataTypes();
        if (!(allowedDataTypes == null || allowedDataTypes.isEmpty())) {
            ArrayList arrayList = new ArrayList(allowedDataTypes.size());
            for (String add : allowedDataTypes) {
                arrayList.add(add);
            }
            bundle.putStringArrayList("allowedDataTypes", arrayList);
        }
        return bundle;
    }

    private static RemoteInput[] a(Bundle[] bundleArr) {
        if (bundleArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[bundleArr.length];
        for (int i = 0; i < bundleArr.length; i++) {
            remoteInputArr[i] = b(bundleArr[i]);
        }
        return remoteInputArr;
    }

    private static Bundle[] a(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[remoteInputArr.length];
        for (int i = 0; i < remoteInputArr.length; i++) {
            bundleArr[i] = a(remoteInputArr[i]);
        }
        return bundleArr;
    }

    private static Bundle[] a(Bundle bundle, String str) {
        Parcelable[] parcelableArray = bundle.getParcelableArray(str);
        if ((parcelableArray instanceof Bundle[]) || parcelableArray == null) {
            return (Bundle[]) parcelableArray;
        }
        Bundle[] bundleArr = (Bundle[]) Arrays.copyOf(parcelableArray, parcelableArray.length, Bundle[].class);
        bundle.putParcelableArray(str, bundleArr);
        return bundleArr;
    }
}
