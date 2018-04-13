package antistatic.spinnerwheel.adapters;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractWheelAdapter implements WheelViewAdapter {
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

    protected void a() {
        if (this.a != null) {
            for (DataSetObserver onInvalidated : this.a) {
                onInvalidated.onInvalidated();
            }
        }
    }
}
