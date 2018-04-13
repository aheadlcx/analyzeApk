package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class adt implements OnClickListener {
    final /* synthetic */ ads a;

    adt(ads ads) {
        this.a = ads;
    }

    public void onClick(View view) {
        this.a.cancel();
    }
}
