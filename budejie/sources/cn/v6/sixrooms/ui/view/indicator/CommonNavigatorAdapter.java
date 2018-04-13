package cn.v6.sixrooms.ui.view.indicator;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;

public abstract class CommonNavigatorAdapter {
    private final DataSetObservable mDataSetObservable = new DataSetObservable();

    public abstract int getCount();

    public abstract IPagerIndicator getIndicator(Context context);

    public abstract IPagerTitleView getTitleView(Context context, int i);

    public float getTitleWeight(Context context, int i) {
        return 1.0f;
    }

    public final void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.mDataSetObservable.registerObserver(dataSetObserver);
    }

    public final void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.mDataSetObservable.unregisterObserver(dataSetObserver);
    }

    public final void notifyDataSetChanged() {
        this.mDataSetObservable.notifyChanged();
    }

    public final void notifyDataSetInvalidated() {
        this.mDataSetObservable.notifyInvalidated();
    }
}
