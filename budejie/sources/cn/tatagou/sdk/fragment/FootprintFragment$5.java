package cn.tatagou.sdk.fragment;

import cn.tatagou.sdk.R;
import cn.tatagou.sdk.util.g;
import cn.tatagou.sdk.util.g.a;
import cn.tatagou.sdk.util.l;

class FootprintFragment$5 extends a {
    final /* synthetic */ FootprintFragment a;

    FootprintFragment$5(FootprintFragment footprintFragment, g gVar) {
        this.a = footprintFragment;
        gVar.getClass();
        super(gVar);
    }

    public void onOK() {
        super.onOK();
        if (FootprintFragment.a(this.a) == null || FootprintFragment.a(this.a).getCount() <= 0) {
            l.showToastCenter(this.a.getActivity(), this.a.getString(R.string.no_footprint));
        } else {
            FootprintFragment.h(this.a);
        }
    }
}
