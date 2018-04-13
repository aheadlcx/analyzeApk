package cn.v6.sixrooms.widgets.phone;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.GuardPropDetailBean;
import cn.v6.sixrooms.utils.LogUtils;

final class ac implements OnItemClickListener {
    final /* synthetic */ OpenGuardPage a;

    ac(OpenGuardPage openGuardPage) {
        this.a = openGuardPage;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int i2 = this.a.v[0];
        int i3 = this.a.v[1];
        LogUtils.i("OpenGuardPage", "isSilver: " + i3 + "  isGold: " + i2);
        GuardPropDetailBean guardPropDetailBean = (GuardPropDetailBean) adapterView.getItemAtPosition(i);
        if (i3 == 1 || i2 == 1) {
            if (this.a.s != null) {
                this.a.s.dismiss();
                this.a.s = null;
            }
            this.a.s = this.a.r.createConfirmDialog(0, this.a.a.getString(R.string.tip_show_tip_title), "您已是" + this.a.t.getAlias() + "的守护，需要继续购买吗？", this.a.a.getString(R.string.cancel), this.a.a.getString(R.string.confirm), new ad(this, guardPropDetailBean));
            this.a.s.show();
            return;
        }
        OpenGuardPage.a(this.a, guardPropDetailBean);
    }
}
