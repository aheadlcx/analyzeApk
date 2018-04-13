package cn.tatagou.sdk.fragment;

import cn.tatagou.sdk.util.g;
import cn.tatagou.sdk.util.g.a;

class MineFragment$4 extends a {
    final /* synthetic */ MineFragment a;

    MineFragment$4(MineFragment mineFragment, g gVar) {
        this.a = mineFragment;
        gVar.getClass();
        super(gVar);
    }

    public void onOK() {
        super.onOK();
        cn.tatagou.sdk.util.a.logout(this.a.getActivity(), MineFragment.e(this.a));
    }
}
