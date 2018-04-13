package com.budejie.www.activity;

import com.budejie.www.activity.CommentShowBigPicture.AnonymousClass8;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.view.NativeAdDataLoadedListener;

class CommentShowBigPicture$8$1 implements NativeAdDataLoadedListener {
    final /* synthetic */ AnonymousClass8 a;

    CommentShowBigPicture$8$1(AnonymousClass8 anonymousClass8) {
        this.a = anonymousClass8;
    }

    public void onAdLoaded(NativeAdData nativeAdData) {
        CommentShowBigPicture.a(this.a.b, nativeAdData);
        CommentShowBigPicture.a(this.a.b);
    }
}
