package cn.htjyb.b.a;

import java.util.HashSet;
import java.util.Iterator;

public abstract class a<T> {
    private final HashSet<a> onListUpdateListeners = new HashSet();

    public interface a {
        void a();
    }

    public abstract T itemAt(int i);

    public abstract int itemCount();

    public void notifyListUpdate() {
        Iterator it = this.onListUpdateListeners.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a();
        }
    }

    public void registerOnListUpdateListener(a aVar) {
        this.onListUpdateListeners.add(aVar);
    }

    public void unregisterOnListUpdateListener(a aVar) {
        this.onListUpdateListeners.remove(aVar);
    }
}
