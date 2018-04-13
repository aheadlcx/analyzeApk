package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.engine.PropListEngine;
import cn.v6.sixrooms.view.interfaces.ProplistViewable;
import cn.v6.sixrooms.view.interfaces.RedViewable;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class PropListPresenter {
    private static PropListPresenter a;
    private static final String d = PropListPresenter.class.getSimpleName();
    private CopyOnWriteArrayList<ProplistViewable> b = new CopyOnWriteArrayList();
    private PropListEngine c;
    private int[] e = null;
    private int[] f = null;

    public static PropListPresenter getInstance() {
        if (a == null) {
            synchronized (PropListPresenter.class) {
                if (a == null) {
                    a = new PropListPresenter();
                }
            }
        }
        return a;
    }

    public int[] getGuardLocalData() {
        return this.e;
    }

    public int[] getVipLocalData() {
        return this.f;
    }

    public void clearLocalData() {
        this.e = null;
        this.f = null;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((ProplistViewable) it.next()).updatedPermission(this.e, this.f);
        }
    }

    public void getNetData(String str, String str2, String str3) {
        if (this.c == null) {
            this.c = new PropListEngine();
            this.c.setUserCallBack(new i(this));
        }
        this.c.getUserPermission(str, str2, str3);
    }

    public void register(ProplistViewable proplistViewable) {
        if (!this.b.contains(proplistViewable)) {
            this.b.add(proplistViewable);
        }
    }

    public void unregister(RedViewable redViewable) {
        this.b.remove(redViewable);
    }

    public void onDestroy() {
        a = null;
    }
}
