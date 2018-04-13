package cn.v6.sixrooms.widgets.phone.indicator;

import android.view.View;
import android.view.View.OnClickListener;

final class b implements OnClickListener {
    final /* synthetic */ TabPageIndicator a;

    b(TabPageIndicator tabPageIndicator) {
        this.a = tabPageIndicator;
    }

    public final void onClick(View view) {
        a aVar = (a) view;
        int currentItem = this.a.g.getCurrentItem();
        int a = aVar.a();
        if (((Integer) this.a.c.get(Integer.valueOf(currentItem))).intValue() != a) {
            for (int i = a; i < this.a.g.getAdapter().getCount(); i++) {
                if (((Integer) this.a.c.get(Integer.valueOf(i))).intValue() == a) {
                    this.a.k = i;
                    break;
                }
            }
            this.a.g.setCurrentItem(this.a.k);
            if (this.a.l != null) {
                this.a.l.onTabReselected(a);
            }
        }
    }
}
