package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class ad implements OnClickListener {
    final /* synthetic */ BrowseLongImgFragment a;

    ad(BrowseLongImgFragment browseLongImgFragment) {
        this.a = browseLongImgFragment;
    }

    public void onClick(View view) {
        this.a.mMediaClickListener.onMediaClick();
    }
}
