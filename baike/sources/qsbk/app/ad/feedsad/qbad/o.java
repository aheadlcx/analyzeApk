package qsbk.app.ad.feedsad.qbad;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

class o implements OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ n b;

    o(n nVar, View view) {
        this.b = nVar;
        this.a = view;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.a.onAppClick(this.a);
        dialogInterface.dismiss();
    }
}
