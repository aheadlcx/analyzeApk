package qsbk.app.model;

import java.util.ArrayList;
import java.util.Iterator;

public class LiveRecommend {
    public long interval;
    public long lastUpdateTime;
    public ArrayList<Live> lives;

    public ArrayList<Live> getLives() {
        return this.lives;
    }

    public void updateStatus(long j) {
        if (this.lives != null && this.lives.size() > 0) {
            Iterator it = this.lives.iterator();
            while (it.hasNext()) {
                Live live = (Live) it.next();
                if (live.liveId == j) {
                    live.isFollow = false;
                    a(this.lives);
                    return;
                }
            }
        }
    }

    private void a(ArrayList<Live> arrayList) {
        arrayList.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Live live = (Live) it.next();
            if (live.isFollow) {
                arrayList.add(0, live);
            } else {
                arrayList.add(live);
            }
        }
    }
}
