package com.budejie.www.activity.labelsubscription;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

class c$2 implements TextWatcher {
    final /* synthetic */ c a;

    c$2(c cVar) {
        this.a = cVar;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(c.h(this.a).getText().toString())) {
            c.u(this.a).setVisibility(8);
            return;
        }
        c.u(this.a).setVisibility(0);
        c.b(this.a, c.h(this.a).getText().toString());
        c.a(this.a, c.v(this.a).e(c.k(this.a)));
        if (c.g(this.a) != null) {
            c.j(this.a).setAdapter(c.g(this.a));
            c.g(this.a).a(c.i(this.a), c.k(this.a));
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        if (TextUtils.isEmpty(c.h(this.a).getText().toString())) {
            c.j(this.a).setVisibility(8);
            c.w(this.a).setVisibility(0);
            return;
        }
        c.j(this.a).setVisibility(0);
        c.w(this.a).setVisibility(8);
    }
}
