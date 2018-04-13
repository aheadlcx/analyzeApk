package android.support.transition;

import android.support.annotation.RequiresApi;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;
import android.view.View;

@RequiresApi(14)
class bl {
    final ArrayMap<View, TransitionValues> a = new ArrayMap();
    final SparseArray<View> b = new SparseArray();
    final LongSparseArray<View> c = new LongSparseArray();
    final ArrayMap<String, View> d = new ArrayMap();

    bl() {
    }
}
