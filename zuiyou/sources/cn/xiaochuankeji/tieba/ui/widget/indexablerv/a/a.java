package cn.xiaochuankeji.tieba.ui.widget.indexablerv.a;

import android.database.Observable;

public class a extends Observable<b> {
    public void a() {
        synchronized (this.mObservers) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((b) this.mObservers.get(size)).a();
            }
        }
    }

    public void a(int i) {
        synchronized (this.mObservers) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((b) this.mObservers.get(size)).a(i);
            }
        }
    }
}
