package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class bv implements OnClickListener {
    final /* synthetic */ GroupLevelHelpDialog a;

    bv(GroupLevelHelpDialog groupLevelHelpDialog) {
        this.a = groupLevelHelpDialog;
    }

    public void onClick(View view) {
        this.a.cancel();
    }
}
