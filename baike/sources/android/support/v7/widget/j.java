package android.support.v7.widget;

import android.database.DataSetObserver;

class j extends DataSetObserver {
    final /* synthetic */ ActivityChooserView a;

    j(ActivityChooserView activityChooserView) {
        this.a = activityChooserView;
    }

    public void onChanged() {
        super.onChanged();
        this.a.a.notifyDataSetChanged();
    }

    public void onInvalidated() {
        super.onInvalidated();
        this.a.a.notifyDataSetInvalidated();
    }
}
