package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.bean.PropBean;
import cn.v6.sixrooms.engine.PropListEngine.CallBack;
import java.util.List;

final class bk implements CallBack {
    final /* synthetic */ MyPropActivity a;

    bk(MyPropActivity myPropActivity) {
        this.a = myPropActivity;
    }

    public final void result(List<PropBean> list) {
        this.a.a(8, "");
        if (list.size() == 0) {
            this.a.d.setVisibility(0);
        } else {
            this.a.d.setVisibility(8);
        }
        this.a.i.setDataChanged(list);
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.a(8, "");
        this.a.handleErrorResult(str, str2, this.a);
    }

    public final void error(int i) {
        this.a.a(8, "");
        this.a.showErrorToast(i);
    }
}
