package com.meizu.cloud.pushsdk.util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.impl.model.PlatformMessage;
import com.meizu.cloud.pushsdk.handler.impl.model.Statics;
import com.meizu.cloud.pushsdk.notification.MPushMessage;
import com.meizu.cloud.pushsdk.pushtracer.QuickTracker;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.meizu.cloud.pushsdk.pushtracer.event.PushEvent;
import com.meizu.cloud.pushsdk.pushtracer.event.PushEvent.Builder;
import com.umeng.analytics.b.g;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class UxIPUtils {
    private static final String TAG = "UxIPUtils";

    public static void init(Context context) {
    }

    public static void notificationEvent(Context context, String str, int i, String str2, String str3) {
        if (!TextUtils.isEmpty(str2)) {
            onRecordMessageFlow(context, context.getPackageName(), str3, str2, PushManager.TAG, str, i);
        }
    }

    public static void notificationEvent(Context context, Intent intent, String str, int i) {
        notificationEvent(context, intent, PushManager.TAG, str, i);
    }

    public static void notificationEvent(Context context, Intent intent, String str, String str2, int i) {
        if (!TextUtils.isEmpty(getTaskId(intent))) {
            onRecordMessageFlow(context, context.getPackageName(), intent.getStringExtra(PushConstants.MZ_PUSH_MESSAGE_STATISTICS_IMEI_KEY), getTaskId(intent), str, str2, i);
        }
    }

    public static String getTaskId(Intent intent) {
        CharSequence stringExtra = intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_TASK_ID);
        if (TextUtils.isEmpty(stringExtra)) {
            try {
                MPushMessage mPushMessage = (MPushMessage) intent.getSerializableExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE);
                if (mPushMessage != null) {
                    return mPushMessage.getTaskId();
                }
            } catch (Exception e) {
                Exception exception = e;
                String str = "no push platform task";
                a.d(TAG, "paese MessageV2 error " + exception.getMessage());
                return str;
            }
        }
        return stringExtra;
    }

    public static void onRecordMessageFlow(Context context, String str, String str2, String str3, String str4, String str5, int i) {
        Map hashMap = new HashMap();
        hashMap.put(Statics.TASK_ID, str3);
        hashMap.put("deviceId", str2);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put(g.e, str);
        hashMap.put("pushsdk_version", str4);
        hashMap.put("push_info", str5);
        hashMap.put("push_info_type", String.valueOf(i));
        onLogEvent(context, false, "notification_service_message", hashMap);
    }

    public static void onShowPushMessageEvent(Context context, String str, String str2) {
        PlatformMessage buildPlatformMessage = buildPlatformMessage(str2);
        onShowPushMessageEvent(context, str, buildPlatformMessage.getDeviceId(), buildPlatformMessage.getTaskId(), buildPlatformMessage.getSeqId(), buildPlatformMessage.getPushTimesTamp());
    }

    public static PlatformMessage buildPlatformMessage(String str) {
        String str2 = null;
        PlatformMessage platformMessage = new PlatformMessage();
        if (TextUtils.isEmpty(str)) {
            a.d(TAG, "the platformExtra is empty");
        } else {
            try {
                String string;
                String string2;
                String string3;
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("task_id")) {
                    string = jSONObject.getString("task_id");
                } else {
                    string = null;
                }
                if (jSONObject.has(PlatformMessage.PLATFORM_SEQ_ID)) {
                    string2 = jSONObject.getString(PlatformMessage.PLATFORM_SEQ_ID);
                } else {
                    string2 = null;
                }
                if (jSONObject.has(PlatformMessage.PLATFORM_PUSH_TIMESTAMP)) {
                    string3 = jSONObject.getString(PlatformMessage.PLATFORM_PUSH_TIMESTAMP);
                } else {
                    string3 = null;
                }
                if (jSONObject.has("device_id")) {
                    str2 = jSONObject.getString("device_id");
                }
                platformMessage = PlatformMessage.builder().taskId(string).deviceId(str2).pushTimesTamp(string3).seqId(string2).build();
            } catch (Exception e) {
                a.d(TAG, "the platformExtra parse error");
            }
        }
        return platformMessage;
    }

    public static void onShowPushMessageEvent(Context context, String str, String str2, String str3, String str4, String str5) {
        onLogEvent(context, true, str, str2, str3, str4, Parameters.SHOW_PUSH_MESSAGE, str5);
    }

    public static void onDeletePushMessageEvent(Context context, String str, String str2, String str3, String str4, String str5) {
        onLogEvent(context, true, str, str2, str3, str4, Parameters.DELETE_PUSH_MESSAGE, str5);
    }

    public static void onReceivePushMessageEvent(Context context, String str, String str2, String str3, String str4, String str5) {
        onLogEvent(context, false, str, str2, str3, str4, Parameters.RECEIVE_PUSH_EVNET, str5);
    }

    public static void onReceiveThroughMessage(Context context, String str, String str2, String str3, String str4, String str5) {
        onLogEvent(context, true, str, str2, str3, str4, Parameters.RECEIVE_PUSH_EVNET, str5);
    }

    public static void onClickPushMessageEvent(Context context, String str, String str2, String str3, String str4, String str5) {
        onLogEvent(context, true, str, str2, str3, str4, Parameters.CLICK_PUSH_MESSAGE, str5);
    }

    public static void onInvalidPushMessage(Context context, String str, String str2, String str3, String str4, String str5) {
        onLogEvent(context, true, str, str2, str3, str4, Parameters.INVALID_PUSH_MESSAGE, str5);
    }

    public static void onShowInBoxPushMessage(Context context, String str, String str2, String str3, String str4, String str5) {
        onLogEvent(context, false, str, str2, str3, str4, Parameters.SHOWINBOX_PUSH_MESSAGE, str5);
    }

    public static void onNoShowPushMessage(Context context, String str, String str2, String str3, String str4, String str5) {
        onLogEvent(context, false, str, str2, str3, str4, Parameters.NOSHOW_PUSH_MESSAGE, str5);
    }

    public static void onReceiveServerMessage(Context context, String str, String str2, String str3, String str4, String str5) {
        onLogEvent(context, false, str, str2, str3, str4, Parameters.RECEIVER_SERVER_MESSAGE, str5);
    }

    public static void onLogEvent(Context context, boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        Map hashMap = new HashMap();
        hashMap.put(Statics.TASK_ID, str3);
        hashMap.put("deviceId", str2);
        String str7 = "timestamp";
        if (TextUtils.isEmpty(str6)) {
            str6 = String.valueOf(System.currentTimeMillis() / 1000);
        }
        hashMap.put(str7, str6);
        hashMap.put(g.e, str);
        hashMap.put("pushsdk_version", PushManager.TAG);
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put(PlatformMessage.PLATFORM_SEQ_ID, str4);
        }
        onLogEvent(context, z, str5, hashMap);
    }

    public static void onLogEvent(Context context, boolean z, String str, Map<String, String> map) {
        a.d(TAG, "onLogEvent eventName [" + str + "] properties = " + map);
        if (!"notification_service_message".equals(str)) {
            QuickTracker.getAndroidTrackerClassic(context, null).track(((Builder) PushEvent.builder().eventName(str).timestamp(Long.valueOf((String) map.get("timestamp")).longValue())).eventCreateTime(String.valueOf(System.currentTimeMillis() / 1000)).deviceId((String) map.get("deviceId")).packageName((String) map.get(g.e)).pushsdkVersion((String) map.get("pushsdk_version")).taskId((String) map.get(Statics.TASK_ID)).seqId(TextUtils.isEmpty((CharSequence) map.get(PlatformMessage.PLATFORM_SEQ_ID)) ? "" : (String) map.get(PlatformMessage.PLATFORM_SEQ_ID)).messageSeq(String.valueOf(PushPreferencesUtils.getMessageSeqInCrease(context, (String) map.get(g.e)))).build(), z);
        }
    }
}
