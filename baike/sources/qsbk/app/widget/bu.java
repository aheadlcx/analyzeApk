package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class bu implements OnClickListener {
    final /* synthetic */ GroupDialog a;

    bu(GroupDialog groupDialog) {
        this.a = groupDialog;
    }

    public void onClick(View view) {
        if (GroupDialog.b(this.a) != null) {
            GroupDialog.b(this.a).onClick(this.a, -1);
        }
        this.a.dismiss();
    }
}
