package qsbk.app.im;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

class aj implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ ChatMsg e;
    final /* synthetic */ z f;

    aj(z zVar, String str, String str2, String str3, String str4, ChatMsg chatMsg) {
        this.f = zVar;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = chatMsg;
    }

    public void onClick(View view) {
        if (!TextUtils.isEmpty(this.a)) {
            this.f.a.a(this.a, this.b, this.c, this.d, this.e.time);
        }
    }
}
