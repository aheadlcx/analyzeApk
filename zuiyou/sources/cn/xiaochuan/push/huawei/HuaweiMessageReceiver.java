package cn.xiaochuan.push.huawei;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.xiaochuan.push.a;
import cn.xiaochuan.push.e;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.huawei.hms.support.api.push.PushReceiver;
import com.huawei.hms.support.api.push.PushReceiver.BOUND_KEY;
import com.huawei.hms.support.api.push.PushReceiver.Event;
import com.izuiyou.a.a.b;

public class HuaweiMessageReceiver extends PushReceiver {
    public void onEvent(Context context, Event event, Bundle bundle) {
        super.onEvent(context, event, bundle);
        if (Event.NOTIFICATION_OPENED.equals(event) || Event.NOTIFICATION_CLICK_BTN.equals(event)) {
            try {
                int i = bundle.getInt(BOUND_KEY.pushNotifyId, -1);
                if (-1 != i) {
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                    if (notificationManager != null) {
                        notificationManager.cancel(i);
                    }
                }
                String string = bundle.getString(BOUND_KEY.pushMsgKey);
                b.c(string);
                JSONArray parseArray = JSON.parseArray(string);
                if (parseArray.size() > 0) {
                    a.a().a(3, "hw", e.a(JSON.parseObject(parseArray.getJSONObject(0).getString("payload"))));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onToken(Context context, String str, Bundle bundle) {
        super.onToken(context, str, bundle);
        a.a().a("hw", str);
    }

    public void onPushMsg(Context context, byte[] bArr, String str) {
        try {
            String string = JSON.parseObject(new String(bArr, "UTF-8")).getString("content");
            if (!(TextUtils.isEmpty(string) || e.a(JSON.parseObject(string)) == null)) {
                a.a().a(1, "hw", e.a(JSON.parseObject(string)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onPushMsg(context, bArr, str);
    }

    public void onPushState(Context context, boolean z) {
        super.onPushState(context, z);
    }

    public void onToken(Context context, String str) {
        super.onToken(context, str);
    }
}
