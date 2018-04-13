package cn.v6.sixrooms.hall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import cn.v6.sixrooms.engine.AppUpdateEngine;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;

final class aw extends BroadcastReceiver {
    final /* synthetic */ MineFragment a;

    aw(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public final void onReceive(Context context, Intent intent) {
        LogUtils.e("LoginReceive", MineFragment.class.getName());
        MineFragment.a(this.a, LoginUtils.getLoginUID());
        Object stringExtra = intent.getStringExtra("op");
        AppUpdateEngine appUpdateEngine = new AppUpdateEngine(null);
        if (!TextUtils.isEmpty(stringExtra)) {
            appUpdateEngine.update(stringExtra, "3");
        }
        MineFragment.e(this.a).postDelayed(new ax(this), 50);
    }
}
