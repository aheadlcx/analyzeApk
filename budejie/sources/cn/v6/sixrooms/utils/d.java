package cn.v6.sixrooms.utils;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class d implements a {
    final /* synthetic */ DialogListener a;
    final /* synthetic */ DialogUtils b;

    d(DialogUtils dialogUtils, DialogListener dialogListener) {
        this.b = dialogUtils;
        this.a = dialogListener;
    }

    public final void a() {
        if (this.a != null) {
            this.a.positive(0);
        }
    }

    public final void b() {
        if (this.a != null) {
            this.a.negative(0);
        }
    }
}
