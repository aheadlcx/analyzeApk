package cn.v6.sixrooms.utils;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class e implements a {
    final /* synthetic */ DialogListener a;
    final /* synthetic */ int b;
    final /* synthetic */ DialogUtils c;

    e(DialogUtils dialogUtils, DialogListener dialogListener, int i) {
        this.c = dialogUtils;
        this.a = dialogListener;
        this.b = i;
    }

    public final void a() {
        if (this.a != null) {
            this.a.positive(this.b);
        }
    }

    public final void b() {
    }
}
