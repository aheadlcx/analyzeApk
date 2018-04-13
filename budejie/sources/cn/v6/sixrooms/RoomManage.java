package cn.v6.sixrooms;

import android.app.Activity;
import java.util.Iterator;
import java.util.LinkedList;

public class RoomManage {
    private static RoomManage b;
    private LinkedList<Activity> a = new LinkedList();

    private RoomManage() {
    }

    public static RoomManage getInstance() {
        if (b == null) {
            b = new RoomManage();
        }
        return b;
    }

    public void addActivity(Activity activity) {
        this.a.addFirst(activity);
    }

    public void exit() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        this.a.clear();
    }

    public Activity getLastActivity() {
        if (this.a.size() <= 0) {
            return null;
        }
        return (Activity) this.a.getFirst();
    }
}
