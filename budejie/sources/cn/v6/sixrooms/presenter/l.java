package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.bean.RedBean;
import cn.v6.sixrooms.engine.RedInfoEngine.CallBack;
import cn.v6.sixrooms.view.interfaces.RedViewable;
import java.util.Iterator;

final class l implements CallBack {
    final /* synthetic */ RedPresenter a;

    l(RedPresenter redPresenter) {
        this.a = redPresenter;
    }

    public final void result(RedBean redBean) {
        if (redBean != null) {
            this.a.h = redBean;
            Iterator it = this.a.b.iterator();
            while (it.hasNext()) {
                ((RedViewable) it.next()).updateRed(redBean);
            }
        }
    }

    public final void error(int i) {
    }

    public final void handerError(String str, String str2) {
        Iterator it = this.a.b.iterator();
        while (it.hasNext()) {
            ((RedViewable) it.next()).handerError(str, str2);
        }
    }
}
