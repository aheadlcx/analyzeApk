package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ AcrossFragment a;

    d(AcrossFragment acrossFragment) {
        this.a = acrossFragment;
    }

    public void onClick(View view) {
        this.a.e = "top_refresh";
        this.a.refresh();
    }
}
