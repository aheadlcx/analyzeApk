package cn.tatagou.sdk.view.dialog;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

public interface i {
    View getEmptyItem(View view, ViewGroup viewGroup);

    View getItem(int i, View view, ViewGroup viewGroup);

    int getItemsCount();

    void registerDataSetObserver(DataSetObserver dataSetObserver);

    void unregisterDataSetObserver(DataSetObserver dataSetObserver);
}
