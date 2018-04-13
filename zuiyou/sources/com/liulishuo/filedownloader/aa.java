package com.liulishuo.filedownloader;

import android.os.Handler;
import android.util.SparseArray;
import java.util.List;

class aa implements v {
    private final SparseArray<Handler> a = new SparseArray();

    aa() {
    }

    public void a() {
        for (int i = 0; i < this.a.size(); i++) {
            a((Handler) this.a.get(this.a.keyAt(i)));
        }
    }

    public void a(List<Integer> list) {
        for (Integer intValue : list) {
            b((Handler) this.a.get(intValue.intValue()));
        }
    }

    public int b() {
        return this.a.size();
    }

    public boolean a(int i) {
        return this.a.get(i) != null;
    }

    private void a(Handler handler) {
        handler.sendEmptyMessage(2);
    }

    private void b(Handler handler) {
        handler.sendEmptyMessage(3);
    }
}
