package com.meizu.cloud.pushsdk.handler.impl.notification;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.AbstractAppLogicListener;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.impl.AbstractMessageHandler;
import com.meizu.cloud.pushsdk.handler.impl.model.PlatformMessage;
import com.meizu.cloud.pushsdk.notification.MPushMessage;
import com.meizu.cloud.pushsdk.notification.PushNotification;
import com.meizu.cloud.pushsdk.util.PushPreferencesUtils;
import com.meizu.cloud.pushsdk.util.UxIPUtils;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.util.Map.Entry;

public class NotificationClickMessageHandler extends AbstractMessageHandler<MessageV3> {
    public NotificationClickMessageHandler(Context context, AbstractAppLogicListener abstractAppLogicListener) {
        super(context, abstractAppLogicListener);
    }

    protected MessageV3 getMessage(Intent intent) {
        MPushMessage mPushMessage;
        try {
            a.d("AbstractMessageHandler", "parse message V3");
            MessageV3 messageV3 = (MessageV3) intent.getParcelableExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE);
            if (messageV3 != null) {
                return messageV3;
            }
            a.d("AbstractMessageHandler", "parse MessageV2 to MessageV3");
            mPushMessage = (MPushMessage) intent.getSerializableExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE);
            return MessageV3.parse(getPushServiceDefaultPackageName(intent), getDeviceId(intent), mPushMessage.getTaskId(), mPushMessage);
        } catch (Exception e) {
            a.d("AbstractMessageHandler", "cannot get messageV3");
            if (null != null) {
                return null;
            }
            a.d("AbstractMessageHandler", "parse MessageV2 to MessageV3");
            mPushMessage = (MPushMessage) intent.getSerializableExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE);
            return MessageV3.parse(getPushServiceDefaultPackageName(intent), getDeviceId(intent), mPushMessage.getTaskId(), mPushMessage);
        } catch (Throwable th) {
            Throwable th2 = th;
            if (null == null) {
                a.d("AbstractMessageHandler", "parse MessageV2 to MessageV3");
                mPushMessage = (MPushMessage) intent.getSerializableExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE);
                MessageV3.parse(getPushServiceDefaultPackageName(intent), getDeviceId(intent), mPushMessage.getTaskId(), mPushMessage);
            }
        }
    }

    protected void unsafeSend(MessageV3 messageV3, PushNotification pushNotification) {
        PushPreferencesUtils.putDiscardNotificationIdByPackageName(context(), messageV3.getPackageName(), 0);
        Intent buildIntent = buildIntent(context(), messageV3);
        if (buildIntent != null) {
            buildIntent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            try {
                context().startActivity(buildIntent);
            } catch (Exception e) {
                a.d("AbstractMessageHandler", "Click message StartActivity error " + e.getMessage());
            }
        }
        if (!TextUtils.isEmpty(messageV3.getTitle()) && !TextUtils.isEmpty(messageV3.getContent()) && appLogicListener() != null) {
            appLogicListener().onNotificationClicked(context(), messageV3.getTitle(), messageV3.getContent(), selfDefineContentString(messageV3.getWebUrl(), messageV3.getParamsMap()));
        }
    }

    public boolean messageMatch(Intent intent) {
        a.a("AbstractMessageHandler", "start NotificationClickMessageHandler match");
        return PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_PRIVATE.equals(getIntentMethod(intent));
    }

    public int getProcessorType() {
        return 64;
    }

    protected void onBeforeEvent(MessageV3 messageV3) {
        UxIPUtils.onClickPushMessageEvent(context(), messageV3.getUploadDataPackageName(), TextUtils.isEmpty(messageV3.getDeviceId()) ? getDeviceId(null) : messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp());
    }

    private Intent buildIntent(Context context, MessageV3 messageV3) {
        String uploadDataPackageName;
        Intent intent;
        CharSequence uriPackageName = messageV3.getUriPackageName();
        if (TextUtils.isEmpty(uriPackageName)) {
            uploadDataPackageName = messageV3.getUploadDataPackageName();
        } else {
            CharSequence charSequence = uriPackageName;
        }
        a.a("AbstractMessageHandler", "openClassName is " + uploadDataPackageName);
        if (messageV3.getClickType() == 0) {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(uploadDataPackageName);
            if (launchIntentForPackage == null || messageV3.getParamsMap() == null) {
                intent = launchIntentForPackage;
            } else {
                for (Entry entry : messageV3.getParamsMap().entrySet()) {
                    a.a("AbstractMessageHandler", " launcher activity key " + ((String) entry.getKey()) + " value " + ((String) entry.getValue()));
                    if (!(TextUtils.isEmpty((CharSequence) entry.getKey()) || TextUtils.isEmpty((CharSequence) entry.getValue()))) {
                        launchIntentForPackage.putExtra((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                intent = launchIntentForPackage;
            }
        } else if (1 == messageV3.getClickType()) {
            Intent intent2 = new Intent();
            if (messageV3.getParamsMap() != null) {
                for (Entry entry2 : messageV3.getParamsMap().entrySet()) {
                    a.a("AbstractMessageHandler", " key " + ((String) entry2.getKey()) + " value " + ((String) entry2.getValue()));
                    if (!(TextUtils.isEmpty((CharSequence) entry2.getKey()) || TextUtils.isEmpty((CharSequence) entry2.getValue()))) {
                        intent2.putExtra((String) entry2.getKey(), (String) entry2.getValue());
                    }
                }
            }
            intent2.setClassName(uploadDataPackageName, messageV3.getActivity());
            a.a("AbstractMessageHandler", intent2.toUri(1));
            intent = intent2;
        } else if (2 == messageV3.getClickType()) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse(messageV3.getWebUrl()));
            String uriPackageName2 = messageV3.getUriPackageName();
            if (!TextUtils.isEmpty(uriPackageName2)) {
                intent.setPackage(uriPackageName2);
                a.a("AbstractMessageHandler", "set uri package " + uriPackageName2);
            }
        } else {
            if (3 == messageV3.getClickType()) {
                a.a("AbstractMessageHandler", "CLICK_TYPE_SELF_DEFINE_ACTION");
            }
            intent = null;
        }
        if (intent != null) {
            intent.putExtra(PushConstants.MZ_PUSH_PLATFROM_EXTRA, PlatformMessage.builder().taskId(messageV3.getTaskId()).build().toJson());
        }
        return intent;
    }
}
