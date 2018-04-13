package antistatic.spinnerwheel;

import android.database.DataSetObserver;

class a extends DataSetObserver {
    final /* synthetic */ AbstractWheel a;

    a(AbstractWheel abstractWheel) {
        this.a = abstractWheel;
    }

    public void onChanged() {
        this.a.invalidateItemsLayout(false);
    }

    public void onInvalidated() {
        this.a.invalidateItemsLayout(true);
    }
}
