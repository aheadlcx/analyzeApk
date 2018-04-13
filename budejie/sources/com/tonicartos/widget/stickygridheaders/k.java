package com.tonicartos.widget.stickygridheaders;

import android.database.DataSetObserver;

final class k extends DataSetObserver {
    final /* synthetic */ c a;

    k(c cVar) {
        this.a = cVar;
    }

    public final void onChanged() {
        this.a.notifyDataSetChanged();
    }

    public final void onInvalidated() {
        this.a.notifyDataSetInvalidated();
    }
}
