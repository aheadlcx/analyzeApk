package com.tonicartos.widget.stickygridheaders;

import android.database.DataSetObserver;

final class f extends DataSetObserver {
    final /* synthetic */ b a;

    f(b bVar) {
        this.a = bVar;
    }

    public final void onChanged() {
        this.a.a();
    }

    public final void onInvalidated() {
        this.a.c = false;
    }
}
