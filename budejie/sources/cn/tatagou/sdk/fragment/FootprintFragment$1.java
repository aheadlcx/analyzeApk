package cn.tatagou.sdk.fragment;

import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.tatagou.sdk.view.pullview.a;

class FootprintFragment$1 extends a {
    final /* synthetic */ FootprintFragment a;

    FootprintFragment$1(FootprintFragment footprintFragment) {
        this.a = footprintFragment;
    }

    public void onScrollList(AbsListView absListView, int i, int i2, boolean z) {
        int i3;
        super.onScrollList(absListView, i, i2, z);
        FootprintFragment.a(this.a, FootprintFragment.a(this.a) != null ? FootprintFragment.a(this.a).getCount() : FootprintFragment.b(this.a));
        LinearLayout linearLayout = this.a.a;
        if (i > 5) {
            i3 = 0;
        } else {
            i3 = 8;
        }
        linearLayout.setVisibility(i3);
        if (z && i > 5) {
            FootprintFragment.c(this.a).setVisibility(0);
            FootprintFragment.d(this.a).setVisibility(8);
            TextView e = FootprintFragment.e(this.a);
            if (i > FootprintFragment.b(this.a)) {
                i = FootprintFragment.b(this.a);
            }
            e.setText(String.valueOf(i));
            FootprintFragment.f(this.a).setText(String.valueOf(FootprintFragment.b(this.a)));
        }
    }

    public void onStopScroll(boolean z, int i, int i2) {
        super.onStopScroll(z, i, i2);
        if (i > 5) {
            FootprintFragment.d(this.a).setVisibility(0);
            this.a.a.setVisibility(0);
            FootprintFragment.c(this.a).setVisibility(8);
        }
    }
}
