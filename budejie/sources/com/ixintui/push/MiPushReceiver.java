package com.ixintui.push;

import android.content.Context;
import com.ixintui.plugin.IPushReceiver;
import com.ixintui.pushsdk.SdkConstants;
import com.ixintui.smartlink.a.a;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

public class MiPushReceiver extends PushMessageReceiver {
    private IPushReceiver a;

    private void a(Context context) {
        if (this.a == null) {
            this.a = a.c(context);
        }
    }

    public void onReceivePassThroughMessage(Context context, Object obj) {
        a.b(SdkConstants.TAG, "onReceivePassThroughMessage is called. " + obj.toString());
        a(context);
        if (this.a != null) {
            a.a(this.a, "onReceivePassThroughMessage", new Class[]{Context.class, Object.class}, new Object[]{context, obj});
        }
    }

    public void onNotificationMessageClicked(Context context, Object obj) {
        a.b(SdkConstants.TAG, "onNotificationMessageClicked is called. " + obj.toString());
        a(context);
        if (this.a != null) {
            a.a(this.a, "onNotificationMessageClicked", new Class[]{Context.class, Object.class}, new Object[]{context, obj});
        }
    }

    public void onNotificationMessageArrived(Context context, Object obj) {
        a.b(SdkConstants.TAG, context.getPackageName() + ":onNotificationMessageArrived is called. " + obj.toString());
    }

    public void onCommandResult(Context context, Object obj) {
        a.b(SdkConstants.TAG, context.getPackageName() + ":onCommandResult is called. " + obj.toString());
        a(context);
        if (this.a != null) {
            a.a(this.a, "onCommandResult", new Class[]{Context.class, Object.class}, new Object[]{context, obj});
        }
    }

    public void onReceiveRegisterResult(Context context, Object obj) {
        a.b(SdkConstants.TAG, "onReceiveRegisterResult is called. " + obj.toString());
    }
}
