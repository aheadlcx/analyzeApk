package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class bt implements OnClickListener {
    final /* synthetic */ GroupDialog a;

    bt(GroupDialog groupDialog) {
        this.a = groupDialog;
    }

    public void onClick(View view) {
        if (GroupDialog.a(this.a) != null) {
            GroupDialog.a(this.a).onClick(this.a, -2);
        }
        this.a.cancel();
    }
}
