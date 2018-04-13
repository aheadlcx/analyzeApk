package qsbk.app.share;

import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;

final class n implements OnClickListener {
    final /* synthetic */ AlertDialog a;

    n(AlertDialog alertDialog) {
        this.a = alertDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
