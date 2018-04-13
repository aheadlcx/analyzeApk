package cn.v6.sixrooms.event;

import android.util.SparseArray;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventManager {
    static volatile EventManager a;
    private final SparseArray<CopyOnWriteArrayList<EventObserver>> b = new SparseArray();

    public static EventManager getDefault() {
        if (a == null) {
            synchronized (EventManager.class) {
                if (a == null) {
                    a = new EventManager();
                }
            }
        }
        return a;
    }

    public synchronized void attach(EventObserver eventObserver, Class cls) {
        int hashCode = cls.hashCode();
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.b.get(hashCode);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            this.b.put(hashCode, copyOnWriteArrayList);
        }
        if (!copyOnWriteArrayList.contains(eventObserver)) {
            copyOnWriteArrayList.add(eventObserver);
        }
        System.out.println("Attached an observer");
    }

    public synchronized void detach(EventObserver eventObserver, Class cls) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.b.get(cls.hashCode());
        if (copyOnWriteArrayList != null) {
            if (copyOnWriteArrayList.contains(eventObserver)) {
                copyOnWriteArrayList.remove(eventObserver);
            }
        }
    }

    public synchronized void nodifyObservers(Object obj, String str) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.b.get(obj.getClass().hashCode());
        if (!(copyOnWriteArrayList == null || copyOnWriteArrayList.size() == 0)) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((EventObserver) it.next()).onEventChange(obj, str);
            }
        }
    }
}
