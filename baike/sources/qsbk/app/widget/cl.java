package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.widget.LiveRecommendCell.LivePagerAdapter;

class cl implements OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ LivePagerAdapter b;

    cl(LivePagerAdapter livePagerAdapter, View view) {
        this.b = livePagerAdapter;
        this.a = view;
    }

    public void onClick(View view) {
        this.b.a.onClick();
        this.a.setEnabled(false);
        this.a.postDelayed(new cm(this), 1000);
    }
}
