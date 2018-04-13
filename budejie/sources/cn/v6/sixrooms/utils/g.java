package cn.v6.sixrooms.utils;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class g implements a {
    final /* synthetic */ DialogListener a;
    final /* synthetic */ int b;
    final /* synthetic */ DialogUtils c;

    g(DialogUtils dialogUtils, DialogListener dialogListener, int i) {
        this.c = dialogUtils;
        this.a = dialogListener;
        this.b = i;
    }

    public final void a() {
        this.a.positive(this.b);
    }

    public final void b() {
        this.a.negative(this.b);
    }
}
