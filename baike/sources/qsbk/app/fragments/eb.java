package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class eb implements OnClickListener {
    final /* synthetic */ LaiseeVoiceGetFragment a;

    eb(LaiseeVoiceGetFragment laiseeVoiceGetFragment) {
        this.a = laiseeVoiceGetFragment;
    }

    public void onClick(View view) {
        this.a.b.setEnabled(false);
    }
}
