package com.meizu.cloud.pushsdk.notification.android;

import android.app.Notification.Builder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.AbstractPushNotification;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;

public class AndroidStandardNotification extends AbstractPushNotification {
    public AndroidStandardNotification(Context context, PushNotificationBuilder pushNotificationBuilder) {
        super(context, pushNotificationBuilder);
    }

    protected void appIconSettingBuilder(Builder builder, MessageV3 messageV3) {
        AppIconSetting appIconSetting = messageV3.getmAppIconSetting();
        if (appIconSetting == null) {
            return;
        }
        Bitmap appIcon;
        if (appIconSetting.isDefaultLargeIcon()) {
            appIcon = (this.pushNotificationBuilder == null || this.pushNotificationBuilder.getmLargIcon() == 0) ? getAppIcon(this.context, messageV3.getUploadDataPackageName()) : BitmapFactory.decodeResource(this.context.getResources(), this.pushNotificationBuilder.getmLargIcon());
            builder.setLargeIcon(appIcon);
        } else if (Thread.currentThread() != this.context.getMainLooper().getThread()) {
            appIcon = getBitmapFromURL(appIconSetting.getLargeIconUrl());
            if (appIcon != null) {
                a.a("AbstractPushNotification", "On other Thread down load largeIcon image success");
                builder.setLargeIcon(appIcon);
                return;
            }
            builder.setLargeIcon(getAppIcon(this.context, messageV3.getUploadDataPackageName()));
        }
    }
}
