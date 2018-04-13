package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class ih implements OnClickListener {
    final /* synthetic */ ig a;

    ih(ig igVar) {
        this.a = igVar;
    }

    public void onClick(View view) {
        this.a.cancel();
    }
}
