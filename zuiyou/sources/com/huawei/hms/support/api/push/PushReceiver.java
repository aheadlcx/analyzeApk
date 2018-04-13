package com.huawei.hms.support.api.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executors;

public abstract class PushReceiver extends BroadcastReceiver {

    public interface BOUND_KEY {
        public static final String deviceTokenKey = "deviceToken";
        public static final String pushMsgKey = "pushMsg";
        public static final String pushNotifyId = "pushNotifyId";
        public static final String pushStateKey = "pushState";
        public static final String receiveTypeKey = "receiveType";
    }

    public enum Event {
        NOTIFICATION_OPENED,
        NOTIFICATION_CLICK_BTN
    }

    private class a implements Runnable {
        final /* synthetic */ PushReceiver a;
        private Context b;
        private Bundle c;

        public a(PushReceiver pushReceiver, Context context, Bundle bundle) {
            this.a = pushReceiver;
            this.b = context;
            this.c = bundle;
        }

        public void run() {
            try {
                if (this.c != null) {
                    int i = this.c.getInt(BOUND_KEY.receiveTypeKey);
                    if (i >= 0 && i < c.values().length) {
                        switch (c.values()[i]) {
                            case ReceiveType_Token:
                                this.a.onToken(this.b, this.c.getString(BOUND_KEY.deviceTokenKey), this.c);
                                return;
                            case ReceiveType_Msg:
                                byte[] byteArray = this.c.getByteArray(BOUND_KEY.pushMsgKey);
                                if (byteArray != null) {
                                    this.a.onPushMsg(this.b, byteArray, this.c);
                                    return;
                                }
                                return;
                            case ReceiveType_PushState:
                                this.a.onPushState(this.b, this.c.getBoolean(BOUND_KEY.pushStateKey));
                                return;
                            case ReceiveType_NotifyClick:
                                this.a.onEvent(this.b, Event.NOTIFICATION_OPENED, this.c);
                                return;
                            case ReceiveType_ClickBtn:
                                this.a.onEvent(this.b, Event.NOTIFICATION_CLICK_BTN, this.c);
                                return;
                            default:
                                return;
                        }
                    } else if (com.huawei.hms.support.log.a.a()) {
                        com.huawei.hms.support.log.a.a("PushReceiver", "invalid receiverType:" + i);
                    }
                }
            } catch (Exception e) {
                if (com.huawei.hms.support.log.a.a()) {
                    com.huawei.hms.support.log.a.a("PushReceiver", "call EventThread(ReceiveType cause:" + e.getMessage());
                }
            }
        }
    }

    private static class b implements Runnable {
        Context a;
        String b;

        public b(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        public void run() {
            com.huawei.hms.support.api.push.a.a.a.c cVar = new com.huawei.hms.support.api.push.a.a.a.c(this.a, "push_client_self_info");
            cVar.a("hasRequestToken", false);
            cVar.d("token_info");
            com.huawei.hms.support.api.push.a.a.a.a(this.a, "push_client_self_info", "token_info", this.b);
        }
    }

    enum c {
        ReceiveType_Init,
        ReceiveType_Token,
        ReceiveType_Msg,
        ReceiveType_PushState,
        ReceiveType_NotifyClick,
        ReceiveType_ClickBtn
    }

    public void onEvent(Context context, Event event, Bundle bundle) {
    }

    public void onToken(Context context, String str, Bundle bundle) {
        onToken(context, str);
    }

    public boolean onPushMsg(Context context, byte[] bArr, Bundle bundle) {
        String str = "";
        if (bundle != null) {
            str = bundle.getString(BOUND_KEY.deviceTokenKey);
        }
        onPushMsg(context, bArr, str);
        return true;
    }

    public void onPushMsg(Context context, byte[] bArr, String str) {
    }

    public void onPushState(Context context, boolean z) {
    }

