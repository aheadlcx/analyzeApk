package cn.tatagou.sdk.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.util.c;
import cn.tatagou.sdk.util.p;

class c$1 implements OnClickListener {
    final /* synthetic */ LinearLayout a;
    final /* synthetic */ c b;
    final /* synthetic */ String c;

    c$1(LinearLayout linearLayout, c cVar, String str) {
        this.a = linearLayout;
        this.b = cVar;
        this.c = str;
    }

    public void onClick(View view) {
        this.a.setVisibility(8);
        if (this.b != null && !p.isEmpty(this.c)) {
            Config.getInstance().setSex(this.c);
            this.b.onCheckIdentityResult("L".equals(this.c) ? "LAMA" : "");
        }
    }
}
