package qsbk.app.pay.ui;

import java.util.Collection;
import java.util.List;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.ConfigInfoUtil.UpdateConfigCallback;

class b implements UpdateConfigCallback {
    final /* synthetic */ List a;
    final /* synthetic */ PayActivity b;

    b(PayActivity payActivity, List list) {
        this.b = payActivity;
        this.a = list;
    }

    public void onSuccess() {
        Collection b = this.b.e();
        this.b.c.clear();
        this.b.c();
        if (b.size() > 0) {
            this.b.c.addAll(b);
        } else {
            this.b.c.addAll(this.a);
        }
        this.b.b.setHelpMsg(ConfigInfoUtil.instance().getHelpMsg());
        this.b.b.setHelpUrl(ConfigInfoUtil.instance().getHelpUrl() + "?package=" + this.b.getPackageName());
        this.b.b.notifyDataSetChanged();
    }

    public void onFailed(int i) {
        this.b.c.clear();
        this.b.c.addAll(this.a);
        this.b.b.setHelpMsg(ConfigInfoUtil.instance().getHelpMsg());
        this.b.b.setHelpUrl(ConfigInfoUtil.instance().getHelpUrl() + "?package=" + this.b.getPackageName());
        this.b.b.notifyDataSetChanged();
    }

    public void onFinish() {
        if (this.b.g()) {
            this.b.j = true;
        }
        this.b.n.setVisibility(8);
    }
}
