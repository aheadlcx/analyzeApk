package cn.v6.sixrooms.socket.IM;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

public class IMListenerManager {
    private CopyOnWriteArrayList<IMListener> a = new CopyOnWriteArrayList();

    public boolean add(IMListener iMListener) {
        if (this.a.contains(iMListener)) {
            return false;
        }
        this.a.add(iMListener);
        return true;
    }

    public boolean remove(IMListener iMListener) {
        return this.a.remove(iMListener);
    }

    public void removeAll() {
        this.a.clear();
    }

    public void onActionReceive(int i, long j, String str) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((IMListener) it.next()).onActionReceive(i, j, str);
        }
    }

    public void onContentReceive(int i, long j, String str, JSONObject jSONObject) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((IMListener) it.next()).onContentReceive(i, j, str, jSONObject);
        }
    }
}
