package com.meizu.cloud.pushsdk.notification;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.PowerManager;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.networking.AndroidNetworking;
import com.meizu.cloud.pushsdk.networking.common.ANConstants;
import com.meizu.cloud.pushsdk.networking.common.ANResponse;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.notification.util.NotificationUtils;
import com.meizu.cloud.pushsdk.notification.util.RProxy;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.meizu.cloud.pushsdk.util.PushPreferencesUtils;
import org.json.JSONObject;

public abstract class AbstractPushNotification implements PushNotification {
    protected static final String TAG = "AbstractPushNotification";
    protected Context context;
    protected Handler handler;
    private NotificationManager notificationManager;
    protected PushNotificationBuilder pushNotificationBuilder;

    protected AbstractPushNotification(Context context, PushNotificationBuilder pushNotificationBuilder) {
        this.pushNotificationBuilder = pushNotificationBuilder;
        this.context = context;
        this.handler = new Handler(context.getMainLooper());
        this.notificationManager = (NotificationManager) context.getSystemService("notification");
    }

    protected void buildExpandableContent(Builder builder, MessageV3 messageV3) {
    }

    protected void buildContentView(Notification notification, MessageV3 messageV3) {
    }

    protected void buildBigContentView(Notification notification, MessageV3 messageV3) {
    }

    protected void appIconSettingBuilder(Builder builder, MessageV3 messageV3) {
    }

    protected Notification construtNotificationFinal(MessageV3 messageV3, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        Notification build;
        Builder builder = new Builder(this.context);
        basicSettingBuilder(builder, messageV3, pendingIntent, pendingIntent2);
        advanceSettingBuilder(builder, messageV3);
        appIconSettingBuilder(builder, messageV3);
        buildExpandableContent(builder, messageV3);
        if (MinSdkChecker.isSupportNotificationBuild()) {
            build = builder.build();
        } else {
            build = builder.getNotification();
        }
        buildContentView(build, messageV3);
        buildBigContentView(build, messageV3);
        return build;
    }

