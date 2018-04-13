package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class abj implements OnClickListener {
    final /* synthetic */ abi a;

    abj(abi abi) {
        this.a = abi;
    }

    public void onClick(View view) {
        this.a.cancel();
    }
}
