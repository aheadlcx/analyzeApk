package cn.v6.sixrooms.surfaceanim.flybanner.utils.touch;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class SurfaceTouchManager {
    static volatile SurfaceTouchManager a;
    private CopyOnWriteArrayList<SurfaceTouchEvent> b = new CopyOnWriteArrayList();

    public static SurfaceTouchManager getDefault() {
        if (a == null) {
            synchronized (SurfaceTouchManager.class) {
                if (a == null) {
                    a = new SurfaceTouchManager();
                }
            }
        }
        return a;
    }

    public synchronized void attach(SurfaceTouchEvent surfaceTouchEvent) {
        if (!this.b.contains(surfaceTouchEvent)) {
            this.b.add(surfaceTouchEvent);
        }
    }

    public synchronized void detach(SurfaceTouchEvent surfaceTouchEvent) {
        if (this.b.contains(surfaceTouchEvent)) {
            this.b.remove(surfaceTouchEvent);
        }
    }

    public synchronized void addTouchEntity(TouchEntity touchEntity) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((SurfaceTouchEvent) it.next()).addTouchEntity(touchEntity);
        }
    }

    public synchronized void removeTouchEntity(TouchEntity touchEntity) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((SurfaceTouchEvent) it.next()).removeTouchEntity(touchEntity);
        }
    }

    public synchronized void clear() {
        if (this.b != null) {
            this.b.clear();
        }
    }
}
