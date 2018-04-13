package com.budejie.www.activity.video;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.http.i;
import com.budejie.www.util.ao;
import com.umeng.analytics.MobclickAgent;

class f$7 implements OnClickListener {
    final /* synthetic */ f a;

    f$7(f fVar) {
        this.a = fVar;
    }

    @SuppressLint({"NewApi"})
    public void onClick(View view) {
        if (k.a(null).f.getParentView().getKeyBoardState()) {
            ao.a(f.e(this.a));
        } else if (!k.a(null).f.c() && !k.a(null).f.d()) {
            f.b(this.a, !f.p(this.a));
            if (VERSION.SDK_INT >= 9) {
                f.q(this.a).edit().putBoolean("barrage_status", f.p(this.a)).apply();
            } else {
                f.q(this.a).edit().putBoolean("barrage_status", f.p(this.a)).commit();
            }
            this.a.setBarrageStatus(Boolean.valueOf(f.p(this.a)));
            if (view != null) {
                StringBuilder stringBuilder = new StringBuilder();
                if (f.p(this.a)) {
                    stringBuilder.append("开|开启弹幕");
                    MobclickAgent.onEvent(f.b(this.a), "E06_A13", "开启单行");
                } else {
                    MobclickAgent.onEvent(f.b(this.a), "E06_A13", "关闭单行");
                    stringBuilder.append("关|关闭弹幕");
                }
                stringBuilder.append("|控制栏按钮");
                i.a(f.b(this.a).getString(R.string.track_event_open_or_close_barrage), stringBuilder.toString());
            }
        }
    }
}
