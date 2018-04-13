package cn.xiaochuankeji.tieba.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

@Deprecated
public class DynamicReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        intent.getAction();
    }
}
