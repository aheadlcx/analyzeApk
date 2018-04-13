package com.sina.weibo.sdk.statistic;

import android.content.Context;
import com.sina.weibo.sdk.utils.Utility;
import java.util.TimerTask;

class o extends TimerTask {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ a c;
    final /* synthetic */ k d;

    o(k kVar, Context context, String str, a aVar) {
        this.d = kVar;
        this.a = context;
        this.b = str;
        this.c = aVar;
    }

    public void run() {
        this.c.setmAid(Utility.getAid(this.a, this.b));
        this.d.uploadAdlog(this.a, this.c);
    }
}
