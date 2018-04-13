package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class ai implements OnClickListener {
    final /* synthetic */ BrowseUltraFragment a;

    ai(BrowseUltraFragment browseUltraFragment) {
        this.a = browseUltraFragment;
    }

    public void onClick(View view) {
        if (this.a.mMediaClickListener != null) {
            this.a.mMediaClickListener.onMediaClick();
        }
    }
}
