package cn.xiaochuankeji.tieba.ui.widget.indicator;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;

public abstract class c {
    private final DataSetObservable a = new DataSetObservable();

    public abstract int a();

    public abstract h a(Context context);

    public abstract j a(Context context, int i);

    public float a(int i) {
        return 1.0f;
    }

    public final void a(DataSetObserver dataSetObserver) {
        this.a.registerObserver(dataSetObserver);
    }

    public final void b(DataSetObserver dataSetObserver) {
        this.a.unregisterObserver(dataSetObserver);
    }

    public final void b() {
        this.a.notifyChanged();
    }
}
