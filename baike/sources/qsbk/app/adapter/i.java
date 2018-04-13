package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.ak.android.engine.nav.NativeAd;
import qsbk.app.ad.feedsad.FeedsAdStat;

class i implements OnClickListener {
    final /* synthetic */ NativeAd a;
    final /* synthetic */ ArticleAdapter b;

    i(ArticleAdapter articleAdapter, NativeAd nativeAd) {
        this.b = articleAdapter;
        this.a = nativeAd;
    }

    public void onClick(View view) {
        this.a.onAdClick(this.b.k, view);
        FeedsAdStat.onClick(view.getContext(), "qh_native");
    }
}
