package cn.v6.sixrooms.ui.view.indicator;

import android.database.DataSetObserver;

final class a extends DataSetObserver {
    final /* synthetic */ CommonNavigator a;

    a(CommonNavigator commonNavigator) {
        this.a = commonNavigator;
    }

    public final void onChanged() {
        this.a.f.setTotalCount(this.a.e.getCount());
        this.a.a();
    }

    public final void onInvalidated() {
    }
}
