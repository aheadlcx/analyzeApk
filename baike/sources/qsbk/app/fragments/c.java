package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class c implements OnClickListener {
    final /* synthetic */ AcrossFragment a;

    c(AcrossFragment acrossFragment) {
        this.a = acrossFragment;
    }

    public void onClick(View view) {
        this.a.e = "top_refresh";
        this.a.refresh();
    }
}
