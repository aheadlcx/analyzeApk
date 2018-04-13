package cn.v6.sixrooms.hall;

import cn.v6.sixrooms.room.statistic.StatiscProxy;

final class al implements a {
    final /* synthetic */ HotFragment a;

    al(HotFragment hotFragment) {
        this.a = hotFragment;
    }

    public final void a(int i) {
        if (HotFragment.c(this.a).isShowing()) {
            HotFragment.c(this.a).dismiss();
        }
        HotFragment.b(this.a).setCurrentItem(i, true);
        StatiscProxy.moreClickStatistics(i);
    }
}
