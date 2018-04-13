package com.meizu.cloud.pushsdk.handler.impl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.NotificationService;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.AbstractAppLogicListener;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.PictureNotification;
import com.meizu.cloud.pushsdk.notification.PushNotification;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.notification.StandardNotificationV2;
import com.meizu.cloud.pushsdk.notification.android.AndroidExpandablePicNotification;
import com.meizu.cloud.pushsdk.notification.android.AndroidExpandableTextNotification;
import com.meizu.cloud.pushsdk.notification.android.AndroidStandardNotification;
import com.meizu.cloud.pushsdk.notification.android.AndroidVideoNotification;
import com.meizu.cloud.pushsdk.notification.flyme.ExpandablePicNotification;
import com.meizu.cloud.pushsdk.notification.flyme.ExpandableTextNotification;
import com.meizu.cloud.pushsdk.notification.flyme.StandardNotification;
import com.meizu.cloud.pushsdk.notification.model.styleenum.BaseStyleModel;
import com.meizu.cloud.pushsdk.notification.model.styleenum.InnerStyleLayout;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.meizu.cloud.pushsdk.util.UxIPUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageV3Handler extends AbstractMessageHandler<MessageV3> {
    public MessageV3Handler(Context context, AbstractAppLogicListener abstractAppLogicListener) {
        super(context, abstractAppLogicListener);
    }

    protected MessageV3 getMessage(Intent intent) {
        String stringExtra;
        if (PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_SHOW_V3.equals(getIntentMethod(intent))) {
            stringExtra = intent.getStringExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE);
        } else {
            stringExtra = intent.getStringExtra("message");
        }
        return MessageV3.parse(context().getPackageName(), getPushServiceDefaultPackageName(intent), getPushTimestamp(intent), getDeviceId(intent), getTaskId(intent), getSeqId(intent), stringExtra);
    }

    protected void unsafeSend(MessageV3 messageV3, PushNotification pushNotification) {
        if (pushNotification != null) {
            pushNotification.show(messageV3);
            appLogicListener().onNotificationArrived(context(), messageV3.getTitle(), messageV3.getContent(), selfDefineContentString(messageV3.getWebUrl(), messageV3.getParamsMap()));
        }
    }

    protected PushNotification onCreateNotification(MessageV3 messageV3) {
        PushNotificationBuilder pushNotificationBuilder = new PushNotificationBuilder();
        appLogicListener().onUpdateNotificationBuilder(pushNotificationBuilder);
        PushNotification pushNotification = null;
        if (messageV3.getmNotificationStyle() != null) {
            int baseStyle = messageV3.getmNotificationStyle().getBaseStyle();
            if (BaseStyleModel.FLYME.getCode() == baseStyle) {
                baseStyle = messageV3.getmNotificationStyle().getInnerStyle();
                if (InnerStyleLayout.EXPANDABLE_STANDARD.getCode() == baseStyle) {
                    a.a("AbstractMessageHandler", "show Standard Notification with Expandable disable");
                    pushNotification = new StandardNotification(context(), pushNotificationBuilder);
                } else if (InnerStyleLayout.EXPANDABLE_TEXT.getCode() == baseStyle) {
                    a.a("AbstractMessageHandler", "show Standard Notification with Expandable Text");
                    pushNotification = new ExpandableTextNotification(context(), pushNotificationBuilder);
                } else if (InnerStyleLayout.EXPANDABLE_PIC.getCode() == baseStyle) {
                    a.a("AbstractMessageHandler", "show Standard Notification with Expandable Picture");
                    pushNotification = new ExpandablePicNotification(context(), pushNotificationBuilder);
                } else if (InnerStyleLayout.EXPANDABLE_VIDEO.getCode() == baseStyle) {
                    a.a("AbstractMessageHandler", "show Flyme Video notification");
                    pushNotification = new AndroidVideoNotification(context(), pushNotificationBuilder);
                }
            } else if (BaseStyleModel.PURE_PICTURE.getCode() == baseStyle) {
                pushNotification = new PictureNotification(context(), pushNotificationBuilder);
                a.a("AbstractMessageHandler", "show Pure Picture Notification");
            } else if (BaseStyleModel.ANDROID.getCode() == baseStyle) {
                baseStyle = messageV3.getmNotificationStyle().getInnerStyle();
                if (InnerStyleLayout.EXPANDABLE_STANDARD.getCode() == baseStyle) {
                    a.a("AbstractMessageHandler", "show Android  Notification with Expandable disable");
                    pushNotification = new AndroidStandardNotification(context(), pushNotificationBuilder);
                } else if (InnerStyleLayout.EXPANDABLE_TEXT.getCode() == baseStyle) {
                    a.a("AbstractMessageHandler", "show Android  Notification with Expandable Text");
                    pushNotification = new AndroidExpandableTextNotification(context(), pushNotificationBuilder);
                } else if (InnerStyleLayout.EXPANDABLE_PIC.getCode() == baseStyle) {
                    a.a("AbstractMessageHandler", "show Android  Notification with Expandable Picture");
                    pushNotification = new AndroidExpandablePicNotification(context(), pushNotificationBuilder);
                } else if (InnerStyleLayout.EXPANDABLE_VIDEO.getCode() == baseStyle) {
                    a.a("AbstractMessageHandler", "show Flyme Video notification");
                    pushNotification = new AndroidVideoNotification(context(), pushNotificationBuilder);
                }
            }
        }
        if (pushNotification != null) {
            return pushNotification;
        }
        a.d("AbstractMessageHandler", "use standard v2 notification");
        return new StandardNotificationV2(context(), pushNotificationBuilder);
    }

    protected boolean canSendMessage(MessageV3 messageV3) {
        Object uriPackageName = messageV3.getUriPackageName();
        if (TextUtils.isEmpty(uriPackageName)) {
            return true;
        }
        return MzSystemUtils.isPackageInstalled(context(), uriPackageName);
    }

    protected int scheduleNotificationStatus(MessageV3 messageV3) {
        if (messageV3.getmTimeDisplaySetting() == null || !messageV3.getmTimeDisplaySetting().isTimeDisplay()) {
            return 0;
        }
        if (System.currentTimeMillis() > Long.valueOf(messageV3.getmTimeDisplaySetting().getEndShowTime()).longValue()) {
            UxIPUtils.notificationEvent(context(), "schedule notification expire", (int) PushConstants.EXPIRE_NOTIFICATION, messageV3.getTaskId(), messageV3.getDeviceId());
            return 1;
        } else if (System.currentTimeMillis() > Long.valueOf(messageV3.getmTimeDisplaySetting().getStartShowTime()).longValue()) {
            UxIPUtils.notificationEvent(context(), "schedule notification on time", (int) PushConstants.ONTIME_NOTIFICATION, messageV3.getTaskId(), messageV3.getDeviceId());
            return 2;
        } else {
            UxIPUtils.notificationEvent(context(), "schedule notification delay", (int) PushConstants.DELAY_NOTIFICATION, messageV3.getTaskId(), messageV3.getDeviceId());
            return 3;
        }
    }

    protected void scheduleShowNotification(MessageV3 messageV3) {
        Context context = context();
        context();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(context(), NotificationService.class);
        intent.setPackage(messageV3.getPackageName());
        intent.addCategory(messageV3.getPackageName());
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        intent.putExtra("command_type", "reflect_receiver");
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        intent.putExtra(PushConstants.EXTRA_APP_PUSH_SCHEDULE_NOTIFICATION_MESSAGE, messageV3);
        intent.putExtra(PushConstants.MZ_PUSH_MESSAGE_METHOD, PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_SCHEDULE_NOTIFICATION);
        PendingIntent service = PendingIntent.getService(context(), 0, intent, 1073741824);
        String startShowTime = messageV3.getmTimeDisplaySetting().getStartShowTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = null;
        if (!TextUtils.isEmpty(startShowTime)) {
            str = simpleDateFormat.format(new Date(Long.valueOf(startShowTime).longValue()));
        }
        long longValue = Long.valueOf(startShowTime).longValue() - System.currentTimeMillis();
        a.a("AbstractMessageHandler", "after " + (longValue / 1000) + " seconds Notification AlarmManager execute At " + str);
        if (VERSION.SDK_INT >= 19) {
            a.a("AbstractMessageHandler", "setAlarmManager setWindow ELAPSED_REALTIME_WAKEUP");
            alarmManager.setExact(2, longValue + SystemClock.elapsedRealtime(), service);
            return;
        }
        alarmManager.set(2, longValue + SystemClock.elapsedRealtime(), service);
    }

    public boolean messageMatch(Intent intent) {
        a.a("AbstractMessageHandler", "start MessageV3Handler match");
        if (!canReceiveMessage(0, getPushServiceDefaultPackageName(intent))) {
            return false;
        }
        if (PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_SHOW_V3.equals(getIntentMethod(intent))) {
            return true;
        }
        if (!TextUtils.isEmpty(getIntentMethod(intent))) {
            return false;
        }
        Object stringExtra = intent.getStringExtra("message");
        if (TextUtils.isEmpty(stringExtra) || !isNotificationJson(stringExtra)) {
            return false;
        }
        a.d("AbstractMessageHandler", "old cloud notification message");
        return true;
    }

    public int getProcessorType() {
        return 4;
    }

    protected void onBeforeEvent(MessageV3 messageV3) {
        UxIPUtils.onReceivePushMessageEvent(context(), messageV3.getUploadDataPackageName(), messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp());
    }

    protected void onAfterEvent(MessageV3 messageV3) {
        UxIPUtils.onShowPushMessageEvent(context(), messageV3.getUploadDataPackageName(), messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp());
    }
}
