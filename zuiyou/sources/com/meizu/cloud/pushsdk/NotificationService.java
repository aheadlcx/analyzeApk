package com.meizu.cloud.pushsdk;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Process;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.base.reflect.ReflectClass;
import com.meizu.cloud.pushsdk.base.reflect.ReflectResult;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.impl.model.ControlMessage;
import com.meizu.cloud.pushsdk.pushtracer.QuickTracker;
import com.meizu.cloud.pushsdk.util.UxIPUtils;
import java.util.List;

public class NotificationService extends IntentService {
    private static final String TAG = "NotificationService";
    private Object newInstance;

    public NotificationService(String str) {
        super(str);
    }

    public NotificationService() {
        super(TAG);
    }

    public void onDestroy() {
        a.a(TAG, "NotificationService destroy");
        this.newInstance = null;
        super.onDestroy();
    }

    protected void onHandleIntent(Intent intent) {
        Process.setThreadPriority(10);
        if (intent != null) {
            try {
                a.a(TAG, "onHandleIntentaction " + intent.getAction());
                Object stringExtra = intent.getStringExtra("command_type");
                a.b(TAG, "-- command_type -- " + stringExtra);
                if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals("reflect_receiver")) {
                    stringExtra = intent.getStringExtra(PushConstants.MZ_PUSH_CONTROL_MESSAGE);
                    a.a(TAG, "control message is " + stringExtra);
                    if (!TextUtils.isEmpty(stringExtra)) {
                        QuickTracker.init(this, new ControlMessage(stringExtra, null, null).getStatics().getPushExtra());
                    }
                    reflectReceiver(intent);
                }
            } catch (Exception e) {
                a.d(TAG, "onHandleIntent error " + e.getMessage());
            }
        }
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public String getReceiver(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Intent intent = new Intent(str2);
        intent.setPackage(str);
        List queryBroadcastReceivers = getPackageManager().queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
            return null;
        }
        return ((ResolveInfo) queryBroadcastReceivers.get(0)).activityInfo.name;
    }

    public void reflectReceiver(Intent intent) {
        String receiver = getReceiver(getPackageName(), intent.getAction());
        if (TextUtils.isEmpty(receiver)) {
            UxIPUtils.notificationEvent(this, intent, "reflectReceiver sendbroadcast", PushConstants.NOTIFICATIONSERVICE_SEND_MESSAGE_BROADCAST);
            a.a(TAG, " reflectReceiver error: receiver for: " + intent.getAction() + " not found, package: " + getPackageName());
            intent.setPackage(getPackageName());
            sendBroadcast(intent);
            return;
        }
        try {
            UxIPUtils.notificationEvent(this, intent, "reflectReceiver startservice", PushConstants.NOTIFICATIONSERVICE_SEND_MESSAGE);
            intent.setClassName(getPackageName(), receiver);
            ReflectResult newInstance = ReflectClass.forName(receiver).constructor((Class[]) null).newInstance((Object[]) null);
            if (newInstance.ok && newInstance.value != null) {
                a.a(TAG, "Reflect MzPushReceiver " + newInstance.ok);
                ReflectClass.forObject(newInstance.value).method("onReceive", Context.class, Intent.class).invoke(newInstance.value, getApplicationContext(), intent);
            }
        } catch (Exception e) {
            a.a(TAG, "reflect e: " + e);
            UxIPUtils.notificationEvent(this, intent, e.getMessage(), PushConstants.NOTIFICATIONSERVICE_SEND_MESSAGE_ERROR);
        }
    }
}
