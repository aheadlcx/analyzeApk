package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.InboxStyle;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompatBase.Action;
import android.support.v4.app.NotificationCompatBase.Action.Factory;
import android.support.v4.app.RemoteInputCompatBase.RemoteInput;
import android.util.Log;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class NotificationCompatJellybean {
    static final String EXTRA_ACTION_EXTRAS = "android.support.actionExtras";
    static final String EXTRA_GROUP_KEY = "android.support.groupKey";
    static final String EXTRA_GROUP_SUMMARY = "android.support.isGroupSummary";
    static final String EXTRA_LOCAL_ONLY = "android.support.localOnly";
    static final String EXTRA_REMOTE_INPUTS = "android.support.remoteInputs";
    static final String EXTRA_SORT_KEY = "android.support.sortKey";
    static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    private static final String KEY_ACTION_INTENT = "actionIntent";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_ICON = "icon";
    private static final String KEY_REMOTE_INPUTS = "remoteInputs";
    private static final String KEY_TITLE = "title";
    public static final String TAG = "NotificationCompat";
    private static Class<?> sActionClass;
    private static Field sActionIconField;
    private static Field sActionIntentField;
    private static Field sActionTitleField;
    private static boolean sActionsAccessFailed;
    private static Field sActionsField;
    private static final Object sActionsLock = new Object();
    private static Field sExtrasField;
    private static boolean sExtrasFieldAccessFailed;
    private static final Object sExtrasLock = new Object();

    public static class Builder implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor {
        private android.app.Notification.Builder b;
        private List<Bundle> mActionExtrasList = new ArrayList();
        private final Bundle mExtras;

        public Builder(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i2, int i3, boolean z, boolean z2, int i4, CharSequence charSequence4, boolean z3, Bundle bundle, String str, boolean z4, String str2) {
            boolean z5;
            android.app.Notification.Builder lights = new android.app.Notification.Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            if ((notification.flags & 2) != 0) {
                z5 = true;
            } else {
                z5 = false;
            }
            lights = lights.setOngoing(z5);
            if ((notification.flags & 8) != 0) {
                z5 = true;
            } else {
                z5 = false;
            }
            lights = lights.setOnlyAlertOnce(z5);
            if ((notification.flags & 16) != 0) {
                z5 = true;
            } else {
                z5 = false;
            }
            lights = lights.setAutoCancel(z5).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 128) != 0) {
                z5 = true;
            } else {
                z5 = false;
            }
            this.b = lights.setFullScreenIntent(pendingIntent2, z5).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(z2).setPriority(i4).setProgress(i2, i3, z);
            this.mExtras = new Bundle();
            if (bundle != null) {
                this.mExtras.putAll(bundle);
            }
            if (z3) {
                this.mExtras.putBoolean("android.support.localOnly", true);
            }
            if (str != null) {
                this.mExtras.putString("android.support.groupKey", str);
                if (z4) {
                    this.mExtras.putBoolean("android.support.isGroupSummary", true);
                } else {
                    this.mExtras.putBoolean("android.support.useSideChannel", true);
                }
            }
            if (str2 != null) {
                this.mExtras.putString("android.support.sortKey", str2);
            }
        }

        public void addAction(Action action) {
            this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.b, action));
        }

        public android.app.Notification.Builder getBuilder() {
            return this.b;
        }

        public Notification build() {
            Notification build = this.b.build();
            Bundle extras = NotificationCompatJellybean.getExtras(build);
            Bundle bundle = new Bundle(this.mExtras);
            for (String str : this.mExtras.keySet()) {
                if (extras.containsKey(str)) {
                    bundle.remove(str);
                }
            }
            extras.putAll(bundle);
            SparseArray buildActionExtrasMap = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if (buildActionExtrasMap != null) {
                NotificationCompatJellybean.getExtras(build).putSparseParcelableArray("android.support.actionExtras", buildActionExtrasMap);
            }
            return build;
        }
    }

    NotificationCompatJellybean() {
    }

    public static void addBigTextStyle(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, CharSequence charSequence, boolean z, CharSequence charSequence2, CharSequence charSequence3) {
        BigTextStyle bigText = new BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(charSequence).bigText(charSequence3);
        if (z) {
            bigText.setSummaryText(charSequence2);
        }
    }

    public static void addBigPictureStyle(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, CharSequence charSequence, boolean z, CharSequence charSequence2, Bitmap bitmap, Bitmap bitmap2, boolean z2) {
        BigPictureStyle bigPicture = new BigPictureStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(charSequence).bigPicture(bitmap);
        if (z2) {
            bigPicture.bigLargeIcon(bitmap2);
        }
        if (z) {
            bigPicture.setSummaryText(charSequence2);
        }
    }

    public static void addInboxStyle(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, CharSequence charSequence, boolean z, CharSequence charSequence2, ArrayList<CharSequence> arrayList) {
        InboxStyle bigContentTitle = new InboxStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(charSequence);
        if (z) {
            bigContentTitle.setSummaryText(charSequence2);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            bigContentTitle.addLine((CharSequence) it.next());
        }
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
        synchronized (sExtrasLock) {
            if (sExtrasFieldAccessFailed) {
                return null;
            }
            try {
                if (sExtrasField == null) {
                    Field declaredField = Notification.class.getDeclaredField(KEY_EXTRAS);
                    if (Bundle.class.isAssignableFrom(declaredField.getType())) {
                        declaredField.setAccessible(true);
                        sExtrasField = declaredField;
                    } else {
                        Log.e(TAG, "Notification.extras field is not of type Bundle");
                        sExtrasFieldAccessFailed = true;
                        return null;
                    }
                }
                Bundle bundle = (Bundle) sExtrasField.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    sExtrasField.set(notification, bundle);
                }
                return bundle;
            } catch (Throwable e) {
                Log.e(TAG, "Unable to access notification extras", e);
                sExtrasFieldAccessFailed = true;
                return null;
            } catch (Throwable e2) {
                Log.e(TAG, "Unable to access notification extras", e2);
                sExtrasFieldAccessFailed = true;
                return null;
            }
        }
    }

    public static Action readAction(Factory factory, RemoteInput.Factory factory2, int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle) {
        RemoteInput[] remoteInputArr = null;
        if (bundle != null) {
            remoteInputArr = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "android.support.remoteInputs"), factory2);
        }
        return factory.build(i, charSequence, pendingIntent, bundle, remoteInputArr);
    }

    public static Bundle writeActionAndGetExtras(android.app.Notification.Builder builder, Action action) {
        builder.addAction(action.getIcon(), action.getTitle(), action.getActionIntent());
        Bundle bundle = new Bundle(action.getExtras());
        if (action.getRemoteInputs() != null) {
            bundle.putParcelableArray("android.support.remoteInputs", RemoteInputCompatJellybean.toBundleArray(action.getRemoteInputs()));
        }
        return bundle;
    }

    public static int getActionCount(Notification notification) {
        int length;
        synchronized (sActionsLock) {
            Object[] actionObjectsLocked = getActionObjectsLocked(notification);
            length = actionObjectsLocked != null ? actionObjectsLocked.length : 0;
        }
        return length;
    }

    public static Action getAction(Notification notification, int i, Factory factory, RemoteInput.Factory factory2) {
        Action readAction;
        synchronized (sActionsLock) {
            try {
                Bundle bundle;
                Object obj = getActionObjectsLocked(notification)[i];
                Bundle extras = getExtras(notification);
                if (extras != null) {
                    SparseArray sparseParcelableArray = extras.getSparseParcelableArray("android.support.actionExtras");
                    if (sparseParcelableArray != null) {
                        bundle = (Bundle) sparseParcelableArray.get(i);
                        readAction = readAction(factory, factory2, sActionIconField.getInt(obj), (CharSequence) sActionTitleField.get(obj), (PendingIntent) sActionIntentField.get(obj), bundle);
                    }
                }
                bundle = null;
                readAction = readAction(factory, factory2, sActionIconField.getInt(obj), (CharSequence) sActionTitleField.get(obj), (PendingIntent) sActionIntentField.get(obj), bundle);
            } catch (Throwable e) {
                Log.e(TAG, "Unable to access notification actions", e);
                sActionsAccessFailed = true;
                return null;
            }
        }
        return readAction;
    }

    private static Object[] getActionObjectsLocked(Notification notification) {
        synchronized (sActionsLock) {
            if (ensureActionReflectionReadyLocked()) {
                try {
                    Object[] objArr = (Object[]) sActionsField.get(notification);
                    return objArr;
                } catch (Throwable e) {
                    Log.e(TAG, "Unable to access notification actions", e);
                    sActionsAccessFailed = true;
                    return null;
                }
            }
            return null;
        }
    }

    private static boolean ensureActionReflectionReadyLocked() {
        boolean z = true;
        if (sActionsAccessFailed) {
            return false;
        }
        try {
            if (sActionsField == null) {
                sActionClass = Class.forName("android.app.Notification$Action");
                sActionIconField = sActionClass.getDeclaredField(KEY_ICON);
                sActionTitleField = sActionClass.getDeclaredField("title");
                sActionIntentField = sActionClass.getDeclaredField(KEY_ACTION_INTENT);
                sActionsField = Notification.class.getDeclaredField("actions");
                sActionsField.setAccessible(true);
            }
        } catch (Throwable e) {
            Log.e(TAG, "Unable to access notification actions", e);
            sActionsAccessFailed = true;
        } catch (Throwable e2) {
            Log.e(TAG, "Unable to access notification actions", e2);
            sActionsAccessFailed = true;
        }
        if (sActionsAccessFailed) {
            z = false;
        }
        return z;
    }

    public static Action[] getActionsFromParcelableArrayList(ArrayList<Parcelable> arrayList, Factory factory, RemoteInput.Factory factory2) {
        if (arrayList == null) {
            return null;
        }
        Action[] newArray = factory.newArray(arrayList.size());
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = getActionFromBundle((Bundle) arrayList.get(i), factory, factory2);
        }
        return newArray;
    }

    private static Action getActionFromBundle(Bundle bundle, Factory factory, RemoteInput.Factory factory2) {
        return factory.build(bundle.getInt(KEY_ICON), bundle.getCharSequence("title"), (PendingIntent) bundle.getParcelable(KEY_ACTION_INTENT), bundle.getBundle(KEY_EXTRAS), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, KEY_REMOTE_INPUTS), factory2));
    }

    public static ArrayList<Parcelable> getParcelableArrayListForActions(Action[] actionArr) {
        if (actionArr == null) {
            return null;
        }
        ArrayList<Parcelable> arrayList = new ArrayList(actionArr.length);
        for (Action bundleForAction : actionArr) {
            arrayList.add(getBundleForAction(bundleForAction));
        }
        return arrayList;
    }

    private static Bundle getBundleForAction(Action action) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_ICON, action.getIcon());
        bundle.putCharSequence("title", action.getTitle());
        bundle.putParcelable(KEY_ACTION_INTENT, action.getActionIntent());
        bundle.putBundle(KEY_EXTRAS, action.getExtras());
        bundle.putParcelableArray(KEY_REMOTE_INPUTS, RemoteInputCompatJellybean.toBundleArray(action.getRemoteInputs()));
        return bundle;
    }

    public static boolean getLocalOnly(Notification notification) {
        return getExtras(notification).getBoolean("android.support.localOnly");
    }

    public static String getGroup(Notification notification) {
        return getExtras(notification).getString("android.support.groupKey");
    }

    public static boolean isGroupSummary(Notification notification) {
        return getExtras(notification).getBoolean("android.support.isGroupSummary");
    }

    public static String getSortKey(Notification notification) {
        return getExtras(notification).getString("android.support.sortKey");
    }
}
