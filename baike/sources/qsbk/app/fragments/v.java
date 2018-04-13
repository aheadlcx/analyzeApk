package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class v implements OnClickListener {
    final /* synthetic */ BrowseGIFVideoFragment a;

    v(BrowseGIFVideoFragment browseGIFVideoFragment) {
        this.a = browseGIFVideoFragment;
    }

    public void onClick(View view) {
        if (this.a.mMediaClickListener != null) {
            this.a.mMediaClickListener.onMediaClick();
        }
    }
}
