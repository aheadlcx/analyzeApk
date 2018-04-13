package qsbk.app.ad.feedsad.gdtad;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.VideoImmersionActivity;

class e implements OnClickListener {
    final /* synthetic */ AdView a;
    final /* synthetic */ GdtAdView b;

    e(GdtAdView gdtAdView, AdView adView) {
        this.b = gdtAdView;
        this.a = adView;
    }

    public void onClick(View view) {
        VideoImmersionActivity.launch(this.a.getContext(), this.b.ref, 0);
    }
}
