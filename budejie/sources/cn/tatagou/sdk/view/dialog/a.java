package cn.tatagou.sdk.view.dialog;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import java.util.List;

public abstract class a implements i {
    private List<DataSetObserver> a;

    public View getEmptyItem(View view, ViewGroup viewGroup) {
        return null;
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.a == null) {
            this.a = new LinkedList();
        }
        this.a.add(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.a != null) {
            this.a.remove(dataSetObserver);
        }
    }
}
