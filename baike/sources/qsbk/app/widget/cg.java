package qsbk.app.widget;

import android.database.DataSetObserver;

class cg extends DataSetObserver {
    final /* synthetic */ LinearReactiveLayout a;

    cg(LinearReactiveLayout linearReactiveLayout) {
        this.a = linearReactiveLayout;
    }

    public void onInvalidated() {
        super.onInvalidated();
        this.a.requestLayout();
    }

    public void onChanged() {
        super.onChanged();
        this.a.requestLayout();
    }
}
