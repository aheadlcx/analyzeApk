package com.budejie.www.activity.phonenumber;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.budejie.www.R;

class g$2 implements TextWatcher {
    final /* synthetic */ g a;

    g$2(g gVar) {
        this.a = gVar;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(g.e(this.a).getText().toString())) {
            g.b(this.a, false);
            if ("phone_register".equals(g.b(this.a))) {
                g.c(this.a).setBackgroundResource(R.drawable.default_next_button);
                g.c(this.a).setClickable(false);
                return;
            } else if ("third_party".equals(g.b(this.a)) || "MyAccountActivity".equals(g.b(this.a)) || "PostInviteRow".equals(g.b(this.a))) {
                g.c(this.a).setBackgroundResource(R.drawable.default_next_button);
                g.c(this.a).setClickable(false);
                return;
            } else {
                return;
            }
        }
        g.b(this.a, true);
        if ("phone_register".equals(g.b(this.a))) {
            g.c(this.a).setBackgroundResource(R.drawable.next_button);
            g.c(this.a).setClickable(true);
        } else if (!"third_party".equals(g.b(this.a)) && !"MyAccountActivity".equals(g.b(this.a)) && !"PostInviteRow".equals(g.b(this.a))) {
        } else {
            if (g.f(this.a)) {
                g.c(this.a).setBackgroundResource(R.drawable.next_button);
                g.c(this.a).setClickable(true);
                return;
            }
            g.c(this.a).setBackgroundResource(R.drawable.default_next_button);
            g.c(this.a).setClickable(false);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
    }
}
