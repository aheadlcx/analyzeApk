package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.bean.FansBean;
import cn.v6.sixrooms.engine.FansListEngine.CallBack;
import java.util.List;

final class i implements CallBack {
    final /* synthetic */ FansPage a;

    i(FansPage fansPage) {
        this.a = fansPage;
    }

    public final void fansList(List<FansBean> list, List<FansBean> list2, List<FansBean> list3) {
        if (this.a.C == 22) {
            this.a.u.clear();
            this.a.u.addAll(list);
            this.a.v.notifyDataSetChanged();
        } else if (this.a.C == 23) {
            this.a.u.clear();
            this.a.u.addAll(list2);
            this.a.v.notifyDataSetChanged();
        } else if (this.a.C == 24) {
            this.a.u.clear();
            this.a.u.addAll(list3);
            this.a.v.notifyDataSetChanged();
        }
        if (this.a.u.size() == 0) {
            this.a.s.setVisibility(0);
        } else {
            this.a.s.setVisibility(8);
        }
    }

    public final void fansList(List<FansBean> list, List<FansBean> list2) {
        this.a.u.clear();
        if (this.a.C == 21) {
            this.a.u.addAll(list);
        } else if (this.a.C == 25) {
            this.a.u.addAll(list2);
        }
        this.a.v.notifyDataSetChanged();
        if (this.a.u.size() == 0) {
            this.a.s.setVisibility(0);
        } else {
            this.a.s.setVisibility(8);
        }
    }

    public final void error(int i) {
        FansPage.e(this.a);
        this.a.a.showErrorToast(i);
    }

    public final void handleErrorInfo(String str, String str2) {
        FansPage.e(this.a);
        this.a.s.setVisibility(0);
        if (this.a.a != null) {
            this.a.a.handleErrorResult(str, str2, this.a.a);
        }
    }
}
