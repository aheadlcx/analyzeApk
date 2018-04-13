package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class k implements OnClickListener {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.a.m.setVisibility(8);
        dialogInterface.dismiss();
        this.a.a.n.setText("");
        this.a.a.o.setText("");
        this.a.a.b.requestFocus();
    }
}
