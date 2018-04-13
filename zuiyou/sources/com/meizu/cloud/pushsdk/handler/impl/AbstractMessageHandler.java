package com.meizu.cloud.pushsdk.handler.impl;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.AbstractAppLogicListener;
import com.meizu.cloud.pushsdk.handler.MessageHandler;
import com.meizu.cloud.pushsdk.notification.PushNotification;
import com.meizu.cloud.pushsdk.pushtracer.utils.Util;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.meizu.cloud.pushsdk.util.PushPreferencesUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public abstract class AbstractMessageHandler<T> implements MessageHandler {
    public static final int MESSAGE_TYPE_NOTIFICATION_CLICK = 64;
    public static final int MESSAGE_TYPE_NOTIFICATION_DELETE = 128;
    public static final int MESSAGE_TYPE_NOTIFICATION_STATE = 32768;
    public static final int MESSAGE_TYPE_PUSH_REGISTER_STATUS = 512;
    public static final int MESSAGE_TYPE_PUSH_SERVICE_V2 = 2;
    public static final int MESSAGE_TYPE_PUSH_SERVICE_V3 = 4;
    public static final int MESSAGE_TYPE_PUSH_SUBALIAS_STATUS = 4096;
    public static final int MESSAGE_TYPE_PUSH_SUBTAGS_STATUS = 2048;
    public static final int MESSAGE_TYPE_PUSH_SWITCH_STATUS = 256;
    public static final int MESSAGE_TYPE_PUSH_UNREGISTER_STATUS = 1024;
    public static final int MESSAGE_TYPE_RECEIVE_NOTIFY_MESSAGE = 16384;
    public static final int MESSAGE_TYPE_REGISTER = 16;
    public static final int MESSAGE_TYPE_SCHEDULE_NOTIFICATION = 8192;
    public static final int MESSAGE_TYPE_THROUGH = 8;
    public static final int MESSAGE_TYPE_UNREGISTER = 32;
    public static final int MESSAGE_TYPE_UPLOAD_FILE_LOG = 65536;
    public static final int SCHEDULE_OFF = 0;
    public static final int SCHEDULE_ON_DELAY = 3;
    public static final int SCHEDULE_ON_EXPIRE = 1;
    public static final int SCHEDULE_ON_TIME = 2;
    protected static final String TAG = "AbstractMessageHandler";
    private AbstractAppLogicListener abstractAppLogicListener;
    private Context context;
    private Map<Integer, String> messageHandlerMap;

    protected abstract T getMessage(Intent intent);

    protected abstract void unsafeSend(T t, PushNotification pushNotification);

    protected AbstractMessageHandler(Context context) {
        this(context, null);
    }

    protected AbstractMessageHandler(Context context, AbstractAppLogicListener abstractAppLogicListener) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        this.context = context.getApplicationContext();
        this.abstractAppLogicListener = abstractAppLogicListener;
        this.messageHandlerMap = new HashMap();
        this.messageHandlerMap.put(Integer.valueOf(2), "MESSAGE_TYPE_PUSH_SERVICE_V2");
        this.messageHandlerMap.put(Integer.valueOf(4), "MESSAGE_TYPE_PUSH_SERVICE_V3");
        this.messageHandlerMap.put(Integer.valueOf(16), "MESSAGE_TYPE_REGISTER");
        this.messageHandlerMap.put(Integer.valueOf(32), "MESSAGE_TYPE_UNREGISTER");
        this.messageHandlerMap.put(Integer.valueOf(8), "MESSAGE_TYPE_THROUGH");
        this.messageHandlerMap.put(Integer.valueOf(64), "MESSAGE_TYPE_NOTIFICATION_CLICK");
        this.messageHandlerMap.put(Integer.valueOf(128), "MESSAGE_TYPE_NOTIFICATION_DELETE");
        this.messageHandlerMap.put(Integer.valueOf(256), "MESSAGE_TYPE_PUSH_SWITCH_STATUS");
        this.messageHandlerMap.put(Integer.valueOf(512), "MESSAGE_TYPE_PUSH_REGISTER_STATUS");
        this.messageHandlerMap.put(Integer.valueOf(2048), "MESSAGE_TYPE_PUSH_SUBTAGS_STATUS");
        this.messageHandlerMap.put(Integer.valueOf(1024), "MESSAGE_TYPE_PUSH_UNREGISTER_STATUS");
        this.messageHandlerMap.put(Integer.valueOf(4096), "MESSAGE_TYPE_PUSH_SUBALIAS_STATUS");
        this.messageHandlerMap.put(Integer.valueOf(8192), "MESSAGE_TYPE_SCHEDULE_NOTIFICATION");
        this.messageHandlerMap.put(Integer.valueOf(16384), "MESSAGE_TYPE_RECEIVE_NOTIFY_MESSAGE");
        this.messageHandlerMap.put(Integer.valueOf(32768), "MESSAGE_TYPE_NOTIFICATION_STATE");
        this.messageHandlerMap.put(Integer.valueOf(65536), "MESSAGE_TYPE_UPLOAD_FILE_LOG");
    }

    protected PushNotification onCreateNotification(T t) {
        return null;
    }

    protected void onBeforeEvent(T t) {
    }

    protected void onAfterEvent(T t) {
    }

    protected int scheduleNotificationStatus(T t) {
        return 0;
    }

    protected void scheduleShowNotification(T t) {
    }

    protected boolean canSendMessage(T t) {
        return true;
    }

    protected String getDeviceId(Intent intent) {
        String str = null;
        if (intent != null) {
            str = intent.getStringExtra(PushConstants.MZ_PUSH_MESSAGE_STATISTICS_IMEI_KEY);
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        str = MzSystemUtils.getDeviceId(context());
        a.d(TAG, "force get deviceId " + str);
        return str;
    }

    protected String getTaskId(Intent intent) {
        return intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_TASK_ID);
    }

    protected String getSeqId(Intent intent) {
        return intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_SEQ_ID);
    }

    protected String getPushServiceDefaultPackageName(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_SERVICE_DEFAULT_PACKAGE_NAME);
        if (TextUtils.isEmpty(stringExtra)) {
            return context().getPackageName();
        }
        return stringExtra;
    }

    protected String getPushTimestamp(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_TASK_TIMES_TAMP);
        a.d(TAG, "receive push timestamp from pushservice " + stringExtra);
        if (TextUtils.isEmpty(stringExtra)) {
            return String.valueOf(System.currentTimeMillis() / 1000);
        }
        return stringExtra;
    }

    public boolean sendMessage(Intent intent) {
        boolean z = false;
        boolean z2 = true;
        if (messageMatch(intent)) {
            a.d(TAG, "current message Type " + getMessageHandlerType(getProcessorType()));
            Object message = getMessage(intent);
            a.d(TAG, "current Handler message " + message);
            onBeforeEvent(message);
            switch (scheduleNotificationStatus(message)) {
                case 0:
                    a.d(TAG, "schedule send message off, send message directly");
                    z = true;
                    break;
                case 1:
                    a.d(TAG, "expire notification, dont show message");
                    z2 = false;
                    break;
                case 2:
                    a.d(TAG, "notification on time ,show message");
                    z = true;
                    break;
                case 3:
                    a.d(TAG, "schedule notification");
                    scheduleShowNotification(message);
                    z = true;
                    z2 = false;
                    break;
                default:
                    z2 = false;
                    break;
            }
            boolean canSendMessage = canSendMessage(message);
            a.d(TAG, "can send message " + canSendMessage);
            if (z && r0 && canSendMessage) {
                unsafeSend(message, onCreateNotification(message));
                onAfterEvent(message);
                a.d(TAG, "send message end ");
            }
        }
        return z;
    }

    public AbstractAppLogicListener appLogicListener() {
        return this.abstractAppLogicListener;
    }

    public Context context() {
        return this.context;
    }

    public String getIntentMethod(Intent intent) {
        return intent.getStringExtra(PushConstants.MZ_PUSH_MESSAGE_METHOD);
    }

    public boolean isNotificationJson(String str) {
        try {
            return context().getPackageName().equals(new JSONObject(str).getString("appId"));
        } catch (Exception e) {
            a.d(TAG, "parse notification error");
            return false;
        }
    }

    public String getDeskTopNotificationPkg(String str) {
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("launcher");
            if (jSONObject.has("pkg") && !TextUtils.isEmpty(jSONObject.getString("pkg"))) {
                str2 = jSONObject.getString("pkg");
            }
        } catch (Exception e) {
            a.d(TAG, "parse desk top json error");
        }
        return str2;
    }

    public String selfDefineContentString(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            if (map != null) {
                String str2 = (String) map.get("sk");
                str = TextUtils.isEmpty(str2) ? Util.mapToJSONObject(map).toString() : str2;
            } else {
                str = null;
            }
        }
        a.d(TAG, "self json " + str);
        return str;
    }

    private String getMessageHandlerType(int i) {
        return (String) this.messageHandlerMap.get(Integer.valueOf(i));
    }

    protected boolean canReceiveMessage(int i, String str) {
        boolean z = true;
        if (i == 0) {
            z = PushPreferencesUtils.getNotificationMessageSwitchStatus(context(), str);
        } else if (i == 1) {
            z = PushPreferencesUtils.getThroughMessageSwitchStatus(context(), str);
        }
        a.d(TAG, str + (i == 0 ? " canNotificationMessage " : " canThroughMessage ") + z);
        return z;
    }
}