    protected PendingIntent createClickIntent(MessageV3 messageV3) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        intent.putExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE, messageV3);
        intent.putExtra(PushConstants.MZ_PUSH_MESSAGE_METHOD, PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_PRIVATE);
        intent.setClassName(messageV3.getUploadDataPackageName(), MzSystemUtils.findReceiver(this.context, PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, messageV3.getUploadDataPackageName()));
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        return PendingIntent.getBroadcast(this.context, 0, intent, 1073741824);
    }

    protected PendingIntent createDeleteIntent(MessageV3 messageV3) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        intent.putExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE, messageV3);
        intent.putExtra(PushConstants.MZ_PUSH_MESSAGE_METHOD, PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_DELETE);
        intent.setClassName(messageV3.getPackageName(), MzSystemUtils.findReceiver(this.context, PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, messageV3.getPackageName()));
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        return PendingIntent.getBroadcast(this.context, 0, intent, 1073741824);
    }

    protected PendingIntent createStateIntent(MessageV3 messageV3) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        intent.putExtra(PushConstants.MZ_PUSH_NOTIFICATION_STATE_MESSAGE, messageV3.getNotificationMessage());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_TASK_ID, messageV3.getTaskId());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_SEQ_ID, messageV3.getSeqId());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_DEVICE_ID, messageV3.getDeviceId());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_PUSH_TIMESTAMP, messageV3.getPushTimestamp());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_SHOW_PACKAGE_NAME, messageV3.getUploadDataPackageName());
        intent.putExtra(PushConstants.MZ_PUSH_MESSAGE_METHOD, PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_STATE);
        intent.setClassName(messageV3.getPackageName(), MzSystemUtils.findReceiver(this.context, PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, messageV3.getPackageName()));
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        return PendingIntent.getBroadcast(this.context, 0, intent, 1073741824);
    }

    protected void basicSettingBuilder(Builder builder, MessageV3 messageV3, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        builder.setContentTitle(messageV3.getTitle());
        builder.setContentText(messageV3.getContent());
        builder.setTicker(messageV3.getContent());
        builder.setAutoCancel(true);
        if (MinSdkChecker.isSupportSendNotification()) {
            builder.setVisibility(1);
        }
        if (MinSdkChecker.isSupportSetDrawableSmallIcon()) {
            Icon loadSmallIcon = loadSmallIcon(messageV3.getUploadDataPackageName());
            if (loadSmallIcon != null) {
                builder.setSmallIcon(loadSmallIcon);
            } else {
                a.d(TAG, "cannot get " + messageV3.getUploadDataPackageName() + " smallIcon");
                builder.setSmallIcon(RProxy.stat_sys_third_app_notify(this.context));
            }
        } else {
            int stat_sys_third_app_notify = (this.pushNotificationBuilder == null || this.pushNotificationBuilder.getmStatusbarIcon() == 0) ? RProxy.stat_sys_third_app_notify(this.context) : this.pushNotificationBuilder.getmStatusbarIcon();
            builder.setSmallIcon(stat_sys_third_app_notify);
        }
        builder.setContentIntent(pendingIntent);
        builder.setDeleteIntent(pendingIntent2);
    }

    protected void advanceSettingBuilder(Builder builder, MessageV3 messageV3) {
        AdvanceSetting advanceSetting = messageV3.getmAdvanceSetting();
        if (advanceSetting != null) {
            if (advanceSetting.getNotifyType() != null) {
                boolean isVibrate = advanceSetting.getNotifyType().isVibrate();
                boolean isLights = advanceSetting.getNotifyType().isLights();
                boolean isSound = advanceSetting.getNotifyType().isSound();
                if (isVibrate || isLights || isSound) {
                    int i;
                    if (isVibrate) {
                        i = 2;
                    } else {
                        i = 0;
                    }
                    if (isLights) {
                        i |= 4;
                    }
                    if (isSound) {
                        i |= 1;
                    }
                    a.d(TAG, "current notification type is " + i);
                    builder.setDefaults(i);
                }
            }
            builder.setOngoing(!advanceSetting.isClearNotification());
            if (advanceSetting.isHeadUpNotification() && MinSdkChecker.isSupportNotificationBuild()) {
                builder.setPriority(2);
            }
        }
    }

    public Bitmap getBitmapFromURL(String str) {
        ANResponse executeForBitmap = AndroidNetworking.get(str).build().executeForBitmap();
        if (!executeForBitmap.isSuccess() || executeForBitmap.getResult() == null) {
            a.a(TAG, "ANRequest On other Thread down load largeIcon " + str + "image fail");
            return null;
        }
        a.a(TAG, "ANRequest On other Thread down load largeIcon " + str + "image " + (executeForBitmap.getResult() != null ? ANConstants.SUCCESS : "fail"));
        return (Bitmap) executeForBitmap.getResult();
    }

    @TargetApi(23)
    private Icon loadSmallIcon(String str) {
        Icon icon = null;
        try {
            int identifier = this.context.getPackageManager().getResourcesForApplication(str).getIdentifier(PushConstants.MZ_PUSH_NOTIFICATION_SMALL_ICON, "drawable", str);
            if (identifier != 0) {
                a.a(TAG, "get " + str + " smallIcon success resId " + identifier);
                icon = Icon.createWithResource(str, identifier);
            }
        } catch (Exception e) {
            a.d(TAG, "cannot load smallIcon form package " + str + " Error message " + e.getMessage());
        }
        return icon;
    }

    public Bitmap getAppIcon(Context context, String str) {
        try {
            return ((BitmapDrawable) context.getPackageManager().getApplicationIcon(str)).getBitmap();
        } catch (NameNotFoundException e) {
            a.a(TAG, "getappicon error " + e.getMessage());
            return ((BitmapDrawable) context.getApplicationInfo().loadIcon(context.getPackageManager())).getBitmap();
        }
    }

    protected boolean isOnMainThread() {
        return Thread.currentThread() == this.context.getMainLooper().getThread();
    }

    protected boolean isScreenOnAndUnlock() {
        PowerManager powerManager = (PowerManager) this.context.getSystemService("power");
        if (VERSION.SDK_INT >= 20 ? powerManager.isInteractive() : powerManager.isScreenOn()) {
            if (!((KeyguardManager) this.context.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
                return true;
            }
        }
        return false;
    }

    protected String getFlymeGreenNotificationSetting(MessageV3 messageV3) {
        String str = null;
        try {
            if (!TextUtils.isEmpty(messageV3.getNotificationMessage())) {
                str = new JSONObject(messageV3.getNotificationMessage()).getJSONObject("data").getJSONObject(PushConstants.EXTRA).getString("fns");
            }
        } catch (Exception e) {
            a.d(TAG, "parse flyme notifification setting error " + e.getMessage());
        }
        a.a(TAG, "current notification setting is " + str);
        return str;
    }

    @SuppressLint({"NewApi"})
    public void show(MessageV3 messageV3) {
        Notification construtNotificationFinal = construtNotificationFinal(messageV3, createClickIntent(messageV3), createDeleteIntent(messageV3));
        NotificationUtils.setInternalApp(construtNotificationFinal, true);
        NotificationUtils.setReplyIntent(construtNotificationFinal, createStateIntent(messageV3));
        construtNotificationFinal.extras.putString(PushConstants.EXTRA_ORIGINAL_NOTIFICATION_PACKAGE_NAME, messageV3.getUploadDataPackageName());
        construtNotificationFinal.extras.putString(PushConstants.EXTRA_FLYME_GREEN_NOTIFICATION_SETTING, getFlymeGreenNotificationSetting(messageV3));
        construtNotificationFinal.extras.putString(PushConstants.NOTIFICATION_EXTRA_TASK_ID, messageV3.getTaskId());
        construtNotificationFinal.extras.putString(PushConstants.NOTIFICATION_EXTRA_SEQ_ID, messageV3.getSeqId());
        construtNotificationFinal.extras.putString(PushConstants.NOTIFICATION_EXTRA_DEVICE_ID, messageV3.getDeviceId());
        construtNotificationFinal.extras.putString(PushConstants.NOTIFICATION_EXTRA_PUSH_TIMESTAMP, messageV3.getPushTimestamp());
        int currentTimeMillis = (int) System.currentTimeMillis();
        if (messageV3.isDiscard()) {
            if (PushPreferencesUtils.getDiscardNotificationId(this.context, messageV3.getPackageName()) == 0) {
                PushPreferencesUtils.putDiscardNotificationIdByPackageName(this.context, messageV3.getPackageName(), currentTimeMillis);
                a.a(TAG, "no notification show so put notification id " + currentTimeMillis);
            }
            if (!TextUtils.isEmpty(messageV3.getTaskId())) {
                if (PushPreferencesUtils.getDiscardNotificationTaskId(this.context, messageV3.getPackageName()) == 0) {
                    PushPreferencesUtils.putDiscardNotificationTaskId(this.context, messageV3.getPackageName(), Integer.valueOf(messageV3.getTaskId()).intValue());
                } else if (Integer.valueOf(messageV3.getTaskId()).intValue() < PushPreferencesUtils.getDiscardNotificationTaskId(this.context, messageV3.getPackageName())) {
                    a.a(TAG, "current package " + messageV3.getPackageName() + " taskid " + messageV3.getTaskId() + " dont show notification");
                    return;
                } else {
                    PushPreferencesUtils.putDiscardNotificationTaskId(this.context, messageV3.getPackageName(), Integer.valueOf(messageV3.getTaskId()).intValue());
                    currentTimeMillis = PushPreferencesUtils.getDiscardNotificationId(this.context, messageV3.getPackageName());
                }
            }
            a.a(TAG, "current package " + messageV3.getPackageName() + " notificationId=" + currentTimeMillis + " taskId=" + messageV3.getTaskId());
        }
        this.notificationManager.notify(currentTimeMillis, construtNotificationFinal);
        dismissFloatNotification(this.notificationManager, currentTimeMillis, messageV3);
    }

    protected void dismissFloatNotification(final NotificationManager notificationManager, final int i, MessageV3 messageV3) {
        AdvanceSetting advanceSetting = messageV3.getmAdvanceSetting();
        if (advanceSetting != null) {
            boolean isHeadUpNotification = advanceSetting.isHeadUpNotification();
            boolean isClearNotification = advanceSetting.isClearNotification();
            if (isHeadUpNotification && !isClearNotification) {
                messageV3.getmAdvanceSetting().setHeadUpNotification(false);
                messageV3.getmAdvanceSetting().getNotifyType().setSound(false);
                messageV3.getmAdvanceSetting().getNotifyType().setVibrate(false);
                final Notification construtNotificationFinal = construtNotificationFinal(messageV3, createClickIntent(messageV3), createDeleteIntent(messageV3));
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        notificationManager.notify(i, construtNotificationFinal);
                    }
                }, 5000);
            }
        }
    }
}
