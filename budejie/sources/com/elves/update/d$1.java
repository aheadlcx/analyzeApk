package com.elves.update;

import android.os.Handler;
import android.os.Message;
import com.budejie.www.R;

class d$1 extends Handler {
    final /* synthetic */ d a;

    d$1(d dVar) {
        this.a = dVar;
    }

    public void handleMessage(Message message) {
        boolean z;
        int i = message.what;
        if (message.arg1 == 1) {
            z = true;
        } else {
            z = false;
        }
        String str;
        String str2;
        if (i == 999999) {
            str = (String) message.obj;
            str2 = str.split("&")[0];
            this.a.a.a(Integer.parseInt(str.split("&")[1]), R.drawable.down_success, str2, false, "");
        } else if (i == 888888) {
            str = (String) message.obj;
            str2 = str.split("&")[0];
            this.a.a.a(Integer.parseInt(str.split("&")[1]), R.drawable.down_fail, str2, str.split("&")[2], this.a.d);
        } else if (i == 777777) {
            str = (String) message.obj;
            int parseInt = Integer.parseInt(str.split("&")[1]);
            int parseInt2 = Integer.parseInt(str.split("&")[0]);
            String str3 = str.split("&")[2];
            this.a.a.a(Integer.parseInt(str.split("&")[3]), parseInt, parseInt2, this.a.c, z);
        } else if (i == 666666) {
            str = (String) message.obj;
            str2 = str.split("&")[0];
            int parseInt3 = Integer.parseInt(str.split("&")[1]);
            this.a.a.a(parseInt3, R.drawable.down_success, str2, true, this.a.c);
            d.a(this.a, str2);
        }
    }
}
