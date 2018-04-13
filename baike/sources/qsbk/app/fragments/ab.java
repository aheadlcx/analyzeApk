package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class ab implements OnClickListener {
    final /* synthetic */ BrowseImgFragment a;

    ab(BrowseImgFragment browseImgFragment) {
        this.a = browseImgFragment;
    }

    public void onClick(View view) {
        if (this.a.mMediaClickListener != null) {
            this.a.mMediaClickListener.onMediaClick();
        }
    }
}
