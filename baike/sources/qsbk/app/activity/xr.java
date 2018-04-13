package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.NewFan;
import qsbk.app.widget.BlackReportDialog;

class xr implements OnClickListener {
    final /* synthetic */ NewFansActivity a;

    xr(NewFansActivity newFansActivity) {
        this.a = newFansActivity;
    }

    public void onClick(View view) {
        BlackReportDialog blackReportDialog = new BlackReportDialog(this.a, ((NewFan) NewFansActivity.c(this.a).get(NewFansActivity.b(this.a))).userId);
        blackReportDialog.registerListener(new xs(this));
        blackReportDialog.createDialog();
        blackReportDialog.show();
    }
}
