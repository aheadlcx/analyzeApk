package android.support.v7.widget;

import android.database.DataSetObserver;

class n extends DataSetObserver {
    final /* synthetic */ ActivityChooserView a;

    n(ActivityChooserView activityChooserView) {
        this.a = activityChooserView;
    }

    public void onChanged() {
        super.onChanged();
        this.a.a();
    }
}
