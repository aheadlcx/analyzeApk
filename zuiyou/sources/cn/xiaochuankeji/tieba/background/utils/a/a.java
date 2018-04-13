package cn.xiaochuankeji.tieba.background.utils.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import java.util.LinkedList;
import java.util.List;

public class a {
    private static a a;
    private LinkedList<b> b = new LinkedList();

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public b a(Context context) {
        b bVar = new b(b(context));
        this.b.addLast(bVar);
        return bVar;
    }

    public void b() {
        if (!this.b.isEmpty()) {
            b bVar = (b) this.b.removeLast();
            if (bVar.a() == 2 && !this.b.isEmpty()) {
                b bVar2 = (b) this.b.getLast();
                if (bVar2.a() == 1 && bVar2.f() == bVar.f() - 1) {
                    bVar2.b(bVar.e());
                }
            }
        }
    }

    private int b(Context context) {
        List runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        if (runningTasks.isEmpty()) {
            return -1;
        }
        return ((RunningTaskInfo) runningTasks.get(0)).numActivities;
    }
}
