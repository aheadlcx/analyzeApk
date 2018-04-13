package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.engine.PropListEngine$UserCallBack;
import cn.v6.sixrooms.view.interfaces.ProplistViewable;
import java.util.Iterator;

final class i implements PropListEngine$UserCallBack {
    final /* synthetic */ PropListPresenter a;

    i(PropListPresenter propListPresenter) {
        this.a = propListPresenter;
    }

    public final void result(int i, int i2, int i3, int i4, int i5) {
        PropListPresenter.a(this.a, new int[]{i5, i4});
        PropListPresenter.b(this.a, new int[]{i3, i2, i});
        Iterator it = PropListPresenter.a(this.a).iterator();
        while (it.hasNext()) {
            ((ProplistViewable) it.next()).updatedPermission(PropListPresenter.b(this.a), PropListPresenter.c(this.a));
        }
    }
}
