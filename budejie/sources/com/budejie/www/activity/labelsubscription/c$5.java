package com.budejie.www.activity.labelsubscription;

import com.budejie.www.widget.KeyboardListenerRelativeLayout.a;

class c$5 implements a {
    final /* synthetic */ c a;

    c$5(c cVar) {
        this.a = cVar;
    }

    public void a(boolean z) {
        if (!z && c.f(this.a) != null && c.f(this.a).isShown() && (c.g(this.a) == null || c.g(this.a).getCount() == 0)) {
            this.a.d();
        }
        if (!z) {
            c.h(this.a).clearFocus();
        }
    }
}
