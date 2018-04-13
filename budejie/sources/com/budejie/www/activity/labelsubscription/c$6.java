package com.budejie.www.activity.labelsubscription;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Toast;
import com.budejie.www.util.ao;

class c$6 implements OnKeyListener {
    final /* synthetic */ c a;

    c$6(c cVar) {
        this.a = cVar;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == 66 && keyEvent.getAction() == 0) {
            ao.a(c.c(this.a));
            if (TextUtils.isEmpty(c.h(this.a).getText().toString())) {
                Toast.makeText(c.c(this.a), "请输入要搜索的标签", 0).show();
            } else {
                this.a.c();
                c.a(this.a, c.h(this.a).getText().toString());
            }
        }
        return false;
    }
}