    public void onToken(Context context, String str) {
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("PushReceiver", "enter PushMsgReceiver:onReceive(Intent:" + intent.getAction() + " pkgName:" + context.getPackageName() + ")");
            }
            String action = intent.getAction();
            if ("com.huawei.android.push.intent.REGISTRATION".equals(action) && intent.hasExtra("device_token")) {
                a(context, intent);
            } else if ("com.huawei.android.push.intent.RECEIVE".equals(action) && intent.hasExtra("msg_data")) {
                b(context, intent);
            } else if ("com.huawei.android.push.intent.CLICK".equals(action) && intent.hasExtra("click")) {
                c(context, intent);
            } else if ("com.huawei.android.push.intent.CLICK".equals(action) && intent.hasExtra("clickBtn")) {
                d(context, intent);
            } else if ("com.huawei.intent.action.PUSH_STATE".equals(action)) {
                e(context, intent);
            } else if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.b("PushReceiver", "message can't be recognised:" + intent.toUri(0));
            }
        } catch (Exception e) {
            if (com.huawei.hms.support.log.a.d()) {
                com.huawei.hms.support.log.a.d("PushReceiver", "call onReceive(intent:" + intent + ") cause:" + e.getMessage());
            }
        }
    }

    private void a(Context context, Intent intent) throws UnsupportedEncodingException {
        byte[] byteArrayExtra = intent.getByteArrayExtra("device_token");
        if (byteArrayExtra != null) {
            String str = new String(byteArrayExtra, "UTF-8");
            if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("PushReceiver", "get a deviceToken:" + com.huawei.hms.support.api.push.a.a.b.c.a(str));
            }
            boolean a = new com.huawei.hms.support.api.push.a.a.a.c(context, "push_client_self_info").a("hasRequestToken");
            String a2 = com.huawei.hms.support.api.push.a.a.a.a(context, "push_client_self_info", "token_info");
            if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("PushReceiver", "my oldtoken is :" + com.huawei.hms.support.api.push.a.a.b.c.a(a2));
            }
            if (a || !str.equals(a2)) {
                if (com.huawei.hms.support.log.a.a()) {
                    com.huawei.hms.support.log.a.a("PushReceiver", "push client begin to receive the token");
                }
                Executors.newSingleThreadExecutor().execute(new b(context, str));
                Bundle bundle = new Bundle();
                bundle.putString(BOUND_KEY.deviceTokenKey, str);
                bundle.putByteArray(BOUND_KEY.pushMsgKey, null);
                bundle.putInt(BOUND_KEY.receiveTypeKey, c.ReceiveType_Token.ordinal());
                if (intent.getExtras() != null) {
                    bundle.putAll(intent.getExtras());
                }
                Executors.newSingleThreadExecutor().execute(new a(this, context, bundle));
            } else if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("PushReceiver", "get a deviceToken, but do not requested token, and new token is equals old token");
            }
            if (a && !str.equals(a2) && com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("PushReceiver", "begin to report active state tag");
            }
        } else if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("PushReceiver", "get a deviceToken, but it is null");
        }
    }

    private void b(Context context, Intent intent) throws UnsupportedEncodingException {
        f(context, intent);
        boolean a = new com.huawei.hms.support.api.push.a.a.a.c(context, "push_switch").a("normal_msg_enable");
        if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("PushReceiver", "closePush_Normal:" + a);
        }
        if (!a) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("msg_data");
            byte[] byteArrayExtra2 = intent.getByteArrayExtra("device_token");
            if (byteArrayExtra != null && byteArrayExtra2 != null) {
                if (com.huawei.hms.support.log.a.a()) {
                    com.huawei.hms.support.log.a.a("PushReceiver", "PushReceiver receive a message success");
                }
                String str = new String(byteArrayExtra2, "UTF-8");
                Bundle bundle = new Bundle();
                bundle.putString(BOUND_KEY.deviceTokenKey, str);
                bundle.putByteArray(BOUND_KEY.pushMsgKey, byteArrayExtra);
                bundle.putInt(BOUND_KEY.receiveTypeKey, c.ReceiveType_Msg.ordinal());
                Executors.newSingleThreadExecutor().execute(new a(this, context, bundle));
            } else if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("PushReceiver", "PushReceiver receive a message, but message is empty.");
            }
        } else if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("PushReceiver", "close switch is true, message not dispatch");
        }
    }

    private void c(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("click");
        Bundle bundle = new Bundle();
        bundle.putString(BOUND_KEY.pushMsgKey, stringExtra);
        bundle.putInt(BOUND_KEY.receiveTypeKey, c.ReceiveType_NotifyClick.ordinal());
        Executors.newSingleThreadExecutor().execute(new a(this, context, bundle));
    }

    private void d(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("clickBtn");
        int intExtra = intent.getIntExtra("notifyId", 0);
        Bundle bundle = new Bundle();
        bundle.putString(BOUND_KEY.pushMsgKey, stringExtra);
        bundle.putInt(BOUND_KEY.pushNotifyId, intExtra);
        bundle.putInt(BOUND_KEY.receiveTypeKey, c.ReceiveType_ClickBtn.ordinal());
        Executors.newSingleThreadExecutor().execute(new a(this, context, bundle));
    }

    private void e(Context context, Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("push_state", false);
        Bundle bundle = new Bundle();
        bundle.putBoolean(BOUND_KEY.pushStateKey, booleanExtra);
        bundle.putInt(BOUND_KEY.receiveTypeKey, c.ReceiveType_PushState.ordinal());
        Executors.newSingleThreadExecutor().execute(new a(this, context, bundle));
    }

    private void f(Context context, Intent intent) {
        if (context != null && intent != null) {
            Object stringExtra = intent.getStringExtra("msgIdStr");
            if (!TextUtils.isEmpty(stringExtra) && com.huawei.hms.support.api.push.a.a.a(context)) {
                Intent intent2 = new Intent("com.huawei.android.push.intent.MSG_RESPONSE");
                intent2.putExtra("msgIdStr", stringExtra);
                intent2.setPackage("android");
                intent2.setFlags(32);
                if (com.huawei.hms.support.log.a.a()) {
                    com.huawei.hms.support.log.a.a("PushReceiver", "send msg response broadcast to frameworkPush");
                }
                context.sendBroadcast(intent2);
            }
        }
    }
}
