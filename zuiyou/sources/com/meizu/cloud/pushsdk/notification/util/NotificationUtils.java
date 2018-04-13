package com.meizu.cloud.pushsdk.notification.util;

import android.app.Notification;
import android.app.PendingIntent;
import com.meizu.cloud.a.a;
import java.lang.reflect.Field;

public class NotificationUtils {
    private static final String TAG = "NotificationUtils";
    private static Field mFlymeNotification;
    private static Field mInternalApp;
    private static Field mReplyIntent;

    static {
        mFlymeNotification = null;
        mInternalApp = null;
        try {
            mFlymeNotification = Notification.class.getDeclaredField("mFlymeNotification");
            mInternalApp = Class.forName("android.app.NotificationExt").getDeclaredField("internalApp");
            mInternalApp.setAccessible(true);
            mReplyIntent = Notification.class.getDeclaredField("replyIntent");
            mReplyIntent.setAccessible(true);
        } catch (NoSuchFieldException e) {
            a.d(TAG, "init NotificationUtils error " + e.getMessage());
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public static void setReplyIntent(Notification notification, PendingIntent pendingIntent) {
        if (mReplyIntent != null) {
            try {
                mReplyIntent.set(notification, pendingIntent);
            } catch (IllegalAccessException e) {
                a.d(TAG, "setReplyIntent error " + e.getMessage());
            }
        }
    }

    public static void setInternalApp(Notification notification, boolean z) {
        if (mFlymeNotification != null && mInternalApp != null) {
            try {
                mInternalApp.set(mFlymeNotification.get(notification), Integer.valueOf(z ? 1 : 0));
            } catch (IllegalAccessException e) {
                a.d(TAG, "setInternalApp error " + e.getMessage());
            }
        }
    }
}
