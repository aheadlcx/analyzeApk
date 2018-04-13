package com.tencent.bugly.beta.tinker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.bugly.beta.tinker.TinkerUtils.ScreenState;
import com.tencent.bugly.beta.tinker.TinkerUtils.ScreenState.a;
import com.tencent.tinker.lib.util.TinkerLog;

class TinkerUtils$ScreenState$1 extends BroadcastReceiver {
    final /* synthetic */ a a;
    final /* synthetic */ ScreenState b;

    TinkerUtils$ScreenState$1(ScreenState screenState, a aVar) {
        this.b = screenState;
        this.a = aVar;
    }

    public void onReceive(Context context, Intent intent) {
        TinkerLog.i("Tinker.TinkerUtils", "ScreenReceiver action [%s] ", new Object[]{intent == null ? "" : intent.getAction()});
        if ("android.intent.action.SCREEN_OFF".equals(intent == null ? "" : intent.getAction())) {
            if (this.a != null) {
                this.a.a();
            }
            context.unregisterReceiver(this);
        }
    }
}
