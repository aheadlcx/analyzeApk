package com.meizu.cloud.pushsdk.notification.flyme;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.AbstractPushNotification;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.notification.util.RProxy;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;

public class StandardNotification extends AbstractPushNotification {
    public StandardNotification(Context context, PushNotificationBuilder pushNotificationBuilder) {
        super(context, pushNotificationBuilder);
    }

    protected void buildContentView(Notification notification, MessageV3 messageV3) {
        if (MinSdkChecker.isSupportNotificationBuild()) {
            RemoteViews remoteViews = new RemoteViews(this.context.getPackageName(), RProxy.push_expandable_big_image_notification(this.context));
            remoteViews.setTextViewText(RProxy.push_big_notification_title(this.context), messageV3.getTitle());
            remoteViews.setTextViewText(RProxy.push_big_notification_content(this.context), messageV3.getContent());
            remoteViews.setLong(RProxy.push_big_notification_date(this.context), "setTime", System.currentTimeMillis());
            appLargeIconSetting(remoteViews, messageV3);
            remoteViews.setViewVisibility(RProxy.push_big_bigview_defaultView(this.context), 8);
            remoteViews.setViewVisibility(RProxy.push_big_bigtext_defaultView(this.context), 8);
            notification.contentView = remoteViews;
        }
    }

    protected void appLargeIconSetting(RemoteViews remoteViews, MessageV3 messageV3) {
        if (messageV3.getmAppIconSetting() == null || isOnMainThread()) {
            remoteViews.setImageViewBitmap(RProxy.push_big_notification_icon(this.context), getAppIcon(this.context, messageV3.getUploadDataPackageName()));
        } else if (messageV3.getmAppIconSetting().isDefaultLargeIcon()) {
            remoteViews.setImageViewBitmap(RProxy.push_big_notification_icon(this.context), getAppIcon(this.context, messageV3.getUploadDataPackageName()));
        } else {
            Bitmap bitmapFromURL = getBitmapFromURL(messageV3.getmAppIconSetting().getLargeIconUrl());
            if (bitmapFromURL != null) {
                remoteViews.setImageViewBitmap(RProxy.push_big_notification_icon(this.context), bitmapFromURL);
            } else {
                remoteViews.setImageViewBitmap(RProxy.push_big_notification_icon(this.context), getAppIcon(this.context, messageV3.getUploadDataPackageName()));
            }
        }
    }
}
