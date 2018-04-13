package cn.v6.sixrooms.room;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.room.verify.VerifyEngine;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class r implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ q d;

    r(q qVar, String str, String str2, String str3) {
        this.d = qVar;
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final void run() {
        if (BaseRoomActivity.d(this.d.a.a) == null) {
            BaseRoomActivity.a(this.d.a.a, new VerifyEngine(new s(this)));
        }
        BaseRoomActivity.d(this.d.a.a).postCaptcha(this.a, this.b, this.c, this.d.a.a.eid, LoginUtils.getLoginUID(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
    }
}
