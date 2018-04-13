package com.meizu.cloud.pushsdk.notification.android;

import android.app.Notification.BigPictureStyle;
import android.app.Notification.Builder;
import android.app.Notification.Style;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;

public class AndroidExpandablePicNotification extends AndroidStandardNotification {
    public AndroidExpandablePicNotification(Context context, PushNotificationBuilder pushNotificationBuilder) {
        super(context, pushNotificationBuilder);
    }

    protected void buildExpandableContent(Builder builder, MessageV3 messageV3) {
        if (MinSdkChecker.isSupportNotificationBuild()) {
            Style bigPictureStyle = new BigPictureStyle();
            if (messageV3.getmNotificationStyle() != null && !isOnMainThread() && !TextUtils.isEmpty(messageV3.getmNotificationStyle().getExpandableImageUrl())) {
                Bitmap bitmapFromURL = getBitmapFromURL(messageV3.getmNotificationStyle().getExpandableImageUrl());
                if (bitmapFromURL != null) {
                    bigPictureStyle.setBigContentTitle(messageV3.getTitle());
                    bigPictureStyle.setSummaryText(messageV3.getContent());
                    bigPictureStyle.bigPicture(bitmapFromURL);
                    builder.setStyle(bigPictureStyle);
                }
            }
        }
    }
}
