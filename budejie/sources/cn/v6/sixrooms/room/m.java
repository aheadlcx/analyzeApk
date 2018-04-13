package cn.v6.sixrooms.room;

import cn.v6.sixrooms.bean.CustomExceptionBean;
import cn.v6.sixrooms.engine.CrashErrorInfoEngine;

final class m implements Runnable {
    final /* synthetic */ CrashErrorInfoEngine a;
    final /* synthetic */ CustomExceptionBean b;
    final /* synthetic */ BaseRoomActivity c;

    m(BaseRoomActivity baseRoomActivity, CrashErrorInfoEngine crashErrorInfoEngine, CustomExceptionBean customExceptionBean) {
        this.c = baseRoomActivity;
        this.a = crashErrorInfoEngine;
        this.b = customExceptionBean;
    }

    public final void run() {
        this.a.sendCrashErroInfo(this.b.getE().toString(), this.b.getTag(), this.b.getData());
    }
}
