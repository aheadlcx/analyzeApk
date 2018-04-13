package cn.xiaochuan.push.xiaomi;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import cn.xiaochuan.push.a;
import cn.xiaochuan.push.e;
import com.alibaba.fastjson.JSON;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import java.util.List;

public class XiaomiMessageReceiver extends PushMessageReceiver {
    private String a() {
        return a.a("mi") ? "mi" : "ma";
    }

    public void a(Context context, MiPushMessage miPushMessage) {
        a.a().a(1, a(), a(miPushMessage));
    }

    public void b(Context context, MiPushMessage miPushMessage) {
        a.a().a(3, a(), a(miPushMessage));
    }

    public void c(Context context, MiPushMessage miPushMessage) {
        a.a().a(2, a(), a(miPushMessage));
    }

    public void a(Context context, MiPushCommandMessage miPushCommandMessage) {
        Log.e("XiaomiMessageReceiver", "onCommandResult is called. " + miPushCommandMessage.toString());
        String command = miPushCommandMessage.getCommand();
        List commandArguments = miPushCommandMessage.getCommandArguments();
        String str = (commandArguments == null || commandArguments.size() <= 0) ? null : (String) commandArguments.get(0);
        if (commandArguments != null && commandArguments.size() > 1) {
            String str2 = (String) commandArguments.get(1);
        }
        if ("register".equals(command)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                a.a().a(a(), str);
            }
        } else if ("set-alias".equals(command)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                a.a().a(a(), MiPushClient.getRegId(context));
            }
        } else if ("set-account".equals(command) && miPushCommandMessage.getResultCode() == 0) {
            a.a().a(a(), MiPushClient.getRegId(context));
        }
    }

    public void b(Context context, MiPushCommandMessage miPushCommandMessage) {
        String command = miPushCommandMessage.getCommand();
        List commandArguments = miPushCommandMessage.getCommandArguments();
        String str = (commandArguments == null || commandArguments.size() <= 0) ? null : (String) commandArguments.get(0);
        if ("register".equals(command) && miPushCommandMessage.getResultCode() == 0) {
            a.a().a(a(), str);
        }
    }

    @Nullable
    private e a(MiPushMessage miPushMessage) {
        try {
            e a = e.a(JSON.parseObject(miPushMessage.getContent()));
            a.a = miPushMessage.getMessageId();
            a.c = miPushMessage.getTitle();
            a.d = miPushMessage.getDescription();
            a.e = miPushMessage.isNotified();
            a.g = miPushMessage.getNotifyId();
            a.l = a();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
