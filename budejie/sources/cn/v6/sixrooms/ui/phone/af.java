package cn.v6.sixrooms.ui.phone;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;
import cn.v6.sixrooms.engine.GameParamsEngine.CallBack;
import cn.v6.sixrooms.room.game.GameCenterBean;
import cn.v6.sixrooms.ui.phone.EventActivity.a;
import cn.v6.sixrooms.utils.ToastUtils;

final class af implements CallBack {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    af(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    public final void onGetParams(GameCenterBean gameCenterBean, String str, String str2) {
        EventActivity.e(this.b.b).put(this.a, Integer.valueOf(1));
        Object action = gameCenterBean.getAction();
        Intent intent = new Intent();
        if (TextUtils.isEmpty(action)) {
            intent.setComponent(new ComponentName(gameCenterBean.getPackageName(), gameCenterBean.getClassName()));
        } else {
            intent.setAction(action);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra(str, str2);
        }
        this.b.b.startActivity(intent);
    }

    public final void onGetFail(String str) {
        EventActivity.e(this.b.b).put(this.a, Integer.valueOf(1));
        if (!this.b.b.isFinishing()) {
            ToastUtils.showToast(str);
        }
    }
}
