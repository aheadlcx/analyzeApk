package cn.tatagou.sdk.fragment;

import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.tatagou.sdk.view.pullview.a;

class TtgSearchGoodsFragment$3 extends a {
    final /* synthetic */ TtgSearchGoodsFragment a;

    TtgSearchGoodsFragment$3(TtgSearchGoodsFragment ttgSearchGoodsFragment) {
        this.a = ttgSearchGoodsFragment;
    }

    public void onScrollList(AbsListView absListView, int i, int i2, boolean z) {
        int i3;
        super.onScrollList(absListView, i, i2, z);
        TtgSearchGoodsFragment.a(this.a, TtgSearchGoodsFragment.d(this.a) != null ? TtgSearchGoodsFragment.d(this.a).getCount() : TtgSearchGoodsFragment.m(this.a));
        LinearLayout linearLayout = this.a.a;
        if (i > 5) {
            i3 = 0;
        } else {
            i3 = 8;
        }
        linearLayout.setVisibility(i3);
        if (z && i > 5) {
            TtgSearchGoodsFragment.n(this.a).setVisibility(0);
            TtgSearchGoodsFragment.o(this.a).setVisibility(8);
            TextView p = TtgSearchGoodsFragment.p(this.a);
            if (i > TtgSearchGoodsFragment.m(this.a)) {
                i = TtgSearchGoodsFragment.m(this.a);
            }
            p.setText(String.valueOf(i));
            TtgSearchGoodsFragment.q(this.a).setText(String.valueOf(TtgSearchGoodsFragment.m(this.a)));
        }
    }

    public void onStopScroll(boolean z, int i, int i2) {
        super.onStopScroll(z, i, i2);
        if (i > 5) {
            TtgSearchGoodsFragment.o(this.a).setVisibility(0);
            this.a.a.setVisibility(0);
            TtgSearchGoodsFragment.n(this.a).setVisibility(8);
        }
    }
}
