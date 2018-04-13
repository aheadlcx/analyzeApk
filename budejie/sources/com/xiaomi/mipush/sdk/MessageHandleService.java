package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import com.ixintui.pushsdk.SdkConstants;
import com.xiaomi.a.a;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageHandleService extends IntentService {
    private static ConcurrentLinkedQueue e = new ConcurrentLinkedQueue();
    CmdMsg a;
    Msg b;
    Class c;
    Object d;

    public MessageHandleService() {
        super("MessageHandleThread");
    }

    public void onCreate() {
        super.onCreate();
        try {
            this.c = a.c(this).loadClass("com.xiaomi.mipush.sdk.MessageHandleService");
            this.d = this.c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    protected void onHandleIntent(Intent intent) {
        try {
            a(intent);
        } catch (Exception e) {
            com.ixintui.smartlink.a.a.a(e);
        } catch (Exception e2) {
            com.ixintui.smartlink.a.a.a(e2);
        } catch (Exception e22) {
            com.ixintui.smartlink.a.a.a(e22);
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void a(Intent intent) {
        if (intent != null) {
            a aVar = (a) e.poll();
            if (aVar != null) {
                try {
                    PushMessageReceiver a = aVar.a();
                    Intent b = aVar.b();
                    switch (b.getIntExtra("message_type", 1)) {
                        case 1:
                            Object a2 = com.ixintui.smartlink.a.a.a(getApplicationContext(), b);
                            if (a2 != null) {
                                this.a = CmdMsg.getInstance(getApplicationContext(), a2);
                                this.b = Msg.getInstance(getApplicationContext(), a2);
                                if (a2.getClass() == this.b.getRemote()) {
                                    if (!this.b.isArrivedMessage()) {
                                        a.onReceiveMessage(getApplicationContext(), a2);
                                    }
                                    if (this.b.getPassThrough() == 1) {
                                        a.onReceivePassThroughMessage(getApplicationContext(), a2);
                                        return;
                                    } else if (this.b.isNotified()) {
                                        a.onNotificationMessageClicked(getApplicationContext(), a2);
                                        return;
                                    } else {
                                        a.onNotificationMessageArrived(getApplicationContext(), a2);
                                        return;
                                    }
                                } else if (a2.getClass() == this.a.getRemote()) {
                                    a.onCommandResult(getApplicationContext(), a2);
                                    if (TextUtils.equals(this.a.getCommand(), SdkConstants.REGISTER)) {
                                        a.onReceiveRegisterResult(getApplicationContext(), a2);
                                        return;
                                    }
                                    return;
                                } else {
                                    return;
                                }
                            }
                            return;
                        case 3:
                            MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) b.getSerializableExtra("key_command");
                            a.onCommandResult(getApplicationContext(), miPushCommandMessage);
                            if (TextUtils.equals(miPushCommandMessage.getCommand(), SdkConstants.REGISTER)) {
                                a.onReceiveRegisterResult(getApplicationContext(), miPushCommandMessage);
                                return;
                            }
                            return;
                        case 4:
                            return;
                        default:
                            return;
                    }
                } catch (Exception e) {
                    com.ixintui.smartlink.a.a.a(e);
                } catch (Exception e2) {
                    com.ixintui.smartlink.a.a.a(e2);
                }
            }
        }
    }

    public static void a(a aVar) {
        if (aVar != null) {
            e.add(aVar);
        }
    }
}
