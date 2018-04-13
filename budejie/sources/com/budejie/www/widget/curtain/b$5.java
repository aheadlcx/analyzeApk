package com.budejie.www.widget.curtain;

import com.budejie.www.widget.curtain.BarrageStatusManager.BarrageState;
import com.budejie.www.widget.curtain.c.a;

class b$5 implements a {
    final /* synthetic */ b a;

    b$5(b bVar) {
        this.a = bVar;
    }

    public void a(String str, String str2) {
        com.budejie.www.activity.video.barrage.a aVar;
        if (BarrageStatusManager.a(b.e(this.a)) == BarrageState.SINGLE) {
            aVar = new com.budejie.www.activity.video.barrage.a();
            aVar.d = str;
            aVar.b = str2;
            aVar.g = true;
            new b$a(this.a, aVar).execute(new Object[0]);
        } else if (BarrageStatusManager.a(b.e(this.a)) == BarrageState.MULTI) {
            aVar = new com.budejie.www.activity.video.barrage.a();
            aVar.b = str2;
            aVar.c = "0";
            aVar.e = true;
            new b$a(this.a, aVar).execute(new Object[0]);
        }
    }
}
