package cn.tatagou.sdk.fragment;

import cn.tatagou.sdk.util.g;
import cn.tatagou.sdk.util.g.a;
import cn.tatagou.sdk.util.p;

class MineFragment$5 extends a {
    final /* synthetic */ MineFragment a;

    MineFragment$5(MineFragment mineFragment, g gVar) {
        this.a = mineFragment;
        gVar.getClass();
        super(gVar);
    }

    public void onChooseSex(String str) {
        super.onChooseSex(str);
        if (!p.isEmpty(str)) {
            MineFragment.a(this.a, str);
            MineFragment.b(this.a, "L".equals(str) ? "LAMA" : "");
        }
    }
}
