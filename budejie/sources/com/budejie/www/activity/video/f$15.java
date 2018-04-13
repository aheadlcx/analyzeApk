package com.budejie.www.activity.video;

import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.view.NativeAdDataLoadedListener;

class f$15 implements NativeAdDataLoadedListener {
    final /* synthetic */ AsyncImageView a;
    final /* synthetic */ f b;

    f$15(f fVar, AsyncImageView asyncImageView) {
        this.b = fVar;
        this.a = asyncImageView;
    }

    public void onAdLoaded(NativeAdData nativeAdData) {
        if (nativeAdData != null) {
            if (this.a != null) {
                this.a.setPostImage(nativeAdData.getPic());
            }
            if (f.H(this.b) != null) {
                f.H(this.b).findViewById(R.id.ad_close).setVisibility(0);
            }
        }
    }
}
