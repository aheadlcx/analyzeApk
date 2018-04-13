package cn.xiaochuan.push.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.xiaochuan.push.a;
import cn.xiaochuan.push.e;
import com.izuiyou.a.a.b;

public class MessageReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("cn.xiaochuankeji.tieba.internal".equalsIgnoreCase(action)) {
            b.b(intent);
            return;
        }
        e eVar = (e) intent.getParcelableExtra("p_m_extra_data");
        b.a("MessageReceiver", "action:" + action + "   extra :" + eVar);
        if (eVar == null) {
            return;
        }
        if ("cn.xiaochuan.push.action.clicked".equalsIgnoreCase(action)) {
            a.a().a(3, eVar.l, eVar);
        } else if ("cn.xiaochuan.push.action.delete".equalsIgnoreCase(action)) {
            a.a().a(4, eVar.l, eVar);
        }
    }
}
