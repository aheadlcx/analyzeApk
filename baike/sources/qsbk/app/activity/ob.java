package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.GroupRankActivity.RemindDialog;

class ob implements OnClickListener {
    final /* synthetic */ RemindDialog a;

    ob(RemindDialog remindDialog) {
        this.a = remindDialog;
    }

    public void onClick(View view) {
        this.a.cancel();
    }
}
