package qsbk.app.live.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class x implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ LiveRecommendAdapter b;

    x(LiveRecommendAdapter liveRecommendAdapter, int i) {
        this.b = liveRecommendAdapter;
        this.a = i;
    }

    public void onClick(View view) {
        if (this.b.f != null) {
            this.b.f.onItemClick(null, view, this.a, 0);
        }
    }
}
