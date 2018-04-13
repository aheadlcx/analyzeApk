package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class b implements OnClickListener {
    final /* synthetic */ AcrossFragment a;

    b(AcrossFragment acrossFragment) {
        this.a = acrossFragment;
    }

    public void onClick(View view) {
        if (this.a.m != null) {
            this.a.e = "top_refresh";
            this.a.refresh();
        }
    }
}
